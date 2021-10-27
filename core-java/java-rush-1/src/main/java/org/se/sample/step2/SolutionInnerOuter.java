package org.se.sample.step2;


/**
 * Требования:
 * В методе main класса Solution должен быть создан объект класса Inner.
 * В методе main класса Solution должен быть создан объект класса Nested.
 * Класс Outer изменять нельзя.
 */
public class SolutionInnerOuter {
    public static void main(String[] args) {
        //напишите тут ваш код
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();

        Outer.Nested nested = new Outer.Nested();

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.out.println("Windows detected!");
        }
        SolutionInnerOuter s = new SolutionInnerOuter();

        System.out.println("Test package");

        Cat cat = new Cat("name");

        System.out.println(cat.name);

    }

    public static class Cat {
        String name;

        public Cat(String name) {
            this.name = name;
        }
    }
}
