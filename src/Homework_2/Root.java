package Homework_2;

public class Root {

    public static double getRoot1(double D, double b, double a) {
        return (-b - Math.sqrt(D)) / (2 * a);
    }

    public static double getRoot2(double D, double b, double a) {
        return (-b + Math.sqrt(D)) / (2 * a);
    }
}
