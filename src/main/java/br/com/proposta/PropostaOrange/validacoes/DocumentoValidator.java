package br.com.proposta.PropostaOrange.validacoes;

import org.springframework.util.Assert;


import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DocumentoValidator implements ConstraintValidator<ValidarDocumento, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    public void initialize(UniqueValue parametros) {
        domainAttribute = parametros.fieldName();
        klass = parametros.domainClass();
    }

    @Override
    public boolean isValid(Object valor, ConstraintValidatorContext contexto) {
        if(!CPF.isCPF(valor.toString()) && !CNPJ.isCNPJ(valor.toString())){
            return false;
        }
       return true;
    }
}