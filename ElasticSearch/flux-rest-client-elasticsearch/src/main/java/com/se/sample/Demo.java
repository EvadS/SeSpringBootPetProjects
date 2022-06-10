package com.se.sample;

public class Demo {
    public static void main(String[] args) {
        strsrt("abcabc", "abd");
    }

    /***
     *
     * @param source abcabc
     * @param test abd
     * @return
     */
    static int strsrt(String source, String  test){

     // not found -1
        // found -> index
        boolean found = false;
        for(int i = 0; i < source.length(); i++ ){
            int item = 0;
            for(int j =0; j < test.length(); j++){
                if(String.valueOf(source.charAt(i)).equals(String.valueOf(source.charAt(j))))
                    item++;
            }
            if(item == test.length()){
                found = true;
                break;
            }


        }

        return found ? 1: 0;
    }
}
