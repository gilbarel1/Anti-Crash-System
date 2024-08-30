import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DataStructure implements DT {
	private SortedLinkedList xAxis;
	private SortedLinkedList yAxis;


	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		//TODO
		Comparator<Container> xComp = new ComparatorByX();
		Comparator<Container> yComp = new ComparatorByY();
		xAxis = new SortedLinkedList(xComp);
		yAxis = new SortedLinkedList(yComp);
	}
	// copy Builder
	public DataStructure(SortedLinkedList xAxis, SortedLinkedList yAxis){
		System.out.println("This is 4.1.1");
		Comparator<Container> xComp = new ComparatorByX();
		Comparator<Container> yComp = new ComparatorByY();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		Container c1 = new Container(point);
		Container copyC1 = new Container(c1);
		c1.setTwin(copyC1);
		copyC1.setTwin(c1);
		xAxis.add(c1);
		yAxis.add(copyC1);
	}


	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub
		Point[] pointsInRange;
		if (axis){
			SortedLinkedList l = new SortedLinkedList(new ComparatorByX());
			Container xCon = xAxis.getTail();
			int size = 0;
			while (xCon != null){
				Point current = xCon.getData();
				if (current.getX() <= max & current.getX() >= min){
					Container copy = new Container(xCon);
					l.add(copy);
					size = size + 1;
				}
				xCon = xCon.getPrev();
			}
			pointsInRange = getPoints(l, size);
		}
		else{
			SortedLinkedList l = new SortedLinkedList(new ComparatorByY());
			Container yCon = yAxis.getTail();
			int size = 0;
			while (yCon != null){
				Point current = yCon.getData();
				if (current.getY() <= max & current.getY() >= min){
					Container copy = new Container(yCon);
					l.add(copy);
					size = size + 1;
				}
				yCon = yCon.getPrev();
			}
			pointsInRange = getPoints(l, size);
		}
		return pointsInRange;
	}

	private Point[] getPoints(SortedLinkedList l, int size) {
		Point[] pointsInRange;
		pointsInRange = new Point[size];
		Container curr = l.getHead();
		int index = 0;
		while (curr != null && index < size){
			Point p = curr.getData();
			pointsInRange[index] = p;
			index = index + 1;
			curr = curr.getNext();
		}
		return pointsInRange;
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub
		Point[] pointsInRange;
		if(axis){
			SortedLinkedList l = new SortedLinkedList(new ComparatorByY());
			Container yCon = yAxis.getTail();
			int size = 0;
			while (yCon != null){
				Point current = yCon.getData();
				if (current.getX() <= max & current.getX() >= min){
					Container copy = new Container(yCon);
					l.add(copy);
					size = size + 1;
				}
				yCon = yCon.getPrev();
			}
			pointsInRange = getPoints(l, size);
		}
		else {
			SortedLinkedList l = new SortedLinkedList(new ComparatorByX());
			Container xCon = xAxis.getTail();
			int size = 0;
			while (xCon != null){
				Point current = xCon.getData();
				if (current.getY() <= max & current.getY() >= min){
					Container copy = new Container(xCon);
					l.add(copy);
					size = size + 1;
				}
				xCon = xCon.getPrev();
			}
			pointsInRange = getPoints(l, size);
		}
		return pointsInRange;
	}
	public int xMaxVal(){
		return xAxis.getTail().getData().getX();
	}
	public int xMinVal(){
		return  xAxis.getHead().getData().getX();
	}
	public int yMaxVal(){
		return yAxis.getTail().getData().getY();
	}
	public int yMinVal(){
		return yAxis.getHead().getData().getY();
	}


	@Override
	public double getDensity() {
		// TODO Auto-generated method stub
		return xAxis.size() / ((xMaxVal() - xMinVal()) * (yMaxVal() - yMinVal()));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub
		if (axis){
			Container start = xAxis.getHead();
			Container end = xAxis.getTail();
			while(start.getData().getX() < min | end.getData().getX() > max){
				if (start.getData().getX() < min){
					Container next = start.getNext();
					xAxis.remove(start);
					yAxis.remove(start.getTwin());
					start = next;
				}
				if(end.getData().getX() > max){
					Container prev = end.getPrev();
					xAxis.remove(end);
					yAxis.remove(end.getTwin());
					end = prev;
				}
			}
		}
		else {
			Container start = yAxis.getHead();
			Container end = yAxis.getTail();
			while(start.getData().getY()<min | end.getData().getY()> max){
				if (start.getData().getY()<min){
					Container next = start.getNext();
					yAxis.remove(start);
					xAxis.remove(start.getTwin());
					start = next;
				}
				if(end.getData().getY() > max){
					Container prev = end.getPrev();
					yAxis.remove(end);
					xAxis.remove(end.getTwin());
					end = prev;
				}
			}
		}
	}

	@Override
	public Boolean getLargestAxis() {
		// TODO Auto-generated method stub
		return ((xMaxVal()-xMinVal()) > (yMaxVal()-yMinVal()));
	}

	@Override
	public Container getMedian(Boolean axis) {;
		// TODO Auto-generated method stub
		if (axis)
			return xAxis.getMid();
		else {
			return yAxis.getMid();
		}
	}

	@Override
	public Point[] nearestPairInStrip(Container container, double width,
									  Boolean axis) {

		int median = container.getPointData(axis);
		Point[] stripPoints = GetPointsinStrip(container, width, axis); // O(b)
		double counter = (double) stripPoints.length;
		if(counter * Math.log(counter) > xAxis.size()) {
			stripPoints = getPointsInRangeOppAxis((int) (median - (width / 2)), (int) (median + (width / 2) + 1), axis); // O(n)
		}
		else {
			Arrays.sort(stripPoints, 0, stripPoints.length, getComparator(!axis)); // O(blog b)
		}
		if(counter <= 1)
			return new Point[0];
		else {
			Point[] ans = new Point[2];
			for (int i = 0; i < stripPoints.length; ++i) { // O(b)
				for (int j = i + 1; j < stripPoints.length; ++j) {
					if (dist(stripPoints[i], stripPoints[j]) < width) {
						ans[0] = stripPoints[i];
						ans[1] = stripPoints[j];
						width = dist(ans[0], ans[1]);
					}
				}
			}
			return ans;
		}
	}

	public Point[] GetPointsinStrip (Container median, double width, Boolean axis){
		SortedLinkedList strip = new SortedLinkedList(new ComparatorByX());
		Point[] pointsInRange;
		Container current = median;
		double upper = current.getPointData(axis) + (width / 2);
		double lower = current.getPointData(axis) - (width / 2);

		while(current != null && current.getPointData(axis) >= lower){
			strip.addFirst(new Container(current));
			current = current.getPrev();
		}
		current = median.getNext();
		while(current != null && current.getPointData(axis) <= upper){
			strip.addFirst(new Container(current));
			current = current.getNext();
		}
		pointsInRange = getPoints(strip, strip.size());
		return pointsInRange;
	}

	@Override
	public Point[] nearestPair() {
		// TODO Auto-generated method stub
		boolean axis = getLargestAxis();
		Point[] points = getAllPoints(axis); // O(n)
		int n = points.length;
		int midRange = n/2;
		Point[] left = nearestPairRecursive(0, midRange , points, axis);  // T(n/2)
		Point[] right = nearestPairRecursive(midRange + 1, n-1, points, axis); // T(n/2)
		return calcMinDistance(left,right);
	}

	public Point[] getAllPoints(Boolean axis){
		Point[] ans = new Point[xAxis.size()];
		if(axis){
			Container xCon = xAxis.getHead();
			for(int i = 0; i < ans.length && xCon != null ; i = i + 1){
				ans[i] = xCon.getData();
				xCon = xCon.getNext();
			}
			return ans;
		}
		else{
			Container yCon = yAxis.getHead();
			for(int i = 0; i < ans.length && yCon != null ; i = i + 1){
				ans[i] = yCon.getData();
				yCon = yCon.getNext();
			}
			return ans;
		}
	}

	public Point[] nearestPairRecursive (int start, int end, Point[] points, Boolean axis){
		if ((end - start) == 1){
			return new Point[] {points[start], points[end]};
		}
		else if ((end - start) < 1){
			return new Point[0];
		}
		int mid = (end - start) / 2;
		Container median;
		if(axis){
			median = xAxis.getContainer(points[mid]);
		}
		else {
			median = yAxis.getContainer(points[mid]);
		}
		Point[] left = nearestPairRecursive(start, mid  , points, axis);
		Point[] right = nearestPairRecursive(mid+1 , end, points, axis);
		Point[] closest = calcMinDistance(left, right);
		double delta = calcMin(left, right);
		Point[] nearestInStrip = nearestPairInStrip(median ,2*delta,axis);
		if(nearestInStrip.length == 2)
			return nearestInStrip;
		else
			return closest;
	}

	public Point[] calcMinDistance(Point[] points1,Point[] points2){
		double distance1 = 0;
		double distance2 = 0;
		if(points1.length == 1 && points2.length == 1){
			return new Point[] {points1[0], points2[0]};
		}
		if(points1.length > 1) {
			distance1 = Math.sqrt(Math.pow((points1[0].getX() - points1[1].getX()), 2) + Math.pow((points1[0].getY() - points1[1].getY()), 2));
		}
		if(points2.length > 1){
			distance2 = Math.sqrt( Math.pow( (points2[0].getX()-points2[1].getX()),2 ) + Math.pow( (points2[0].getY()- points2[1].getY()),2 ) );
		}
		double minDistance = Math.min(distance1,distance2);
		if(minDistance == distance1){
			return points1;
		}
		else
			return points2;
	}
	public double calcMin(Point[] points1, Point[] points2){
		double distance1 = 0;
		double distance2 = 0;
		if(points1.length == 1 && points2.length == 1){
			return Math.sqrt(Math.pow((points1[0].getX() - points2[0].getX()), 2) + Math.pow((points1[0].getY() - points2[0].getY()), 2));
		}
		if(points1.length > 1) {
			distance1 = Math.sqrt(Math.pow((points1[0].getX() - points1[1].getX()), 2) + Math.pow((points1[0].getY() - points1[1].getY()), 2));
		}
		if(points2.length > 1) {
			distance2 = Math.sqrt(Math.pow((points2[0].getX() - points2[1].getX()), 2) + Math.pow((points2[0].getY() - points2[1].getY()), 2));
		}
		return  Math.min(distance1,distance2);
	}

	public double dist(Point p1 , Point p2){
		return Math.sqrt(Math.pow((p1.getX()-p2.getX()),2) + Math.pow((p1.getY()- p2.getY()),2));
	}

	public Comparator<Point> getComparator(Boolean axis){
		if(axis)
			return new PointXComparator();
		else
			return new PointYComparator();
	}


	//TODO: add members, methods, etc.

}

