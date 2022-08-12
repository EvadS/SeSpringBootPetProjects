package com.se.demo.trans.repository;

import com.se.demo.trans.entity.Wallet;
import com.se.demo.trans.entity.WalletID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, WalletID> {
}
