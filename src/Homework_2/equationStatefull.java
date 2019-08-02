package Homework_2;

public class equationStatefull {

    double a;
    double b;
    double c;
    boolean isQuadratic;
    Double x1;
    Double x2;
    Discriminant D;

    public equationStatefull(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a == 0) {
            isQuadratic = false;
            System.out.println("Not quadratic");
        } else {
            isQuadratic = true;
            D = new Discriminant(this.a, this.b, this.c);
        }
    }

    public void getRoots() {
        if (isQuadratic) {
            if (D.equationHasRoots) {
                if (D.value > 0) {
                    twoRoots();
                } else {
                    oneRoot();
                }
                System.out.println("X1 = " + x1 + "; X2 = " + x2);
            } else {
                System.out.println("Equation has no roots");
            }
        } else {
            System.out.println("Equation is not quadratic, can't get roots");
        }
    }

    private void oneRoot() {
        x1 = Root.getRoot1(D.value, b, a);
        x2 = null;
    }

    private void twoRoots() {
        x1 = Root.getRoot1(D.value, b, a);
        x2 = Root.getRoot2(D.value, b, a);
    }

    public static void main(String[] args) {
        equationStatefull e1 = new equationStatefull(1, 2, -3);
        e1.getRoots();
        equationStatefull e2 = new equationStatefull(1, 6, 9);
        e2.getRoots();
        equationStatefull e3 = new equationStatefull(1, 2, 17);
        e3.getRoots();
        equationStatefull e4 = new equationStatefull(0, 2, -3);
        e4.getRoots();
    }
}
