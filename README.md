# No REST for the wicked

These are a set of demos to support the 'No REST for the wicked' talk. The slides can be found on the [GitHub page](https://paul-pop.github.io/no-rest-for-the-wicked-demos).

## Structure

* [kafka-connect](kafka-connect/)
  * Kafka cluster with Kafka Connect used to pull tweets from the Twitter Stream API and write them to Kafka.
* [live-tweets-apollo-react](live-tweets-apollo-react/)
  * React web app that uses Apollo client to stream the GraphQL subscriptions to a UI.
* [live-tweets-graphql-api](live-tweets-graphql-api/)
  * GraphQL API that streams tweets from Kafka and exposes the data using GraphQL subscriptions.
* [live-tweets-grpc](live-tweets-grpc/)
  * gRPC server that streams tweets from Kafka and stores the data in-memory.
  * gRPC client that interacts with the gRPC service to display tweets.
  * [ghz](https://github.com/bojand/ghz) script that load tests the gRPC server.

## Installation instructions

These can be found in each individual folder but from here you can have an overview on everything that needs to be run.

1. First off, you want to navigate to the [kafka-connect](kafka-connect/) folder and execute the following:

```bash
docker-compose up
```
to spin up you Kafka/ZK/Connect cluster.

Then put in your Twitter Dev keys in `twitter-source.json` and install the plugin for Kafka Connect Twitter:

```bash
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" \
    -d @twitter-source.json \
    localhost:8003/api/kafka-connect-1/connectors
```

2. Secondly, you can either run the gRPC or the GraphQL demos.

    2.1 gRPC

    Navigate to the [live-tweets-grpc](live-tweets-grpc/) folder and run the server main class and the client main class.
    Kafka should be running for this to start correctly.

    2.2 GraphQL

    You need to run the GraphQL server by going into [live-tweets-graphql-api](live-tweets-graphql-api/) and executing:

    ```bash
    yarn start
    ```
    
    then start the front-end from [live-tweets-apollo-react](live-tweets-apollo-react/) by executing:
    
    ```bash
    yarn start
    ```
