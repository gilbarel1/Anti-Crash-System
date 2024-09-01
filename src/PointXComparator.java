import java.util.Comparator;

class PointXComparator implements Comparator<Point> {

    // Needed to sort array of points
    // according to X coordinate
    @Override
    public int compare(Point pointA, Point pointB) {
        return Integer.compare(pointA.getX(), pointB.getX());
    }

}