package org.se.sample;

import java.util.*;

public class OutOfMemoryList {


    public static void addRandomDataToList() throws InterruptedException {
        List<String> dataList = new ArrayList<>();
        Random r = new Random();

        int size =1000000;
        for (int i = 0; i < size; i++) {
            Thread.sleep(1l);
            dataList.add(String.valueOf(r.nextInt()));

            if(i > 1000 && i% 1000 == 0 ){
                System.out.println("i: "+ i/1000 + "000 of " + size);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OutOfMemoryList.addRandomDataToList();
        System.out.println("finished: ....." );
        Thread.sleep(10000);

    }

}