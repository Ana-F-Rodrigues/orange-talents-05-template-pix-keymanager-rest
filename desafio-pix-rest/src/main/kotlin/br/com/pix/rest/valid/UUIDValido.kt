package br.com.pix.rest.valid

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [UUIDValidoValidator::class])
annotation class UUIDValido(
    val message: String = "nao é um UUID valido",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
