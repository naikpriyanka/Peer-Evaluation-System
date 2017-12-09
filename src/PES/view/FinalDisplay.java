package PES.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Map;
/*
 * Class for Final Evaluation Window where Normalized scores are displayed.
 * */
public class FinalDisplay {

    /*Map to hold the normalized scores.
    * Key will be the name and value will be the normalized score.
    * */
    private Map<String, Float> normalizedScores;

    //Constructor method
    public FinalDisplay(Map<String, Float> normalizedScores) {
        this.normalizedScores = normalizedScores;
    }

    /*
    Method Name : start()
    Description : Initial method of program flow. Creates the window layout for Final Evaluation screen.
    Parameters : None.
    Return Value : None.
     */
    public void start() {
        //Create the frame
        JFrame frame = new JFrame("Evaluation");
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        //Get the necessary data to display the table
        String[] columnNames = {"Name", "Normalized Score"};
        String[][] data = prepareScoresForDisplay();

        //Create the table with the above data
        JTable table = new JTable(data, columnNames);
        table.setShowGrid(true);
        table.setGridColor(Color.lightGray);

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

    /*
    Method Name : prepareScoresForDisplay()
    Description : Method for return a 2D String array of Names and normalized scores from Map which will be used for Display.
    Parameters :  None.
    Return Value : String[][] (String 2D array representation of Names and normalized Scores).
     */
    private String[][] prepareScoresForDisplay() {
        int size = normalizedScores.size();

        //Create a String[][] which can store all the data from the Map
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
