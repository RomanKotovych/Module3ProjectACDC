package com.javarush.kotovych.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PreDestroy;

public class RedisConfig {
    private static final RedisClient redisClient;
    private static final StatefulRedisConnection<String, String> connection;
    private static final ApplicationProperties properties = NanoSpring.find(ApplicationProperties.class);


    static {
        redisClient = RedisClient.create(properties.getProperty(ApplicationProperties.REDIS_URL));
        connection = redisClient.connect();
    }

    public static RedisCommands<String, String> getRedisCommands() {
        return connection.sync();
    }

    @PreDestroy
    public static void shutdown() {
        connection.close();
        redisClient.shutdown();
    }
}
