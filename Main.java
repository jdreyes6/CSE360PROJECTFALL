package project01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.*;
import java.io.*;

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
	MenuItem about = new MenuItem("About");
	MenuItem load = new MenuItem("Load a Roaster");
	
	
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
		//top.add(file);
		//top.add(about);
		
		this.add(top,BorderLayout.NORTH);
	
		/*
		 * This is for the roster to open up
		 */
		//Middle
		JPanel middle = new JPanel();
		
		
		 MenuBar mb=new MenuBar();  
         Menu menu=new Menu("File");  
         Menu menu2 = new Menu("About");
         menu2.add(about);
         //All of these need to be replaced with MenuItems
         MenuItem i1=new MenuItem("Load a Roaster"); 
		 i1 actionLoad = new i1();
         i1.addActionListener(actionLoad);
		 MenuItem i2=new MenuItem("Add Attendence");  
         MenuItem i3=new MenuItem("Save");  
         MenuItem i4=new MenuItem("Plot data");  
         menu.add(i1);  
         menu.add(i2);  
         menu.add(i3); 
         menu.add(i4);
         mb.add(menu);  
         mb.add(menu2);
         this.setMenuBar(mb);  
         
      
				
	}
	
	
	public class i1 implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.print("test");
			//getFile(); // uncomment this to see a csv file read
			table();

			

		}
		public void table()
		{
			// Copy and paste this whole section into main to see how table works and looks
			setLayout(new FlowLayout());
			
			String[] columnNames = {"1", "2", "3", "4", "5", "6"};
			Object[][] data1 = {{"bruh1", "bruh2", "bruh3", "bruh4", "bruh5", "bruh6"}};

			JTable table = new JTable(data1, columnNames);
			table.setPreferredScrollableViewportSize(new Dimension (500, 50));
			table.setFillsViewportHeight(true);

			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane);
		}
	}

	
	private static java.io.File getFile(){
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv"); // filter only csv files
		fc.setFileFilter(filter);

	    java.io.File filePath = null;
		int returnVal = fc.showOpenDialog(null);
		String line = "";

	    if (returnVal == JFileChooser.APPROVE_OPTION) {
			filePath = fc.getSelectedFile();  // gets file path of selected file
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));

				while ((line = br.readLine()) != null)
				{
					System.out.println(line);
					String[] rowData = line.split(","); // parses data based on ,
					//Object[][] data = { {rowData[0]}, {rowData[1]}, {rowData[2]}, {rowData[3]}, {rowData[4]}, {rowData[5]} };
				}
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			System.out.println(filePath);
			//System.out.println(rowData[0]);
	    } 
	    return filePath; // ideally return data for table .?
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