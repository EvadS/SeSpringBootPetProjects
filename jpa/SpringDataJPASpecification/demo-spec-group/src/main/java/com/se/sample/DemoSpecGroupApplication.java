package com.se.sample;

import com.se.sample.model.AlarmData;
import com.se.sample.model.AlarmMsg;
import com.se.sample.repository.AlarmDataRepository;
import com.se.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class DemoSpecGroupApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpecGroupApplication.class, args);
    }

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AlarmDataRepository alarmDataRepository;

    @Override
    public void run(String... args) throws Exception {


    }
}
