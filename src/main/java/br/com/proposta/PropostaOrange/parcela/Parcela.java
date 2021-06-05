package br.com.proposta.PropostaOrange.parcela;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
public class Parcela {
    @Id
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
