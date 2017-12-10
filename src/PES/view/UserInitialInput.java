package PES.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Class for Starting Window of Peer Evaluation System where the user is asked for number of team members
 * and if scores have been previously entered.
 * */
public class UserInitialInput {

    private JFrame frame;
    private JRadioButton yesOption;
    private JRadioButton noOption;
    private JComboBox<Integer> teamMembersCombo;

    public static void main(String args[]) {
        UserInitialInput peerEvaluation = new UserInitialInput();
        peerEvaluation.addElements();
    }

    /*
    Method Name : addElements()
    Description : Adds elements to the newly created window.
    Parameters : None.
    Return Value : None.
     */
    protected void addElements() {

        frame = new JFrame("Peer Evaluation System");
        frame.setSize(415, 210);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        Integer[] teamMemberOptions = {2, 3, 4, 5, 6, 7};

        JLabel teamMembersLabel = new JLabel("Choose the number of team members");
        JLabel previousScoresLabel = new JLabel("Have you entered the scores previously?");

        teamMembersCombo = new JComboBox<>(teamMemberOptions);
        teamMembersCombo.setSelectedIndex(0);

        yesOption = new JRadioButton("Yes");
        noOption = new JRadioButton("No", true);
        ButtonGroup decisionGroup = new ButtonGroup();
        decisionGroup.add(yesOption);
        decisionGroup.add(noOption);

        JButton submitButton = new JButton("Submit");

        teamMembersLabel.setBounds(frame.getWidth() / 2 - 150, 20, 300, 25);
        teamMembersCombo.setBounds(frame.getWidth() / 2 - 50, 50, 60, 25);
        previousScoresLabel.setBounds(frame.getWidth() / 2 - 150, 80, 300, 25);
        yesOption.setBounds(frame.getWidth() / 2 - 60, 100, 60, 25);
        noOption.setBounds(frame.getWidth() / 2, 100, 50, 25);
        submitButton.setBounds(frame.getWidth() / 2 - 50, 140, 90, 25);

        panel.add(teamMembersLabel);
        panel.add(previousScoresLabel);
        panel.add(teamMembersCombo);
        panel.add(yesOption);
        panel.add(noOption);
        panel.add(submitButton);

        frame.add(panel);
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
            boolean previousScoresEntered = false;

            if (yesOption.isSelected()) {
                previousScoresEntered = true;
            } else if (noOption.isSelected()) {
                previousScoresEntered = false;
            }

            frame.setVisible(false);
            frame.dispose();

            PeerAndSelfEvaluation eval = new PeerAndSelfEvaluation((int) teamMembersCombo.getSelectedItem(), previousScoresEntered);
            eval.start();
        }
    }
}
