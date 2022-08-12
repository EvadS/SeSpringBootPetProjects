package com.se.additional;

import com.se.additional.entity.Book;
import com.se.additional.entity.TransactionUUID;
import com.se.additional.repo.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.orm.jpa.JpaSystemException;

@SpringBootApplication
public class TestSpringApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestSpringApplication.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    com.se.additional.repo.TransactionRepo transactionRepo;

    public static void main(String[] args) {
      SpringApplication.run(TestSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        bookRepository.deleteAllInBatch();
       createBook();

        createTransaction();
    }

    private void createTransaction() {

        try {
            TransactionUUID transactionUUID = new TransactionUUID();
            transactionUUID.setMessage("Message ");

            transactionRepo.save(transactionUUID);
        }
        catch (JpaSystemException ex){
            // java.sql.SQLException: Field 'transactionid' doesn't have a default value
            logger.error("Can't store transaction entity. ");
            ex.printStackTrace();
        }
        int a =0;
    }

    private void createBook() {
        Book book = new Book();
        book.setName("Book name");

        bookRepository.save(book);

        book = new Book();
        book.setName("Book name3");

        bookRepository.save(book);

       bookRepository.findAll()
                .stream().forEach(x ->  logger.info(x.toString()));
    }
}
