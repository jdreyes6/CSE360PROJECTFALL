package project01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/* 
 * Description: Main GUI for the final project
 * 
 */

public class Main extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	//Sizes of main GUI
	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 1000;
	
	//Panels
	Button file = new Button("File");
	Button about = new Button("About");
	
	
	public Main() {
		//Print out the Main Frame with File and about 
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("CSE360 Final Project");
		this.setVisible(true);
		this.setResizable(false); //Locks the resizing of the Frame once it is open
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		//Actions
		File actionFile = new File();
		file.addActionListener(actionFile);
		About actionAbout = new About();
		about.addActionListener(actionAbout);
		
		//Top panel
		JPanel top = new JPanel();		
		top.add(file);
		top.add(about);
		
		this.add(top,BorderLayout.NORTH);
	
		/*
		 * This is for the roster to open up
		 */
		//Middle
		JPanel middle = new JPanel();
				
	}
	


	//File ActionListener
	public class File implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Pop-up for the File option
			JFrame files = new JFrame("File");
			files.setSize(500,300);
			
			//Prints out this sample text
			String file_info = " This is just an example text";
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage(file_info);
			files.add(optionPane);
			
			files.setVisible(true);
		}
		
	}
	
	//About ActionListener
	public class About implements ActionListener {
		
		@Override
		//Pop-up for the File option
		public void actionPerformed(ActionEvent e) {
			JFrame about = new JFrame("About");
			about.setSize(500,300);
			
			//Prints out this sample text
			String about_info = "This program will keep track of the students \n"
					+ "who are participating in class. \n"
					+ "As well as it will show a plotted data of the attendance. \n";
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage(about_info);
			about.add(optionPane);
			
			about.setVisible(true);
		}
		
		
	}
	

	public static void main(String[] args) {
		Main main = new Main();
		
		main.setTitle("CSE360 Final Project");
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
