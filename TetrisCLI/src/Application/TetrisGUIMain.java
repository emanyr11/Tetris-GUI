package Application;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GameArrays.BlockToDisplay;
import GameArrays.LogicDisplayCoordinateArrays;
import GameArrays.Point;
import Shapes.BasicShape;
import Shapes.CurrentBlock;
import Shapes.LShape;
import Shapes.SquareShape;
import Shapes.StraightShape;
import Shapes.TShape;
import Shapes.ZigZagShape;
/**
 * 
 * 
 * 
 * @author Eman
 *
 */
public class TetrisGUIMain extends Canvas implements Runnable{
	
	static Point startBlock = new Point(200,0);
	static CurrentBlock cb = new CurrentBlock();
	static LShape lShape = new LShape(startBlock.getX(), startBlock.getY());
	static SquareShape squareShape = new SquareShape(startBlock.getX(), startBlock.getY());
	static StraightShape straightShape = new StraightShape(startBlock.getX(), startBlock.getY());
	static TShape tShape = new TShape(startBlock.getX(), startBlock.getY());
	static ZigZagShape zigZagShape = new ZigZagShape(startBlock.getX(), startBlock.getY());
	static LogicDisplayCoordinateArrays ldca = new LogicDisplayCoordinateArrays();
	public static final int WIDTH = 400, HEIGHT = 800;
	static int xCord = 0;
	static int yCord = 0;
	static int nextXCord = 0;
	static int nextYCord = 0;
	static int dropSpeed = 1;
	static String frames="";
	static int counter=0;
	static Point[][] current = cb.getEmptyShape();
	Point lock = ldca.getPoint(0, 0);
	public static int blockNumber = 0;
	public static Image[] tetrisBlocks;
	Controller control;
	private int FPS =60;
	private long targetTime = 1000/FPS;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	public static boolean alreadyMoving = false;
	Random rand = new Random();
	static BasicShape screenShape = new BasicShape(startBlock.getX(),startBlock.getY());
	static ArrayList<BlockToDisplay> onScreen = new ArrayList<>();
	
