package PES.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeerEvaluation {

    //Start window
    private JFrame frame;

    //Yes radio button option for user to enter whether he has entered the scores previously
    private JRadioButton yesOption;

    //No radio button option for user to enter whether he has entered the scores previously
    private JRadioButton noOption;

    //Combo box for user to enter number of team members in his team
    private JComboBox<Integer> teamMembersCombo;

    public static void main(String args[]) {
        PeerEvaluation peerEvaluation = new PeerEvaluation();
        peerEvaluation.addElements();
    }

    //Add elements to the newly created window
    protected void addElements() {
        //Create a frame. This is the window
        frame = new JFrame("Peer Evaluation System");
        frame.setSize(415, 210);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel. Java's equivalent of HTML's div tag
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Integer[] teamMemberOptions = {2, 3, 4, 5, 6, 7};

        //Create a labels
        JLabel teamMembersLabel = new JLabel("Choose the number of team members");
        JLabel previousScoresLabel = new JLabel("Have you entered the scores previously?");

        //Create the combo boxes.
        teamMembersCombo = new JComboBox<>(teamMemberOptions);
        teamMembersCombo.setSelectedIndex(0);

        //Create the radio buttons
        yesOption = new JRadioButton("Yes");
        noOption = new JRadioButton("No", true);
        ButtonGroup decisionGroup = new ButtonGroup();
        decisionGroup.add(yesOption);
        decisionGroup.add(noOption);

        //The submit button
        JButton submitButton = new JButton("Submit");

        //Sizes and Positioning
        teamMembersLabel.setBounds(frame.getWidth() / 2 - 150, 20, 300, 25);
        teamMembersCombo.setBounds(frame.getWidth() / 2 - 50, 50, 60, 25);
        previousScoresLabel.setBounds(frame.getWidth() / 2 - 150, 80, 300, 25);
        yesOption.setBounds(frame.getWidth() / 2 - 60, 100, 60, 25);
        noOption.setBounds(frame.getWidth() / 2, 100, 50, 25);
        submitButton.setBounds(frame.getWidth() / 2 - 50, 140, 90, 25);

        //Add the components to panel
        panel.add(teamMembersLabel);
        panel.add(previousScoresLabel);
        panel.add(teamMembersCombo);
        panel.add(yesOption);
        panel.add(noOption);
        panel.add(submitButton);

        //Add the panel to frame
        frame.add(panel);

        //Centers the window
        frame.setLocationRelativeTo(null);

        //Set the frame to be visible
        frame.setVisible(true);

        //Specify what happens when close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Action listener to the submit button
        submitButton.addActionListener(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean previousScoresEntered = false;

            //Get the selected option
            if (yesOption.isSelected()) {
                previousScoresEntered = true;
            } else if (noOption.isSelected()) {
                previousScoresEntered = false;
            }

            //Close the present window
            frame.setVisible(false);
            frame.dispose();

            //Proceed to the next window
            Evaluation eval = new Evaluation((int) teamMembersCombo.getSelectedItem(), previousScoresEntered);
            eval.start();
        }
    }
}
