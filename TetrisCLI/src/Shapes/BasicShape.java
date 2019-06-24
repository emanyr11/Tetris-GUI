package Shapes;

public class BasicShape {

	private int locX = 0;
	private int locY = 0;
	
	public BasicShape(int x, int y){
		this.setLocX(x);
		this.setLocY(y);
	}

	public int getLocX() {
		return locX;
	}

	public void setLocX(int locX) {
		this.locX = locX;
	}

	public int getLocY() {
		return locY;
	}

	public void setLocY(int locY) {
		this.locY = locY;
	}
	
}
