package Shapes;

public class TShape extends BasicShape{
	public TShape(int x, int y) {
		super(x, y);
		current = tPiece;
	}

	public char[][] getCurrent() {
		return current;
	}

	public void setCurrent(char[][] current) {
		this.current = current;
	}

	private char[][] current = new char[4][4];
	private char[][] tPiece ={{' ',' ',' ',' '},
					  		  {' ',' ',' ',' '},
					  		  {' ','#','#','#'},
					  		  {' ',' ','#',' '}};
	
	private char[][] tPieceR1 ={{' ',' ',' ',' '},
								{' ',' ',' ','#'},
								{' ',' ','#','#'},
								{' ',' ',' ','#'}};
	
	private char[][] tPieceR2 ={{' ',' ',' ',' '},
			  	 	  			{' ',' ',' ',' '},
			  	 	  			{' ',' ','#',' '},
			  	 	  			{' ','#','#','#'}};

	private char[][] tPieceR3={{' ',' ',' ',' '},
			  	      		   {' ','#',' ',' '},
			  	      		   {' ','#','#',' '},
			  	      		   {' ','#',' ',' '}};
}
