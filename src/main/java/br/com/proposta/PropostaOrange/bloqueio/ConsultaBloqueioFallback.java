package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.avisoviagem.AvisoStatusDTOResponse;
import br.com.proposta.PropostaOrange.avisoviagem.AvisoViagemDTORequest;
import br.com.proposta.PropostaOrange.cartao.StatusCartao;
import br.com.proposta.PropostaOrange.proposta.ConsultaDadosDTOResponse;
import br.com.proposta.PropostaOrange.proposta.StatusResultado;
import org.springframework.stereotype.Component;

@Component
public class ConsultaBloqueioFallback implements ConsultaBloqueio {

    @Override
    public BloqueioStatusDTOResponse consultarBloqueio(String idCartao, BloqueioDTORequest request) {
        return new BloqueioStatusDTOResponse(StatusCartao.LIBERADO);
    }

}
