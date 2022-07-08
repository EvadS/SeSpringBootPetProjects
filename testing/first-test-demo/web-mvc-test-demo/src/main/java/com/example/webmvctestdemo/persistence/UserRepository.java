package com.example.webmvctestdemo.persistence;

import com.example.webmvctestdemo.persistence.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByName(String name);

    @Query("select u from UserEntity u where u.name = :name")
    UserEntity findByNameCustomQuery(@Param("name") String name);
}
