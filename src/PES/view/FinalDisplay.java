package PES.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.util.Map;
import java.util.TreeMap;

public class FinalDisplay {

    /*Map to hold the normalized scores.
    * Key will be the name and value will be the normalized score.
    * */
    private Map<String, Float> normalizedScores;

    //Constructor
    public FinalDisplay(Map<String, Float> normalizedScores) {
        this.normalizedScores = normalizedScores;
    }

    //For debugging purposes
    public static void main(String args[]) {
        Map<String, Float> scores = new TreeMap<String, Float>();
        scores.put("Gideon", (float) 4.0);
        scores.put("Harleen", (float) 5.0);
        scores.put("Taran", (float) 3.5);
        scores.put("Naveen", (float) 4.5);
        scores.put("Praveen", (float) 5.0);

        FinalDisplay eval = new FinalDisplay(scores);
        eval.start();
    }

    public void start() {
        //Create the frame
        JFrame frame = new JFrame("Evaluation");
        frame.setSize(500, 240);
        frame.setLayout(null);

        //Get the necessary data to display the table
        String[] columnNames = {"Name", "Normalized Score"};
        String[][] data = prepareScoresForDisplay();

        //Create the table with the above data
        JTable table = new JTable(data, columnNames);

        //Make the table uneditable
        table.setEnabled(false);

        //Table Header
        JTableHeader header = table.getTableHeader();

        //Sizes and Positioning
        table.setBounds(0, 40, 500, 180);
        header.setBounds(0, 0, 500, 40);
        table.setRowHeight(25);

        //Add components to the frame
        frame.add(header);
        frame.add(table);

        //Centers the window
        frame.setLocationRelativeTo(null);

        //Set the frame to be visible
        frame.setVisible(true);

        //Specify what happens when close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*Receive the normalized scores from previous screen and make a String[][] from that data.
    * This step must be done because the constructor for generating the table accepts a String[][]
    * but not a Map.
    * */
    private String[][] prepareScoresForDisplay() {
        int size = normalizedScores.size();

        //Create a String[][] which can store all the stuff from the Map
        String[][] scores = new String[size][4];

        int i = 0;

        for (String key : normalizedScores.keySet()) {
            //All the 0th columns will be names
            scores[i][0] = key;

            //Get the normalized score and put it in the first column
            scores[i][1] = String.format("%.2f", normalizedScores.get(key));

            //Proceed to the next row
            i++;
        }
        return scores;
    }

}
