import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("To solve the inequality (x-a)(x^2+bx+c) enter the value for a, b, c:");
            System.out.print("a >> ");
            float a = scanner.nextFloat();
            System.out.print("b >> ");
            float b = scanner.nextFloat();
            System.out.print("c >> ");
            float c = scanner.nextFloat();

            InequalitySolver.solveInequality(a, b, c);
        }
    }
}
