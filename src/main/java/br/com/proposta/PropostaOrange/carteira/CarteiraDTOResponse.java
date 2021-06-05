package br.com.proposta.PropostaOrange.carteira;

import java.time.LocalDateTime;

public class CarteiraDTOResponse {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public CarteiraDTOResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira converter(){
        return new Carteira(this.id, this.email, this.associadaEm, this.emissor);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
