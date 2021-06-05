package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
