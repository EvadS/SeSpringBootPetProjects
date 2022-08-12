package com.se.element.collection;


import com.se.element.collection.model.Address;
import com.se.element.collection.model.JobSearcher;
import com.se.element.collection.model.SearchersSkills;
import com.se.element.collection.model.User;
import com.se.element.collection.repository.JobSearcherRepository;
import com.se.element.collection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JpaElementCollectionDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSearcherRepository searcherRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaElementCollectionDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testUserAddress();
testSearcher();

    }

    private void testSearcher() {
        searcherRepository.deleteAll();

        SearchersSkills skills1  = new SearchersSkills("c++","2");
        SearchersSkills skills2  = new SearchersSkills("java","12");

        Set<SearchersSkills> searchersSkills = new HashSet<>();
        searchersSkills.add(skills1);
        searchersSkills.add(skills2);

        JobSearcher jobSearcher = new JobSearcher("name1", searchersSkills);
        searcherRepository.save(jobSearcher);

        System.out.println("--------------------------");
        searcherRepository.findAll().stream().forEach(System.out::println);

    }

    private void testUserAddress() {
        // Cleanup database tables.
        userRepository.deleteAll();

        // Insert a user with multiple phone numbers and addresses.
        Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("+91-9999999999");
        phoneNumbers.add("+91-9898989898");

        Set<Address> addresses = new HashSet<>();
        addresses.add(new Address("747", "Golf View Road", "Bangalore",
                "Karnataka", "India", "560008"));
        addresses.add(new Address("Plot No 44", "Electronic City", "Bangalore",
                "Karnataka", "India", "560001"));

        User user = new User("Rajeev Kumar Singh", "rajeev@callicoder.com",
                phoneNumbers, addresses);

        userRepository.save(user);
    }
}