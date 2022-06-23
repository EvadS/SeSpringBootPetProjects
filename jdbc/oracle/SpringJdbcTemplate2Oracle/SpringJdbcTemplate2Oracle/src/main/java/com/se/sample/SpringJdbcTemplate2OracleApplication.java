package com.se.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class SpringJdbcTemplate2OracleApplication {

    Logger logger = LoggerFactory.getLogger(SpringJdbcTemplate2OracleApplication.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcTemplate2OracleApplication.class, args);
    }


    @Bean
    ApplicationRunner init() {
        return args -> {
            logger.info("*******************************");

            String sql = "SELECT * FROM Student";

            List<Student> studentsList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));

            studentsList.forEach(i -> logger.info(i.toString()));
        };
    }

}
