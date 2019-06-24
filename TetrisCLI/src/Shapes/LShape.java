package Shapes;


public class LShape extends BasicShape{
	
	public LShape(int x, int y){
		super(x,y);
		current = lPiece;
	}
	
	public char[][] getCurrent() {
		return current;
	}

	public void setCurrent(char[][] current) {
		this.current = current;
	}

	private char[][] current = new char[4][4];
	private char[][] lPiece=  {{' ',' ',' ',' '},
	   		   {' ','#',' ',' '},
	   		   {' ','#',' ',' '},
	   		   {' ','#','#',' '}};

	private char[][] lPieceR1={{' ',' ',' ',' '},
	   		   {' ',' ',' ',' '},
	   		   {' ','#','#','#'},
	   		   {' ','#',' ',' '}};

	private char[][] lPieceR2=  {{' ',' ',' ',' '},
	     		 {'#','#',' ',' '},
	     		 {' ','#',' ',' '},
	     		 {' ','#',' ',' '}};

	private char[][] lPieceR3=  {{' ',' ',' ',' '},
		 		 {' ',' ',' ',' '},
		 		 {' ',' ','#',' '},
		 		 {'#','#','#',' '}};


	private char[][] lPieceAlt= {{' ',' ',' ',' '}, /// Check this one out 
	     		 {' ','#',' ',' '},
	     		 {' ','#',' ',' '},
	     		 {'#','#',' ',' '}};

	private char[][] lPieceAltR1={{' ',' ',' ',' '},
			  		  {' ',' ',' ',' '},
			  		  {'#',' ',' ',' '},
			  		  {'#','#','#',' '}};

	private char[][] lPieceAltR2={{' ',' ',' ',' '},
				  {' ','#','#',' '},
				  {' ','#',' ',' '},
				  {' ','#',' ',' '}};

	private char[][] lPieceAltR3={{' ',' ',' ',' '},
				  {' ',' ',' ',' '},
				  {'#','#','#',' '},
				  {' ',' ','#',' '}};
	
}
