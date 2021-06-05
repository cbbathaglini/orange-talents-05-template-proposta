package br.com.proposta.PropostaOrange.renegociacao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    private String id;
    private Long quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;


    public Renegociacao() {
    }

    public Renegociacao(String id, Long quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
