package br.com.proposta.PropostaOrange.biometria;

import br.com.proposta.PropostaOrange.upload.UploadImages;

import javax.persistence.*;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificadorCartao;

    @Column(columnDefinition = "LONGTEXT")
    private String biometriaBase64;

    public Biometria() {
    }

    public Biometria(String identificadorCartao, String biometriaBase64) {
        this.identificadorCartao = identificadorCartao;
        this.biometriaBase64 = biometriaBase64;
    }

    public Long getId() {
        return id;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getBiometriaBase64() {
        return biometriaBase64;
    }
}
