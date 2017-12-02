import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Evaluation{
   public static void main(String args[])
   {
       //TODO: Pass in the params
        Evaluation eval = new Evaluation();
        eval.start();
   }

   public void start()
   {
       JFrame frame = new JFrame("Evaluation");
       frame.setSize(500,500);

       String[] columnNames = getColumnNames();
       String[][] data = generateScores(3);

       JTable table = new JTable(data,columnNames);
       JScrollPane pane = new JScrollPane(table);
       table.setFillsViewportHeight(true);

       frame.add(pane);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   //TODO:Randomly generate the names and scores
   private String[] getColumnNames()
   {
       String[] columnNames={"Name","Column #1","Column #2","Column #3"};
       return columnNames;
   }

   private String[][] generateScores(int count)
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
                   stuff[i][j] = "Name";
                   continue;
               }
               //TODO: Generate random marks
               stuff[i][j]="5";
           }
       }
       return stuff;
   }
}