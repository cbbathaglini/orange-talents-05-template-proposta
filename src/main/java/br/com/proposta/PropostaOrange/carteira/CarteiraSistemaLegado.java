package br.com.proposta.PropostaOrange.carteira;

import br.com.proposta.PropostaOrange.avisoviagem.AvisoSistLegadoFallback;
import br.com.proposta.PropostaOrange.avisoviagem.AvisoStatusDTOResponse;
import br.com.proposta.PropostaOrange.avisoviagem.AvisoViagemDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(url = "http://localhost:8888", name = "carteira", fallback = CarteiraSistemaLegadoFallback.class)
public interface CarteiraSistemaLegado{
    @RequestMapping(value="/api/cartoes/{id}/carteiras", method= RequestMethod.POST, consumes = "application/json")
    CarteiraResultadoDTOResponse postCarteira(@PathVariable("id") String idCartao, @RequestBody @Valid CarteiraDTORequest request);
}