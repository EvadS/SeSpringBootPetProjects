package com.se.one.to.one;

import com.se.one.to.one.entity.*;
import com.se.one.to.one.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@EnableJpaRepositories
@EntityScan("com.se.one.to.one.entity")
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    @Autowired
    private Environment environment;

    @Autowired
    PassportRepository passportRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    RegistrationNumberRepository numberRepository;

    @Autowired
    AutoRepository autoRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println("++ SERVER PORT: " +  Integer.parseInt(environment.getProperty("local.server.port")));

        // Clean up database tables
        personRepository.deleteAllInBatch();
        passportRepository.deleteAllInBatch();

        numberRepository.deleteAllInBatch();
        autoRepository.deleteAllInBatch();

        System.out.println("One-to-one cascade all demo");

        Passport passport = new Passport();
        passport.setNumber("Passport number");
        passport.setSeries("Passport series");

        // create parent
        Person person = new Person("Person Name");

        // Set parent reference(user) in child entity(userProfile)
        passport.setOwner(person);

        // Set child reference(userProfile) in parent entity(user)
        person.setPassport(passport);
        personRepository.save(person);

        System.out.println("---- Person results ");
        List<Person> personList = personRepository.findAll();
        personList.stream().forEach(System.out::println);

        System.out.println("Remove passport  ");
        passportRepository.delete(passport);

        System.out.println("---- Person results  ");
        personList = personRepository.findAll();
        personList.stream().forEach(System.out::println);

        // возвращаем(создаем) паспорт
        person.setPassport(passport);
        personRepository.save(person);

        System.out.println("---- Person results  ");
        personList = personRepository.findAll();
        personList.stream().forEach(System.out::println);

        // удаляем чела
        personRepository.delete(person);
        System.out.println("---- Person results  ");
        personList = personRepository.findAll();
        personList.stream().forEach(System.out::println);

        System.out.println("---- Pasports   results  ");
        List<Passport>  passportList = passportRepository.findAll();
        passportList.stream().forEach(System.out::println);

        System.out.println("=================================================================");
        System.out.println("==== Auto <---- << Registration number");



        Auto auto = new Auto();
        auto.setModel("TAZ");

        //***************************************************************
        RegistrationNumber regNumber = new RegistrationNumber();
        regNumber.setCode("BP0000BP");

        // Сначала дочернему присвоить родителя
        auto.setOwner(regNumber);

        // Родителю присвоить дочерний
        regNumber.setAuto(auto);

        numberRepository.save(regNumber);

        System.out.println("=== Current RegistrationNumbers: ");
        List<RegistrationNumber> registrationNumberList = numberRepository.findAll();
        registrationNumberList.stream().forEach(System.out::println);

        System.out.println("=== Current auto List: ");
        List<Auto>  autoList= autoRepository.findAll();
        autoList.stream().forEach(System.out::println);

        System.out.println("Remove auto "+ auto.getId());
        autoRepository.delete(auto);
        System.out.println("=================After auto ");

        System.out.println("==-= Current RegistrationNumbers: ");
        registrationNumberList = numberRepository.findAll();
        registrationNumberList.stream().forEach(System.out::println);

        System.out.println("==-= Current auto List: ");
        autoList= autoRepository.findAll();
        autoList.stream().forEach(System.out::println);


        System.out.println("Remove reg number ");
        numberRepository.delete(regNumber);

        System.out.println("After Removed reg. number ");

        System.out.println("=== Current RegistrationNumbers: ");
        registrationNumberList = numberRepository.findAll();
        registrationNumberList.stream().forEach(System.out::println);

        System.out.println("=== Current auto List: ");
         autoList= autoRepository.findAll();
        autoList.stream().forEach(System.out::println);



    }
}
