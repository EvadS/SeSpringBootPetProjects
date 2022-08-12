package com.se.enums.mapping;


import com.se.enums.mapping.enums.PaymentMethod;
import com.se.enums.mapping.model.UserBalance;
import com.se.enums.mapping.model.UserBalanceId;
import com.se.enums.mapping.repository.TransactionRepo;
import com.se.enums.mapping.repository.UserBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication implements CommandLineRunner {

    @Autowired
    private  TransactionRepo transactionRepo;

    @Autowired
    private UserBalanceRepo userBalanceRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        transactionRepo.deleteAllInBatch();
        userBalanceRepo.deleteAllInBatch();


        UserBalanceId userBalanceId = new UserBalanceId();
        userBalanceId.setPaymentMethod(PaymentMethod.XLM);
        userBalanceId.setUser_id("user_id");

        UserBalance userBalance = new UserBalance();
        userBalance.setAmount( BigInteger.valueOf(1000l));
        userBalance.setUserBalanceId(userBalanceId);


        userBalanceRepo.save(userBalance);

        List<UserBalance> userBalanceList = userBalanceRepo.findAll();
        System.out.println("==================================");
        userBalanceList.stream().forEach(System.out::println);
    }
}
