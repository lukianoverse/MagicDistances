package one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OneMeasure {
    static private final Scanner sc = new Scanner(System.in);
    private static Point getPoint(int i) {
        IO.println("Введи координаты %d-й точки через пробел:".formatted(i + 1));
        int[] coor = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point(coor[0], coor[1]);
    }

    /**
     * Находит минимальное расстояние между парой точек из списка.
     * @param points список точек.
     * @param method способ определения расстояния.
     * @return  расстояние между парой ближайших точек из числа тех, что в списке.
     */
    static double findMinDistance(List<Point> points, Rangefinder method) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.size() - 1; i++)
            for (int j = i + 1; j < points.size(); j++) {
                double distance = method.findDistance(points.get(i), points.get(j));
                if (distance < min)
                    min = distance;
            }
        return min;
    }

    void main() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            points.add(getPoint(i));

        IO.print("Кратчайшее здесь расстояние по Эвклиду: ");
        IO.println(findMinDistance(points,
                (a, b) -> Math.hypot(a.x() - b.x(), a.y() - b.y())));

        IO.print("Кратчайшее здесь расстояние по Нойманну: ");
        IO.println(findMinDistance(points,
                (a, b) -> Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y())));

        IO.print("Кратчайшее здесь расстояние по Муру: ");
        IO.println(findMinDistance(points,
                (a, b) -> Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()))));
    }


}
record Point(int x, int y) {}
interface Rangefinder {
    double findDistance(Point a, Point b);
}

