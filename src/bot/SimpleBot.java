package bot;

import java.util.Scanner;

public class SimpleBot {
    static Scanner sc = new Scanner(System.in);

    public static void greet(String botName, String birthYear) {
        System.out.println("Hello my name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
    }

    public static void remindName() {
        System.out.println("Please, remind me your name.");
        String name = sc.nextLine();

        System.out.println("What a great name you have, " + name);
    }

    public static void guessAge() {
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = sc.nextInt();
        int remainder5 = sc.nextInt();
        int remainder7 = sc.nextInt();
        int yourAge = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + yourAge + " that's a good time to start programming!");
    }

    public static void count() {
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int numToCount = sc.nextInt();

        for (int i = 0; i <= numToCount; i++) {
            System.out.println(i + "!");
        }

        System.out.println("Completed, have a nice day!");
    }

    public static void test() {
        System.out.println("Let's test your programming knowledge.");
        System.out.println("Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times.");
        System.out.println("2. To decompose a program into several small subroutines.");
        System.out.println("3. To determine the execution time of a program.");
        System.out.println("4. To interrupt the execution of a program.");

        int response = 0;

        while (response != 2) {
            response = sc.nextInt();
            if (response != 2) {
                System.out.println("Please, try again.");
            }
        }
    }

    public static void end() {
        System.out.println("Congratulations, have a nice day!");
    }
}
