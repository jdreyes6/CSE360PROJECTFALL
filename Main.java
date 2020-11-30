package project01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
//import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

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
        i2 atendLoad = new i2();
        i2.addActionListener(atendLoad);
        MenuItem i3=new MenuItem("Save");
        i3 save = new i3();
        i3.addActionListener(save);
        MenuItem i4=new MenuItem("Plot data");
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
            //System.out.print("test1");
            firstRead= getRosterFile(); // uncomment this to see a csv file read
            table(firstRead);
            repaint();
            revalidate();
        }

        public void table(String[][] rows)
        {
            // Copy and paste this whole section into main to see how table works and looks
            setLayout(new FlowLayout());
            //System.out.println("table");
            String[] columnNames = {"ID", "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"};
            table = new JTable(rows, columnNames);
            DefaultTableCellRenderer hr = new DefaultTableCellRenderer();
            hr.setHorizontalAlignment(JLabel.LEFT);
            table.getTableHeader().setDefaultRenderer(hr);
            table.setPreferredScrollableViewportSize(new Dimension (800, 300));
            table.setFillsViewportHeight(true);
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
            //System.out.println(filePath);
            try {
                //System.out.println("br");
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    //System.out.println(line);
                    rowNumber++;
                }
                //System.out.println(rowNumber);
                String[][] totalData=new String[rowNumber][6];
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    //System.out.println(line);
                    String[] rowData = line.split(","); // parses data based on ,
                    //System.out.println(rowData.getClass().getTypeName());
                    for(int i=0;i<6;i++) {
                        //System.out.println(rowData[i]);
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                //System.out.println("return");
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            //System.out.println(filePath);
            //System.out.println(rowData[0]);
        }

        //System.out.println("wrong");
        return null; // ideally return data for table .?
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
    public class i2 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	if(add == 0)
        	{
        		//System.out.print("test2");
        		input = JOptionPane.showInputDialog("Please enter date?");
        		String[][] attendRows= getAttendanceFile(); // uncomment this to see a csv file read
        		attendTable2(attendRows);
        		attendTable(firstRead);
        		scrollPane1.removeAll();
        		repaint();
        		revalidate();
        		add++;
        	}
        	else
        	{
        		input = JOptionPane.showInputDialog("Please enter date?");
        		String[][] attendRows= getAttendanceFile(); // uncomment this to see a csv file read
                attendTable2(attendRows);
        		model.addColumn(input);

                positionColumn(table,table.getColumnCount()-1);
                
                for(int x = 0; x < table.getRowCount(); x++)
                {
                	table.setValueAt("", x, table.getColumnCount()-1);
                }
          
                //table2.setValueAt("7", 1, 6);
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
            // Copy and paste this whole section into main to see how table works and looks
            setLayout(new FlowLayout());
            JFrame frame = new JFrame("Inserting a Column Example!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //System.out.println("attendTable");
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
      
            //table2.setValueAt("7", 1, 6);
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
            // Copy and paste this whole section into main to see how table works and looks
            setLayout(new FlowLayout());
            //System.out.println("attendTable");
            String[] columnNames = {"ASU ID", "time"};
            smallTable = new JTable(attendRows, columnNames);
            JScrollPane scrollPane = new JScrollPane(smallTable);
            //add(scrollPane);
        }
    }

    
    //Actionlistener for save button 
    public class i3 implements ActionListener
    {
    	@Override
    	public void actionPerformed(ActionEvent e) 
    	{
    		//System.out.print("test3");
    		
    		//System.out.print("Did it Work?");
    		
    		//If we have a certain file that we want to save it to
    		//Change "Book2.csv" to the csv file that reupdate it to
    		exportToCSV(table, "saveFile.csv");
  
    		
    	}
    	
    	public boolean exportToCSV(JTable table, String file) 
    	{
    		try {

    	        TableModel model = table.getModel();
    	        FileWriter csv = new FileWriter(file);

    	        for (int i = 0; i < model.getColumnCount(); i++) {
    	            csv.write(model.getColumnName(i) + ",");
    	        }

    	        csv.write("\n");

    	        for (int i = 0; i < model.getRowCount(); i++) {
    	            for (int j = 0; j < model.getColumnCount(); j++) {
    	                csv.write(model.getValueAt(i, j).toString() + ",");
    	            }
    	            csv.write("\n");
    	        }
    	        //System.out.print("Worked");
    	        csv.close();
    	        return true;
    	        
    	        
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    		
    		 //System.out.print("did NOT Worked");
    	    return false;
    	}
    }
    
    private static String[][] getAttendanceFile(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv"); // filter only csv files
        fc.setFileFilter(filter);
        java.io.File filePath = null;
        int returnVal = fc.showOpenDialog(null);
        String line = "";

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = fc.getSelectedFile();  // gets file path of selected file
            //System.out.println(filePath);
            try {
                //System.out.println("br");
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    //System.out.println(line);
                    rowNumber++;
                }
                //System.out.println(rowNumber);
                String[][] totalData=new String[rowNumber][2];
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    //System.out.println(line);
                    String[] rowData = line.split(","); // parses data based on ,
                    //System.out.println(rowData.getClass().getTypeName());
                    for(int i=0;i<2;i++) {
                        //System.out.println(rowData[i]);
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                //System.out.println("return");
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            //System.out.println(filePath);
            //System.out.println(rowData[0]);
        }

        //System.out.println("wrong");
        return null; // ideally return data for table .?
    }

    
    public void positionColumn(JTable table,int col_Index) {
    	  table.moveColumn(table.getColumnCount()-1, col_Index);
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