const {GraphQLServer} = require('graphql-yoga');
const {KafkaPubSub} = require('graphql-kafka-subscriptions');

const pubsub = new KafkaPubSub({
    topic: process.env.KAFKA_TOPIC,
    host: 'localhost',
    port: '9092'
});

const typeDefs = './src/schema.graphql';
const resolvers = {
    Query: {
        info: () => 'Please follow @paulicipop on Twitter'
    },
    Subscription: {
        tweets: {
            resolve: (data) => {
                const payload = data.payload;
                const tweet = {
                    id: payload.Id,
                    body: payload.Text,
                    date: new Date(payload.CreatedAt),
                    author: {
                        id: payload.User.Id,
                        name: payload.User.Name,
                        screenName: payload.User.ScreenName,
                        profileImageURL: payload.User.BiggerProfileImageURL,
                        followers: payload.User.FollowersCount
                    }
                };

                console.log(JSON.stringify(tweet));
                return tweet;
            },
            subscribe: () => pubsub.asyncIterator(process.env.KAFKA_TOPIC)
        }
    }
};

const server = new GraphQLServer({
    typeDefs,
    resolvers
});

server.start({tracing: true}, () => console.log(`Server is running on http://localhost:4000`));
