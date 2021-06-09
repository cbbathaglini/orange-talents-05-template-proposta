package br.com.proposta.PropostaOrange.carteira;

import org.springframework.stereotype.Component;

@Component
public class CarteiraSistemaLegadoFallback implements CarteiraSistemaLegado{
    @Override
    public CarteiraResultadoDTOResponse postCarteira(String idCartao, CarteiraDTORequest request) {
        return new CarteiraResultadoDTOResponse(StatusCarteira.FALHA,idCartao);
    }
}
