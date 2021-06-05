package br.com.proposta.PropostaOrange.bloqueio;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    private String id;
    private LocalDateTime bloqueadosEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime bloqueadosEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadosEm = bloqueadosEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadosEm() {
        return bloqueadosEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
