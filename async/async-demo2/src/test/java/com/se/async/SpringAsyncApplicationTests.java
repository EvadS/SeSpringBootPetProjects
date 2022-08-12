package com.se.async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringAsyncApplicationTests {

    @Autowired
    private AsyncComponent asyncAnnotationExample;


    @Test
    void contextLoads() {

        int a =0;
    }

}
