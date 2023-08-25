package org.example.review.engine.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public StatefulRedisConnection<String, String> redisConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        redisClient.setDefaultTimeout(Duration.ofMillis(200));
        return redisClient.connect();
    }
}
