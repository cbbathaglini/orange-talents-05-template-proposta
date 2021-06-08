package br.com.proposta.PropostaOrange.avisoviagem;

public class AvisoSistLegadoFallback implements AvisoSistLegado{
    @Override
    public AvisoStatusDTOResponse postAviso(String idCartao, AvisoViagemDTORequest avisoViagemDTORequest) {
        return  new AvisoStatusDTOResponse(StatusAviso.FALHA);
    }
}
