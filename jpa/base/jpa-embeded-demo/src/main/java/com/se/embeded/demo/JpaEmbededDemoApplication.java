package com.se.embeded.demo;

import com.se.embeded.demo.entity.Account;
import com.se.embeded.demo.repo.AccountRepo;
import com.se.embeded.demo.repo.FooBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("com.se.embeded.demo.entity")
@SpringBootApplication
public class JpaEmbededDemoApplication  implements CommandLineRunner {

    @Autowired
    private AccountRepo accountRepo;
//
    @Autowired
    FooBarRepository fooBarRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaEmbededDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

      //  testAccount();

        //@Covert annotation not honored when used with @IdClass composite key
//
//        try {
//            FooBar fooBar = new FooBar();
//
//            PrimaryKey primaryKey = new PrimaryKey();
//            primaryKey.setFooBarType(FooBarType.Bar);
//            primaryKey.setId("STRING_ID");
//
//            fooBar.setCompositeKey(primaryKey);
//            fooBar.setValue(10000);
//
//            fooBarRepository.save(fooBar);
//
//            int bb = 10;
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
    }

    private void testAccount() {
        Account account = new Account();
        try {

            account.setAccountNumber("ACCOUNT_NUMBER");
            account.setAccountType("ACCOUNT_TYPE");
            account.setBalance(10000);

            accountRepo.save(account);
            int da =10 ;
        }
        catch (Exception ex){
            int a =10;
        }
    }
}
