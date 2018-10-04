package com.paulpop.livetweets;

import com.paulpop.livetweets.proto.LiveTweetsServiceGrpc;
import com.paulpop.livetweets.proto.TweetsList;
import com.paulpop.livetweets.proto.TweetsRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

        LiveTweetsServiceGrpc.LiveTweetsServiceBlockingStub stub = LiveTweetsServiceGrpc.newBlockingStub(channel);

        TweetsList response = stub.get(TweetsRequest.newBuilder().build());

        LOG.info("Response" + response.toString());

        channel.shutdown();
    }
}
