package telran.streams;

import java.text.ParseException;

record Range(int min, int max, int quantity) {
}

public class SportLotto {
    public static void main(String[] args) {

        Range range = null;
        try {
            range = getRange(args);
            printLottery(range);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //TODO
        //printing out sportLotto numbers
        //application with command line arguments
        //first argument minimal val
        //second argument maximal inclusive value
        //third argument amount of the random numbers
        //Example: java -jar sportLotto 1 49 7
        // 3, 10, 49, 1, 40, 6, 7
    }

    private static void printLottery(Range range) {
        int[] array = StreamsIntroductionMethods.getRandomArray(range.min(), range.max(), range.quantity());
        StreamsIntroductionMethods.displayShuffle(array);
    }

    private static Range getRange(String[] args) throws Exception {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough data. Must be minimum 3 element with numbers. Right now is " + args.length + " elements");
        }
        int min = getMin(args);
        int max = getMax(args);
        int count = getCount(args);
        return new Range(min, max, count);
    }

    private static int getCount(String[] args) throws Exception {
        int res = 0;
        if (args.length > 0) {
            try {
                res = Integer.parseInt(args[2]);
                if (res < 1){
                    throw new Exception("Count of numbers ");
                }
            } catch (NumberFormatException e) {
                throw new Exception("wrong type of quantity range lottery");
            }
        }
        return res;
    }

    private static int getMax(String[] args) throws Exception {
            try {
                int res = Integer.parseInt(args[1]);
                if (res < getMin(args)){
                    throw new Exception("max value must be more than minimum");
                }
                return res;
            } catch (NumberFormatException e) {
                throw new Exception("wrong type of maximum range lottery");
            }
    }

    private static int getMin(String[] args) throws Exception {
        try {
            int res = Integer.parseInt(args[0]);
            if (res < 1) {
                throw new Exception("min value must be more then 0");
            }
            return res;
        } catch (NumberFormatException e) {
            throw new Exception("wrong type of minimal range lottery");
        }

    }
}
