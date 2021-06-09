package br.com.proposta.PropostaOrange.carteira;

import br.com.proposta.PropostaOrange.bloqueio.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository  extends JpaRepository<Carteira, Long> {
}
