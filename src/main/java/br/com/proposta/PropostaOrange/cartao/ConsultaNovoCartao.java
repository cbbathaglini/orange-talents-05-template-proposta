package br.com.proposta.PropostaOrange.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "http://localhost:8888", name = "consultaNovoCartao")//,fallback = ConsultaNovoCartaoFallback.class)
public interface ConsultaNovoCartao{
    @RequestMapping(value="/api/cartoes", method= RequestMethod.POST, consumes = "application/json")
    CartaoDTOResponse consultarCartao(CartaoDTORequest request);
}