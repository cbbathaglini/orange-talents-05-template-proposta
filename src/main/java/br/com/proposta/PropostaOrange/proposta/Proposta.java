package br.com.proposta.PropostaOrange.proposta;


import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoDTORequest;
import br.com.proposta.PropostaOrange.cartao.CartaoDTOResponse;
import br.com.proposta.PropostaOrange.cartao.ConsultaNovoCartao;
import br.com.proposta.PropostaOrange.ofuscador.Ofuscador;
import com.google.common.hash.Hashing;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = Ofuscador.class)
    private String documento;
    private String hashDocumento;

    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne(mappedBy = "proposta", fetch = FetchType.EAGER)
    private Cartao cartao;

    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {

        this.documento = documento;
        this.hashDocumento = Hashing.sha256().hashString(documento, StandardCharsets.UTF_8).toString();
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void setStatusProposta(StatusResultado statusResultado){
        this.statusProposta = statusResultado.retornaStatus();
    }



    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getHashDocumento() {
        return hashDocumento;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", hashDocumento='" + hashDocumento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", statusProposta=" + statusProposta +
                ", cartao=" + cartao +
                '}';
    }
}
