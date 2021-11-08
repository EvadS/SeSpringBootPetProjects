package com.se.sample;

import com.se.sample.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoH2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoH2Application.class, args);
    }

    @Autowired
    CoommentRepository commentRepository;
    @Override
    public void run(String... args) throws Exception {

        commentRepository.deleteAll();

        Comment comment1 = new Comment(2000, true,"content");
        Comment comment2 = new Comment(2000, true,"content2");
        Comment comment3 = new Comment(2001, true,"content3");
        Comment comment4 = new Comment(2001, true,"content4");

        List<Comment> comments = Arrays.asList(comment1, comment2, comment3, comment4);
        commentRepository.saveAll(comments);

        GenericSpecifications<Comment> genericSpecifications = new GenericSpecifications<Comment>();

        Specification<Comment> specification = Specification.where(null);

        List<String> names  = Arrays.asList("year");
        specification = genericSpecifications.groupBy(specification, names);
        List<Comment> all = commentRepository.findAll(specification);


        all.stream().forEach(System.out::println);

        commentRepository.deleteAll();
    }
}
