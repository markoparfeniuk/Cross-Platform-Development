import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the temperature in Fahrenheit: ");
        float fahrenheit = scanner.nextFloat();
        float celsius = Converter.fahrenheitToCelsius(fahrenheit);
        System.out.print("Enter the temperature in Celsius: " + celsius);
    }
}