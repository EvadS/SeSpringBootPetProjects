package org.se.sample;

/**
 * В методе main конвертируй строку string в тип double с помощью статического метода parseDouble класса Double.
 * Затем округли полученное значение, используя статический метод round класса Math.
 * Результат выведи на экран.
 *
 * В методе main должен вызываться метод parseDouble класса Double.
 * В методе main должен вызываться метод round класса Math.
 * В консоли должно выводиться число 13.
 */
public class Task1104 {
    public static void main(String[] args) {
        String string = "12.84";

        double v = Double.parseDouble(string);
        long round = Math.round(v);

        System.out.printf("%s", round);
    }
}
