package PES.view;

import PES.util.NormalizerUtility;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
/*
* Class to handle User Input for Peer UserInitialInput Scores.
*/
public class UserInitialInput {

    private int memberCount;
    private boolean previousScoresEntered;
    private JFrame frame;
    private JTable table;

    /*
    Method Name : UserInitialInput()
    Description : Constructor method to initialize class variables.
    Parameters : 1) memberCount (int value for Number of members in the team).
                 2) previousScoresEntered (boolean value to state if scores previously entered.).
    Return Value : instance of the class.
     */
    public UserInitialInput(int memberCount, boolean previousScoresEntered) {
        this.memberCount = memberCount;
        this.previousScoresEntered = previousScoresEntered;
        frame = new JFrame("UserInitialInput");
    }


    /*
    Method Name : start()
    Description : Initial method of program flow. Creates the window layout for UserInitialInput screen.
    Parameters : None.
    Return Value : None.
     */
    public void start() {

        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        String[] columnNames = {"Name", "Professionalism", "Meeting Participation", "Work UserInitialInput"};
        LinkedList<String> names = getNames();
        String[][] data = generateScores(names);

        table = createRawScoreTable(columnNames, data);
        table.setShowGrid(true);
        table.setGridColor(Color.lightGray);
        String[] rawScores = {"0", "1", "2", "3", "4", "5"};
        JComboBox comboBox = new JComboBox(rawScores);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click to enter score");

        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setCellEditor(new DefaultCellEditor(comboBox));
        column1.setCellRenderer(renderer);

        TableColumn column2 = table.getColumnModel().getColumn(2);
        column2.setCellEditor(new DefaultCellEditor(comboBox));
        column2.setCellRenderer(renderer);

        TableColumn column3 = table.getColumnModel().getColumn(3);
        column3.setCellEditor(new DefaultCellEditor(comboBox));
        column3.setCellRenderer(renderer);

        JButton submitButton = new JButton("Submit");
        JTableHeader header = table.getTableHeader();

        submitButton.setBounds(frame.getWidth() / 2 - 50, 230, 100, 25);
        table.setBounds(0, 40, 500, 180);
        header.setBounds(0, 0, 500, 40);
        table.setRowHeight(25);

        JPanel panel = new JPanel();
        panel.add(submitButton);
        panel.add(new JScrollPane(table));

        frame.add(submitButton);
        frame.add(header);
        frame.add(table);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new SubmitButtonListener());
    }

    /*
    Event Handler class for Submit button.
    */
    private class SubmitButtonListener implements ActionListener {
        /*
        Method Name : actionPerformed()
        Description : Overriden method to receive the inputs entered by the user on clicking the Submit button.
        Parameters :  ActionEvent object that gives information about the event and its source.
        Return Value : None.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Map<String, List<Integer>> rawScoresMap = getRawScores(table);
            if(!rawScoresMap.isEmpty() && memberCount == rawScoresMap.size()) {

                Map<String, Float> normalizedScoresMap = NormalizerUtility.normalize(rawScoresMap);

                frame.setVisible(false);
                frame.dispose();
                NormalizedScoreDisplay normalizedScoreDisplay = new NormalizedScoreDisplay(normalizedScoresMap);
                normalizedScoreDisplay.start();
            }
        }
    }

    /*
    Method Name : createRawScoreTable()
    Description : Creates a table based on the number of team members and initial raw score data.
    Parameters :  1) String[] columnNames - String array for names of columns
                  2) String[][] data - 2D String array with pre-filled data of names and scores.
    Return Value : JTable(data, columnNames) (Returns a JTable with required data.).
     */
    private JTable createRawScoreTable(String[] columnNames, String[][] data) {
        return new JTable(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1 || column == 2 || column == 3;
                }
            };
    }

    /*
    Method Name : getRawScores()
    Description : Method to receive the scores entered by the user/pre-filled in the GUI and returns them as a Map.
    Parameters :  JTable table (JTable object).
    Return Value : Map<String, List<Integer>> - (Map  with key as name of team members and value as List of scores entered).
     */
    private Map<String, List<Integer>> getRawScores(JTable table) {
        Map<String, List<Integer>> rawScoresMap = new LinkedHashMap<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            List<Integer> rawScores = new LinkedList<Integer>();
            String name = null;
            boolean flag = false;

            for (int j = 0; j < table.getColumnCount(); j++) {
                if (j == 0) {
                    name = (String) table.getValueAt(i, j);
                    continue;
                }

                String enteredScore = (String) table.getValueAt(i, j);
                if(enteredScore.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Entered invalid scores", "Invalid Input", ERROR_MESSAGE);
                    flag = true;
                    break;
                } else {
                    rawScores.add(Integer.parseInt(enteredScore));
                }
            }
            if(flag) break;
            rawScoresMap.put(name, rawScores);
        }
        return rawScoresMap;
    }

    /*
    Method Name : generateScores()
    Description : Method to assign Random names from a pre-defined list of names and Random scores between 0-5.
    Parameters :  List<String> names (Predefined List of Names).
    Return Value : String[][] (String array containing names and their scores)
     */

    private String[][] generateScores(List<String> names) {
        String[][] rawScoresMap = new String[memberCount][4];

        for (int i = 0; i < memberCount; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    rawScoresMap[i][j] = randomNameGenerator(names);
                    continue;
                }

                if (previousScoresEntered) {
                    Random rand = new Random();
                    int n = rand.nextInt(5) + 1;
                    rawScoresMap[i][j] = Integer.toString(n);
                } else {
                    rawScoresMap[i][j] = "";
                }
            }
        }
        return rawScoresMap;
    }

    /*
    Method Name : getNames()
    Description : Getter Method for predefined List of Random Names.
    Parameters :  None.
    Return Value : LinkedList<String> (LinkedList of names).
     */
    private LinkedList<String> getNames() {
        LinkedList<String> names = new LinkedList<String>();
        names.add("Gideon");
        names.add("Sumedha");
        names.add("Anirudh");
        names.add("Hrishikesh");
        names.add("Priyanka");
        names.add("Olivia");
        names.add("Harleen");
        names.add("Dino");
        return names;
    }

    /*
    Method Name : randomNameGenerator()
    Description : Method for generating a new random name from a given predefined List of Random Names.
    Parameters :  List<String> names (List of names).
    Return Value : String (Generated random name).
     */
    private String randomNameGenerator(List<String> names) {
        Random r = new Random();
        int i = r.nextInt(names.size());
        String name = names.get(i);
        names.remove(i);
        return name;
    }
}