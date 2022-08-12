package com.se.nil.embedded.repository;

import com.se.nil.embedded.entity.work.Wallet;
import com.se.nil.embedded.entity.work.WalletId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, WalletId> {
}
