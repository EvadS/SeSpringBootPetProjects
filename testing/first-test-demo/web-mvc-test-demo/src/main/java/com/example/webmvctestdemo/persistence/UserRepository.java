package com.example.webmvctestdemo.persistence;

import com.example.webmvctestdemo.persistence.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
