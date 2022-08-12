package com.sample.quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRipperApplication {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        while (true) {
            Thread.sleep(500);
            context.getBean(Quoter.class).sayQuotes();

        }

    }
}
