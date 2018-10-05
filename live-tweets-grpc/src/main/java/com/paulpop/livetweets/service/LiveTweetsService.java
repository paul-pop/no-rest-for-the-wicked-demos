package com.paulpop.livetweets.service;

import com.paulpop.livetweets.kafka.TweetsConsumer;
import io.grpc.stub.StreamObserver;
import livetweets.LiveTweetsServiceGrpc.LiveTweetsServiceImplBase;
import livetweets.Tweet;
import livetweets.TweetsRequest;
import livetweets.TweetsResponse;

import java.util.List;

/**
 * Implementation of the gRPC service based on {@link LiveTweetsServiceImplBase}
 */
public class LiveTweetsService extends LiveTweetsServiceImplBase {

    private final TweetsConsumer tweetsConsumer;

    public LiveTweetsService(final TweetsConsumer tweetsConsumer) {
        this.tweetsConsumer = tweetsConsumer;
    }

    @Override
    public void get(final TweetsRequest request,
                    final StreamObserver<TweetsResponse> responseObserver) {

        List<Tweet> tweets = tweetsConsumer.getTweets();
        TweetsResponse response = TweetsResponse.newBuilder()
                .addAllTweets(tweets)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
