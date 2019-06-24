package Shapes;

public class StraightShape extends BasicShape{
	public StraightShape(int x, int y) {
		super(x, y);
		current= straightPiece;
	}

	private char[][] current = new char[4][4];
	private char[][] straightPiece ={{' ','#',' ',' '},
									 {' ','#',' ',' '},
									 {' ','#',' ',' '},
									 {' ','#',' ',' '}};
	
	private char[][] straightPieceR ={{' ',' ',' ',' '},
									  {' ',' ',' ',' '},
									  {' ',' ',' ',' '},
									  {'#','#','#','#'}};

	public char[][] getCurrent() {
		return current;
	}

	public void setCurrent(char[][] current) {
		this.current = current;
	}

}
