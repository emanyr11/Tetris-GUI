package GameArrays;

public class BlockToDisplay {
	private int type=0;
	private Point location = new Point(0,0);
	
	public BlockToDisplay(Point newPoint, int type){
		this.setType(type);
		setLocation(newPoint);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
