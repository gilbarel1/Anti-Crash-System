import java.util.Comparator;

class PointYComparator implements Comparator<Point> {

    // Needed to sort array of points
    // according to Y coordinate
    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.getY(), pointB.getY());
    }

}