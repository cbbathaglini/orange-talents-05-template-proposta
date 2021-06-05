package br.com.proposta.PropostaOrange.vencimento;

import br.com.proposta.PropostaOrange.proposta.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VencimentoRepository  extends JpaRepository<Vencimento, Long> {
}
