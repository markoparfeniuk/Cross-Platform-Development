import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int N = 10;
        double[] A = rand.doubles(N, 10 , 100).toArray();
        double[] B = new double[N];
        double[] C = new double[N];

        for (int i = 0; i < B.length; i++) {
            B[i] = Math.random() * (2 - (-4) + 1) + (-4);
        }

        double sumA = 0;
        for (double ai: A){
            sumA += ai;
        }
        double avgA = sumA / A.length;
        for (int i = 0; i < B.length; i++) {
            C[i] = B[i] < 0 ? avgA : B[i];
        }

        double targetValue = 1;
        System.out.println("Array A:");
        ArrayWorker.printArray(ArrayWorker.sortArray(A));
        System.out.printf("Element closest to %f in A: %f%n", targetValue, ArrayWorker.findClosestValue(A, targetValue));
        System.out.println("Array B:");
        ArrayWorker.printArray(ArrayWorker.sortArray(B));
        System.out.printf("Element closest to %f in B: %f%n", targetValue, ArrayWorker.findClosestValue(B, targetValue));
        System.out.println("Array C:");
        ArrayWorker.printArray(ArrayWorker.sortArray(C));
        System.out.printf("Element closest to %f in C: %f%n", targetValue, ArrayWorker.findClosestValue(C, targetValue));
    }
}