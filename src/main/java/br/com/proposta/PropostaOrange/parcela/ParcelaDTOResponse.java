package br.com.proposta.PropostaOrange.parcela;

import java.math.BigDecimal;

public class ParcelaDTOResponse {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public ParcelaDTOResponse(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela converter(){
        return new Parcela(this.id, this.quantidade, this.valor);
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
