package br.com.proposta.PropostaOrange.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);
}
