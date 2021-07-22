package br.com.pix.rest.valid

import br.com.pix.rest.chave.RegistraPixRequest
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext

class ChavePixValidaValidator : ConstraintValidator<ChavePixValida, RegistraPixRequest> {
    override fun isValid(
        value: RegistraPixRequest,
        annotationMetadata: AnnotationValue<ChavePixValida>,
        context: ConstraintValidatorContext
    ): Boolean {

        if(value.tipoChave == null) return false

        return value.tipoChave.valida(value.chave)
    }
}