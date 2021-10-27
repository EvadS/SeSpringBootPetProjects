package com.se.semple.repository;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
import com.se.semple.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
