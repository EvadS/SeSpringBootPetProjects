package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base1 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Visit W3Schools!");
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
        System.out.println("=========================================");
        String input = "+12343454556";
        boolean result = input.matches("(\\+*)\\d{11}");
        if(result){
            System.out.println("It is a phone number");
        }
        else{
            System.out.println("It is not a phone number!");
        }
    }
}