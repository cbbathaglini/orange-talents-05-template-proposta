package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoRepository;
import br.com.proposta.PropostaOrange.cartao.StatusCartao;
import br.com.proposta.PropostaOrange.proposta.*;
import br.com.proposta.PropostaOrange.utils.IPAddress;
import br.com.proposta.PropostaOrange.validateErrors.ErroAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private ConsultaBloqueio consultaBloqueio;

    @PostMapping(value = "/cartoes/{id}/bloqueios")
    @Transactional
    @CacheEvict(value = "listaDeBloqueios", allEntries = true)
    public ResponseEntity cadastrar(@PathVariable("id") String numCartao,
                                    HttpServletRequest request,
                                    @RequestBody @Valid BloqueioDTORequest bloqueioDTORequest){

        Cartao cartaoEncontrado = cartaoRepository.findByNumCartao(numCartao);
        if( cartaoEncontrado == null){
            return ResponseEntity.status(404).body(new ErroAPI("Cartão","O cartão não foi encontrado na base de dados."));
        }

        if(cartaoEncontrado.jaBloqueado(cartaoRepository)){
            return ResponseEntity.status(422).body(new ErroAPI("Cartão","O cartão já tinha sido bloqueado."));
        }

        cartaoEncontrado.setStatusCartao(StatusCartao.BLOQUEADO);
        Bloqueio bloqueio = bloqueioDTORequest.converter(cartaoEncontrado, request.getHeader("User-Agent"), IPAddress.getClientIp(request));
        bloqueioRepository.save(bloqueio);
        return ResponseEntity.status(200).build();

    }
}
