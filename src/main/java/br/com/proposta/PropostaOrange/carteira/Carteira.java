package br.com.proposta.PropostaOrange.carteira;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.proposta.StatusProposta;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private LocalDateTime associadaEm;
    private String emissor;
    private String idSistLegado;
    private String carteira;

    @Enumerated(EnumType.STRING)
    private StatusCarteira statusCarteira;

    @ManyToOne
    private Cartao cartao;

    public Carteira(String email, LocalDateTime associadaEm, Cartao cartao,String carteira,StatusCarteira statusCarteira,String idSistLegado) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.cartao = cartao;
        this.carteira = carteira;
        this.statusCarteira = statusCarteira;
        this.idSistLegado = idSistLegado;
    }

    public Carteira() {
    }

    public Carteira(Long id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Long getId() {
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

    public void setStatusCarteira(StatusCarteira statusCarteira) {
        this.statusCarteira = statusCarteira;
    }

    public void setIdSistLegado(String idSistLegado) {
        this.idSistLegado = idSistLegado;
    }
}
