package br.com.proposta.PropostaOrange.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query(value = "SELECT LAST_INSERT_ID() FROM proposta as p", nativeQuery = true)
    Long getLastId();
    Optional<Proposta> findByDocumento(String documento);

    @Query("SELECT count(p.id) FROM Proposta as p where p.documento = :documento")
    Integer findPropostaDocumento(String documento);
}
