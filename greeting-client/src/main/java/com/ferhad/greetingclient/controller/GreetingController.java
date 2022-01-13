package com.ferhad.greetingclient.controller;

import com.ferhad.grpc.GreetingRequest;
import com.ferhad.grpc.GreetingResponse;
import com.ferhad.grpc.GreetingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GrpcClient("greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceBlockingStub;

    @GetMapping
    public ResponseEntity<String> greeting(@RequestParam(defaultValue = "") String firstName,
                                           @RequestParam(defaultValue = "") String lastName) {
        GreetingResponse response = greetingServiceBlockingStub.greeting(GreetingRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build());

        return ResponseEntity
                .ok(
                        response.getMessage()
                );
    }
}
