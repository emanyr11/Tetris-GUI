package Application;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import Shapes.CurrentBlock;


public class Controller implements KeyListener {
	
	TetrisGUIMain game;
	public boolean left, right, down, rotate, pause;
	
	public Controller(TetrisGUIMain game)
	{
		this.game = game;
	}
	
	
	public void keyPressed(KeyEvent e)
	{
		if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.left))
		{
			if(!TetrisGUIMain.alreadyMoving){
				if(CurrentBlock.xOffset>0){
					TetrisGUIMain.moveLeft = true;
				}
			}
			e.consume();
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.right))
		{
			if(!TetrisGUIMain.alreadyMoving){
				if(CurrentBlock.xOffset<12){
					TetrisGUIMain.moveRight = true;
				}
			}
			e.consume();
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.rotate))
		{
			rotate = true;
			e.consume();
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.down))
		{
			down = true;
			e.consume();
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.pause))
		{
			pause = true;
			e.consume();
		}
		
	}
	public void keyTyped(KeyEvent e)
	{
	
		
	}
	public void keyReleased(KeyEvent e)
	{
		if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.left))
		{
			left = false;
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.right))
		{
			right = false;
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.rotate))
		{
			rotate = false;
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.down))
		{
			down = false;
		}
		else if(KeyEvent.getKeyText(e.getKeyCode()).equals(Config.pause))
		{
			pause = false;
		}
	}

}
