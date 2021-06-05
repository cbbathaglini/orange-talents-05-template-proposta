package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.aviso.Aviso;
import br.com.proposta.PropostaOrange.bloqueio.Bloqueio;
import br.com.proposta.PropostaOrange.carteira.Carteira;
import br.com.proposta.PropostaOrange.parcela.Parcela;
import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.renegociacao.Renegociacao;
import br.com.proposta.PropostaOrange.vencimento.Vencimento;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {
    @Id
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private int limite;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;

    @ManyToOne(cascade = CascadeType.ALL)
    private Renegociacao renegociacao;

    @OneToMany
    private List<Aviso> avisosList = new ArrayList<>();

    @OneToMany
    private List<Bloqueio> bloqueioList = new ArrayList<>();

    @OneToMany
    private List<Parcela> parcelasList = new ArrayList<>();

    @OneToMany
    private List<Carteira> carteirasList = new ArrayList<>();

    @OneToOne @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, int limite, Vencimento vencimento, Renegociacao renegociacao, List<Aviso> avisosList, List<Bloqueio> bloqueioList, List<Parcela> parcelasList, List<Carteira> carteirasList, Proposta proposta) {
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
}
