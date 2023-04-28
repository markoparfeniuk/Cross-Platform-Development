public class PowerSeries {
    public static double[] calculateLn(double x, double eps) {
        double sum = 0;
        double term = x;
        int k = 0;
        while (Math.abs(term) >= eps) {
            sum += term;
            k++;
            term = Math.pow(x, 2 * k + 1) / (2 * k + 1);
        }
        return new double[]{ 2 * sum, k };
    }
}