package Shapes;

public class ZigZagShape extends BasicShape{
	public ZigZagShape(int x, int y) {
		super(x, y);
		current = zigzagPiece;
	}

	public char[][] getCurrent() {
		return current;
	}


	public void setCurrent(char[][] current) {
		this.current = current;
	}

	private char[][] current = new char[4][4];
	private char[][] zigzagPiece ={{' ',' ',' ',' '},
			   					   {' ',' ',' ',' '},
			   					   {' ','#','#',' '},
			   					   {' ',' ','#','#'}};

	private char[][] zigzagPieceR ={{' ',' ',' ',' '},
			   			{' ',' ','#',' '},
			   			{' ','#','#',' '},
			   			{' ','#',' ',' '}};

	private char[][] zigzagPiecealt ={{' ',' ',' ',' '},
				  		 {' ',' ',' ',' '},
				  		 {' ',' ','#','#'},
				  		 {' ','#','#',' '}};

	
	private char[][] zigzagPiecealtR ={{' ',' ',' ',' '},
				  		   {' ','#',' ',' '},
				  		   {' ','#','#',' '},
				  		   {' ',' ','#',' '}};

	
}
