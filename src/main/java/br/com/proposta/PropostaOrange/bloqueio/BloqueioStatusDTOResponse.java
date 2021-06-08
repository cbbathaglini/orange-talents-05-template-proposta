package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.StatusCartao;

public class BloqueioStatusDTOResponse {
    private StatusCartao resultado;

    public BloqueioStatusDTOResponse() {
    }

    public BloqueioStatusDTOResponse(StatusCartao resultado) {
        this.resultado = resultado;
    }

    public StatusCartao getStatusBloqueio() {
        return resultado;
    }

    public void setResultado(StatusCartao resultado) {
        this.resultado = resultado;
    }
}
