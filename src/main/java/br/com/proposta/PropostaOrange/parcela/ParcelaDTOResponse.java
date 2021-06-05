package br.com.proposta.PropostaOrange.parcela;

import java.math.BigDecimal;

public class ParcelaDTOResponse {
    private Long id;
    private Integer quantidade;
    private BigDecimal valor;

    public ParcelaDTOResponse(Long id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
