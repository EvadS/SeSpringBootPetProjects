package org.se.sample.statics;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * the time lime of static is application  time -life
 */
public class NotStaticTest {

    private final static Logger logger = Logger.getLogger(NotStaticTest.class.getName());

    public  List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }

        logger.info("Debug Point 2");
    }


    public static void main(String[] args) {
        logger.info("Debug Point 1");
        new NotStaticTest().populateList();
        // the heap memory isn't yet garbage collected
        sleep_5_sec_todebug();
        logger.info("Debug Point 3");
    }

    private static void sleep_5_sec_todebug() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}