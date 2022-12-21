package com.example.json;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        Assert.notNull(redisTemplate, "redisTemplate is required");
        this.redisTemplate = redisTemplate;
    }

    @Cacheable(value = "user", key = "#userRedis.getId()")
    public UserRedis saveUser(UserRedis userRedis) {
        log.trace("user{} is saved", userRedis);
        return userRedis;
    }
}