package com.se.sample.springboottetinggradle.utils;

class MathUtils {
    public int add(Integer x, Integer y) {
        return x + y;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public long squareLong(long l) {
        return l*l;
    }


}
