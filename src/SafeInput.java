import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                console.next();
                System.out.print("Please enter an integer: ");
            }
            value = console.nextInt();
            if (value < low || value > high) {
                System.out.println("Please enter an integer between " + low + " and " + high + ".");
            }
        } while (value < low || value > high);
        return value;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = console.next().toUpperCase();
            if (response.startsWith("Y")) {
                return true;
            } else if (response.startsWith("N")) {
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
    }
}
