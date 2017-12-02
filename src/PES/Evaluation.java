package PES;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Evaluation{
    int memberCount;
    boolean previousScores;

    Evaluation(int count,boolean flag)
    {
        this.memberCount = count;
        this.previousScores = flag;
    }

   public static void main(String args[])
   {
        Evaluation eval = new Evaluation(5,true);
        eval.start();
   }

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
               return column == 1 || column == 2 || column == 3;
           }
       };

       JComboBox comboBox = new JComboBox();
       comboBox.addItem(" ");
       comboBox.addItem("1");
       comboBox.addItem("2");
       comboBox.addItem("3");
       comboBox.addItem("4");
       comboBox.addItem("5");


       //Make all the column editing as dropdown
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

       //Add components to the frame
       frame.add(submitButton);
       frame.add(table);

       //Make the frame visible
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       //Add an action listener
       submitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               //TODO:Collect all the scores
               Map<String,List<Integer>> rawScoresMap = new TreeMap<String, List<Integer>>();

               for (int i=0;i<table.getRowCount();i++)
               {
                   List<Integer> scores = new LinkedList<Integer>();
                   String name = null;
                   for(int j=0;j<table.getColumnCount();j++)
                   {
                       if(j==0)
                       {
                           name = (String) table.getValueAt(i,j);
                           continue;
                       }
                       int score = Integer.parseInt((String) table.getValueAt(i,j));
                       scores.add(score);
                   }
                   rawScoresMap.put(name,scores);
               }

               //For debuggin purposes
//               for (String key : rawScoresMap.keySet())
//               {
//                   System.out.println(key+" "+rawScoresMap.get(key));
//               }

               //TODO:Normalize the scores
               Normalizer normalizer = new Normalizer();
               Map<String, Float> normalizedScoresMap = normalizer.normalize(rawScoresMap);


               //Move to the next screen
               frame.setVisible(false);
               frame.dispose();
               FinalDisplay finalDisplay = new FinalDisplay(normalizedScoresMap);
               finalDisplay.start();
           }
       });
   }

   private String[] getColumnNames()
   {
       String[] columnNames={"Name","Column #1","Column #2","Column #3"};
       return columnNames;
   }

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