package controller

import io.quarkus.example.Greeter
import io.quarkus.example.GreeterGrpc
import io.quarkus.example.HelloRequest
import io.quarkus.grpc.GrpcClient
import io.quarkus.grpc.GrpcService
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Path("/hello-resteasy")
class HelloWorldResource {
    @Inject
    @GrpcService("hello")
    lateinit var client: GreeterGrpc.GreeterBlockingStub

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String? {
        return "hello"
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@PathParam("name") name: String?): String? {
        return client?.sayHello(HelloRequest.newBuilder().setName(name).build()).toString()
    }
}