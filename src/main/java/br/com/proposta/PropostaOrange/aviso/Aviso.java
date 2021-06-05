package br.com.proposta.PropostaOrange.aviso;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date validoAte;
    private String destino;

    public Aviso() {
    }

    public Aviso( Date validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
