package com.javarush.kotovych.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;
    private final ApplicationProperties properties;

    public RedisConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    public void init() {
        redisClient = RedisClient.create(properties.getProperty(ApplicationProperties.REDIS_URL));
        connection = redisClient.connect();
    }

    public static RedisCommands<String, String> getRedisCommands() {
        return connection.sync();
    }

    @PreDestroy
    public void shutdown() {
        connection.close();
        redisClient.shutdown();
    }
}