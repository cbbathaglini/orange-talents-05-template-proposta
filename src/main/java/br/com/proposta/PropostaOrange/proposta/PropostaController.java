package br.com.proposta.PropostaOrange.proposta;


import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDePropostas", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid PropostaDTORequest propostaDTORequest,  UriComponentsBuilder uriBuilder){

        if(propostaRepository.findPropostaDocumento(propostaDTORequest.getDocumento()) > 0){
            return ResponseEntity.status(422).body(new ErroAPI("Proposta", "O solicitante já requisitou uma proposta"));
        }

        Proposta proposta = propostaDTORequest.converter();
        propostaRepository.save(proposta);


        URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaDTOResponse(proposta));
    }
}
