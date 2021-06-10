package br.com.proposta.PropostaOrange.ofuscador;

import javax.persistence.Convert;

public class OfuscadorDTORequest {

    @Convert(converter = Ofuscador.class)
    private String valor;

    public OfuscadorDTORequest() {
    }

    public OfuscadorDTORequest(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
