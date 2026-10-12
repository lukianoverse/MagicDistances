package math;

public class Euklidian implements Rangefinder {
    @Override
    public double findDistance(Point a, Point b) {
        return Math.hypot(a.x() - b.x(), a.y() - b.y());
    }
}
