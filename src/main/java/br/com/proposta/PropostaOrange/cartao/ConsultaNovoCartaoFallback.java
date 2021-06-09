package br.com.proposta.PropostaOrange.cartao;

import feign.FeignException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Component
public class ConsultaNovoCartaoFallback implements ConsultaNovoCartao{

    @Override
    public CartaoDTOResponse consultarCartao(CartaoDTORequest request) {

        System.out.println("Fallback: " + request.toString());
        return null;
    }
}
