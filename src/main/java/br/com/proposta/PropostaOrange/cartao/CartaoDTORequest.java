package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.proposta.Proposta;

public class CartaoDTORequest {

    private Long idProposta;
    private String documento;
    private String nome;

    public CartaoDTORequest(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }



    public Long getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "CartaoDTORequest{" +
                "idProposta=" + idProposta +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
