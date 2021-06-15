package br.com.proposta.PropostaOrange.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "${local.consulta.dados.financeiros}", name = "consultaRestricao", fallback = ConsultaDadosFinanceirosFallback.class)
public interface ConsultaDadosFinanceiros {

    @RequestMapping(value="${consulta.dados.financeiros}", method= RequestMethod.GET, consumes = "application/json")
    ConsultaDadosDTOResponse consultarDados(ConsultaDadosDTORequest request);
}