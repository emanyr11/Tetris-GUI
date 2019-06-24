package Shapes;

import GameArrays.LogicDisplayCoordinateArrays;
import GameArrays.Point;

public class CurrentBlock {

	LogicDisplayCoordinateArrays coords = new LogicDisplayCoordinateArrays();
	private BasicShape sceenShape;
	private char[][] shape;
	private int numBlocks=0;
	Point[][] empty = new Point[4][4];
	public static int height= 0;
	public static int xOffset = 6;
	
	
	
	public boolean canBlockMoveLeft(Point[][] toLoad){
		boolean canMoveLeft = true;
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				if(shape[y][x]=='#'){
					Point test = coords.getPoint(x, y);
					if(test.getX()<25){
						canMoveLeft=false;
					}
				}
			}
		}
		return canMoveLeft;
	}
	public boolean canBlockMoveRight(Point[][] toLoad){
		boolean canMoveLeft = true;
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				if(shape[y][x]=='#'){
					Point test = coords.getPoint(x, y);
					if(test.getX()>375){
						canMoveLeft=false;
					}
				}
			}
		}
		return canMoveLeft;
	}
	
	public Point[][] moveBlockDown(Point[][] toLoad){
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				if(shape[y][x]=='#'){
					Point test = coords.getPoint(x, y);
					if(test.getY()<975){
						//System.out.println("xOffset "+xOffset);
						toLoad[y][x] = coords.getPoint(x+xOffset, y+height);
						//System.out.println("moveBlockDown new coords X "+toLoad[y][x].getX()+" Y "+toLoad[y][x].getY() );
					}
				}
			}
		}
		height++;
		return toLoad;
	}
	public boolean testBlockDown(Point[][] toLoad){
		boolean canMove = true;
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				if(toLoad[y][x].getY()>=500){
						canMove = false;
				}
			}
		}
		return canMove;
	}
	
	public Point[][] loadBlockCoords(Point[][] toLoad,int xO){
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				
				if(shape[y][x]=='#'){
					toLoad[y][x] = coords.getPoint(x+xOffset, y);
				}
			}
		}
		return toLoad;
	}
	
	
	public Point[][] getEmptyShape(){
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				Point e = new Point(0,0);
				empty[y][x] = e;
			}
		}
		return empty;
	}
	public Point[][] updateLocation(Point[][] current, int direction){
		boolean test = true;
		for(int yz =0;yz< 4;yz++){
			for(int xz=0;xz<4;xz++){
				if((current[yz][xz].getX()<=25)||(current[yz][xz].getX()>375)){
					test = false;
				}
			}
		}
		if(test){
			for(int yz =0;yz< 4;yz++){
				for(int xz=0;xz<4;xz++){
					if(shape[yz][xz]=='#'){
						current[yz][xz] = coords.getPoint(direction+xz, yz);
					}
				}
			}
		}
		return current;
	}

	public void setLShape(LShape sceenShape) {
		this.sceenShape = sceenShape;
		shape = sceenShape.getCurrent();
		numBlocks = 4;
	}
	public void setSquareShape(SquareShape sceenShape) {
		this.sceenShape = sceenShape;
		shape = sceenShape.getCurrent();
		numBlocks = 4;
	}
	public void setStrraightShape(StraightShape sceenShape) {
		this.sceenShape = sceenShape;
		shape = sceenShape.getCurrent();
		numBlocks = 4;
	}
	public void setZigZagShape(ZigZagShape sceenShape) {
		this.sceenShape = sceenShape;
		shape = sceenShape.getCurrent();
		numBlocks = 4;
	}
	public void setTShape(TShape sceenShape) {
		this.sceenShape = sceenShape;
		shape = sceenShape.getCurrent();
		numBlocks = 4;
	}
	public char[][] getShape() {
		return shape;
	}
	public void setShape(char[][] shape) {
		this.shape = shape;
	}
	public BasicShape getSceenShape() {
		return sceenShape;
	}
	public void setSceenShape(BasicShape input) {
		sceenShape = input;
		
	}

}
