# Live tweets GraphQL API

This API will stream tweets directly from Kafka into GraphQL subscriptions. 

## Setup

```bash
yarn install
```

**Note**: If this doesn't work, please run the following command (based on https://github.com/Blizzard/node-rdkafka/issues/373#issuecomment-395876157):

```bash
CFLAGS=-I/usr/local/opt/openssl/include LDFLAGS=-L/usr/local/opt/openssl/lib yarn install
```

## Run

```bash
yarn start
```

then navigate to http://localhost:4000 to explore the GraphQL playground.
