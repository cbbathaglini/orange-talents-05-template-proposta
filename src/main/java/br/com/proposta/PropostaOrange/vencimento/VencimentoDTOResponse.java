package br.com.proposta.PropostaOrange.vencimento;

import com.fasterxml.jackson.databind.node.ValueNode;

import java.time.LocalDateTime;

public class VencimentoDTOResponse {
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoDTOResponse(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento converter(){
        return  new Vencimento(this.id,this.dia,this.dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public String toString() {
        return "VencimentoDTOResponse{" +
                "id='" + id + '\'' +
                ", dia=" + dia +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
