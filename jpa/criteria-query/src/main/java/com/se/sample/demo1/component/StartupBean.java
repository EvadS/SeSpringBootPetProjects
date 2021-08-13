package com.se.sample.demo1.component;

import com.se.sample.demo1.domain.MyBook;
import com.se.sample.demo1.domain.spec.BookSpec;
import com.se.sample.demo1.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StartupBean  implements CommandLineRunner {

    @Autowired
    MyBookRepository myBookRepository;

    @Override
    public void run(String... args) throws Exception {

        initWhenEmpty();

        Specification<MyBook> bookSpec = BookSpec.hasOwnerName(3l);
        List productsList = myBookRepository.findAll(bookSpec);



    }

    private void initWhenEmpty() {

        if(myBookRepository.count()<1){
            MyBook book = new MyBook( "title 1",  "author 1");
            MyBook book2 = new MyBook("title 12", "author 2");
            MyBook book3 = new MyBook("title 13", "author 3");
            MyBook book4 = new MyBook("title 14", "author 4");
            MyBook book5 = new MyBook("title 15", "author 5");
            MyBook book6 = new MyBook("title 16", "author 6");
            MyBook book7 = new MyBook("title 17", "author 7");

            myBookRepository.saveAll(Arrays.asList(book,book2,book3,book4,book5,book6,book7));
        }

    }
}
