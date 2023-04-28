import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N;
        // Repeatedly prompt the user to enter a value for N until they enter a valid value
        do {
            System.out.print("Enter N (must be > 0): ");
            N = scanner.nextInt();
        } while (N <= 0);
        // Create an array A of size N+1 to store the values of the sequence
        int[] A = new int[N+1];
        // Initialize the first two elements of the sequence
        A[1] = 3;
        A[2] = 7;
        int k = 3;
        // Compute the remaining elements of the sequence using a while loop
        while (k <= N) {
            A[k] = 2 * A[k-1] + 3 * A[k-2];
            ++k;
        }
        // Print the elements of the sequence
        for (int i = 1; i <= N; i++) {
            System.out.print(A[i] + " ");
        }
    }
}
