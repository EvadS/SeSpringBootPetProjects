package com.se.sample.demo.repository;

import com.se.sample.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Evgeniy Skiba on 01.04.21
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}