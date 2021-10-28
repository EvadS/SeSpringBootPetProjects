package org.example;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Get Percentage of arraylist values from sum of arraylist
 */
public class PercentageDemo {

    public static void work( double[] arr ) {

        // The issue is about Autoboxing. Java automatically converts int to Integer automatically,
        // but it does not convert int[] to Integer[]
        // List<Integer> list = new ArrayList(Arrays.asList(arr));
        List<Double> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        // then
        Double mn = list
                .stream()
                .mapToDouble(v -> v)
                .min().orElseThrow(NoSuchElementException::new);

        Double mx = Collections.max(list);

        double[] perc_arr = new double[arr.length];

        Double sum = list.stream()
                .reduce(0.0, (a, b) -> a + b);

        for (int i = 0; i < arr.length; i++) {
//            perc_arr[i] = (arr[i] - mn) / (mx - mn) * 100;

            double  percentage = Math.round((arr[i] * 100.0) / sum);

            System.out.println("item ["+ i+"]: " + percentage );
            perc_arr[i] = percentage;
        }

        for (int i = 0; i < perc_arr.length; i++) {
            System.out.println("i["+i+"]: "+ perc_arr[i]);
        }

        System.out.println("Check sum ");

        List<Double> resList = Arrays.stream(perc_arr).boxed().collect(Collectors.toList());

        Double sumOfPercentage = resList.stream()
                .reduce(0.0, (a, b) -> a + b);

        System.out.println("sum of percentage :"+  sumOfPercentage);
        System.out.println("=============================");
    }

    public static void main(String[] args) {
        double[] arr = { 40.20, 18.80, 45.00};
        work(arr);
    }
}
