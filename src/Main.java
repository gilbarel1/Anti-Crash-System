import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1,3);
        Point p2 = new Point(2,2);
        Point p3 = new Point(3,4);
        Point p4 = new Point(5,7);
        Point p5 = new Point(8,9);
        Point p6 = new Point(10,5);

        DataStructure d1 = new DataStructure();
        d1.addPoint(p1);
        d1.addPoint(p2);
        d1.addPoint(p3);
        d1.addPoint(p4);
        d1.addPoint(p5);
        d1.addPoint(p6);
        System.out.println("The two nearest points are: " + Arrays.toString(d1.nearestPair()));



    }
}