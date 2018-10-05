package com.paulpop.livetweets.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import livetweets.Author;
import livetweets.Tweet;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.connect.json.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Kafka consumer used to fetch tweets from Kafka in JSON format and store them in memory.
 */
public class TweetsConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TweetsConsumer.class);
    private static final String BROKER_LIST = "localhost:9092";
    private static final String GROUP_ID = "grpc";
    private static final String TOPIC = "twitter";

    // Data structure where we store all tweets in memory
    private final List<Tweet> tweets = new CopyOnWriteArrayList<>();

    public void run() {
        KafkaConsumer<JsonNode, JsonNode> consumer = new KafkaConsumer<>(createProperties());
        consumer.subscribe(Collections.singletonList(TOPIC));
        try {
            while (true) {
                ConsumerRecords<JsonNode, JsonNode> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<JsonNode, JsonNode> record : records) {
                    // Get the payload and add the tweet to the synchronised list
                    JsonNode node = record.value().get("payload");

                    tweets.add(Tweet.newBuilder()
                            .setId(node.get("Id").asLong())
                            .setBody(node.get("Text").asText())
                            .setDate(Instant
                                    .ofEpochMilli(node.get("CreatedAt").asLong())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime()
                                    .toString())
                            .setAuthor(Author.newBuilder()
                                    .setId(node.get("User").get("Id").asLong())
                                    .setName(node.get("User").get("Name").asText())
                                    .setScreenName(node.get("User").get("ScreenName").asText())
                                    .setAvatarUrl(node.get("User").get("BiggerProfileImageURL").asText())
                                    .setFollowers(node.get("User").get("FollowersCount").asInt())
                                    .build())
                            .setId(node.get("Id").asLong())
                            .build());
                }
            }
        } catch (Exception ex) {
            LOG.error("Failed to process message", ex);
        } finally {
            consumer.close();
        }
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    private Properties createProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }
}