	public static void main(String[] args) 	{
			
		
		
		
		//cb.setLShape(lShape);                     //set the block shape
		//cb.setZigZagShape(zigZagShape);
		cb.setSquareShape(squareShape);
		current = cb.loadBlockCoords(current,8);             //initialise the block with coordords
		screenShape = straightShape;
		ldca.setNewDisplay();
		
		
			
			final JFrame frame = new JFrame("Tetris");
			frame.setSize(WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setLayout(null);
			KeyGetter.loadKeys();
			try 
			{
				Config.loadConfig();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JMenu file = new JMenu("File");
			file.setBounds(0, 0, 45, 24);
			JMenuBar bar = new JMenuBar();
			bar.setBounds(0, 0, WIDTH, 25);
			JMenuItem newGame = new JMenuItem("New Game");
			newGame.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// Code for New Game
					System.out.println("Starting new Game ... ");
				}
				
			});
			JMenuItem highScore = new JMenuItem("Highscore");
			highScore.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// Code for High score
					int highscore = 1;
					JLabel score = new JLabel("The Highscore is "+ highscore);
					score.setBounds(0, 0, 200, 50);
					JFrame alert = new JFrame("Highscore");
					JButton okayButton = new JButton("Okay");
					okayButton.setBounds(50, 120, 100, 30);
					okayButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							alert.dispose();							
						}
					});
					
					
					alert.add(score);
					alert.add(okayButton);
					// alert.setAlwaysOnTop(true);
					alert.setSize(400, 200);
					alert.setLayout(null);
					alert.setLocationRelativeTo(null);
					alert.setResizable(false);
					alert.setVisible(true);
				}
				
			});
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// Code for new game
					System.out.println("Closing ...");
					System.exit(0);
				}
				
			});
			JMenuItem options = new JMenuItem("Options");
			options.addActionListener(new ActionListener()
			{
					public void actionPerformed(ActionEvent e)
					{
						Config.openConfig(frame);
					}
			
			});
			
			
			
			
			TetrisGUIMain tm = new TetrisGUIMain();
			tm.setBounds(0, 25, WIDTH, HEIGHT - 25);
			
			frame.add(tm);
			file.add(newGame);
			file.add(highScore);
			file.add(options);
			file.add(exit);
			frame.add(bar);
			bar.add(file);
			frame.setVisible(true);
			tm.start();
	}
	
	public void start()
	{
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	
	public void newBlockStart(){
		ldca.clearLine();
		onScreen = ldca.getLogic();
		CurrentBlock.height = 0;
		CurrentBlock.xOffset = 6;
		//int nextBlock = rand.nextInt(5)+1;
		int nextBlock =3;
		current = cb.getEmptyShape();
		switch(nextBlock){
		case 1:
			cb.setStrraightShape(straightShape);
			screenShape = straightShape;
			blockNumber =0;
			break;
		case 2:
			cb.setLShape(lShape);
			screenShape =lShape;
			blockNumber =1;
			break;
		case 3:
			cb.setSquareShape(squareShape);
			screenShape =squareShape;
			blockNumber =2;
			break;
		case 4:
			cb.setZigZagShape(zigZagShape);
			screenShape = zigZagShape;
			blockNumber =3;
			break;
		case 5:
			cb.setTShape(tShape);
			screenShape = tShape;
			blockNumber =4;
			break;
		}
		current = cb.loadBlockCoords(current,8); 
	}
	public void run(){
	
	boolean running = true;
	init();
	while(running){
		long start, elapsed, wait;
		
		
			if((counter%20)==0){
				//repaint();
				update();
				//System.out.println("looping?");
				
				if(moveLeft==true){
					if(cb.canBlockMoveLeft(current)){
						CurrentBlock.xOffset = CurrentBlock.xOffset-1;
					}
					moveLeft=false;
				}
				if(moveRight==true){
					if(cb.canBlockMoveRight(current)){
						CurrentBlock.xOffset = CurrentBlock.xOffset+1;
					}
					moveRight = false;
				}
				
				if((cb.testBlockDown(current))&&(ldca.blockCollision(current))){
					current = cb.moveBlockDown(current);
					alreadyMoving = false;
					
					
				}else{
					ldca.stationary(current, blockNumber);
					onScreen = ldca.getLogic();
					newBlockStart();
				}
			}//end of step
			
			start = System.nanoTime();
			BufferStrategy buf = getBufferStrategy();
			if(buf == null)
			{
				createBufferStrategy(3);
				continue;
			}
			
			Graphics g =  buf.getDrawGraphics();
			paintComponent(g);
			buf.show();
		counter++;
		frames = ""+counter;
		
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			
			if(wait<=0){
				wait=5;
			}
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calabri",Font.PLAIN, 20));
		g.drawString("Tetris", 175, 50);
		//System.out.println("stationary array size in paint component "+onScreen.size());
		for(int i=0;i<onScreen.size();i++){
			Point temp = onScreen.get(i).getLocation();
			//System.out.println("in paint component draw image at X "+temp.getX()+ ","+temp.getY());
			g.drawImage(tetrisBlocks[onScreen.get(i).getType()], temp.getX(), temp.getY(), 25, 25,null);
		}
		
		if((current[0][0].getX()!=0)&&(current[0][0].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[0][0].getX(), current[0][0].getY(), 25, 25, null);
		}
		if((current[0][1].getX()!=0)&&(current[0][1].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[0][1].getX(), current[0][1].getY(), 25, 25, null);
		}
		if((current[0][2].getX()!=0)&&(current[0][2].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[0][2].getX(), current[0][2].getY(), 25, 25, null);
		}
		if((current[0][3].getX()!=0)&&(current[0][3].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[0][3].getX(), current[0][3].getY(), 25, 25, null);
		}
		if((current[1][0].getX()!=0)&&(current[1][0].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[1][0].getX(), current[1][0].getY(), 25, 25, null);
		}
		if((current[1][1].getX()!=0)&&(current[1][1].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[1][1].getX(), current[1][1].getY(), 25, 25, null);
		}
		if((current[1][2].getX()!=0)&&(current[1][2].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[1][2].getX(), current[1][2].getY(), 25, 25, null);
		}
		if((current[1][3].getX()!=0)&&(current[1][3].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[1][3].getX(), current[1][3].getY(), 25, 25, null);
		}
		if((current[2][0].getX()!=0)&&(current[2][0].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[2][0].getX(), current[2][0].getY(), 25, 25, null);
		}
		if((current[2][1].getX()!=0)&&(current[2][1].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[2][1].getX(), current[2][1].getY(), 25, 25, null);
		}
		if((current[2][2].getX()!=0)&&(current[2][2].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[2][2].getX(), current[2][2].getY(), 25, 25, null);
		}
		if((current[2][3].getX()!=0)&&(current[2][3].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[2][3].getX(), current[2][3].getY(), 25, 25, null);
		}
		if((current[3][0].getX()!=0)&&(current[3][0].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[3][0].getX(), current[3][0].getY(), 25, 25, null);
		}
		if((current[3][1].getX()!=0)&&(current[3][1].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[3][1].getX(), current[3][1].getY(), 25, 25, null);
		}
		if((current[3][2].getX()!=0)&&(current[3][2].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[3][2].getX(), current[3][2].getY(), 25, 25, null);
		}
		if((current[3][3].getX()!=0)&&(current[3][3].getY()!=0)){
			g.drawImage(tetrisBlocks[blockNumber], current[3][3].getX(), current[3][3].getY(), 25, 25, null);
		}
		g.drawString(frames, 20, 20);
		//g.drawImage(tetrisBlocks[blockNumber], xCord, yCord, 25, 25, null);
	}
	
	public void init()
	{
		control = new Controller(this);
		this.addKeyListener(control);
		requestFocus();
		try 
		{
			tetrisBlocks = ImageLoader.loadImage("/Tetris blocks.png", 25);
		} 
		catch (IOException e) 
		{
			System.out.println("Error loading image");
			System.exit(1);
		}
	}

	public void update() 
	{
		//System.out.println(control.left + " : " + control.right + " : " + control.rotate + " : " + control.down + " : " + control.pause + " : ");
	}


}
