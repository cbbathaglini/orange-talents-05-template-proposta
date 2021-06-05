package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.aviso.Aviso;
import br.com.proposta.PropostaOrange.aviso.AvisoDTOResponse;
import br.com.proposta.PropostaOrange.bloqueio.Bloqueio;
import br.com.proposta.PropostaOrange.bloqueio.BloqueioDTOResponse;
import br.com.proposta.PropostaOrange.carteira.Carteira;
import br.com.proposta.PropostaOrange.carteira.CarteiraDTOResponse;
import br.com.proposta.PropostaOrange.parcela.Parcela;
import br.com.proposta.PropostaOrange.parcela.ParcelaDTOResponse;
import br.com.proposta.PropostaOrange.proposta.PropostaRepository;
import br.com.proposta.PropostaOrange.renegociacao.Renegociacao;
import br.com.proposta.PropostaOrange.renegociacao.RenegociacaoDTOResponse;
import br.com.proposta.PropostaOrange.vencimento.Vencimento;
import br.com.proposta.PropostaOrange.vencimento.VencimentoDTOResponse;
import br.com.proposta.PropostaOrange.vencimento.VencimentoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartaoDTOResponse {
    private String id;
    private Long idProposta;
    private LocalDateTime emitidoEm;
    private String titular;
    private int limite;
    private List<BloqueioDTOResponse> bloqueios = new ArrayList<>();
    private List<AvisoDTOResponse> avisos = new ArrayList<>();
    private List<CarteiraDTOResponse> carteiras = new ArrayList<>();
    private List<ParcelaDTOResponse> parcelas = new ArrayList<>();
    private RenegociacaoDTOResponse renegociacao;
    private VencimentoDTOResponse vencimento;

    public Cartao converter(PropostaRepository propostaRepository){

        List<Aviso> listaAvisos = this.avisos.stream()
                .map(a -> a.converter())
                .collect(Collectors.toList());

        List<Bloqueio> listaBloqueios = this.bloqueios.stream()
                .map(b -> b.converter())
                .collect(Collectors.toList());

        List<Parcela> listaParcelas = this.parcelas.stream()
                .map(p -> p.converter())
                .collect(Collectors.toList());

        List<Carteira> listaCarteiras = this.carteiras.stream()
                .map(c -> c.converter())
                .collect(Collectors.toList());

        return new Cartao(this.id,this.emitidoEm,this.titular, this.limite,
                this.vencimento != null ? this.vencimento.converter() : null,
                this.renegociacao != null ? this.renegociacao.converter() : null,
                listaAvisos, listaBloqueios , listaParcelas, listaCarteiras,
                propostaRepository.getOne(this.idProposta) );
    }

    public CartaoDTOResponse(String id, Long idProposta, LocalDateTime emitidoEm, String titular, int limite, List<BloqueioDTOResponse> bloqueios, List<AvisoDTOResponse> avisos, List<CarteiraDTOResponse> carteiras, List<ParcelaDTOResponse> parcelas, RenegociacaoDTOResponse renegociacao, VencimentoDTOResponse vencimento) {
        this.id = id;
        this.idProposta = idProposta;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
    }

    public String getId() {
        return id;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public int getLimite() {
        return limite;
    }

    public List<BloqueioDTOResponse> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoDTOResponse> getAvisos() {
        return avisos;
    }

    public List<CarteiraDTOResponse> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaDTOResponse> getParcelas() {
        return parcelas;
    }

    public RenegociacaoDTOResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoDTOResponse getVencimento() {
        return vencimento;
    }

    @Override
    public String toString() {
        return "CartaoDTOResponse{" +
                "id='" + id + '\'' +
                ", idProposta=" + idProposta +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", limite=" + limite +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                '}';
    }
}
