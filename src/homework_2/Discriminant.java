package homework_2;

public class Discriminant {
    double value;
    boolean equationHasRoots;

    public Discriminant(double a, double b, double c) {
        value = Math.pow(b, 2) - 4 * a * c;
        equationHasRoots = (value >=0);
    }
}
