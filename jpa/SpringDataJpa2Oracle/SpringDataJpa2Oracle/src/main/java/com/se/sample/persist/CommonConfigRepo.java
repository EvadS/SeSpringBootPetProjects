package com.se.sample.persist;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface CommonConfigRepo extends JpaRepository<CommonConfig, String> {
}
