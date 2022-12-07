package com.example.springbootmultimongo.repository.secondary;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<SecondaryModel, String> {
}