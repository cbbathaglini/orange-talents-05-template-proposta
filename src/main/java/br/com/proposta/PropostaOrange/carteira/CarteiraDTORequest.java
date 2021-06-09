package br.com.proposta.PropostaOrange.carteira;

import br.com.proposta.PropostaOrange.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CarteiraDTORequest {

    @Email
    @NotNull
    @NotEmpty
    private String email;

    private String carteira;

    public CarteiraDTORequest() {
    }

    public CarteiraDTORequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public Carteira converter(Cartao cartao,CarteiraResultadoDTOResponse carteiraResultadoDTOResponse) {
        return new Carteira(this.email, LocalDateTime.now(), cartao,this.carteira, carteiraResultadoDTOResponse.getResultado(),carteiraResultadoDTOResponse.getId());
    }
}
