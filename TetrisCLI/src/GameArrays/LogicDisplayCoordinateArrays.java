package GameArrays;
import GameArrays.Point;
import Shapes.BasicShape;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LogicDisplayCoordinateArrays 
{
	int innerloop = 16;
	int outerloop = 40;
	int x = 0;
	int y = 0;
	Point[][] coordinate = new Point[40][16];
	public static Point[][] logic = new Point[40][16];
	//Point[][] display = new Point[40][16];
	
	public LogicDisplayCoordinateArrays(){
		coOrdinate();
	}
	
	public void distroyLine(){
		printLogicArray();
		ArrayList<Point[]> build = new ArrayList<>();
		Point[] line = new Point[16];
		for(int i=0;i<40;i++){
			for(int j=0;j<16;j++){
				//line = logic[i][j];
			}
		}
	}
	
	public boolean clearLine()
	{
		int counter = 0;
		ArrayList<Point[]> temp = new ArrayList<>();
		Point emp = new Point(660,660);
		Point[] empty  = new Point[16];
		for(int p=0;p<16;p++){
			empty[p] = emp;
		}

		for(int x=0;x<logic.length;x++)
		{
			temp.add(logic[x]);
		}
		for(int i = 39; i > 0; i --)
		{	

			for(int j = 0; j < 16; j ++)
			{
				if(logic[i][j].getX()>660)
				{
					counter ++;
				}
			}
			if(counter==14)
			{
				temp.remove(i);
				temp.add(0,empty);
			}

			counter=0;
		}
		for(int i =0;i<logic.length;i++)
		{
			logic[i] = temp.get(i);        
		}

		return false;

	}
	public boolean blockCollision(Point[][] current){
		Boolean answer = true;
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				Point test = new Point(current[y][x].getX()/25,current[y][x].getY()/25);
				//System.out.println("block collision   X "+test.getX()+" Y "+test.getY());
				if(test.getX()==0){
					
				}else{
					if(logic[test.getY()+1][test.getX()].getX()>660){
						answer = false;
					}
				}
			}
		}
		return answer;
	}
	
	public void setNewDisplay(){
		Point temp = new Point(660,660);
		for (int y=0;y<40;y++){
			for(int x=0;x<16;x++){
				logic[y][x] = temp;
				//System.out.println("x "+logic[y][x].getX()+" y "+logic[y][x].getY());
			}
			//System.out.println();
		}
	}
	
	public void removeLine(){
		ArrayList<Point[]> build = new ArrayList<>();
		Point[] emptyLine = new Point[16];
		Point empty = new Point(660,600);
		for(int i=16;i<16;i++){
			emptyLine[i] = empty;
		}
		int numberOnLine=0;
		Point[] line = new Point[16];
		//printLogicArray();
		for(int i=0;i<40;i++){
			for(int j=0;j<16;j++){
				line[j] = logic[i][j];
				System.out.println("removeLine  from logic into line array <line> X "+line[j].getX()+" Y "+line[j].getY());
				System.out.println("removeLine  from logic array X <logic> "+logic[i][j].getX()+" Y "+logic[i][j].getY());
				if((line[j].getX()>660)||(line[j].getY()>660)){
					System.out.println("found a block ");
				}
			}
			build.add(line);
		}
		for(int i=0;i<build.size();i++){
			Point[] terd = build.get(i);
			for(int j=0;j<16;j++){
				Point shit = terd[j];
				if(shit.getX()>660){
					System.out.println("found shit in build array   ");
				}
			}
		}
		//System.out.println("Build array size after get "+build.size());
		for(int y=0;y<40;y++){
			Point[] testLine = build.get(y);
			for(int x=0;x<16;x++){
				//Point test = logic[y][x];
				Point test = testLine[x];
				System.out.println("Point value X "+test.getX()+" Y "+test.getY());
				if(test.getX()>660){
					numberOnLine++;
					System.out.println("Point value X "+test.getX()+" Y "+test.getY());
					System.out.println("counted a block in removeline line "+y+" colum "+x);
				}
				if(numberOnLine==16){
					System.out.println("removed line on line "+y);
					build.remove(y);
					build.add(0,emptyLine);
				}
			}
			numberOnLine=0;
		}
		for(int y=0;y<build.size();y++){
			Point[] temp = build.get(y);
			for(int x=0;x<16;x++){
				logic[y][x] = temp[x];
				//System.out.println("place back in logic array X "+ logic[y][x].getX()+" Y "+logic[y][x].getY()+" Line Number "+y);
				if(temp[x].getX()>660){
					System.out.println("place back in logic array X "+ logic[y][x].getX()+" Y "+logic[y][x].getY()+" Line Number "+y);
				}
			}
		}
		printLogicArray();
	}
	
	public void printLogicArray(){
		for(int y=0;y<40;y++){
			for(int x=0;x<16;x++){
				System.out.print(" "+logic[y][x].getX()+ ","+logic[y][x].getY()+" ");
			}
			System.out.println();
		}
	}
	public void stationary(Point[][] toAdd, int blockType){
		ArrayList<BlockToDisplay> adding = new ArrayList<>();
		adding = addPieceToLogicArray(toAdd, blockType);
		for (int i =0;i<adding.size();i++){
			BlockToDisplay temp = adding.get(i);
			Point p1 = temp.getLocation();
			logic[p1.getY()/25][p1.getX()/25] = new Point(temp.getType(),temp.getType());
		}
	}
	
	public ArrayList<BlockToDisplay> addPieceToLogicArray(Point[][] toAdd, int blockType){
		Point test = new Point(660,660);
		Point currLoc = new Point(0,0);
		int count=0;
		ArrayList<BlockToDisplay> addToScreen = new ArrayList<>();
		for(int y=0;y<4;y++){
			for( int x=0;x<4;x++){
				//System.out.print("x "+x+" y "+y);
				if((toAdd[y][x].getX()==0)&&(toAdd[y][x].getY()==0)){
					
				}else{
					switch(blockType){
					case 0:
						test = new Point(661, 661);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());                       //the values stored by the shape
						//System.out.println(" add piece shape values in X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 661));
						break;
					case 1:
						test = new Point(662, 662);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 662));
						break;
					case 2:
						test = new Point(663, 663);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 663));
						break;
					case 3:
						test = new Point(664, 664);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 664));
						break;
					case 4:
						test = new Point(665, 665);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 665));
						break;
					case 5:
						test = new Point(666, 666);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 666));
						break;
					case 6:
						test = new Point(667, 667);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 667));
						break;
					case 7:
						test = new Point(668, 668);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 668));
						break;
					case 8:
						test = new Point(669, 669);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 669));
						break;
					case 9:
						test = new Point(670, 670);      //first block type
						currLoc = new Point(toAdd[y][x].getX(),toAdd[y][x].getY());
						//System.out.println("X "+currLoc.getX()+" Y "+ currLoc.getY());
						addToScreen.add(new BlockToDisplay(currLoc, 670));
						break;	
					}
				}
			}
		}
		return addToScreen;
	}
	
	public ArrayList<BlockToDisplay> getLogic(){
		ArrayList<BlockToDisplay> onScreen = new ArrayList<>();
		Point temp = new Point(0,0);
		for(int y=0;y<40;y++){
			for(int x=0;x<16;x++){
				if(logic[y][x].getX()==661){
					temp = coordinate[y][x];  //get point from coordinates
				//	System.out.println("X "+temp.getX()+" Y "+temp.getY());
					BlockToDisplay btd = new BlockToDisplay(temp,0);     //make new block holder
					onScreen.add(btd);                                    //add to screen array
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 0 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==662){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,1);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 1 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==663){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,2);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 2 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==664){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,3);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 3 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==665){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,4);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 4 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==666){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,5);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 5 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==667){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,6);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 6 "+logic[y][x].getX());
				}else if(logic[y][x].getX()==668){
					//System.out.println("x "+x+" y "+y);
					temp = coordinate[y][x];
					BlockToDisplay btd = new BlockToDisplay(temp,7);
					onScreen.add(btd);
					//System.out.println("x "+btd.getLocation().getX()+" y "+btd.getLocation().getX());
					//System.out.println("found block getLogic type 7 "+logic[y][x].getX());
				}
			}
		}
		return onScreen;
	}
	
			
	public void coOrdinate()
	{
		System.out.println("coordinate called");
		for(int i=0;i<40;i++)
		{
			for(int j=0;j<16;j++)
			{
				//System.out.print("x " + x+ " y " + y);
				coordinate[i][j] = new Point(x,y);
				x += 25;
					
			}
			y += 25;
			x = 0;
			System.out.println();
		}	
	}
	
	public Point getPoint(int xz, int yz)
	{
		Point result = coordinate[yz][xz];
		return result;
		
	}
	
}