package math;

public class Moorean implements Rangefinder {
    @Override
    public double findDistance(Point a, Point b) {
        return Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()));
    }
}
