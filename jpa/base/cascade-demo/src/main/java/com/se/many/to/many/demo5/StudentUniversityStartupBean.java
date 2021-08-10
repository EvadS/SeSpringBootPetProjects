package com.se.many.to.many.demo5;

import com.se.many.to.many.demo5.entity.Student;
import com.se.many.to.many.demo5.entity.University;
import com.se.many.to.many.demo5.repo.StudentRepository;
import com.se.many.to.many.demo5.repo.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class StudentUniversityStartupBean implements CommandLineRunner {


    private static final Logger logger = LoggerFactory.getLogger(StudentUniversityStartupBean.class);

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
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

        logger.info("=== Current university");
        universityRepository.findAll()
                .stream()
                .forEach(i -> logger.info(i.toString()));

        logger.info("=== Current students ===");
        studentRepository.findAll()
                .stream()
                .forEach(i -> logger.info(i.toString()));

        studentRepository.delete(student2);
        logger.info("=== removed (2) Current students ");
        studentRepository.findAll()
                .stream()
                .forEach(i -> logger.info(i.toString()));

        student2 = new Student();
        student2.setName("Student name 3.");
        student2.setUniversities(Arrays.asList(university));
        studentRepository.save(student2);

        logger.info("=== added (3) Current students ");
        studentRepository.findAll()
                .stream()
                .forEach(i -> logger.info(i.toString()));
    }
}
