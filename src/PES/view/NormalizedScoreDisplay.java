package PES.view;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Map;
/*
 * Class for Final Peer Evaluation Window where Normalized scores are displayed.
 */
public class NormalizedScoreDisplay {
    private Map<String, Float> normalizedScores;

    /*
    Method Name : NormalizedScoreDisplay()
    Description : Constructor method to initialize class variables.
    Parameters : Map<String, Float> normalizedScores (Map with key as name of team members and value as final normalized scores for every team member).
    Return Value : instance of the class.
    */
    public NormalizedScoreDisplay(Map<String, Float> normalizedScores) {
        this.normalizedScores = normalizedScores;
    }

    /*
    Method Name : start()
    Description : Initial method of program flow. Creates the window layout for Final Peer and Self Evaluation screen.
    Parameters : None.
    Return Value : None.
     */
    public void start() {

        JFrame frame = new JFrame("Normalized Scores");
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        String[] columnNames = {"Name", "Normalized Score"};
        String[][] data = prepareScoresForDisplay();

        JTable table = new JTable(data, columnNames);
        table.setShowGrid(true);
        table.setGridColor(Color.lightGray);
        table.setEnabled(false);
        JTableHeader header = table.getTableHeader();

        table.setBounds(0, 40, 500, 180);
        header.setBounds(0, 0, 500, 40);
        table.setRowHeight(25);

        frame.add(header);
        frame.add(table);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
    Method Name : prepareScoresForDisplay()
    Description : Method for returning a 2D String array of Names and normalized scores from Map which will be used for Display.
    Parameters :  None.
    Return Value : String[][] (String 2D array representation of Names and normalized Scores).
    */
    private String[][] prepareScoresForDisplay() {
        int size = normalizedScores.size();
        String[][] scores = new String[size][4];
        int i = 0;

        for (String key : normalizedScores.keySet()) {
            scores[i][0] = key;
            scores[i][1] = String.format("%.2f", normalizedScores.get(key));
            i++;
        }
        return scores;
    }
}
