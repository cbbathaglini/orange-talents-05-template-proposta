package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import br.com.proposta.PropostaOrange.proposta.PropostaRepository;
import br.com.proposta.PropostaOrange.vencimento.Vencimento;
import br.com.proposta.PropostaOrange.vencimento.VencimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class AssociaCartao{

    private PropostaRepository propostaRepository;
    private CartaoRepository cartaoRepository;
    private ConsultaNovoCartao consultaNovoCartao;
    public AssociaCartao(PropostaRepository propostaRepository,ConsultaNovoCartao consultaNovoCartao,CartaoRepository cartaoRepository) {
        this.propostaRepository =propostaRepository;
        this.consultaNovoCartao = consultaNovoCartao;
        this.cartaoRepository =cartaoRepository;
    }

    @Autowired
    private VencimentoRepository vencimentoRepository;


    @Scheduled(cron = "5/10 * * * * * ")
    public void associar(){
        List<Proposta> propostaList = propostaRepository.findCartaoNull();

        /*CartaoDTOResponse cartaoDTOResponse = consultaNovoCartao.consultarCartao( new CartaoDTORequest(proposta));*/
        if(propostaList.size() == 0){
            System.out.println("Todos possuem cartões...");
        }else {
            for (Proposta p : propostaList) {
                CartaoDTOResponse cartaoDTOResponse = consultaNovoCartao.consultarCartao(new CartaoDTORequest(p));
                if(cartaoDTOResponse != null) {
                    System.out.println("to string::" + cartaoDTOResponse.toString());
                    Cartao cartao = cartaoDTOResponse.converter(propostaRepository);
                    System.out.println("Salvando cartão...");
                    cartaoRepository.save(cartao);
                }
                System.out.println("Sem cartões ainda...");
            }
        }

    }
}
