package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {

        test2();

        test1();
    }

    private static void test1() {
        final String regex = "\\s+(NAME)\\s+\\:\\s+([A-Z0-9 ]+)\\s+(ISO CODE)\\s+\\:\\s+([A-Z0-9 ]+)";
        final String string = " NAME     : ISRAEL                          ISO CODE : IL";
        final String strin2 = " NAME     : ISRAEL                          ISO CODE : IL";


        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }


    public  static void test2(){

        final String regex = "\\s+(NAME)\\s+\\:\\s+([A-Z0-9 ]+)\\s+(ISO CODE)\\s+\\:\\s+([A-Z0-9 ]+)";
        final String string = " NAME     : ISRAEL                          ISO CODE : IL";

        final Pattern pattern = Pattern.compile(regex);
       // final Matcher matcher = pattern.matcher(string);

        Matcher flight = pattern.matcher(string);
        flight.find();

        System.out.println("NAME: " + flight.group("name"));

        int a =0;
    }
}