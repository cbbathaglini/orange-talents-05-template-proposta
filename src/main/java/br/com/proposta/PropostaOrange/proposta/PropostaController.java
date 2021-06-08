package br.com.proposta.PropostaOrange.proposta;


import br.com.proposta.PropostaOrange.cartao.*;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import feign.FeignException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaDadosFinanceiros consultaDadosInterface;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ConsultaNovoCartao consultaNovoCartao;


    private final MeterRegistry meterRegistry;
    private Counter countPropostaSucesso;
    private Counter countPropostaFalha;

    public PropostaController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void initMetrics() {
        Collection<Tag> tags = new ArrayList<>();
        this.countPropostaSucesso = this.meterRegistry.counter("proposta_sucesso", tags);
        this.countPropostaFalha = this.meterRegistry.counter("proposta_falha", tags);
    }



    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDePropostas", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid PropostaDTORequest propostaDTORequest,  UriComponentsBuilder uriBuilder){

        if(propostaRepository.findPropostaDocumento(propostaDTORequest.getDocumento()) > 0){
            this.countPropostaFalha.increment();
            return ResponseEntity.status(422).body(new ErroAPI("Proposta", "O solicitante já requisitou uma proposta"));
        }

        Proposta proposta = propostaDTORequest.converter();
        propostaRepository.save(proposta);

        ConsultaDadosDTOResponse consultaDadosDTOResponse = consultaDadosInterface.consultarDados(new ConsultaDadosDTORequest(proposta));
        proposta.setStatusProposta(consultaDadosDTOResponse.getStatusResultado());
        this.countPropostaSucesso.increment();

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDTOResponse(proposta));
    }


    @GetMapping(value = "/{id}")
    @Cacheable(value = "listaDePropostas")
    public ResponseEntity consultar(@PathVariable("id") Long idProposta){

        Optional<Proposta> proposta = propostaRepository.findById(idProposta);
        if(!proposta.isPresent()){
            return ResponseEntity.status(404).body(new ErroAPI("Proposta","A proposta não foi encontrada."));
        }

        return ResponseEntity.status(200).body(new PropostaDTOResponse(proposta.get()));
    }


}
