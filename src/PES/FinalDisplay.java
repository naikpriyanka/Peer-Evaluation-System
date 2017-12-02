package PES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FinalDisplay {
    Map<String,Float> normalizedScores;

    //Constructor
    FinalDisplay(Map<String,Float> normalizedScores)
    {
        this.normalizedScores = normalizedScores;
    }

    FinalDisplay()
    {

    }

    public static void main(String args[])
    {
        //For debugging purposes
        Map<String,Float> scores = new HashMap<String, Float>();

        scores.put("Gideon", (float) 4.0);
        scores.put("Harleen", (float) 5.0);
        scores.put("Taran", (float) 3.5);
        scores.put("Naveen", (float) 4.5);
        scores.put("Praveen", (float) 5.0);
//
//        //TODO: Pass in the params
        FinalDisplay eval = new FinalDisplay(scores);
        eval.start();
    }

    public void start()
    {
        JFrame frame = new JFrame("Evaluation");
        frame.setSize(500, 500);
        frame.setLayout(null);

        String[] columnNames = getColumnNames();
        String[][] data = prepareScoresForDisplay(normalizedScores);

        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);

        table.setBounds(0, 10, 500, 250);


        //The submit button
        JButton submitButton = new JButton("Ok");
        submitButton.setBounds(100, 300, 100, 25);


        frame.add(submitButton);
        frame.add(table);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //Add an action listener
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

    private String[] getColumnNames()
    {
        String[] columnNames={"Name","Normalized Score"};
        return columnNames;
    }

    private String[][] prepareScoresForDisplay(Map<String,Float> scores)
    {
        int size = normalizedScores.size();

        String[][] stuff = new String[size][4];

        int i=0,j=0;
        for(String key: scores.keySet())
        {
            stuff[i][j] = key;
            j++;
            stuff[i][j] = String.valueOf(scores.get(key));
            j=0;
            i++;
        }
        return stuff;
    }

}
