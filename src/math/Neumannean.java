package math;

public class Neumannean implements Rangefinder {

    @Override
    public double findDistance(Point a, Point b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
