package com.se.many.to.many.demo;

import com.se.many.to.many.demo.entity.Post;
import com.se.many.to.many.demo.entity.Tag;
import com.se.many.to.many.demo.repo.PostRepository;
import com.se.many.to.many.demo.repo.TagRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * callcoder demo
 */


public class PostTagStartupBean implements CommandLineRunner {

    private final Logger logger = LoggerFactory.logger(PostTagStartupBean.class);

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args)   {

        // Cleanup the tables
        postRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();

        // Create a Post
        Post post = new Post("Hibernate Many to Many Example with Spring Boot");

        // Create two tags
        Tag tag1 = new Tag("Spring Boot");
        Tag tag2 = new Tag("Hibernate");


        // Add tag references in the post
        post.getTags().add(tag1);
        post.getTags().add(tag2);

        // Add post reference in the tags
        tag1.getPosts().add(post);
        tag2.getPosts().add(post);

        postRepository.save(post);

        //смотрим в бд

        logger.info("*** Current tags ****>>");
        List<Tag> tagList = tagRepository.findAll();
        tagList.stream().forEach(i -> logger.info(i));
        logger.info("<< *** Current tags ****");

        logger.info("*** Deleted tag  " + tag1.getId());

        // нихера не удалется
        // ибо удаляется только связь
        postRepository.delete(post);

        logger.info("*** Current tags ****>>");
        tagList = tagRepository.findAll();
        tagList.stream().forEach(i -> logger.info(i));
        logger.info("<< *** Current tags ****");

        // Post является базовой    @JoinTable
        // поэтому для удаления связей с Tag в промежуточной таблице
        post.getTags().clear();
        postRepository.delete(post);

        logger.info("*** Current tags ****>>");
        tagList = tagRepository.findAll();
        tagList.stream().forEach(i -> logger.info(i));

        logger.info("<< *** Current tags ****");


        logger.info("*** remove child tag from post ****>>");
        logger.info("before remove tag1 from post ");
        postRepository.findAll().stream().forEach(i-> logger.info(i.toString()));

        post.removeChild(tag1);
        postRepository.save(post);

        logger.info("the tag1 removed");
        logger.info("*** Current tags ****>>");
        tagList = tagRepository.findAll();
        tagList.stream().forEach(i -> logger.info(i));

        logger.info("<< *** Current tags ****");

        logger.info("Application Started !!");
    }
}
