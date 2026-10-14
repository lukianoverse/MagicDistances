package one;

import java.util.*;

import static java.lang.Math.hypot;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public class OneMeasure {
    static private final Scanner sc = new Scanner(System.in);
    private static Point getPoint() {
        int[] coor = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point(coor[0], coor[1]);
    }
    private static List<Point> getPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            IO.println("Введи координаты %d-й точки через пробел:"
                    .formatted(i + 1));
            points.add(getPoint());
        }
        return points;
    }

    static List<Segment> getSegmentsFromPoints(List<Point> points) {
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++)
            for (int j = i + 1; j < points.size(); j++)
                segments.add(new Segment(points.get(i), points.get(j)));
        return segments;
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

    static double findAvgLength(List<Segment> segments, Rangefinder method) {
        if (segments.isEmpty()) return Double.NaN;
        return segments.stream()
                .mapToDouble(s -> method.findDistance(s.a(), s.b()))
                .sum() / segments.size();

    }

    static double findAvgLength(List<Segment> segments) {
        return findAvgLength(segments,
                (a, b) -> hypot(a.x() - b.x(), a.y() - b.y()));
    }

    private double findMedian(List<Segment> segments, Rangefinder method) {
        if (segments.isEmpty()) return Double.NaN;
        segments.sort(Comparator.comparing(s -> s.getLength(method)));
        int middle = segments.size() / 2;
        return segments.size() % 2 == 0 ?
                (segments.get(middle).getLength(method)
                + segments.get(middle + 1).getLength(method))
                / 2
                : segments.get(middle).getLength(method);
    }

    private double findMedian(List<Segment> segments) {
        return findMedian(segments,
                (a, b) -> hypot(a.x() - b.x(), a.y() - b.y()));
    }

    void main() {
        List<Point> points = getPoints(5);

        IO.print("Кратчайшее здесь расстояние по Эвклиду: ");
        IO.println(findMinDistance(points,
                (a, b) -> hypot(a.x() - b.x(), a.y() - b.y())));

        IO.print("Кратчайшее здесь расстояние по Нойманну: ");
        IO.println(findMinDistance(points,
                (a, b) -> abs(a.x() - b.x()) + abs(a.y() - b.y())));

        IO.print("Кратчайшее здесь расстояние по Муру: ");
        IO.println(findMinDistance(points,
                (a, b) -> max(abs(a.x() - b.x()), abs(a.y() - b.y()))));

        List<Segment> segments = getSegmentsFromPoints(points);

        double avg = findAvgLength(segments);
        double avgNeum = findAvgLength(segments,
                (a, b) -> abs(a.x() - b.x()) + abs(a.y() - b.y()));
        double avgMoor = findAvgLength(segments,
                (a, b) -> max(abs(a.x() - b.x()), abs(a.y() - b.y())));

        double med = findMedian(segments);
        double medNeum = findMedian(segments,
                (a, b) -> abs(a.x() - b.x()) + abs(a.y() - b.y()));
        double medMoor = findMedian(segments,
                (a, b) -> max(abs(a.x() - b.x()), abs(a.y() - b.y())));

        IO.println("""
                   Для списка %s:
                   среднее: %f
                   среднее по Нойманну: %f
                   среднее по Муру: %f
                   медиана: %f
                   медиана по Нойманну: %f
                   медиана по Муру: %f
                   """.formatted(segments.toString(), avg, avgNeum, avgMoor, med, medNeum, medMoor)
        );

    }


}
record Point(int x, int y) {}
record Segment(Point a, Point b) {
    double getLength(Rangefinder method) {
        return method.findDistance(a, b);
    }
    double getLength() {
        return hypot(a.x() - b.x(), a.y() - b.y());
    }

}
interface Rangefinder {
    double findDistance(Point a, Point b);
}

