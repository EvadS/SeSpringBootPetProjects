package org.example;


/**
 * In the process of calculating the percentage of statistical tables, because of the accuracy problem,
 * 99.97% or 100.1% of the calculated results are not equal to 100%, the original scheme generally uses
 * the last value equal to 1 minus the percentage to complete the calculation, but there are disadvantages
 * in this way. Abnormal data often appear. After searching, there are the following methods.
 */
public class PercentageUtil {
    /**
     * array
     * @param arr array
     * @param sum total
     * @param idx Indexes
     * @param precision accuracy
     * @return
     */
    public static double getPercentValue(int[] arr,double sum,int idx,int precision){
        if((arr.length-1) < idx){
            return 0;
        }
        //Summation
        if(sum <= 0){
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
        }
        //The power 2 of 10 is 100 for accuracy.
        double digits = Math.pow(10,precision);
        //Scale up 100
        double[] votesPerQuota = new double[arr.length];
        for(int i = 0; i < arr.length; i++){
            double val = arr[i] / sum * digits * 100;
            votesPerQuota[i] = val;
        }
        //Total, the proportion of expansion means that the total should be expanded
        double targetSeats = digits * 100;
        //Value down again to form an array
        double[] seats = new double[arr.length];
        for(int i = 0; i < votesPerQuota.length; i++){
            seats[i] = Math.floor(votesPerQuota[i]);
        }
        //Calculate the total again to determine whether it is the same as the total quantity. If it is the same, the proportion will be 100%
        double currentSum = 0;
        for (int i = 0; i < seats.length; i++) {
            currentSum += seats[i];
        }
        //Array of remainder part: the original array subtracts the array of downward value to get the array of remainder part
        double[] remainder = new double[arr.length];
        for(int i = 0; i < seats.length; i++){
            remainder[i] = votesPerQuota[i] - seats[i];
        }
        while(currentSum < targetSeats){
            double max = 0;
            int maxId = 0;
            int len = 0;
            for(int i = 0;i < remainder.length;++i){
                if(remainder[i] > max){
                    max = remainder[i];
                    maxId = i;
                }
            }
            //Add 1 to the maximum item balance
            ++seats[maxId];
            //If the maximum balance has been increased by 1, the balance can be judged next time.
            remainder[maxId] = 0;
            //Add 1 to the total. In order to determine whether the total is the same, jump out of the cycle.
            ++currentSum;
        }
        // At this time, the total number of seats will account for 100%
        return seats[idx] / digits;
    }

}
