package br.com.proposta.PropostaOrange.avisoviagem;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.utils.IPAddress;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/*
    {
      "destino": "string",
      "validoAte": "2021-06-08"
    }
 */
public class AvisoViagemDTORequest {
    @NotNull @NotEmpty
    private String destino;

    @NotNull @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    public AvisoViagemDTORequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
    public AvisoViagem converter(Cartao cartao,HttpServletRequest request){
        return new AvisoViagem(this.validoAte, this.destino, LocalDateTime.now(), IPAddress.getClientIp(request),request.getHeader("User-Agent"), cartao);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
