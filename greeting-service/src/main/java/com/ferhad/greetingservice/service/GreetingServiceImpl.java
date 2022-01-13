package com.ferhad.greetingservice.service;

import com.ferhad.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();

        GreetingResponse response = GreetingResponse.newBuilder()
                .setMessage("Hello, " + firstName + " " + lastName)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
