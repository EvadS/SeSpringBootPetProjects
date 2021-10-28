package org.example;

import junit.framework.TestCase;
import org.junit.Test;

public class PercentageUtilTest extends TestCase {
    @Test
    public void test1(){
        int[] arr = new int[]{3,3,3};
        for(int i = 0;i < arr.length; i++){
            System.out.println("value:"+ PercentageUtil.getPercentValue(arr,9,i,2));
        }

    }
}