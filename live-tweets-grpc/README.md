# gRPC service + client

This is a simple gRPC service (with a client) that returns a list of streams on the console.

## Setup

```bash
mvn clean install
```

## Run

### Server

You can run the server by running the `GrpcServer` main class. This will start the server on `localhost:8080`.

### Client

You 

### GHZ

[ghz](https://github.com/bojand/ghz) is a tool used to load test the service given a proto file and some parameters.

We will be running a benchmark of this server from our local using the following command:

```bash
ghz -proto src/main/proto/LiveTweets.proto -call livetweets.LiveTweetsService.Get -d '{}' \
    -n 100000 -c 1000 -insecure -O html -o benchmark.html 0.0.0.0:8080
```

which means gRPC client in ghz will invoke the gRPC server 100k times using 1000 concurrent users. 
The data will be exported to [benchmark.html](benchmark.html/).