package br.com.proposta.PropostaOrange.proposta;

public class ConsultaDadosDTOResponse {
    private Long idProposta;
    private String documento;
    private String nome;
    private StatusResultado resultadoSolicitacao;

    public ConsultaDadosDTOResponse(Long idProposta, String documento, String nome, StatusResultado resultadoSolicitacao) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public StatusResultado getStatusResultado() {
        return resultadoSolicitacao;
    }

}
