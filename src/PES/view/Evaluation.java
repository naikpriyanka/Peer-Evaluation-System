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
* Class to handle User Input for Peer Evaluation Scores.
* */
public class Evaluation {
    //Get the number of people from the previous window
    private int memberCount;

    //Indicates if the previous were entered previously
    private boolean previousScoresEntered;

    //Evaluation window
    private JFrame frame;

    //Score table
    private JTable table;

    /*
    Method Name : Evaluation()
    Description : Constructor method to initialize class variables.
    Parameters : 1) memberCount (int value for Number of members in the team).
                 2) previousScoresEntered (boolean value to state if scores previously entered.).
    Return Value : instance of the class.
     */
    public Evaluation(int memberCount, boolean previousScoresEntered) {
        this.memberCount = memberCount;
        this.previousScoresEntered = previousScoresEntered;
        frame = new JFrame("Evaluation");
    }


    /*
    Method Name : start()
    Description : Initial method of program flow. Creates the window layout for Evaluation screen.
    Parameters : None.
    Return Value : None.
     */
    public void start() {

        //Create the frame
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        //Prepare the data to be inserted in table
        String[] columnNames = {"Name", "Professionalism", "Meeting Participation", "Work Evaluation"};
        LinkedList<String> names = getNames();
        String[][] data = generateScores(names);


        //Generate the table and make the Name column uneditable
        table = createRawScoreTable(columnNames, data);
        table.setShowGrid(true);
        table.setGridColor(Color.lightGray);

       /*The following code makes sure that the user will choose the input from
       * a combo box instead of entering the digits.
       * This restricts the user to certain option making it hard for invalid option to enter
       * the program.*/

        //Create the combobox with options
        String[] rawScores = {"0", "1", "2", "3", "4", "5"};
        JComboBox comboBox = new JComboBox(rawScores);

        //Tooltip to make user aware of the combo box to enter score
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click to enter score");

        //Make all the columns editable by comboboxes only
        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setCellEditor(new DefaultCellEditor(comboBox));
        column1.setCellRenderer(renderer);

        TableColumn column2 = table.getColumnModel().getColumn(2);
        column2.setCellEditor(new DefaultCellEditor(comboBox));
        column2.setCellRenderer(renderer);

        TableColumn column3 = table.getColumnModel().getColumn(3);
        column3.setCellEditor(new DefaultCellEditor(comboBox));
        column3.setCellRenderer(renderer);

        //Create a submit button
        JButton submitButton = new JButton("Submit");

        //Table Header
        JTableHeader header = table.getTableHeader();

        //Sizes and Positioning
        submitButton.setBounds(frame.getWidth() / 2 - 50, 230, 100, 25);
        table.setBounds(0, 40, 500, 180);
        header.setBounds(0, 0, 500, 40);
        table.setRowHeight(25);

        //JPanel
        JPanel panel = new JPanel();
        panel.add(submitButton);
        panel.add(new JScrollPane(table));

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

        //Add an action listener to submit button
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
            /*Collect all the scores from the table. Store them in a Map.
              Each entry of the map contains the Name as key and a List of scores as value.
            */
            Map<String, List<Integer>> rawScoresMap = getRawScores(table);

            /*Check if the user has entered valid scores for all the users.
            If it is valid then only proceed to displaying of normalised scores
            */
            if(!rawScoresMap.isEmpty() && memberCount == rawScoresMap.size()) {
                Map<String, Float> normalizedScoresMap = NormalizerUtility.normalize(rawScoresMap);

                //Close the current screen
                frame.setVisible(false);
                frame.dispose();

                //Move to the next screen
                FinalDisplay finalDisplay = new FinalDisplay(normalizedScoresMap);
                finalDisplay.start();
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
                    //Since column 0 is names, make only column 1,2 and 3 editable.
                    return column == 1 || column == 2 || column == 3;
                }
            };
    }

    /*
    Method Name : getRawScores()
    Description : Method to receive the scores entered by the user/pre-filled in the GUI and returns them as a Map.
    Parameters :  JTable table (JTable object).
    Return Value : Map<String, List<Integer>> - raw scores entered in the GUI.
     */
    private Map<String, List<Integer>> getRawScores(JTable table) {
        Map<String, List<Integer>> rawScoresMap = new LinkedHashMap<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            //These two components are extracted from each row of the table.
            List<Integer> rawScores = new LinkedList<Integer>();
            String name = null;
            boolean flag = false;

            for (int j = 0; j < table.getColumnCount(); j++) {
                //All values of 0th column are names. Store them as strings.
                if (j == 0) {
                    //Get the name
                    name = (String) table.getValueAt(i, j);
                    continue;
                }

                //Get the scores. Parse them to Integers before storing in map.
                String enteredScore = (String) table.getValueAt(i, j);
                if(enteredScore.isEmpty()) {
                    //check if the user has not entered a valid score (if the score is empty)
                    //show the error message and stay on the page
                    JOptionPane.showMessageDialog(null, "Entered invalid scores", "Invalid Input", ERROR_MESSAGE);
                    flag = true;
                    break;
                } else {
                    rawScores.add(Integer.parseInt(enteredScore));
                }
            }
            //Put the name and list of scores in the map.
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
                //Set the name
                if (j == 0) {
                    rawScoresMap[i][j] = randomNameGenerator(names);
                    continue;
                }

                if (previousScoresEntered) {
                    Random rand = new Random();
                    int n = rand.nextInt(5) + 1;
                    rawScoresMap[i][j] = Integer.toString(n);
                } else {
                    rawScoresMap[i][j] = ""; //If the user has not entered score previously, keep the field blank
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