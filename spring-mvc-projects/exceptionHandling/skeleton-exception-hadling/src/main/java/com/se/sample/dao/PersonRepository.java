package com.se.sample.dao;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
import com.se.sample.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}