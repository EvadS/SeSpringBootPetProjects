package com.se.regexp;

import java.util.regex.Pattern;

public class Application {

    public static void main(String[] args) {
        String value = "(123)1234-123";
        String exp = "^\\(\\d{3}\\)\\d{4}\\-\\d{3}$";
        final Pattern pattern = Pattern.compile(exp);
        if (testMath(value, pattern)) {
            System.out.print("TRUE");
        } else {
            System.out.print("FALSE");
        }
    }

    private static boolean testMath(String value, Pattern pattern) {
        if (pattern.matcher(value).matches()) {
            int a = 0;
            return true;
        }

        return false;
    }
}
