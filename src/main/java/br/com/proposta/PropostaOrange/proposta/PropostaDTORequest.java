package br.com.proposta.PropostaOrange.proposta;

import br.com.proposta.PropostaOrange.validacoes.DocumentoCPFouCNPJ;
import br.com.proposta.PropostaOrange.validacoes.ValidarDocumento;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaDTORequest {


    @DocumentoCPFouCNPJ
    private String documento;

    @Email @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private String nome;

    @NotNull @NotEmpty
    private String endereco;

    @NotNull @PositiveOrZero
    private BigDecimal salario;

    public PropostaDTORequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta converter() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
