package com.se.sample.repository;

import com.se.sample.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    Optional<Speaker> findById(long id);

    Optional<Speaker> findByName(String name);
}
