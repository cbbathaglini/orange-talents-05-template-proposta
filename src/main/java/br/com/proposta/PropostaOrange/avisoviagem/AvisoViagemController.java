package br.com.proposta.PropostaOrange.avisoviagem;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoRepository;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private AvisoConsultaSistemaLegado avisoConsultaSistemaLegado;

    private final MeterRegistry meterRegistry;
    private Counter countAvisosViagemSucesso;
    private Counter countAvisosViagemFalha;


    public AvisoViagemController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void initMetrics() {
        Collection<Tag> tags = new ArrayList<>();
        this.countAvisosViagemSucesso = this.meterRegistry.counter("aviso_viagem_sucesso", tags);
        this.countAvisosViagemFalha = this.meterRegistry.counter("aviso_viagem_falha", tags);
    }



    @PostMapping(value = "/api/cartoes/{id}/avisos")
    @Transactional
    @CacheEvict(value = "listaDeAvisosViagens", allEntries = true)
    public ResponseEntity cadastrar(@PathVariable("id") String numCartao,
                                    HttpServletRequest request,
                                    @RequestBody @Valid AvisoViagemDTORequest avisoViagemDTORequest){


        Cartao cartaoEncontrado = cartaoRepository.findByNumCartao(numCartao);
        if( cartaoEncontrado == null){
            this.countAvisosViagemFalha.increment();
            return ResponseEntity.status(404).body(new ErroAPI("Cartão","O cartão não foi encontrado na base de dados."));
        }

        AvisoStatusDTOResponse avisoStatusDTOResponse = avisoConsultaSistemaLegado.postAvisos(numCartao,avisoViagemDTORequest);
        if(avisoStatusDTOResponse.getResultado().equals(StatusAviso.CRIADO)) {
            AvisoViagem avisoViagem = avisoViagemDTORequest.converter(cartaoEncontrado,request);
            avisoViagem.setStatusAviso(avisoStatusDTOResponse.getResultado());
            avisoViagemRepository.save(avisoViagem);
            this.countAvisosViagemSucesso.increment();
            return ResponseEntity.status(200).build();
        }

        this.countAvisosViagemFalha.increment();
        return ResponseEntity.status(422).body(new ErroAPI("Aviso","Falha ao realizar notificação do aviso de viagem"));
    }
}
