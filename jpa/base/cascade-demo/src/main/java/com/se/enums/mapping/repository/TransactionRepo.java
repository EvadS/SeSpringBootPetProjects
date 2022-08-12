package com.se.enums.mapping.repository;

import com.se.enums.mapping.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo  extends JpaRepository<Transaction, String> {

}
