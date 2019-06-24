package Application;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Config {
	
	public static String rotate = "Up", left = "Left", right = "Right", down = "Down", pause = "P";
	private static ArrayList<Choice> choices;
	
	public static void openConfig(JFrame frame)
	{
		choices = new ArrayList<Choice>();
		final JFrame options = new JFrame("Options");
		options.setSize(400,300);
		options.setResizable(false);
		options.setLocationRelativeTo(frame);
		options.setLayout(null);
		Choice left = addChoice("Left", options, 10, 40 );
		left.select(Config.left);
		Choice right = addChoice("Right", options, 150, 40 );
		right.select(Config.right);
		Choice rotate = addChoice("Rotate", options, 10, 80);
		rotate.select(Config.rotate);
		Choice down = addChoice("Down", options, 150, 80 );
		down.select(Config.down);
		Choice pause = addChoice("Pause", options, 10, 120 );
		pause.select(Config.pause);
		JButton done = new JButton("Done");
		done.setBounds(150, 220, 100, 30);
		done.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e)
			{
				options.dispose();
				saveChanges();
			}
			
		});
		options.add(done);
		options.setVisible(true);
	}
	
	public static void saveChanges()
	{
		Choice left = choices.get(0);
		Choice right = choices.get(1);
		Choice rotate = choices.get(2);
		Choice down = choices.get(3);
		Choice pause = choices.get(4);
		Config.left = left.getSelectedItem();
		Config.right = right.getSelectedItem();
		Config.rotate = rotate.getSelectedItem();
		Config.down = down.getSelectedItem();
		Config.pause = pause.getSelectedItem();
		try 
		{
			saveConfig();
		} catch (Exception e1)
		{
			
			e1.printStackTrace();
		}
	}
	
	public static Choice addChoice(String name, JFrame options, int x, int y){
		JLabel label = new JLabel(name);
		label.setBounds(x, y - 20, 100, 20);
		Choice Key = new Choice();
		for(String s: getKeyNames())
		{
			Key.add(s);
		}
		Key.setBounds(x, y, 100, 10);
		options.add(Key);
		options.add(label);
		choices.add(Key);
		return Key;
		
	}
	
	public static ArrayList<String>  getKeyNames(){
		ArrayList<String> result = new ArrayList<String>();
		for(String s: KeyGetter.keyNames)
		{
			result.add(s);
			if(s.equalsIgnoreCase("F24"))
			{
				break;
			}
			
	}
		return result;
	}
	
	public static void loadConfig() throws Exception
	{
		File directory = new File(getDefaultDirectory(), "/Tetris");
		if(!directory.exists())
		{
			directory.mkdirs();
		}
		File config = new File(directory, "/config.txt");
		if(!config.exists())
		{
			config.createNewFile();
			System.out.println("Saving defaults");
			saveConfig();
			return;
		}	
		Scanner scanner = new Scanner(config);
		HashMap<String,String> values = new HashMap<String,String>();
		while(scanner.hasNextLine())
		{
			String[] entry= scanner.nextLine().split(":");
			String key = entry[0];
			String value = entry[1];
			values.put(key, value);			
		}
		if(values.size() != 5)
		{
			System.out.println("Config unusable saving defaults");
			saveConfig();
			return;
		}
		if(!values.containsKey("left") || !values.containsKey("right") || !values.containsKey("rotate") || !values.containsKey("down") || !values.containsKey("pause"))
		{
			System.out.println("Invalid names in config, saving defaluts");
			saveConfig();
			return;
		}
		String left = values.get("left");
		String right = values.get("right");
		String rotate = values.get("rotate");
		String down = values.get("down");
		String pause = values.get("pause");
		
		if(!(getKeyNames().contains(left) && getKeyNames().contains(right) && getKeyNames().contains(rotate) && getKeyNames().contains(down) && getKeyNames().contains(pause)))
		{
			System.out.println("Invalid key in config, saving defaluts");
			saveConfig();
			return;
		}
		Config.left = left;
		Config.right = right;
		Config.rotate = rotate;
		Config.down = down;
		Config.pause = pause;
		
		
	}	
	//File IO
	public static void saveConfig() throws Exception
	{
		File directory = new File(getDefaultDirectory(), "/Tetris");
		if(!directory.exists())
		{
			directory.mkdirs();
		}
		File config = new File(directory, "/config.txt");
		PrintWriter pw = new PrintWriter(config);
		pw.println("right:" + right);
		pw.println("left:" + left);
		pw.println("down:" + down);
		pw.println("rotate:" + rotate);
		pw.println("pause:" + pause);
		pw.close();
	}
	
	public static String getDefaultDirectory()
	{
		String os = System.getProperty("os.name").toUpperCase();
		if(os.contains("WIN"))
		{
			return System.getenv("APPDATA");
		}
		if(os.contains("MAC"))
		{
			return System.getProperty("user.home") + "Library/Application Support";
		}
		return System.getProperty("user.home"); // Linux
	}
}
