package br.com.proposta.PropostaOrange.vencimento;

import com.fasterxml.jackson.databind.node.ValueNode;

import java.time.LocalDateTime;

public class VencimentoDTOResponse {
    private Long id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoDTOResponse(Long id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento converter(){
        return  new Vencimento(this.dia,this.dataDeCriacao);
    }

    public Long getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
