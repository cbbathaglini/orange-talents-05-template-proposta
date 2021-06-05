package br.com.proposta.PropostaOrange.proposta;


import br.com.proposta.PropostaOrange.cartao.*;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDePropostas", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid PropostaDTORequest propostaDTORequest,  UriComponentsBuilder uriBuilder){

        if(propostaRepository.findPropostaDocumento(propostaDTORequest.getDocumento()) > 0){
            return ResponseEntity.status(422).body(new ErroAPI("Proposta", "O solicitante já requisitou uma proposta"));
        }

        Proposta proposta = propostaDTORequest.converter();
        propostaRepository.save(proposta);

        ConsultaDadosDTOResponse consultaDadosDTOResponse = consultaDadosInterface.consultarDados(new ConsultaDadosDTORequest(proposta));
        proposta.setStatusProposta(consultaDadosDTOResponse.getStatusResultado());

        //new AssociaCartao(propostaRepository,consultaNovoCartao,cartaoRepository).associar();

        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDTOResponse(proposta));
    }



}
