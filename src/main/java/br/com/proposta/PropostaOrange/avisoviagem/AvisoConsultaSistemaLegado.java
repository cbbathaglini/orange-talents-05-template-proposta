package br.com.proposta.PropostaOrange.avisoviagem;


import br.com.proposta.PropostaOrange.bloqueio.BloqueioDTORequest;
import br.com.proposta.PropostaOrange.bloqueio.BloqueioStatusDTOResponse;
import br.com.proposta.PropostaOrange.bloqueio.ConsultaBloqueioFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(url = "http://localhost:8888", name = "postAvisos", fallback = AvisoSistLegadoFallback.class)
public interface AvisoConsultaSistemaLegado{
    @RequestMapping(value="/api/cartoes/{id}/avisos", method= RequestMethod.POST, consumes = "application/json")
    AvisoStatusDTOResponse postAvisos(@PathVariable("id") String idCartao, @RequestBody @Valid AvisoViagemDTORequest request);
}