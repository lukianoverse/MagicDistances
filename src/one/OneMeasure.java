import java.util.List;
import java.util.Scanner;

public class OneMeasure {
    static private final Scanner sc = new Scanner(System.in);

    static double findMinDistance(List<Point> points, Rangefinder method) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.size() - 1; i++)
            for (int j = i + 1; j < points.size(); j++) {
                double distance = method.findDistance(points.get(i), points.get(j));
                if (distance < min) min = distance;
            }
        return min;
    }

}
record Point(int x, int y) {}
interface Rangefinder {
    double findDistance(Point a, Point b);
}

