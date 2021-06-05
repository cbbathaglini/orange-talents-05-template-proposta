package br.com.proposta.PropostaOrange.cartao;


import br.com.proposta.PropostaOrange.proposta.*;
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

/*
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private ConsultaNovoCartao consultaNovoCartao;

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeCartoes", allEntries = true)
    public ResponseEntity cadastrar(@RequestBody @Valid CartaoDTORequest cartaoDTORequest, UriComponentsBuilder uriBuilder){

        CartaoDTOResponse cartaoDTOResponse = consultaNovoCartao.consultarCartao( cartaoDTORequest);
        return ResponseEntity.ok().build();
    }
}

 */
