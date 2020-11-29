package project01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableCellRenderer;

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
        i2 atendLoad = new i2();
        i2.addActionListener(atendLoad);
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
            System.out.print("test1");
            String[][] rows= getRosterFile(); // uncomment this to see a csv file read
            table(rows);
            repaint();
            revalidate();
        }

        public void table(String[][] rows)
        {
            // Copy and paste this whole section into main to see how table works and looks
            setLayout(new FlowLayout());
            System.out.println("table");
            String[] columnNames = {"ID", "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"};
            JTable table = new JTable(rows, columnNames);
            DefaultTableCellRenderer hr = new DefaultTableCellRenderer();
            hr.setHorizontalAlignment(JLabel.LEFT);
            table.getTableHeader().setDefaultRenderer(hr);
            table.setPreferredScrollableViewportSize(new Dimension (800, 300));
            table.setFillsViewportHeight(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(300);
            table.getColumnModel().getColumn(1).setPreferredWidth(210);
            table.getColumnModel().getColumn(2).setPreferredWidth(210);
            table.getColumnModel().getColumn(3).setPreferredWidth(460);
            table.getColumnModel().getColumn(4).setPreferredWidth(400);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
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
            System.out.println(filePath);
            try {
                System.out.println("br");
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                    rowNumber++;
                }
                System.out.println(rowNumber);
                String[][] totalData=new String[rowNumber][6];
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    System.out.println(line);
                    String[] rowData = line.split(","); // parses data based on ,
                    //System.out.println(rowData.getClass().getTypeName());
                    for(int i=0;i<6;i++) {
                        System.out.println(rowData[i]);
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                System.out.println("return");
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.out.println(filePath);
            //System.out.println(rowData[0]);
        }

        System.out.println("wrong");
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
    public class i2 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("test2");
            String[][] attendRows= getAttendanceFile(); // uncomment this to see a csv file read
            attendTable(attendRows);
            repaint();
            revalidate();
        }

        public void attendTable(String[][] attendRows)
        {
            // Copy and paste this whole section into main to see how table works and looks
            setLayout(new FlowLayout());
            System.out.println("attendTable");
            String[] columnNames = {"ASU ID", "time"};
            JTable table = new JTable(attendRows, columnNames);
            DefaultTableCellRenderer hr = new DefaultTableCellRenderer();
            hr.setHorizontalAlignment(JLabel.LEFT);
            table.getTableHeader().setDefaultRenderer(hr);
            table.setPreferredScrollableViewportSize(new Dimension (800, 300));
            table.setFillsViewportHeight(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(300);
            table.getColumnModel().getColumn(1).setPreferredWidth(300);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
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
            System.out.println(filePath);
            try {
                System.out.println("br");
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                int rowNumber=0;
                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                    rowNumber++;
                }
                System.out.println(rowNumber);
                String[][] totalData=new String[rowNumber][2];
                BufferedReader br1 = new BufferedReader(new FileReader(filePath));
                for(int currentRow=0;currentRow<rowNumber;currentRow++)
                {
                    line=br1.readLine();
                    System.out.println(line);
                    String[] rowData = line.split(","); // parses data based on ,
                    //System.out.println(rowData.getClass().getTypeName());
                    for(int i=0;i<2;i++) {
                        System.out.println(rowData[i]);
                        totalData[currentRow][i]=rowData[i];
                    }
                }
                System.out.println("return");
                return totalData;
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.out.println(filePath);
            //System.out.println(rowData[0]);
        }

        System.out.println("wrong");
        return null; // ideally return data for table .?
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