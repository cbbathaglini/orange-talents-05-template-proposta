package br.com.proposta.PropostaOrange.avisoviagem;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;
    private String destino;
    private LocalDateTime avisadoEm;
    private String ipcliente;
    private String useragent;
    //private StatusAviso statusAviso;

    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(LocalDate validoAte, String destino, LocalDateTime avisadoEm, String ipcliente, String useragent, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.avisadoEm = avisadoEm;
        this.ipcliente = ipcliente;
        this.useragent = useragent;
        this.cartao = cartao;
    }

    public AvisoViagem() {
    }

    public AvisoViagem(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
