package com.se.one.to.many;

import com.se.one.to.many.entity.Child;
import com.se.one.to.many.entity.Comment;
import com.se.one.to.many.entity.Parent;
import com.se.one.to.many.entity.PostItem;
import com.se.one.to.many.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testParentChild();

        System.out.println("\t*********** second demo ***********  ");
        testCommentPost();
    }

    private void testParentChild() {
        // TODO: один post мног комментов
        childRepository.deleteAllInBatch();
        parentRepository.deleteAllInBatch();

        // убрал Cascade
        Parent parent = new Parent();
        parent.setName("Parent 1 ");
        parentRepository.save(parent);

        Child child = new Child();
        child.setChildName("child name 1.");
      //  parent.addChild(child);

        child.setParent(parent);
        childRepository.save(child);

        Child child2 = new Child();
        child2.setChildName("child name 1.");
        parent.addChild(child2);
        childRepository.save(child2);


        //-----------------------------------------------------
        Parent parent2 = new Parent();
        parent2.setName("Parent 1 ");
        parentRepository.save(parent2);

        Child child21 = new Child();
        child21.setChildName("child name 12.");
        child21.setParent(parent2);
        childRepository.save(child21);

        Child child22 = new Child();
        child22.setChildName("child name 21.");
        child22.setParent(parent2);
        childRepository.save(child22);

        printCurrentChildParent();

        System.out.println("***Removed  parent   : " + parent.getId());


//        parent.removeChild(child);
//        parent.removeChild(child2);

    //    parent.getAttachments().clear();

        child.setParent(null);
        childRepository.save(child);

        child2.setParent(null);
        childRepository.save(child2);

        parentRepository.delete(parent);

        printCurrentChildParent();


        int a = 10;
    }

    private void printCurrentChildParent() {
        System.out.println("*** List of parent : ");
        List<Parent> parentList = parentRepository.findAll();
        parentList.stream().forEach(System.out::println);

        System.out.println("*** List of child : ");
        List<Child> childList = childRepository.findAll();
        childList.stream().forEach(System.out::println);
    }

    private void testCommentPost() {
        // TODO: один post мног комментов
        commentRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();

        PostItem post = new PostItem();
        post.setTitle("first post title");
        postRepository.save(post);

        System.out.println("***** Current posts ");
        postRepository.findAll().stream().forEach(System.out::println);

        Comment comment = new Comment();
        comment.setText("Comment text");

        //-----------------------------
        postRepository.findById(post.getId()).map(item -> {
            comment.setPost(item);
            return commentRepository.save(comment);
        });

        Comment comment2 = new Comment();
        comment2.setText("Comment text 2");

        //-----------------------------
        postRepository.findById(post.getId()).map(item -> {
            comment2.setPost(item);
            return commentRepository.save(comment2);
        });

        Comment comment3 = new Comment();
        comment3.setText("Comment 3.");
        comment3.setPost(post);
        commentRepository.save(comment3);

        System.out.println("***** Current comments  -------- ");
        commentRepository.findAll().stream().forEach(System.out::println);
        //------------------------------

    }
}
