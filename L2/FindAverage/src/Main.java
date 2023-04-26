import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("To find average of numbers (a, b, c), enter the value of each:");
        System.out.print("a >> ");
        float a = scanner.nextFloat();
        System.out.print("b >> ");
        float b = scanner.nextFloat();
        System.out.print("c >> ");
        float c = scanner.nextFloat();

        float smallest = a;
        if (b < smallest) {
            smallest = b;
        }
        if (c < smallest) {
            smallest = c;
        }

        float largest = a;
        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }

        float average = (a + b + c) - smallest - largest;

        System.out.printf("The average of the three numbers is: %f", average);
    }
}