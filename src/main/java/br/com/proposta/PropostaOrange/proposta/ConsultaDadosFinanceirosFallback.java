package br.com.proposta.PropostaOrange.proposta;

import org.springframework.stereotype.Component;

@Component
public class ConsultaDadosFinanceirosFallback implements ConsultaDadosFinanceiros{
    @Override
    public ConsultaDadosDTOResponse consultarDados(ConsultaDadosDTORequest request) {
        return new ConsultaDadosDTOResponse(request.getIdProposta(), request.getDocumento(), request.getNome(), StatusResultado.COM_RESTRICAO);
    }
}
