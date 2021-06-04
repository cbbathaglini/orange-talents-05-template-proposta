package br.com.proposta.PropostaOrange.proposta;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.proposta.StatusResultado;

public class ConsultaDadosDTORequest {

    private Long id;
    private String documento;
    private String nome;

    public ConsultaDadosDTORequest(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}
