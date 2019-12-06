package miguel.highLow;

import java.util.Arrays;

public class highLow {
    public static String hiLo(String s) {
        String[] numbers = s.split(" ");
        int[] myNumbers = new int[numbers.length];

        int i = 0;
        for (String number : numbers) {
            myNumbers[i] = Integer.parseInt(number);
            i++;
        }
        Arrays.sort(myNumbers);
        return myNumbers[myNumbers.length - 1] + " " + myNumbers[0];
    }

    public static void main(String[] args) {
        System.out.println(hiLo("1 2 3 5 4"));
        System.out.println(hiLo("4 5 29 54 4 0 -214 542 -64 1 -3 6 -6"));
        System.out.println(hiLo("1 -1"));
        System.out.println(hiLo("1 1"));
        System.out.println(hiLo("-1 -1"));
        System.out.println(hiLo("1 -1 0"));
        System.out.println(hiLo("1 1 0"));
        System.out.println(hiLo("-1 -1 0"));
        System.out.println(hiLo("42"));
    }
}