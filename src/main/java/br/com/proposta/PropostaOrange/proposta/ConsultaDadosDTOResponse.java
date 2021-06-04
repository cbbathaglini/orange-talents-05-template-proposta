package br.com.proposta.PropostaOrange.proposta;

public class ConsultaDadosDTOResponse {
    private Long id;
    private String documento;
    private String nome;
    private StatusResultado statusResultado;

    public ConsultaDadosDTOResponse(Long id, String documento, String nome, StatusResultado statusResultado) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.statusResultado = statusResultado;
    }


    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusResultado getStatusResultado() {
        return statusResultado;
    }

    @Override
    public String toString() {
        return "ResultadoDados{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", statusResultado=" + statusResultado +
                '}';
    }
}
