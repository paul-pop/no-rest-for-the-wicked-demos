package com.paulpop.livetweets;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import livetweets.LiveTweetsServiceGrpc;
import livetweets.TweetsRequest;
import livetweets.TweetsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client used to connect to the gRPC server and stream tweets
 */
public class GrpcClient {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcServer.class);
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", SERVER_PORT)
                .usePlaintext()
                .build();

        LiveTweetsServiceGrpc.LiveTweetsServiceBlockingStub stub =
                LiveTweetsServiceGrpc.newBlockingStub(channel);
        TweetsResponse response = stub.get(TweetsRequest.newBuilder().build());
        LOG.info("Received: " + response.getTweetsList());

        channel.shutdown();
    }
}
