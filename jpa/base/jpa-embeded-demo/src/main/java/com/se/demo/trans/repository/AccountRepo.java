package com.se.demo.trans.repository;

import com.se.demo.trans.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
