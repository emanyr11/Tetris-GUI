package Shapes;

public class SquareShape extends BasicShape{
	public SquareShape(int x, int y) {
		super(x, y);
		current = squarePiece;
	}
	public char[][] getCurrent() {
		return current;
		
	}
	public void setCurrent(char[][] current) {
		this.current = current;
	}
	private char[][] current = new char[4][4];
	private char[][] squarePiece ={{' ',' ',' ',' '},
								   {' ',' ',' ',' '},
								   {' ','#','#',' '},
								   {' ','#','#',' '}};
}
