package br.com.proposta.PropostaOrange.avisoviagem;

public class AvisoStatusDTOResponse {
    private StatusAviso resultado;

    public AvisoStatusDTOResponse() {
    }

    public AvisoStatusDTOResponse(StatusAviso resultado) {
        this.resultado = resultado;
    }

    public StatusAviso getResultado() {
        return resultado;
    }
}
