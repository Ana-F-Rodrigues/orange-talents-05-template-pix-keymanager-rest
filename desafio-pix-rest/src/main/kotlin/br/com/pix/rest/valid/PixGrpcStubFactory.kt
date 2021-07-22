package br.com.pix.rest.valid

import br.com.pix.rest.KeyManagerConsultaServiceGrpc
import br.com.pix.rest.KeyManagerExcluiServiceGrpc
import br.com.pix.rest.KeyManagerListaServiceGrpc
import br.com.pix.rest.KeyManagerRegistraServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Factory
class PixGrpcStubFactory {

    @Singleton
    fun registraPixStub(@GrpcChannel("pix") channel: ManagedChannel)
            : KeyManagerRegistraServiceGrpc.KeyManagerRegistraServiceBlockingStub{
        return KeyManagerRegistraServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun excluiPixStub(@GrpcChannel("pix") channel: ManagedChannel)
            : KeyManagerExcluiServiceGrpc.KeyManagerExcluiServiceBlockingStub{
        return KeyManagerExcluiServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun consultaPixStub(@GrpcChannel("pix") channel: ManagedChannel)
            : KeyManagerConsultaServiceGrpc.KeyManagerConsultaServiceBlockingStub{
        return KeyManagerConsultaServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun listaPixStub(@GrpcChannel("pix") channel: ManagedChannel)
            : KeyManagerListaServiceGrpc.KeyManagerListaServiceBlockingStub{
        return KeyManagerListaServiceGrpc.newBlockingStub(channel)
    }

}