package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bloqueadosEm;
    private String sistemaResponsavel;
    private String userAgent;
    private String IP;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    public Bloqueio() {
    }

    public Bloqueio(LocalDateTime bloqueadosEm, String userAgent, String IP,String sistemaResponsavel, boolean ativo, Cartao cartao) {
        this.bloqueadosEm = bloqueadosEm;
        this.userAgent = userAgent;
        this.IP = IP;
        this.ativo = ativo;
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
    }

    public Bloqueio(Long id, LocalDateTime bloqueadosEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadosEm = bloqueadosEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Long getId() {
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
