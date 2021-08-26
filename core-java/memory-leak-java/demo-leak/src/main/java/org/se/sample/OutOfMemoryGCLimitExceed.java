package org.se.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OutOfMemoryGCLimitExceed {
    public static void addRandomDataToMap() throws InterruptedException {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();

        int size =  100000;
        for(int i = 0; i <size; i++){
            dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
            Thread.sleep(1l);

            if(i > 1000 && i% 1000 == 0 ){
                System.out.println("i: "+ i/1000 + "000 of " + size);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OutOfMemoryGCLimitExceed.addRandomDataToMap();
    }
}