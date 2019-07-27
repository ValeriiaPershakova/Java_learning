public class Equation {
    public static void main(String[] arg) {
        System.out.println("Уравнение 1");
        QuadraticEquation(1, 2, -3);
        System.out.println("Уравнение 2");
        QuadraticEquation(1, 6, 9);
        System.out.println("Уравнение 3");
        QuadraticEquation(1, 2, 17);
    }

    //Этот метод находит корни квадратного уравнения
    public static void QuadraticEquation(float a, float b, float c) {
        //Расчет дискриминанта
        double D = Math.pow(b, 2) - 4 * a * c;
        System.out.println("Дискриминант: " + D);
        if (D > 0) {
            double x1 = (-b - Math.sqrt(D)) / (2 * a);
            double x2 = (-b + Math.sqrt(D)) / (2 * a);
            System.out.println(x1);
            System.out.println(x2);
        } else if (D == 0) {
            double x = (-b) / (2 * a);
            System.out.println(x);
        } else {
            System.out.println("Корней нет");
        }
    }
}
