package br.com.proposta.PropostaOrange.proposta;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.proposta.StatusResultado;

public class ConsultaDadosDTORequest {

    private Long idProposta;
    private String documento;
    private String nome;

    public ConsultaDadosDTORequest(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }



    public Long getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}
