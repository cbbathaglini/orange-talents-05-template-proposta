package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.vencimento.Vencimento;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cartao {
    @Id
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private int limite;

    @OneToOne @JoinColumn(name = "vencimento_id")
    private Vencimento vencimento;

    @OneToOne @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, int limite, Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public int getLimite() {
        return limite;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
