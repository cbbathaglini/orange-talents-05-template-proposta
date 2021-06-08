package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.avisoviagem.AvisoViagem;
import br.com.proposta.PropostaOrange.biometria.Biometria;
import br.com.proposta.PropostaOrange.bloqueio.Bloqueio;
import br.com.proposta.PropostaOrange.carteira.Carteira;
import br.com.proposta.PropostaOrange.parcela.Parcela;
import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.renegociacao.Renegociacao;
import br.com.proposta.PropostaOrange.upload.UploadImages;
import br.com.proposta.PropostaOrange.vencimento.Vencimento;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cartao {
    @Id
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private int limite;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;

    @ManyToOne(cascade = CascadeType.ALL)
    private Renegociacao renegociacao;

    @OneToMany
    private List<AvisoViagem> avisosList = new ArrayList<>();

    @OneToMany
    private List<Bloqueio> bloqueioList = new ArrayList<>();

    @OneToMany
    private List<Parcela> parcelasList = new ArrayList<>();

    @OneToMany
    private List<Carteira> carteirasList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Biometria> biometriasList = new ArrayList<>();

    @OneToOne @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, int limite, Vencimento vencimento, Renegociacao renegociacao, List<AvisoViagem> avisosList, List<Bloqueio> bloqueioList, List<Parcela> parcelasList, List<Carteira> carteirasList, Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.renegociacao = renegociacao;
        this.avisosList = avisosList;
        this.bloqueioList = bloqueioList;
        this.parcelasList = parcelasList;
        this.carteirasList = carteirasList;
        this.proposta = proposta;
    }

    public String getId() {
        return id;
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

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public void setBiometria(List<Biometria> biometriasList) {
        this.biometriasList = biometriasList;
    }


    public boolean validaImgs(UploadImages uploaderImage) {
        for (Biometria biometria:this.biometriasList) {
            if(!uploaderImage.validarBase64(biometria.getBiometriaBase64())){
                return false;
            }
        }
        return  true;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public void setStatusCartao(StatusCartao statusCartao) {
        this.statusCartao = statusCartao;
    }

    public boolean jaBloqueado(CartaoRepository cartaoRepository) {
        Optional<Cartao> cartaoOptional = cartaoRepository.cartaoBloqueado(this.id, StatusCartao.BLOQUEADO);
         if(cartaoOptional.isPresent()){
             return true;
         }
         return false;
    }
}
