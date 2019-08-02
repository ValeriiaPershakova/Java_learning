package Homework_2;

public class equationStateless {
    public static void equation(double a, double b, double c) {
        if (isQuadratic(a)) {
            getRoots(a, b, c);

        } else {
            System.out.println("Not quadratic");
        }
    }

    private static boolean isQuadratic(double a) {
        return a != 0;
    }

    private static void getRoots(double a, double b, double c) {
        double D = getDiscriminant(a, b, c);
        if (equationHasRoots(D)) {
            if (D > 0) {
                twoRoots(a, b, D);
            } else {
                oneRoot(a, b, D);
            }
        } else {
            System.out.println("Equation has no roots");
        }

    }

    private static double getDiscriminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    private static boolean equationHasRoots(double D) {
        return (D >= 0);
    }

    private static void oneRoot(double a, double b, double D) {
        System.out.println("X1 = " + Root.getRoot1(D, b, a) + "; X2 = null");
    }

    private static void twoRoots(double a, double b, double D) {
        System.out.println("X1 = " + Root.getRoot1(D, b, a) + "; X2 = " + Root.getRoot2(D, b, a));
    }

    public static void main(String[] args) {
        equation(1, 2, -3);
        equation(1, 6, 9);
        equation(1, 2, 17);
        equation(0, 2, -3);
    }
}
