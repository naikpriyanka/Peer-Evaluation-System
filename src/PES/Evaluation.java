package PES;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Evaluation{
    //Get the number of people from the previous window
    int memberCount;

    //Indicates if the previous were entered previously
    boolean previousScores;

    //Constructor
    Evaluation(int count,boolean flag)
    {
        this.memberCount = count;
        this.previousScores = flag;
    }

    //For debugging purposes
   public static void main(String args[])
   {
        Evaluation eval = new Evaluation(5,true);
        eval.start();
   }

   //Contains the GUI and logic
   public void start() {

       //Create the frame
       JFrame frame = new JFrame("Evaluation");
       frame.setSize(500, 500);
       frame.setLayout(null);

       //Prepare the data to be inserted in table
       String[] columnNames = getColumnNames();
       String[][] data = generateScores(memberCount,previousScores);

       //Generate the table and make the Name column uneditable
       JTable table = new JTable(data,columnNames)
       {
           @Override
           public boolean isCellEditable(int row, int column)
           {
               //Since column 0 is names, make only column 1,2 and 3 editable.
               return column == 1 || column == 2 || column == 3;
           }
       };

       /*The following code makes sure that the user will choose the input from
       * a combo box instead of entering the digits.
       * This restricts the user to certain option making it hard for invalid option to enter
       * the program.*/

       //Create the combobox with options
       JComboBox comboBox = new JComboBox();
       comboBox.addItem(" ");
       comboBox.addItem("1");
       comboBox.addItem("2");
       comboBox.addItem("3");
       comboBox.addItem("4");
       comboBox.addItem("5");


       //Make all the columns editable by comboboxes only
       TableColumn column1 = table.getColumnModel().getColumn(1);
       column1.setCellEditor(new DefaultCellEditor(comboBox));

       TableColumn column2 = table.getColumnModel().getColumn(2);
       column2.setCellEditor(new DefaultCellEditor(comboBox));

       TableColumn column3 = table.getColumnModel().getColumn(3);
       column3.setCellEditor(new DefaultCellEditor(comboBox));

       //Create a submit button
       JButton submitButton = new JButton("Submit");

       //Sizes and Positioning
       submitButton.setBounds(100, 300, 100, 25);
       table.setBounds(0, 10, 500, 250);
       table.setRowHeight(25);

       //Add components to the frame
       frame.add(submitButton);
       frame.add(table);

       //Centers the window
       frame.setLocationRelativeTo(null);

       //Set the frame to be visible
       frame.setVisible(true);

       //Specify what happens when close button is clicked
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //Add an action listener to submit button
       submitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               /*Collect all the scores from the table. Store them in a Map.
               * Each entry of the map contains the Name as key and a List of scores as value.
               * */
               Map<String,List<Integer>> rawScoresMap = new TreeMap<String, List<Integer>>();

               for (int i=0;i<table.getRowCount();i++)
               {
                   //These two components are extracted from each row of the table.
                   List<Integer> rawScores = new LinkedList<Integer>();
                   String name = null;

                   for(int j=0;j<table.getColumnCount();j++)
                   {
                       //All values of 0th column are names. Store them as strings.
                       if(j==0)
                       {
                           //Get the name
                           name = (String) table.getValueAt(i,j);
                           continue;
                       }

                       //Get the scores. Parse them to Integers before storing in map.
                       int score = Integer.parseInt((String) table.getValueAt(i,j));
                       rawScores.add(score);
                   }
                   //Put the name and list of scores in the map.
                   rawScoresMap.put(name,rawScores);
               }

               //For debuggin purposes
               for (String key : rawScoresMap.keySet())
               {
                   System.out.println(key+" "+rawScoresMap.get(key));
               }

               //TODO:Normalize the scores
               Normalizer normalizer = new Normalizer();
               Map<String, Float> normalizedScoresMap = normalizer.normalize(rawScoresMap);


               //Close the current screen
               frame.setVisible(false);
               frame.dispose();

               //Move to the next screen
               FinalDisplay finalDisplay = new FinalDisplay(normalizedScoresMap);
               finalDisplay.start();
           }
       });
   }

   //The column headings to be displyed in the table
   private String[] getColumnNames()
   {
       String[] columnNames={"Name","Column #1","Column #2","Column #3"};
       return columnNames;
   }

   //Generates random scores
   private String[][] generateScores(int count, boolean previousScores)
   {
       String[][] stuff = new String[count][4];

       for (int i=0;i<count;i++)
       {
           for(int j=0;j<4;j++)
           {
               //Set the name
               if(j == 0)
               {
                   //TODO: Generate random name
                   stuff[i][j] = "Name #"+i;
                   continue;
               }

               if(previousScores)
               {
                   Random rand = new Random();
                   int  n = rand.nextInt(5) + 1;
                   stuff[i][j] = Integer.toString(n);
               }
               else
               {
                   stuff[i][j] =  Integer.toString(0);
               }
           }
       }
       return stuff;
   }
}