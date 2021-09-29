package com.se.sample.repository;


import com.se.sample.entity.User;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveElasticsearchRepository<User, Long> {
    Mono<UserDetails> findByEmail(final String email);

    Mono<User> findUserByEmail(final String email);


}