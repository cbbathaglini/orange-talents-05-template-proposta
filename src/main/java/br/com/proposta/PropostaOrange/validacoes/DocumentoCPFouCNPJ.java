package br.com.proposta.PropostaOrange.validacoes;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface DocumentoCPFouCNPJ {

    String message() default "o campo deve ser formatado como cpf ou cnpj";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}