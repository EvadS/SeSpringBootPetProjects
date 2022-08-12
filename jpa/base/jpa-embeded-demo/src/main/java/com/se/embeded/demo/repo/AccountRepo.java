package com.se.embeded.demo.repo;

import com.se.embeded.demo.entity.Account;
import com.se.embeded.demo.entity.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, AccountId> {

}