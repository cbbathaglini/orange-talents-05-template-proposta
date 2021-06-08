package br.com.proposta.PropostaOrange.avisoviagem;

import java.time.LocalDate;


public class AvisoViagemDTOResponse {
    private LocalDate validoAte;
    private String destino;

    public AvisoViagemDTOResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem converter(){
        return new AvisoViagem(this.validoAte, this.destino);
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
