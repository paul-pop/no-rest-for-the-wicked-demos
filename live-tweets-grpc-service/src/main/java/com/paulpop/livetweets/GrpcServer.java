package com.paulpop.livetweets;

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
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new LiveTweetsService())
                .build();

        server.start();
        LOG.info("Server started on port " + PORT);

        server.awaitTermination();
    }

}
