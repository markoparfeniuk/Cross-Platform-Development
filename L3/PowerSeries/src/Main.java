import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of x: ");
        double x = scanner.nextDouble();
        System.out.print("Enter the desired accuracy: ");
        double eps = scanner.nextDouble();

        double[] result = PowerSeries.calculateLn(x, eps);
        double terms = result[1];
        double result1 = result[0];
        double result2 = Math.log((1 + x) / (1 - x));
        System.out.println("Number of terms: " + terms);
        System.out.printf("Result using PowerSeries.calculateLn: %.5f%n", result1);
        System.out.printf("Result using Math.log: %.5f%n", result2);
        System.out.printf("Difference: %.5f%n", Math.abs(result1 - result2));
    }
}
