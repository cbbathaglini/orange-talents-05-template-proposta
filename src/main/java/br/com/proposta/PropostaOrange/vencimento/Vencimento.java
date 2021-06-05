package br.com.proposta.PropostaOrange.vencimento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    private String id;

    private int dia;

    private LocalDateTime dataDeCriacao;

    public Vencimento() {
    }

    public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Override
    public String toString() {
        return "Vencimento{" +
                "id=" + id +
                ", dia=" + dia +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }
}
