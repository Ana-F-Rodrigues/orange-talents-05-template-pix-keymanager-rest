package br.com.pix.rest.chave

import br.com.pix.rest.ResumoChavePix

data class ResumoPixResponse(
    val pixId: String,
    val idTitular: String,
    val tipoChave: String,
    val chave: String,
    val tipoConta: String,
    val criadaEm: String
){
    constructor(grpcResponse: ResumoChavePix): this(
        pixId = grpcResponse.pixId,
        idTitular = grpcResponse.idTitular,
        tipoChave = grpcResponse.tipoChave.name,
        chave = grpcResponse.valorChave,
        tipoConta = grpcResponse.tipoConta.name,
        criadaEm = grpcResponse.criadaEm.toString()
    )
}
