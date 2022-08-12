package com.se.embeded.demo.repo;

import com.se.embeded.demo.model.FooBar;
import com.se.embeded.demo.model.PrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooBarRepository  extends JpaRepository<FooBar, PrimaryKey> {

}