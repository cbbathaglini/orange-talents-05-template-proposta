package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoRepository;
import br.com.proposta.PropostaOrange.cartao.StatusCartao;
import br.com.proposta.PropostaOrange.proposta.*;
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
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private ConsultaBloqueio consultaBloqueio;


    private final MeterRegistry meterRegistry;
    private Counter countBloqueiosSucesso;
    private Counter countBloqueiosFalha;



    public BloqueioController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void initMetrics() {
        Collection<Tag> tags = new ArrayList<>();
        this.countBloqueiosSucesso = this.meterRegistry.counter("bloqueios_sucesso", tags);
        this.countBloqueiosFalha = this.meterRegistry.counter("bloqueios_falha", tags);
    }



    @PostMapping(value = "/cartoes/{id}/bloqueios")
    @Transactional
    @CacheEvict(value = "listaDeBloqueios", allEntries = true)
    public ResponseEntity cadastrar(@PathVariable("id") String numCartao,
                                    HttpServletRequest request){
                                   // @RequestBody @Valid BloqueioDTORequest bloqueioDTORequest){


        Cartao cartaoEncontrado = cartaoRepository.findByNumCartao(numCartao);
        if( cartaoEncontrado == null){
            this.countBloqueiosFalha.increment();
            return ResponseEntity.status(404).body(new ErroAPI("Cartão","O cartão não foi encontrado na base de dados."));
        }

        if(cartaoEncontrado.jaBloqueado(cartaoRepository)){
            this.countBloqueiosFalha.increment();
            return ResponseEntity.status(422).body(new ErroAPI("Cartão","O cartão já tinha sido bloqueado."));
        }

        BloqueioDTORequest bloqueioDTORequest = new BloqueioDTORequest("sistema-propostas");
        BloqueioStatusDTOResponse bloqueioStatusDTOResponse = consultaBloqueio.consultarBloqueio(numCartao,bloqueioDTORequest);

        if(bloqueioStatusDTOResponse.getStatusBloqueio().equals(StatusCartao.BLOQUEADO)) {
            cartaoEncontrado.setStatusCartao(bloqueioStatusDTOResponse.getStatusBloqueio());
            Bloqueio bloqueio = bloqueioDTORequest.converter(cartaoEncontrado, request.getHeader("User-Agent"), IPAddress.getClientIp(request));
            bloqueioRepository.save(bloqueio);
            this.countBloqueiosSucesso.increment();
            return ResponseEntity.status(200).build();
        }

        this.countBloqueiosFalha.increment();
        return ResponseEntity.status(422).body(new ErroAPI("Cartão","Falha ao bloquear o cartão"));
    }
}
