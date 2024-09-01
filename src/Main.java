import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(6,6);
        Point p2 = new Point(12,30);
        Point p3 = new Point(40,50);
        Point p4 = new Point(5,0);
        Point p5 = new Point(7,7);
        Point p6 = new Point(8,4);
        Point p7 = new Point(10,40);
        Point p8 = new Point(30,1);
        Point p9 = new Point(14,-20);
        Point p10 = new Point(3,-100);


        DataStructure d1 = new DataStructure();
        d1.addPoint(p1);
        d1.addPoint(p2);
        d1.addPoint(p3);
        d1.addPoint(p4);
        d1.addPoint(p5);
        d1.addPoint(p6);
        d1.addPoint(p7);
        d1.addPoint(p8);
        d1.addPoint(p9);
        d1.addPoint(p10);
        System.out.println(Arrays.toString(d1.nearestPair()));

    }
}