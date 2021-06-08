package br.com.proposta.PropostaOrange.bloqueio;

import br.com.proposta.PropostaOrange.cartao.Cartao;
import br.com.proposta.PropostaOrange.utils.IPAddress;

import java.time.LocalDateTime;

public class BloqueioDTORequest {
    private String sistemaResponsavel;

    public BloqueioDTORequest() {
    }

    public BloqueioDTORequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Bloqueio converter(Cartao cartao,String userAgent, String ip) {
        return new Bloqueio(LocalDateTime.now(), userAgent,ip,this.sistemaResponsavel,true,cartao);
    }
}
