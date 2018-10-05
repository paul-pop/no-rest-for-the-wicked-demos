package com.paulpop.livetweets;

import com.paulpop.livetweets.kafka.TweetsConsumer;
import com.paulpop.livetweets.service.LiveTweetsService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * gRPC basic server configuration
 */
public class GrpcServer {

    private static final Logger LOG = LoggerFactory.getLogger(GrpcServer.class);
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        final TweetsConsumer consumer = new TweetsConsumer();
        final Server server = ServerBuilder
                .forPort(PORT)
                .addService(new LiveTweetsService(consumer))
                .build()
                .start();

        LOG.info(String.format("Server started on port %s. Streaming tweets...", PORT));

        consumer.run();
        server.awaitTermination();
    }

}
