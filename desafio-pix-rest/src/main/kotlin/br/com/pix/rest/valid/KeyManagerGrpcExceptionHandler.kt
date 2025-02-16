//package br.com.pix.rest.valid
//import com.google.rpc.BadRequest
//import io.grpc.Status
//import io.grpc.StatusRuntimeException
//import io.micronaut.context.annotation.Requires
//import io.micronaut.http.HttpRequest
//import io.micronaut.http.HttpResponse
//import io.micronaut.http.HttpStatus
//import io.micronaut.http.annotation.Produces
//import java.util.stream.Collectors
//import javax.inject.Singleton
//
//@Produces
//@Singleton
//@Requires(classes = [StatusRuntimeException::class, io.micronaut.http.server.exceptions.ExceptionHandler::class])
//class KeyManagerGrpcExceptionHandler :
//    io.micronaut.http.server.exceptions.ExceptionHandler<StatusRuntimeException, HttpResponse<Any>> {
//
//    override fun handle(request: HttpRequest<*>?, e: StatusRuntimeException?): HttpResponse<Any> {
//        val status = io.grpc.protobuf.StatusProto.fromThrowable(e)
//        val codigo = e?.status?.code
//
//        when(codigo){
//            Status.Code.INVALID_ARGUMENT -> {
//                val details = if(status?.detailsList?.isEmpty() == true) BadRequest.getDefaultInstance() else status?.detailsList?.last()?.unpack(BadRequest::class.java)
//                val body = Erro(
//                    status = HttpStatus.BAD_REQUEST.code,
//                    titulo = "Dados invalidos",
//                    descricao = "Há parametros invalidos em sua requisicao. Por favor, corrija e envie novamente",
//                    propriedadesInvalidas = details?.fieldViolationsList?.stream()?.map {
//                        Erro.PropriedadeInvalida(
//                            propriedade = it.field,
//                            descricao = it.description
//                        )
//                    }?.collect(Collectors.toList())
//                )
//                return HttpResponse.badRequest(body)
//            }
//            Status.Code.FAILED_PRECONDITION -> return HttpResponse.unprocessableEntity()
//            Status.Code.ALREADY_EXISTS -> return HttpResponse.status(HttpStatus.CONFLICT)
//            Status.Code.NOT_FOUND -> return HttpResponse.notFound()
//        }
//        return HttpResponse.serverError();
//    }
//}