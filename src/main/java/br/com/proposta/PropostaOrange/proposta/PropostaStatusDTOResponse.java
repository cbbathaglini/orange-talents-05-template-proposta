package br.com.proposta.PropostaOrange.proposta;

public class PropostaStatusDTOResponse {
    private StatusProposta statusProposta;

    public PropostaStatusDTOResponse() {
    }

    public PropostaStatusDTOResponse(Proposta proposta) {
        this.statusProposta = proposta.getStatusProposta();
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
