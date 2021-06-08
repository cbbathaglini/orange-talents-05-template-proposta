package br.com.proposta.PropostaOrange.avisoviagem;

import org.springframework.stereotype.Component;

@Component
public class AvisoSistLegadoFallback implements AvisoConsultaSistemaLegado{

    @Override
    public AvisoStatusDTOResponse postAvisos(String idCartao, AvisoViagemDTORequest request) {
        return  new AvisoStatusDTOResponse(StatusAviso.FALHA);
    }
}
