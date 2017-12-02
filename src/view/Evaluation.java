import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Evaluation{
    static int memberCount;
    Evaluation(int count)
    {
        memberCount = count;
    }

   public static void main(String args[])
   {
       //TODO: Pass in the params
        Evaluation eval = new Evaluation(5);
        eval.start();
   }

   public void start() {
       //Create the frame
       JFrame frame = new JFrame("Evaluation");
       frame.setSize(500, 500);
       frame.setLayout(null);

       //Prepare the data to be inserted in table
       String[] columnNames = getColumnNames();
       String[][] data = generateScores(memberCount);

       //Generate the table
       JTable table = new JTable(data, columnNames);

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
               //TODO:Normalize the scores

               //TODO: Move to the next screen
               frame.setVisible(false);
               frame.dispose();
               FinalDisplay finalDisplay = new FinalDisplay();
               finalDisplay.start();
           }
       });
   }

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

               Random rand = new Random();
               int  n = rand.nextInt(5) + 1;
               stuff[i][j]= Integer.toString(n);
           }
       }
       return stuff;
   }
}