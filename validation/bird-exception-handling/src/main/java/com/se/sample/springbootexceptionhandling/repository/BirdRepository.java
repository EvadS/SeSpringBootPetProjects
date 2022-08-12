package com.se.sample.springbootexceptionhandling.repository;

import com.se.sample.springbootexceptionhandling.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {
}
