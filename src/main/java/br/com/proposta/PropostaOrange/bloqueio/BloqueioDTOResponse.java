package br.com.proposta.PropostaOrange.bloqueio;

import java.time.LocalDateTime;

public class BloqueioDTOResponse {
    private String id;
    private LocalDateTime bloqueadosEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public BloqueioDTOResponse(String id, LocalDateTime bloqueadosEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadosEm = bloqueadosEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio converter(){
        return  new Bloqueio(this.id, this.bloqueadosEm, this.sistemaResponsavel, this.ativo);
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
