package br.com.proposta.PropostaOrange.cartao;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    @Query("SELECT c FROM Cartao c WHERE c.id = :numCartao ")
    Cartao findByNumCartao(String numCartao);
}
