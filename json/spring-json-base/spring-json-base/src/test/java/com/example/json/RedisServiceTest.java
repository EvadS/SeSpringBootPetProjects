package com.example.json;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void saveUser() {
        UserRedis japhy = UserRedis.builder().id(3L).name("japhy").age(10L).build();
        UserRedis userRedis = redisService.saveUser(japhy);
        Assertions.assertThat(userRedis).isNotNull();
        Assertions.assertThat(userRedis.getId()).isEqualTo(3L);
        Assertions.assertThat(userRedis.getName()).isEqualTo("japhy");
        Assertions.assertThat(userRedis.getAge()).isEqualTo(10L);
    }
}