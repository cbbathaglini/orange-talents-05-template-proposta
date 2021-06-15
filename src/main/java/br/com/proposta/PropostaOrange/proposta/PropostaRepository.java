package br.com.proposta.PropostaOrange.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query(value = "SELECT LAST_INSERT_ID() ", nativeQuery = true)
    Long getLastId();

    Optional<Proposta> findByDocumento(String documento);

    @Query("SELECT count(p.id) FROM Proposta as p where p.documento = :documento")
    Integer findPropostaDocumento(String documento);

    @Query("SELECT p FROM Proposta p where  p.id not in " +
            " (select c.proposta.id from Cartao c )  and p.statusProposta = 'ELEGIVEL' ")
    List<Proposta> findCartaoNull();
}
