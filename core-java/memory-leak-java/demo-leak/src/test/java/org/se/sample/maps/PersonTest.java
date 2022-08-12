package org.se.sample.maps;

import org.junit.Assert;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;


public class PersonTest {


    @Test
    public void givenMap_whenEqualsAndHashCodeNotOverridden_thenMemoryLeak() {
        Map<Person, Integer> map = new HashMap<>(100000);
        for(int i=0; i<100000; i++) {
            map.put(new Person("jon"), 1);
        }

        // without equals and hash code
       // Assert.assertFalse(map.size() == 1);


    }
}