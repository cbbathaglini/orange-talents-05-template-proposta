package br.com.proposta.PropostaOrange.proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaDTOResponse {

    private Long id;
    private String email;
    private String nome;
    private StatusProposta statusProposta;

    public PropostaDTOResponse() {
    }

    public PropostaDTOResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.statusProposta = proposta.getStatusProposta();
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
