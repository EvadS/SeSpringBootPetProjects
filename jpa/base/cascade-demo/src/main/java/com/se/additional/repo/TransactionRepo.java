package com.se.additional.repo;

import com.se.additional.entity.TransactionUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepo extends JpaRepository<TransactionUUID, String> {

}