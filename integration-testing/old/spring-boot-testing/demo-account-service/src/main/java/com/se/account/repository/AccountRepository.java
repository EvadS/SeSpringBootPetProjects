package com.se.account.repository;

import com.se.account.model.Account;
import com.se.account.model.AccountNumber;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends
        PagingAndSortingRepository<Account, Long> {

    List<Account> findAccountsByUsername(@Param("username") String username);

    Account findAccountByAccountNumber(
            @Param("accountNumber") String accountNumber);
}
