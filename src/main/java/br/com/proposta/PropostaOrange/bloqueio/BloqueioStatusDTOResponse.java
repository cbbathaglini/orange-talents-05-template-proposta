package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.StatusCartao;

public class BloqueioStatusDTOResponse {
    private StatusCartao statusBloqueio;

    public BloqueioStatusDTOResponse(StatusCartao statusBloqueio) {
        this.statusBloqueio = statusBloqueio;
    }

    public StatusCartao getStatusBloqueio() {
        return statusBloqueio;
    }
}
