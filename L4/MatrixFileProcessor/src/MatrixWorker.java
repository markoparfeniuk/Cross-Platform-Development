import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MatrixWorker {
    public static int[][] readMatrixFromFile(String fileName) throws FileNotFoundException {
        int[][] matrix = new int[5][5];
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (!scanner.hasNextInt()) {
                        throw new IllegalArgumentException("Input file does not contain enough elements");
                    }
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }
        return matrix;
    }

    public static int[] countPositiveNegative(int[][] matrix) {
        int positiveCount = 0;
        int negativeCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    positiveCount++;
                } else if (matrix[i][j] < 0) {
                    negativeCount++;
                }
            }
        }
        return new int[]{ positiveCount, negativeCount };
    }

    public static int[][] reflectMatrixVertically(int[][] inputMatrix) {
        int[][] outputMatrix = new int[inputMatrix.length][inputMatrix[0].length];
        for (int i = 0; i < outputMatrix.length; i++) {
            for (int j = 0; j < outputMatrix[i].length; j++) {
                outputMatrix[i][j] = inputMatrix[inputMatrix.length - 1 - i][j];
            }
        }
        return outputMatrix;
    }
    public static void writeMatrixToFile(int[][] matrix, String fileName) {
        if (matrix == null || matrix.length != 5) {
            throw new IllegalArgumentException("Invalid matrix");
        }
        for (int[] row : matrix) {
            if (row.length != 5) {
                throw new IllegalArgumentException("Invalid matrix");
            }
        }
        File file = new File(fileName);
        try (PrintWriter writer = new PrintWriter(file)) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.print(matrix[i][j] + " ");
                }
                writer.println();
            }
        }
    }
}
