package org.se.sample;

/**
 * Требования:
 * •	Поле modelName класса Car должно быть не статическим.
 * •	Должны быть исправлены геттер и сеттер для поля modelName.
 */
public class Car {
    private String modelName;
    private int speed;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static void main(String[] args) {
        Double d1 = Double.valueOf(-100);
        Double d2 = Double.valueOf(100);

        System.out.println( d1 + "/0 =" + d1/0);
        System.out.println( " "+d2 + "/0 =" + d2/0);



        Character.isLetter('r');


    }
}
