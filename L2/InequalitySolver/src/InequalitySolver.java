public class InequalitySolver {
    public static void solveInequality(double a, double b, double c) {
        double discriminant = b * b - 4 * c;
        if (discriminant < 0) {
            System.out.printf("(-Infinity, %f)%n", a);
        } else if (discriminant == 0) {
            double x = -b / 2;
            if (a <= x) {
                System.out.printf("(-Infinity, %f)%n", a);
            } else {
                System.out.printf("(-Infinity, %f) U (%f, %f)%n", x, x, a);
            }
        } else {
            double x1 = (-b - Math.sqrt(discriminant)) / 2;
            double x2 = (-b + Math.sqrt(discriminant)) / 2;
            if (x1 > x2) {
                double temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (a < x1) {
                System.out.printf("(-Infinity, %f) U (%f, %f)%n", a, x1, x2);
            } else if (a == x2) {
                System.out.printf("(-Infinity, %f)%n", x1);
            } else if (a > x2) {
                System.out.printf("(-Infinity, %f) U (%f, %f)%n", x1, x2, a);
            } else {
                System.out.printf("(-Infinity, %f) U (%f, %f)%n", x1, a, x2);
            }
        }
    }
}
