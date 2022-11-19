package service

import io.grpc.stub.StreamObserver
import io.quarkus.example.GreeterGrpc
import io.quarkus.example.HelloReply
import io.quarkus.example.HelloRequest
import javax.inject.Singleton


@Singleton
class HelloWorldService : GreeterGrpc.GreeterImplBase() {
    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloReply>) {
        val name = request.name
        val message = "Hello $name"
        responseObserver.onNext(HelloReply.newBuilder().setMessage(message).build())
        responseObserver.onCompleted()
    }
}