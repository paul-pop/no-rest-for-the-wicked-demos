package com.paulpop.livetweets.service;

import com.paulpop.livetweets.proto.Author;
import com.paulpop.livetweets.proto.LiveTweetsServiceGrpc.LiveTweetsServiceImplBase;
import com.paulpop.livetweets.proto.Tweet;
import com.paulpop.livetweets.proto.TweetsList;
import com.paulpop.livetweets.proto.TweetsRequest;
import io.grpc.stub.StreamObserver;

/**
 * Implementation of the gRPC service based on {@link LiveTweetsServiceImplBase}
 */
public class LiveTweetsService extends LiveTweetsServiceImplBase {

    public void get(TweetsRequest request, StreamObserver<TweetsList> responseObserver) {
        Author author = Author.newBuilder()
                .setId(1L)
                .setName("Paul Pop")
                .setScreenName("paulicipop")
                .setAvatarUrl("https://google.com")
                .setFollowers(100)
                .build();

        Tweet tweet = Tweet.newBuilder()
                .setId(1L)
                .setDate("01-01-1990")
                .setBody("Tweet")
                .setAuthor(author)
                .build();

        TweetsList response = TweetsList.newBuilder()
                .addTweets(tweet)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public StreamObserver<TweetsRequest> stream(StreamObserver<Tweet> responseObserver) {
        return null;
    }
}
