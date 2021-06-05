package br.com.proposta.PropostaOrange.proposta;


import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.cartao.CartaoDTORequest;
import br.com.proposta.PropostaOrange.cartao.CartaoDTOResponse;
import br.com.proposta.PropostaOrange.cartao.ConsultaNovoCartao;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;
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


}
