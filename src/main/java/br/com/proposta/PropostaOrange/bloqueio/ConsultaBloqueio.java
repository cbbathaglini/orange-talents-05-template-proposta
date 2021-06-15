package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.avisoviagem.AvisoStatusDTOResponse;
import br.com.proposta.PropostaOrange.avisoviagem.AvisoViagemDTORequest;
import br.com.proposta.PropostaOrange.cartao.CartaoDTORequest;
import br.com.proposta.PropostaOrange.cartao.CartaoDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@FeignClient(url = "${local.cartao.resource}", name = "consultarBloqueio", fallback = ConsultaBloqueioFallback.class)
public interface ConsultaBloqueio{
    @RequestMapping(value="${cartao.resource.bloqueio}", method= RequestMethod.POST, consumes = "application/json")
    BloqueioStatusDTOResponse consultarBloqueio(@PathVariable("id") String idCartao, @RequestBody @Valid BloqueioDTORequest request);

}