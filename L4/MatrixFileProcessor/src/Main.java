import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String readFile = "iput.txt";
        String writeFile = "output.txt";
        try {
            int[][] A = MatrixWorker.readMatrixFromFile(readFile);
            int[] result = MatrixWorker.countPositiveNegative(A);
            System.out.printf("The ratio of positive to negative elements in matrix A is: %d to %d", result[0], result[1]);
            int[][] B = MatrixWorker.reflectMatrixVertically(A);
            MatrixWorker.writeMatrixToFile(B, writeFile);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}