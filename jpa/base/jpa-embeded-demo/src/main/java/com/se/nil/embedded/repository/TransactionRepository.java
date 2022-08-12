package com.se.nil.embedded.repository;

import com.se.nil.embedded.entity.work.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TransactionRepository  extends JpaRepository<Transaction, Long> {
}
