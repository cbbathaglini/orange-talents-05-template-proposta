package br.com.proposta.PropostaOrange.ofuscador;

import javax.persistence.*;

@Entity
public class TesteOfuscador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = Ofuscador.class)
    private String valor;

    public TesteOfuscador() {
    }

    public TesteOfuscador(String valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
