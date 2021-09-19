package com.example;

import com.example.dao.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableReactiveElasticsearchRepositories
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

//    @Bean
//    @ConditionalOnProperty("initial-import.enabled")
//    public SampleDataSet dataSet() {
//        return new SampleDataSet();
//    }

    @Autowired
    private EmployeeService employeeService;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            Mono<Page<Employee>> allUsersPaged =
                    employeeService.findAllUsersPaged();

            int a=0;


            Page<Employee> block = allUsersPaged.block();

            block.stream().forEach(System.out::println);

        };
    }
}