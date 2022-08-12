package com.se.demo.trans;


import com.se.demo.trans.repository.AccountRepo;
import com.se.demo.trans.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.se.demo.trans.entity")
@SpringBootApplication
public class TransactionWalletApplication implements CommandLineRunner {

@Autowired
AccountRepo accountRepo;

@Autowired
WalletRepo walletRepo;

    public static void main(String[] args) {
        SpringApplication.run(TransactionWalletApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
