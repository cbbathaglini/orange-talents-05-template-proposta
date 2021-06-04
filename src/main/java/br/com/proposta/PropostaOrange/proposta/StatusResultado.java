package br.com.proposta.PropostaOrange.proposta;

import br.com.proposta.PropostaOrange.proposta.StatusProposta;

public enum StatusResultado {
    COM_RESTRICAO{
        StatusProposta retornaStatus() {
            return StatusProposta.NAO_ELEGIVEL;
        };
    },SEM_RESTRICAO{
        StatusProposta retornaStatus() {
            return StatusProposta.ELEGIVEL;
        };
    };
    abstract StatusProposta retornaStatus();
}
