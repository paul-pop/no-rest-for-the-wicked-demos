# Kafka Connect for Twitter

This contains a [Compose file](docker-compose.yml) used to create a Kafka/ZK Confluent cluster with a single broker.

It also contains:
- Kafka Connect
- Kafka Connect UI

## Setup

To run the cluster use:

```bash
docker-compose up
```

Then you can access:
* Kafka broker - localhost:9092
* Zookeper - localhost:2181
* Kafka Connect - localhost:8083
* Kafka Connect UI - http://localhost:8003

## Kafka Connect

Kafka Connect uses a Twitter connector (https://github.com/jcustenborder/kafka-connect-twitter) to stream tweets 
and dump them into Kafka.

The JARs of the Twitter connector can be found in the [plugin](plugin/) folder. These will be placed in the plugins 
directory so Kafka Connect can pick it up.

Once the Kafka stack is started and you have populated `twitter-source.json` with the relevant values, you will have to 
create the connector by running the following cURL command:

```bash
curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" \
    -d @twitter-source.json \
    localhost:8003/api/kafka-connect-1/connectors
```
