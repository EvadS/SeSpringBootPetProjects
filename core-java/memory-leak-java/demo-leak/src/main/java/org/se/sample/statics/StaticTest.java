package org.se.sample.statics;

import java.util.ArrayList;
import java.util.List;
import  java.util.logging.*;

public class StaticTest {

    private final static Logger logger = Logger.getLogger(StaticTest.class.getName());

    /**
     * static fields have a life that usually matches the entire lifetime of the running application
     */
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }
        sleep_5_sec_todebug();
        logger.info("Debug Point 2");
    }

    public static void main(String[] args) {
        logger.info("Debug Point 1");
        new StaticTest().populateList();
        // the heap memory isn't yet garbage collected
        logger.info("Debug Point 3");


        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    private void sleep_5_sec_todebug() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}