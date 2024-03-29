package com.example.intial;


import com.example.dao.Department;
import com.example.dao.Employee;
import com.example.dao.Organization;
import com.example.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class SampleDataSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataSet.class);
    private static final String INDEX_NAME = "sample";
    private static final String INDEX_TYPE = "employee";

    @Autowired
    EmployeeRepository repository;

    @Autowired
    ReactiveElasticsearchClient client;

    @PostConstruct
    public void init() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            bulk(i);
            Thread.sleep(10000);
        }
    }

    public void bulk(int ii) {
        try {
            Mono<Boolean> exists = client.indices().existsIndex(request -> request.indices(INDEX_NAME));
            exists.subscribe(ex -> {
                if (!ex) {
                    LOGGER.info("Creating index: {}", INDEX_NAME);
                    client.indices().createIndex(request -> request.index(INDEX_NAME));
                }
            });
            List<Employee> employees = employees();
            Flux<Employee> s = repository.saveAll(employees);
            s.subscribe(empl -> LOGGER.info("ADD: {}", empl), e -> LOGGER.info("Error: {}", e.getMessage()));
            LOGGER.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            LOGGER.error("Error bulk index", e);
        }
    }

    private List<Employee> employees() {
        List<Employee> employees = new ArrayList<>();
        int id = repository.count().block().intValue();
        LOGGER.info("Starting from id: {}", id);
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            Employee employee = new Employee();
            employee.setName("JohnSmith" + r.nextInt(1000000));
            employee.setAge(r.nextInt(100));
            employee.setPosition("Developer");
            int departmentId = r.nextInt(5000);
            employee.setDepartment(new Department((long) departmentId, "TestD" + departmentId));
            int organizationId = departmentId % 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;
    }

}