package br.com.proposta.PropostaOrange.aviso;

import java.time.LocalDateTime;
import java.util.Date;

public class AvisoDTOResponse {
    private Date validoAte;
    private String destino;

    public AvisoDTOResponse(Date validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso converter(){
        return new Aviso(this.validoAte, this.destino);
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
