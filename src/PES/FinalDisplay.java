package PES;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class FinalDisplay {

    /*Map to hold the normalized scores.
    * Key will be the name and value will be the normalized score.
    * */
    Map<String,Float> normalizedScores;

    //Constructor
    FinalDisplay(Map<String,Float> normalizedScores)
    {
        this.normalizedScores = normalizedScores;
    }

    //For debugging purposes
    public static void main(String args[])
    {
        Map<String,Float> scores = new TreeMap<String, Float>();
        scores.put("Gideon", (float) 4.0);
        scores.put("Harleen", (float) 5.0);
        scores.put("Taran", (float) 3.5);
        scores.put("Naveen", (float) 4.5);
        scores.put("Praveen", (float) 5.0);

        FinalDisplay eval = new FinalDisplay(scores);
        eval.start();
    }

    public void start()
    {
        //Create the frame
        JFrame frame = new JFrame("Evaluation");
        frame.setSize(500, 500);
        frame.setLayout(null);

        //Get the necessary data to display the table
        String[] columnNames = getColumnNames();
        String[][] data = prepareScoresForDisplay(normalizedScores);

        //Create the table with the above data
        JTable table = new JTable(data, columnNames);

        //Make the table uneditable
        table.setEnabled(false);

        //Table Header
        JTableHeader header = table.getTableHeader();

        //The submit button
        JButton submitButton = new JButton("Ok");

        //Sizes and Positioning
        table.setBounds(0, 40, 500, 250);
        submitButton.setBounds(100, 300, 100, 25);
        header.setBounds(0,0,500,40);
        table.setRowHeight(25);

        //Add components to the frame
        frame.add(submitButton);
        frame.add(header);
        frame.add(table);

        //Centers the window
        frame.setLocationRelativeTo(null);

        //Set the frame to be visible
        frame.setVisible(true);

        //Specify what happens when close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add an action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Close the application
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
    }

    //Column headings
    private String[] getColumnNames()
    {
        String[] columnNames={"Name","Normalized Score"};
        return columnNames;
    }

    /*Receive the normalized scores from previous screen and make a String[][] from that data.
    * This step must be done because the constructor for generating the table accepts a String[][]
    * but not a Map.
    * */
    private String[][] prepareScoresForDisplay(Map<String,Float> scores)
    {
        int size = scores.size();

        //Create a String[][] which can store all the stuff from the Map
        String[][] normalizedScores = new String[size][4];

        int i=0,j=0;

        for(String key: scores.keySet())
        {
            //All the 0th columns will be names
            normalizedScores [i][0] = key;

            //Get the normalized score and put it in the first column
            normalizedScores [i][1] = String.valueOf(scores.get(key));

            //Proceed to the next row
            i++;
        }
        return normalizedScores ;
    }

}
