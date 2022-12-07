package com.example.springbootmultimongo.repository.primary;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PrimaryRepository extends MongoRepository<PrimaryModel, String> {
}