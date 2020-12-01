package project01;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;  


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
    JTable table;
    DefaultTableModel model;
    String[][] firstRead;
    JScrollPane scrollPane1;
    JTable smallTable;
    JTable new_table;
    int users = 0;
    int aditional = 0;
    int counted = 0;
    int add = 0;
    String input;
    


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
        MenuItem i1=new MenuItem("Load a Roaster");
        i1 actionLoad = new i1();
        i1.addActionListener(actionLoad);
        MenuItem i2=new MenuItem("Add Attendence");
        i2 atendLoad = new i2();
        i2.addActionListener(atendLoad);
        MenuItem i3=new MenuItem("Save");
        i3 save = new i3();
        i3.addActionListener(save);
        MenuItem i4=new MenuItem("Plot data");
        i4 plot = new i4();
        i4.addActionListener(plot);
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        mb.add(menu);
        mb.add(menu2);
        this.setMenuBar(mb);
        this.setLocationRelativeTo(null);
    }


    public class i1 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            firstRead= getRosterFile();
            table(firstRead);
            repaint();
            revalidate();
        }

        //table gets the row data and show them in the table
        public void table(String[][] rows)
        {
            setLayout(new FlowLayout());
            String[] columnNames = {"ID", "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"};
            table = new JTable(rows, columnNames);
            DefaultTableCellRenderer hr = new DefaultTableCellRenderer();
            hr.setHorizontalAlignment(JLabel.LEFT);
            table.getTableHeader().setDefaultRenderer(hr);
            table.setPreferredScrollableViewportSize(new Dimension (800, 300));
            table.setFillsViewportHeight(true);
            
            //set the width of each column
            table.getColumnModel().getColumn(0).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(160);
            table.getColumnModel().getColumn(2).setPreferredWidth(160);
            table.getColumnModel().getColumn(3).setPreferredWidth(460);
            table.getColumnModel().getColumn(4).setPreferredWidth(300);
            table.getColumnModel().getColumn(5).setPreferredWidth(180);
            scrollPane1 = new JScrollPane(table);
            add(scrollPane1);
        }
    }


    private static String[][] getRosterFile(){
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
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    rowNumber++;//get the total count of rows
                }
                String[][] totalData=new String[rowNumber][6];//totalData to store the content read from the file
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    String[] rowData = line.split(","); // parses data based on
                    for(int i=0;i<6;i++) {
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
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
        //show team information in JDialog
        public void actionPerformed(ActionEvent e) {
            JFrame about = new JFrame("About");
            JDialog info = new JDialog(about, "About"); 
            JLabel first = new JLabel("Team members:"); 
            first.setBounds(100, 37, 300, 15);
            JLabel second = new JLabel("Jianlei Jiang");
            second.setBounds(100, 65, 300, 15);
            JLabel third = new JLabel("Keyth Ybanez");
            third.setBounds(100, 80, 300, 15);
            JLabel forth = new JLabel("Jason Reyes");
            forth.setBounds(100, 95, 300, 15);
            JLabel fifth = new JLabel("Joel Woodyard");
            fifth.setBounds(100, 110, 300, 15);
            JLabel sixth = new JLabel("Haomiao Liu");
            sixth.setBounds(100, 125, 300, 15);
            JLabel seventh = new JLabel("");
            about.add(first); 
            about.add(second);
            about.add(third);
            about.add(forth);
            about.add(fifth);
            about.add(sixth);
            about.add(seventh);
            about.setSize(400, 400); 
            about.setLocationRelativeTo(null);

            // set visibility of dialog 
            about.setVisible(true);     
        }
    }
    
    //add attendance 
    public class i2 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	if(add == 0)//no new data added
        	{
        		//it will get the input data as the new column (date and attendance-duration)
        		input = JOptionPane.showInputDialog("Please enter date?");
        		String[][] attendRows= getAttendanceFile();
        		attendTable2(attendRows);
        		attendTable(firstRead);
        		scrollPane1.removeAll();
        		repaint();
        		revalidate();
        		add++;
        	}
        	//already add data
        	//add all new data to the table and show them
        	else
        	{
        		input = JOptionPane.showInputDialog("Please enter date?");
        		String[][] attendRows= getAttendanceFile();
                attendTable2(attendRows);
        		model.addColumn(input);

                positionColumn(table,table.getColumnCount()-1);
                
                for(int x = 0; x < table.getRowCount(); x++)
                {
                	table.setValueAt("", x, table.getColumnCount()-1);
                }
          
                for(int x = 0; x < table.getRowCount(); x++)
                {
                	for(int y = 0; y < smallTable.getRowCount(); y++)
                	{
                		if(table.getValueAt(x, 5).equals(smallTable.getValueAt(y, 0)))
                    	{
                			if(table.getValueAt(x, table.getColumnCount()-1).equals(""))
                			{
                				table.setValueAt(smallTable.getValueAt(y, 1), x, table.getColumnCount()-1);
                				users++;
                				smallTable.setValueAt("", y, 1);
                			}
                			else
                			{
                				table.setValueAt(Integer.toString(Integer.parseInt((String) smallTable.getValueAt(y, 1)) + Integer.parseInt((String) table.getValueAt(x, table.getColumnCount()-1))), x, table.getColumnCount()-1);
                				smallTable.setValueAt("", y, 1);
                			}
                    	}
                	}
                }
                for(int y = 0; y < smallTable.getRowCount(); y++)
                {
                	if (smallTable.getValueAt(y, 1).equals(""))
                	{
                		counted++;
                	}
                }
                
                //a new frame of JDialog showing the data-loading information
                aditional = (smallTable.getRowCount()) - counted;
                JFrame f = new JFrame(); 
                
                f.setSize(1000, 1000);
                JDialog d = new JDialog(f, "Report"); 
                JLabel first = new JLabel("Data loaded for " + users + " users in the roster."); 
                users = 0;
                first.setBounds(86, 37, 300, 15);
                JLabel second = new JLabel(aditional +" aditional attendee was found.");
                counted = 0;
                second.setBounds(86, 80, 300, 15);
                JLabel third = new JLabel("");
                String temp = "";
                int holder = 0;
                if(aditional > 0)
                {
                	for(int y = 0; y < smallTable.getRowCount(); y++)
                	{
                		if(smallTable.getValueAt(y, 1) != "")
                		{
                			temp = (String) smallTable.getValueAt(y, 0);
                			holder = Integer.parseInt((String) smallTable.getValueAt(y, 1));
                		}
                	}
                	third = new JLabel(temp + ", connected for " + holder + " minutes");
                }
                third.setBounds(86, 130, 300, 15);
                aditional = 0;
                third.setBounds(86, 130, 300, 15);
                JLabel forth = new JLabel("");
                d.add(first); 
                d.add(second);
                d.add(third);
                d.add(forth);
                d.setSize(400, 400); 
                d.setLocationRelativeTo(null);
                // set visibility of dialog 
                d.setVisible(true); 
        	}
        }

        public void attendTable(String[][] attendRows)
        {
            setLayout(new FlowLayout());
            JFrame frame = new JFrame("Inserting a Column Example!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String[] columnNames = {"ID", "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"};
            model = new DefaultTableModel(attendRows, columnNames);
            model.addColumn(input);
            table = new JTable(model);
            table.setPreferredScrollableViewportSize(new Dimension (800, 300));
            table.setFillsViewportHeight(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(200);
            table.getColumnModel().getColumn(1).setPreferredWidth(160);
            table.getColumnModel().getColumn(2).setPreferredWidth(160);
            table.getColumnModel().getColumn(3).setPreferredWidth(460);
            table.getColumnModel().getColumn(4).setPreferredWidth(300);
            table.getColumnModel().getColumn(5).setPreferredWidth(180);

            positionColumn(table,table.getColumnCount()-1);
            
            for(int x = 0; x < table.getRowCount(); x++)
            {
            	table.setValueAt("", x, 6);
            }
            
            for(int x = 0; x < table.getRowCount(); x++)
            {
            	for(int y = 0; y < smallTable.getRowCount(); y++)
            	{
            		if(table.getValueAt(x, 5).equals(smallTable.getValueAt(y, 0)))
                	{
            			if(table.getValueAt(x, 6).equals(""))
            			{
            				table.setValueAt(smallTable.getValueAt(y, 1), x, 6);
            				users++;
            				smallTable.setValueAt("", y, 1);
            			}
            			else
            			{
            				table.setValueAt(Integer.toString(Integer.parseInt((String) smallTable.getValueAt(y, 1)) + Integer.parseInt((String) table.getValueAt(x, 6))), x, 6);
            				smallTable.setValueAt("", y, 1);
            			}
                	}
            	}
            }
            
            for(int y = 0; y < smallTable.getRowCount(); y++)
            {
            	if (smallTable.getValueAt(y, 1).equals(""))
            	{
            		counted++;
            	}
            }
            aditional = (smallTable.getRowCount()) - counted;
            JFrame f = new JFrame(); 
            
            f.setSize(1000, 1000);
            JDialog d = new JDialog(f, "Report"); 
            JLabel first = new JLabel("Data loaded for " + users + " users in the roster."); 
            users = 0;
            first.setBounds(86, 37, 300, 15);
            JLabel second = new JLabel(aditional +" aditional attendee was found:");
            counted = 0;
            second.setBounds(86, 80, 300, 15);
            JLabel third = new JLabel("");
            String temp = "";
            int holder = 0;
            if(aditional > 0)
            {
            	for(int y = 0; y < smallTable.getRowCount(); y++)
            	{
            		if(smallTable.getValueAt(y, 1) != "")
            		{
            			temp = (String) smallTable.getValueAt(y, 0);
            			holder = Integer.parseInt((String) smallTable.getValueAt(y, 1));
            		}
            	}
            	third = new JLabel(temp + ", connected for " + holder + " minutes");
            }
            third.setBounds(86, 130, 300, 15);
            aditional = 0;
            JLabel forth = new JLabel("");
            d.add(first); 
            d.add(second);
            d.add(third);
            d.add(forth);
            d.setSize(400, 400); 
            d.setLocationRelativeTo(null);

            // set visibility of dialog 
            d.setVisible(true); 
            
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
        }
        
        public void attendTable2(String[][] attendRows)
        {
            setLayout(new FlowLayout());
            String[] columnNames = {"ASU ID", "time"};
            smallTable = new JTable(attendRows, columnNames);
            JScrollPane scrollPane = new JScrollPane(smallTable);
        }
    }

    
    //Actionlistener for save button 
    public class i3 implements ActionListener
    {
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{
    		exportToCSV(table, "saveFile.csv");	
    	}
    	
    	//it will check whether the table is empty and finally stores the table content into a csv.file
    	public boolean exportToCSV(JTable table, String file) 
    	{
    		try {

    	        TableModel model = table.getModel();
    	        FileWriter csv = new FileWriter(file);

    	        //save the header
    	        for (int i = 0; i < model.getColumnCount(); i++) {
    	            csv.write(model.getColumnName(i) + ",");
    	        }

    	        csv.write("\n");

    	        //save the contents
    	        for (int i = 0; i < model.getRowCount(); i++) {
    	            for (int j = 0; j < model.getColumnCount(); j++) {
    	                csv.write(model.getValueAt(i, j).toString() + ",");
    	            }
    	            csv.write("\n");
    	        }
    	        csv.close();
    	        return true;
    	        
    	        
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    return false;
    	}
    }
    
    //print the scatter chart
    public class i4 implements ActionListener
    {
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{
    		//final long serialVersionUID = 6294689542092367723L;
    		Main example = new Main("Scatter Chart", table);
    		example.setSize(800, 400);
    		example.setLocationRelativeTo(null);
    	    //example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	    example.setVisible(true);
    	}

    }
   
    
    public XYDataset createDataset(JTable table) {
    	//initialize ten counters
    	int percent10 = 0;
    	int percent20 = 0;
    	int percent30 = 0;
    	int percent40 = 0;
    	int percent50 = 0;
    	int percent60 = 0;
    	int percent70 = 0;
    	int percent80 = 0;
    	int percent90 = 0;
    	int percent100 = 0;
        XYSeriesCollection dataset = new XYSeriesCollection();

        	XYSeries series = new XYSeries(((String) table.getColumnName(6)));
        	//get the time of attending and calculate the percentage
        	//decide which percent the percentage will fall into and add its count
        	for(int y = 0; y < table.getRowCount(); y++)
        	{
        		if((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.1 )
        		{
        			percent10++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.2 )
        		{
        			percent20++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.3 )
        		{
        			percent30++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.4 )
        		{
        			percent40++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.5 )
        		{
        			percent50++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.6 )
        		{
        			percent60++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.7 )
        		{
        			percent70++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.8 )
        		{
        			percent80++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 6)))/75 < 0.9 )
        		{
        			percent90++;
        		}
        		else
        		{
        			percent100++;
        		}
        	}
        	series.add(10,percent10);
        	series.add(20,percent20);
        	series.add(30,percent30);
        	series.add(40,percent40);
        	series.add(50,percent50);
        	series.add(60,percent60);
        	series.add(70,percent70);
        	series.add(80,percent80);
        	series.add(90,percent90);
        	series.add(100,percent100);
        	dataset.addSeries(series);
        	percent10 = 0;
        	percent20 = 0;
        	percent30 = 0;
        	percent40 = 0;
        	percent50 = 0;
        	percent60 = 0;
        	percent70 = 0;
        	percent80 = 0;
        	percent90 = 0;
        	percent100 = 0;
        
        
        //if there are 7 more columns and we should get data of next several days of attendance
        if(table.getColumnCount() > 7)
        {
        	XYSeries series2 = new XYSeries(((String) table.getColumnName(7)));
        	for(int y = 0; y < table.getRowCount(); y++)
        	{
        		if((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.1 )
        		{
        			percent10++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.2 )
        		{
        			percent20++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.3 )
        		{
        			percent30++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.4 )
        		{
        			percent40++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.5 )
        		{
        			percent50++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.6 )
        		{
        			percent60++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.7 )
        		{
        			percent70++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.8 )
        		{
        			percent80++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 7)))/75 < 0.9 )
        		{
        			percent90++;
        		}
        		else
        		{
        			percent100++;
        		}
        	}
        	series2.add(10,percent10);
        	series2.add(20,percent20);
        	series2.add(30,percent30);
        	series2.add(40,percent40);
        	series2.add(50,percent50);
        	series2.add(60,percent60);
        	series2.add(70,percent70);
        	series2.add(80,percent80);
        	series2.add(90,percent90);
        	series2.add(100,percent100);
        	dataset.addSeries(series2);
        	percent10 = 0;
        	percent20 = 0;
        	percent30 = 0;
        	percent40 = 0;
        	percent50 = 0;
        	percent60 = 0;
        	percent70 = 0;
        	percent80 = 0;
        	percent90 = 0;
        	percent100 = 0;
        }

        if(table.getColumnCount() > 8)
        {
        	XYSeries series3 = new XYSeries(((String) table.getColumnName(8)));
        	for(int y = 0; y < table.getRowCount(); y++)
        	{
        		if((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.1 )
        		{
        			percent10++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.2 )
        		{
        			percent20++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.3 )
        		{
        			percent30++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.4 )
        		{
        			percent40++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.5 )
        		{
        			percent50++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.6 )
        		{
        			percent60++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.7 )
        		{
        			percent70++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.8 )
        		{
        			percent80++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 8)))/75 < 0.9 )
        		{
        			percent90++;
        		}
        		else
        		{
        			percent100++;
        		}
        	}
        	series3.add(10,percent10);
        	series3.add(20,percent20);
        	series3.add(30,percent30);
        	series3.add(40,percent40);
        	series3.add(50,percent50);
        	series3.add(60,percent60);
        	series3.add(70,percent70);
        	series3.add(80,percent80);
        	series3.add(90,percent90);
        	series3.add(100,percent100);
        	dataset.addSeries(series3);
        	percent10 = 0;
        	percent20 = 0;
        	percent30 = 0;
        	percent40 = 0;
        	percent50 = 0;
        	percent60 = 0;
        	percent70 = 0;
        	percent80 = 0;
        	percent90 = 0;
        	percent100 = 0;
        }
        
        if(table.getColumnCount() > 9)
        {
        	XYSeries series4 = new XYSeries(((String) table.getColumnName(9)));
        	for(int y = 0; y < table.getRowCount(); y++)
        	{
        		if((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.1 )
        		{
        			percent10++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.2 )
        		{
        			percent20++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.3 )
        		{
        			percent30++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.4 )
        		{
        			percent40++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.5 )
        		{
        			percent50++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.6 )
        		{
        			percent60++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.7 )
        		{
        			percent70++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.8 )
        		{
        			percent80++;
        		}
        		else if ((Double.parseDouble((String)table.getValueAt(y, 9)))/75 < 0.9 )
        		{
        			percent90++;
        		}
        		else
        		{
        			percent100++;
        		}
        	}
        	series4.add(10,percent10);
        	series4.add(20,percent20);
        	series4.add(30,percent30);
        	series4.add(40,percent40);
        	series4.add(50,percent50);
        	series4.add(60,percent60);
        	series4.add(70,percent70);
        	series4.add(80,percent80);
        	series4.add(90,percent90);
        	series4.add(100,percent100);
        	dataset.addSeries(series4);
        	percent10 = 0;
        	percent20 = 0;
        	percent30 = 0;
        	percent40 = 0;
        	percent50 = 0;
        	percent60 = 0;
        	percent70 = 0;
        	percent80 = 0;
        	percent90 = 0;
        	percent100 = 0;
        }
        return dataset;
      }

    
    //get the data of attendance and return the String
    private static String[][] getAttendanceFile(){
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
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    rowNumber++;
                }
                String[][] totalData=new String[rowNumber][2];
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    String[] rowData = line.split(","); // parses data based on ,
                    for(int i=0;i<2;i++) {
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    
    public void positionColumn(JTable table,int col_Index) {
    	  table.moveColumn(table.getColumnCount()-1, col_Index);
    	  }


    //draw the scatter chart
    public Main(String title, JTable table) {
    	super(title);

        // Create dataset
        XYDataset dataset = createDataset(table);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Attendance Scatter Plot", 
            "X-Axis", "Y-Axis", dataset);

        
        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        
       
        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
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