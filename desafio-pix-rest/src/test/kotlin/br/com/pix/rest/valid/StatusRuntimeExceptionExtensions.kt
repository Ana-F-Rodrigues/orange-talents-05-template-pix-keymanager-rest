package br.com.pix.rest.valid

import com.google.rpc.BadRequest
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto

fun StatusRuntimeException.getViolacao(index: Int): BadRequest.FieldViolation? {
    val statusProto = StatusProto.fromThrowable(this)
    val badRequest = statusProto?.detailsList?.get(0)?.unpack(BadRequest::class.java)
    return badRequest?.getFieldViolations(index);
}

fun StatusRuntimeException.getViolacaons(): List<Pair<String, String>> {
    val statusProto = StatusProto.fromThrowable(this)
    val badRequest = statusProto
        ?.detailsList!!.get(0)
        .unpack(BadRequest::class.java)
    return badRequest.fieldViolationsList.map {
        it.field to it.description
    }
}