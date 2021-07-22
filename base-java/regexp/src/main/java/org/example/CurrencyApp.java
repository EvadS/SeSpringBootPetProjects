package org.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyApp {
    public static void main(String[] args) {


        String lines = " NAME     : ISRAEL                          ISO CODE : IL";
        Pattern pattern2 = Pattern.compile("\\s+(NAME)\\s+\\:\\s+([A-Z0-9]+)\\s+(ISO CODE)\\s+\\:\\s+([A-Z0-9]+)");
        String[] matches = pattern2.split(lines);

        int b;

        //////////////
        String input = "CURRENCY : SHEKEL, SHEQALIM                CODE     : ILS\n";
        final String myPatternStr = "\\s+CURRENCY\\s+\\:\\s+";

       // String phpPattern = "'/\\s+(CURRENCY)\\s+\\:\\s+([A-Z\\:\\/\\;\\,\\.\\=0-9 ]+)\\s+(CODE)\\s+\\:\\s+([A-Z0-9 ]+)/'";

        String phpPattern = "CURRENCY(.*?)CODE";
        Pattern myPattern = Pattern.compile(phpPattern);

        String[] res = myPattern.split(input);

        Arrays.asList(res).forEach(re -> System.out.print(re + " "));

        int a = 0;
        //-----------------------------------------------
        phoneNumberMatcherTest();
        System.out.println("=========================");
        patternmain();
// -----------------------------
        System.out.println("=========================");
        String input3 = "Hello world! Hello Java!";
        Pattern pattern = Pattern.compile("hello");
        //, который в качестве параметра принимает строку, где надо проводить поиск,
        Matcher matcher = pattern.matcher(input3);
        //-------------------------

        separatedMatches();
        //------------------------
        splitRowDemo();
        System.out.println("=========================");


    }

    private static void splitRowDemo() {
        Pattern pattern;
        System.out.println("=========================");
        pattern = Pattern.compile(":|;");
        String[] animals = pattern.split("cat:dog;bird:cow");

        Arrays.asList(animals).forEach(animal -> System.out.print(animal + " "));
    }

    /**
     * найти в строке все вхождения слова Java
     */
    private static void separatedMatches() {
        String input = "Hello Java! Hello JavaScript! JavaSE 8.";
        //Выражение (\\w*) означает, что после "Java" в совпадении может
        // находиться любое количество алфавитно-цифровых символов
        Pattern pattern = Pattern.compile("Java(\\w*)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find())
            System.out.println(matcher.group());

        int a = 0;
    }

    public static void patternmain() {

        String input = "Hello";

        Pattern pattern = Pattern.compile("Hello");
        //Для простого поиска соответствий
        Matcher matcher = pattern.matcher(input);
        boolean found = matcher.matches();
        if (found)
            System.out.println("Найдено");
        else
            System.out.println("Не найдено");
    }

    /**
     * matches() принимает регулярное выражение и возвращает true
     */
    private static void phoneNumberMatcherTest() {
        String input = "+12343454556";
        //в начале может идти знак плюса, но также он может отсутствовать.
        boolean result = input.matches("(\\+*)\\d{11}");
        if (result) {
            System.out.println("It is a phone number");
        } else {
            System.out.println("It is not a phone number!");
        }
    }
}
