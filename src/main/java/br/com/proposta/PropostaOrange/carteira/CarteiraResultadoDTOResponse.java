package br.com.proposta.PropostaOrange.carteira;
/*
{
  "resultado": "ASSOCIADA",
  "id": "string"
}
*/
public class CarteiraResultadoDTOResponse {
    private StatusCarteira resultado;
    private String id;

    public CarteiraResultadoDTOResponse() {
    }

    public CarteiraResultadoDTOResponse(StatusCarteira resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public StatusCarteira getResultado() {
        return resultado;
    }

    public void setResultado(StatusCarteira resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
