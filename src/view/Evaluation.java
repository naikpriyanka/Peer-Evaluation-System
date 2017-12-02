import javax.swing.*;
import java.awt.*;
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
       //TODO: Pass in the params
        Evaluation eval = new Evaluation(5,false);
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

               //TODO:Collect all the scores
               Map<String,List<Integer>> rawScoresMap = new HashMap<String, List<Integer>>();

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


               //Print out the map for debugging purposes
               for(String key: rawScoresMap.keySet())
               {
                   List<Integer> a = rawScoresMap.get(key);

                   System.out.println(key + " - " + Arrays.toString(a.toArray()));
               }

               System.out.println();

               //TODO:Normalize the scores
//               Normalizer normalizer = new Normalizer();
//               Map<String, Float> normalizedScores = normalizer.normalize();


               //Move to the next screen
               frame.setVisible(false);
               frame.dispose();
//               FinalDisplay finalDisplay = new FinalDisplay(normalizedScores);
//               finalDisplay.start();
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