package com.javarush.kotovych.config;

import com.javarush.kotovych.constants.ConnectionConstants;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PreDestroy;

public class RedisConfig {
    private static final RedisClient redisClient;
    private static final StatefulRedisConnection<String, String> connection;

    static {
        redisClient = RedisClient.create(ConnectionConstants.REDIS_URL);
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
