package br.com.proposta.PropostaOrange.carteira;


import br.com.proposta.PropostaOrange.bloqueio.*;
import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoRepository;
import br.com.proposta.PropostaOrange.cartao.StatusCartao;
import br.com.proposta.PropostaOrange.proposta.PropostaDTOResponse;
import br.com.proposta.PropostaOrange.utils.IPAddress;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CarteiraSistemaLegado carteiraSistemaLegado;


    private final MeterRegistry meterRegistry;
    private Counter countCarteiraSucesso;
    private Counter countCarteiraFalha;



    public CarteiraController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void initMetrics() {
        Collection<Tag> tags = new ArrayList<>();
        this.countCarteiraSucesso = this.meterRegistry.counter("carteira_sucesso", tags);
        this.countCarteiraFalha = this.meterRegistry.counter("carteira_falha", tags);
    }



    @PostMapping(value = "/api/cartoes/{id}/carteiras")
    @Transactional
    @CacheEvict(value = "listaDeCarteiras", allEntries = true)
    public ResponseEntity cadastrar(@PathVariable("id") String numCartao,
                                    @Valid @RequestBody CarteiraDTORequest carteiraDTORequest,
                                    UriComponentsBuilder uriBuilder){

        Cartao cartaoEncontrado = cartaoRepository.findByNumCartao(numCartao);
        if( cartaoEncontrado == null){
            this.countCarteiraFalha.increment();
            return ResponseEntity.status(404).body(new ErroAPI("Cartão","O cartão não foi encontrado na base de dados."));
        }

        if(cartaoEncontrado.jaAssociado(cartaoRepository)){
            this.countCarteiraFalha.increment();
            return ResponseEntity.status(422).body(new ErroAPI("Cartão","O cartão já foi associado a uma carteira."));
        }

        CarteiraResultadoDTOResponse carteiraResultadoDTOResponse = carteiraSistemaLegado.postCarteira(numCartao,carteiraDTORequest);
        if(carteiraResultadoDTOResponse.getResultado().equals(StatusCarteira.ASSOCIADA)) {
            Carteira carteira = carteiraDTORequest.converter(cartaoEncontrado,carteiraResultadoDTOResponse);
            carteiraRepository.save(carteira);
            this.countCarteiraSucesso.increment();
            URI uri = uriBuilder.path("/api/cartoes/{idCartao}/carteiras/{idCarteira}").buildAndExpand(cartaoEncontrado.getId(),carteira.getId()).toUri();
            return ResponseEntity.status(201).body(uri);
        }

        this.countCarteiraFalha.increment();
        return ResponseEntity.status(422).body(new ErroAPI("Cartão","Falha ao criar a carteira"));
    }
}
