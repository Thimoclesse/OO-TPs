package com.example.myServiceClient.service;

import com.example.lib.HelloReply;
import com.example.lib.HelloRequest;
import com.example.lib.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    Logger logger = LoggerFactory.getLogger(MyService.class);

    @GrpcClient("myService")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub; // Client pour le service principal

    public String sayHello() {
        logger.info("Hello world");
        HelloReply reply = myServiceStub.sayHello(HelloRequest.newBuilder().build());
        return reply.getMessage();
    }
}
