package com.se.one.to.one.repository;


import com.se.one.to.one.entity.RegistrationNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationNumberRepository extends JpaRepository<RegistrationNumber, Long> {

}