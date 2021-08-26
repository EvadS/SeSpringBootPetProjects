package org.se.sample.maps;

import java.util.HashMap;
import java.util.Map;

public class PersonDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("waiting.... ");
        // 20 sec to find and open in jconsole

        System.out.println("started .... ");

        int size =100000;
        Map<Person, Integer> map = new HashMap<>(100000);
        for(int i=0; i<size; i++) {
            Thread.sleep(1l);
            map.put(new Person("jon"), 1);


            if(i > 1000 && i% 1000 == 0 ){
                System.out.println("i: "+ i/1000 + "000 of " +size );
            }
        }

        System.out.println("waiting before close .... ");
        Thread.sleep(10000l);
    }
}
