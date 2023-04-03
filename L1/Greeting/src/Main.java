import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your name?\n>> ");
        String name = scanner.nextLine();

        System.out.print("How old are you?\n>> ");
        int age = scanner.nextInt();

        int currentYear = java.time.Year.now().getValue();
        int birthYear = currentYear - age;

        System.out.printf("Hello, %s! You were born in %d.", name, birthYear);
    }
}