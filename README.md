# No REST for the wicked

These are a set of demos to support the 'No REST for the wicked' talk. The slides can be found on the [GitHub page](https://paul-pop.github.io/no-rest-for-the-wicked-demos).

## Structure

* [kafka-connect](kafka-connect/)
  * Kafka cluster with Kafka Connect used to pull tweets from Twitter and write them to Kafka.
* [live-tweets-apollo-react](live-tweets-apollo-react/)
  * React web app that uses Apollo client to stream the GraphQL subscriptions.
* [live-tweets-graphql-api](live-tweets-graphql-api/)
  * GraphQL API that streams data directly from Kafka using GraphQL subscriptions.
* [tweets-grpc-web](tweets-grpc-web/)
  * gRPC client that interacts with the gRPC service to display tweets.
* [tweets-grpc-service](tweets-grpc-service/)
  * gRPC server that fetches tweets from Kafka.

