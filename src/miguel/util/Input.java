package miguel.util;

import java.util.Scanner;

public class Input {
    // Private property named scanner. When instance of this object is created,
    // scanner should be set to a new instance of the Scanner class.
    private Scanner scanner = new Scanner(System.in);

    public void inputCleaner() {
        scanner.nextLine();
    }

    public String getString() {
        return scanner.nextLine();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        return getString();
    }

    public boolean yesNo() {
        System.out.println("(y/n)");
        String input = getString();
        return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
    }

    public boolean yesNo(String prompt) {
        System.out.println(prompt);
        return yesNo();
    }

    public int getInt(int min, int max) {
        int userNumber = getInt();

        if (userNumber < min) {
            System.out.println("Number entered is below the minimum.");
            return getInt(min, max);
        } else if (userNumber > max) {
            System.out.println("Number entered is above the maximum.");
            return getInt(min, max);
        }

        return userNumber;
    }

    public int getInt() {
//        System.out.println("Enter a number below.");
        return scanner.nextInt();
        // alternative
//        return scanner.nextInt();
    }

    public int getInt(String prompt) {
        System.out.println(prompt);
        return getInt();
    }

    public double getDouble(double min, double max) {
        double userNumber = getDouble();

        if (userNumber < min) {
            System.out.println("Number entered is below the minimum.");
            return getDouble(min, max);
        } else if (userNumber > max) {
            System.out.println("Number entered is above the maximum.");
            return getDouble(min, max);
        }

        return userNumber;
    }

    public double getDouble() {
        System.out.println("Enter a number below.");
        return Double.parseDouble(getString());
    }

    public double getDouble(String prompt) {
        System.out.println(prompt);
        return getDouble(5, 10);
    }

    public static void main(String[] args) {
        Input input = new Input();

        System.out.println("What's your favorite color?");
        System.out.println(input.getString());
        System.out.println(input.getInt(5, 10));
        System.out.println(input.getDouble(5, 10));
        System.out.println(input.yesNo());
//        BONUS
        System.out.println(input.getString("Bonus starting...Enter your name."));
        System.out.println(input.yesNo("Here's another bonus."));
        System.out.println(input.getInt("Bonus: Enter a number."));
        System.out.println(input.getDouble("Bonus: Enter a double."));
    }

}
