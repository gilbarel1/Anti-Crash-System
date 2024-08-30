
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
	private Container next;
	private Container prev;
	private Container twin;

	public Container(Point p) {
		this.next = null;
		this.prev = null;
		this.twin = null;
		this.data = p;
	}
	public Container (Container copy){
		this.data = copy.data;
		this.twin = null;
		this.prev = null;
		this.next = null;
	}

	//Don't delete or change this function
	public Point getData() { return data; }
	public int getPointData(boolean axis){
		if (axis)
			return data.getX();
		else
			return data.getY();
	}
	public void setData(Point p){ this.data = p;}
	public Container getNext(){return this.next;}
	public Container getPrev(){return this.prev;}
	public Container getTwin(){return this.twin;}
	public void setNext (Container next){ this.next = next;}
	public void setPrev (Container prev){ this.prev = prev;}
	public void setTwin (Container twin){ this.twin = twin; }
	public double getDistance(Container c1){
		double distance = Math.sqrt( Math.pow( (data.getX()-c1.getData().getX()),2 ) + Math.pow( (data.getY()-c1.getData().getY()),2 ) );
		return distance;
	}
	@Override
	public String toString() {
		return data.toString();
	}

}