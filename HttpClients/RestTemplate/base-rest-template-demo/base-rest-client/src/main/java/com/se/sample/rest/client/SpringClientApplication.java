package com.se.sample.rest.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;

@SpringBootApplication
public class SpringClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringClientApplication.class, args);
    }

    @Bean
    CommandLineRunner startup(RestTemplate restTemplate) {
        return args -> {
//            final ResponseEntity<Post> entity = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/1", HttpMethod.GET, null, Post.class);
//            System.out.println(entity.getBody());
        };
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Post {
        @Min(2) // change to '1' and constraint violation will disappear
        private Long id;
        private Long userId;
        private String title;
        private String body;

        @Override
        public String toString() {
            return String.format("Post{id=%d, userId=%d, title='%s', body='%s'}", id, userId, title, body);
        }
    }


}