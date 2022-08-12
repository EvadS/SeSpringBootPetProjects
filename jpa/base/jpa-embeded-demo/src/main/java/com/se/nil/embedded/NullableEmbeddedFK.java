package com.se.nil.embedded;

import com.se.nil.embedded.entity.work.Transaction;
import com.se.nil.embedded.entity.work.WalletId;
import com.se.nil.embedded.entity.ID;
import com.se.nil.embedded.entity.Office;
import com.se.nil.embedded.entity.User;
import com.se.nil.embedded.entity.work.Wallet;
import com.se.nil.embedded.repository.OfficeRepository;
import com.se.nil.embedded.repository.TransactionRepository;
import com.se.nil.embedded.repository.UserRepository;
import com.se.nil.embedded.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;


@EntityScan("com.se.nil.embedded.entity")
@SpringBootApplication
public class NullableEmbeddedFK implements CommandLineRunner {

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepo walletRepo;

    @Autowired
    TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(NullableEmbeddedFK.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        walletRepo.deleteAllInBatch();
        transactionRepository.deleteAllInBatch();


        Wallet wallet = new Wallet();
        WalletId walletID = new WalletId( 1000l,"USD");
        wallet.setWalletId(walletID);
        wallet.setBalance(1000l);

        walletRepo.save(wallet);


        Wallet wallet2 = new Wallet();
        WalletId walletID2 = new WalletId( 2000l,"USD");
        wallet2.setWalletId(walletID2);
        wallet2.setBalance(1000l);

        walletRepo.save(wallet2);

        Transaction transaction = new Transaction();
        transaction.setWalletAmount(100);
        transaction.setWalletFrom(wallet);
       /// transaction.setWalletTo(wallet2);

        try {
            transactionRepository.save(transaction);
            System.out.print("Current transactions ===");
            transactionRepository.findAll().stream().forEach(x-> System.out.print(x));
        }

        catch (Exception ex){
            int a =10;
            ex.printStackTrace();
        }
        testOfficeUser();

    }

    private void testOfficeUser() {
        userRepository.deleteAllInBatch();
        officeRepository.deleteAllInBatch();

        Office office_104 = new Office();
        ID officeId = new ID(1l, 1000l);
        office_104.setId(officeId);
        office_104.setDescription("Some description office 1");

        officeRepository.save(office_104);


        Office officeFrom = new Office();
        ID office_105 = new ID(20l, 2000l);
        officeFrom.setId(office_105);
        officeFrom.setDescription("Some description office 2");

        officeRepository.save(officeFrom);

        User user = new User();
        ID userId = new ID(20l, 2000l);
        user.setId(userId);
        user.setComment("Comment 1.");

        user.setOfficeTo(office_104);
        user.setOfficeFrom(officeFrom);
        userRepository.save(user);

        user = new User();
        userId = new ID(30l, 3000l);
        user.setId(userId);
        user.setComment("Comment 2.");
        user.setOfficeTo(officeFrom);
        user.setOfficeFrom(office_104);
        userRepository.save(user);

        User  ghost = new User();
        ID ghostId = new ID(40l, 4000l);
        ghost.setId(ghostId);
        ghost.setComment("Casper here.");

        userRepository.save(ghost);
        List<User> userList = userRepository.findAll();

        userList.stream().forEach(System.out::println);
    }

}