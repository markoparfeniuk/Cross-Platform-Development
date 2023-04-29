import java.util.Arrays;

public class ArrayWorker {
    public static void printArray(double[] array)
    {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%.3f ", array[i]);
        }
        System.out.println();
    }

    public static double[] sortArray(double[] array)
    {
        double[] sortedArray = array.clone();
        Arrays.sort(array);
        return sortedArray;
    }

    public static double findClosestValue(double[] array, double targetValue)
    {
        double closestValue = array[0];
        double minDiff = Math.abs(targetValue - closestValue);
        for (int i = 1; i < array.length; i++) {
            double diff = Math.abs(targetValue - array[i]);
            if (diff < minDiff) {
                closestValue = array[i];
                minDiff = diff;
            }
        }
        return closestValue;
    }
}
