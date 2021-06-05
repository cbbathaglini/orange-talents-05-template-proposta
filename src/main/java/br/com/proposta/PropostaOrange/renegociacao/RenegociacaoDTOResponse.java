package br.com.proposta.PropostaOrange.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoDTOResponse {
    private String id;
    private Long quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public RenegociacaoDTOResponse(String id, Long quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao converter(){
        return new Renegociacao(this.id, this.quantidade, this.valor, this.dataDeCriacao);
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
