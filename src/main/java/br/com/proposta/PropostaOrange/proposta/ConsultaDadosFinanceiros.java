package br.com.proposta.PropostaOrange.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "http://localhost:9999", name = "consultarDadosFinanceiros")
public interface ConsultaDadosFinanceiros {

    @RequestMapping(value="/api/solicitacao", method= RequestMethod.POST, consumes = "application/json")
    ConsultaDadosDTOResponse consultarDados(ConsultaDadosDTORequest request);

}