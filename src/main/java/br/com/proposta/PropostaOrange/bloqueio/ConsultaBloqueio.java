package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.CartaoDTORequest;
import br.com.proposta.PropostaOrange.cartao.CartaoDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "http://localhost:8888", name = "consultarBloqueio",fallback = ConsultaBloqueioFallback.class)
public interface ConsultaBloqueio{
    @RequestMapping(value="/api/cartoes/{id}/bloqueios", method= RequestMethod.POST, consumes = "application/json")
    BloqueioStatusDTOResponse consultarBloqueio(@PathVariable("id") String idCartao, BloqueioDTORequest request);
}