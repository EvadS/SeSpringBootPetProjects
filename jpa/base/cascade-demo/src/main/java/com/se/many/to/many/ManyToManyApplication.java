package com.se.many.to.many;

import com.se.many.to.many.entity.Post;
import com.se.many.to.many.entity.Student;
import com.se.many.to.many.entity.Tag;
import com.se.many.to.many.entity.University;
import com.se.many.to.many.entity.def.Searcher;
import com.se.many.to.many.entity.def.SkillsScore;
import com.se.many.to.many.repo.PostRepository;
import com.se.many.to.many.repo.StudentRepository;
import com.se.many.to.many.repo.TagRepository;
import com.se.many.to.many.repo.UniversityRepository;

import com.se.many.to.many.repo.def.SearcherRepository;
import com.se.many.to.many.repo.def.SkillsScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private SkillsScoreRepository skillsScoreRepo;

    @Autowired
    private SearcherRepository searcherRepo;

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testStudentUniversity();
      //  testPostsTags();
      //  testSearcher();


    }

    private void testSearcher() {

        searcherRepo.deleteAllInBatch();
        skillsScoreRepo.deleteAllInBatch();

        Searcher searcher = new Searcher();
        searcher.setName("searcher 1 ");
        searcherRepo.save(searcher);

        Searcher searcher2 = new Searcher();
        searcher2.setName("searcher 2 ");
        searcherRepo.save(searcher2);

        Searcher searcher3 = new Searcher();
        searcher3.setName("searcher 3.");
        searcherRepo.save(searcher3);

        SkillsScore skillsScore = new SkillsScore();
        skillsScore.setName("skill 1");
    //    skillsScore.setSearchers(Arrays.asList(searcher));
      ///  skillsScore.setSearchers(Arrays.asList(searcher2));

      //  skillsScore.getSearchers().add(searcher3);
        skillsScoreRepo.save(skillsScore);

        SkillsScore skillsScore2 = new SkillsScore();
        skillsScore2.setName("skill 2");
        skillsScoreRepo.save(skillsScore2);


        skillsScore2.addSearcher(searcher);
        skillsScoreRepo.save(skillsScore2);

        List<Searcher> searchers = skillsScore2.getSearchers();

        for(Searcher s : searchers){
            skillsScore2.removeBook(s);
            skillsScoreRepo.save(skillsScore2);
        }

        skillsScore2.addSearcher(searcher);
        skillsScore2.addSearcher(searcher2);
        skillsScoreRepo.save(skillsScore2);


        // edit

//
//        searcher2.setName("searcher 21.");
//        searcher2.setSkillsScore();
//        searcherRepo.save(searcher2);









    }

    private void testPostsTags() {
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
        int a =10;
        System.out.println("*** Current tags ****************");
        List<Tag> tagList = tagRepository.findAll();
        tagList.stream().forEach(System.out::println);


        System.out.println("*** Deleted tag  " + tag1.getId() );


        // нихера не удалется
        postRepository.delete(post);

        System.out.println("*** Current tags ****************");
        tagList = tagRepository.findAll();
        tagList.stream().forEach(System.out::println);


        // Post является базовой    @JoinTable
        // поэтому для удаления связей с Tag в промежуточной таблице
        post.getTags().clear();
        postRepository.delete(post);

        // Дубль 2


        int aa =10;
    }

    private void testStudentUniversity() {
        // Clean up database tables
        universityRepository.deleteAllInBatch();
        studentRepository.deleteAllInBatch();


        University university = new University();
        university.setName("KPI");
        universityRepository.save(university);

        Student student = new Student();
        student.setName("Student name.");
        student.setUniversities(Arrays.asList(university));
        studentRepository.save(student);

        Student student2 = new Student();
        student2.setName("Student name 2.");
        student2.setUniversities(Arrays.asList(university));
        studentRepository.save(student2);

        System.out.println("=== Current university");
        universityRepository.findAll().stream().forEach(System.out::println);

        System.out.println("=== Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);

        studentRepository.delete(student2);
        System.out.println("=== removed (2) Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);

        student2 = new Student();
        student2.setName("Student name 3.");
        student2.setUniversities(Arrays.asList(university));
        studentRepository.save(student2);

        System.out.println("=== added (3)Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);
    }
}
