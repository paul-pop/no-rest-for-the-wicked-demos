# No REST for the wicked

These are a set of demos to support the 'No REST for the wicked' talk. The slides can be found on the [GitHub page](https://paul-pop.github.io/no-rest-for-the-wicked-demos).

## Structure

* [kafka-connect](kafka-connect/)
  * Kafka cluster with Kafka Connect used to pull tweets from the Twitter Stream API and write them to Kafka.
* [live-tweets-apollo-react](live-tweets-apollo-react/)
  * React web app that uses Apollo client to stream the GraphQL subscriptions to a UI.
* [live-tweets-graphql-api](live-tweets-graphql-api/)
  * GraphQL API that streams tweets from Kafka and exposes the data using GraphQL subscriptions.
* [live-tweets-grpc-web](live-tweets-grpc-web/)
  * gRPC client that interacts with the gRPC service to display tweets.
* [live-tweets-grpc-service](live-tweets-grpc-service/)
  * gRPC server that streams tweets from Kafka and exposes the data using server-streaming.

