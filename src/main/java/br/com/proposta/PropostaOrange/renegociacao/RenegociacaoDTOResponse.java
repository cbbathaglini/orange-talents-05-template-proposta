package br.com.proposta.PropostaOrange.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoDTOResponse {
    private Long id;
    private Long quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public RenegociacaoDTOResponse(Long id, Long quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
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
