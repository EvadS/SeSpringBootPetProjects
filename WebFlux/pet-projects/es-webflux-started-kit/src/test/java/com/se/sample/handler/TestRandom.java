package com.se.sample.handler;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {
        int trueCount = 0;
        int falseCount = 0;
        int size = 100;
        int fieldSize = 20;

        for (int i = 0; i < size; i++) {
            boolean b = RandomBool(fieldSize);

            if (b) {
                trueCount++;
            } else {
                falseCount++;
            }
        }

        System.out.println("true  count: " + trueCount);
        System.out.println("false count: " + falseCount);

        Faker faker = new Faker();

        String format = String.format("%.8g", faker.random().nextDouble() * 180.0D - 90.0D);

        System.out.println("lantitude"+ format);
    }

    public static boolean RandomBool(double count) {
        Random random = new Random();
        double rndPercent = count / 100 ;
    //    boolean b = Math.random() < rndPercent;

        boolean b1  = random.nextDouble() < rndPercent;


        //10%
        //(random.nextInt(10) < 2) ? true : false;

        return b1;
    }
}
