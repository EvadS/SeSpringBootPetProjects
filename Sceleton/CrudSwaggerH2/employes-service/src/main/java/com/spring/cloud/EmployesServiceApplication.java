package com.spring.cloud;

import com.spring.cloud.model.Employee;
import com.spring.cloud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployesServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployesServiceApplication.class, args);
    }


    @Autowired
    private EmployeeRepository repo ;
    
    @Override
    public void run(String... args) throws Exception {
        if(repo.count()>0)
            return;

        for(int i =1; i <= 1000; i++) {
            Employee employee = new Employee();
            employee.setEmailId("email" + i + "@mail.com");
            employee.setFirstName("fname" + i);
            employee.setLastName("lname" + i);

            repo.save(employee);
        }


    }
}
