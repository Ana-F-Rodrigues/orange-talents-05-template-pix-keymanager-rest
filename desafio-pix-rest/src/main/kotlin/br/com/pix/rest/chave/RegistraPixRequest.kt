package br.com.pix.rest.chave

import br.com.pix.rest.RegistraChavePixRequest
import br.com.pix.rest.valid.ChavePixValida
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ChavePixValida
data class RegistraPixRequest(
    @field:NotNull @field:JsonFormat(shape = JsonFormat.Shape.STRING) val tipoChave: TipoChave?,
    @field:NotNull @field:JsonFormat(shape = JsonFormat.Shape.STRING) val tipoConta: TipoConta?,
    @field:Size(max = 77) val chave: String?
){

    fun paraGrpcRequest(idTitular: String) : RegistraChavePixRequest {
        return RegistraChavePixRequest.newBuilder()
            .setIdTitular(idTitular)
            .setTipoChave(br.com.pix.rest.TipoChave.valueOf(tipoChave?.name ?: "TIPO_CHAVE_DESCONHECIDO"))
            .setTipoConta(br.com.pix.rest.TipoConta.valueOf(tipoConta?.name ?: "TIPO_CONTA_DESCONHECIDO"))
            .setValorChave(chave ?: "")
            .build()
    }

}



enum class TipoChave{
    CPF{
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            if (!chave.matches("[0-9]+".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    TELEFONE_CELULAR {
        override fun valida(chave: String?): Boolean {
            if(chave.isNullOrBlank()) return false
            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }
    },
    EMAIL {
        override fun valida(chave: String?): Boolean {
            if(chave.isNullOrBlank()){
                return false
            }
            return EmailValidator().run {
                initialize(null)
                isValid(chave, null)
            }
        }
    },
    ALEATORIA {
        override fun valida(chave: String?): Boolean {
            return chave.isNullOrBlank()
        }
    };

    abstract fun valida(chave: String?) : Boolean
}


enum class TipoConta{
    CONTA_CORRENTE,
    CONTA_POUPANCA;
}