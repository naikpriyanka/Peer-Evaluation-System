package PES.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeerEvaluation {

    public static void main(String args[]) {
        //Create a frame. This is the window.
        JFrame frame = new JFrame("Peer Evaluation System");
        frame.setSize(415, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add elements to the newly created window
        addElements(frame);
    }

    protected static void addElements(JFrame frame) {
        //Create a panel. Java's equivalent of HTML's div tag
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Integer[] teamMemberOptions = {2, 3, 4, 5, 6, 7};

        //Create a labels
        JLabel teamMembersLabel = new JLabel("Choose the number of team members");
        JLabel previousScoresLabel = new JLabel("Have you entered the scores previously?");

        //Create the combo boxes.
        JComboBox<Integer> teamMembersCombo = new JComboBox<>(teamMemberOptions);
        teamMembersCombo.setSelectedIndex(0);

        //Create the radio buttons
        JRadioButton yesOption = new JRadioButton("Yes");
        JRadioButton noOption = new JRadioButton("No", true);
        ButtonGroup decisionGroup = new ButtonGroup();
        decisionGroup.add(yesOption);
        decisionGroup.add(noOption);

        //The submit button
        JButton submitButton = new JButton("Submit");

        //Sizes and Positioning
        teamMembersLabel.setBounds(25, 20, 250, 25);
        teamMembersCombo.setBounds(270, 20, 60, 25);
        previousScoresLabel.setBounds(25, 60, 260, 25);
        yesOption.setBounds(280, 60, 60, 25);
        noOption.setBounds(340, 60, 50, 25);
        submitButton.setBounds(170, 100, 70, 25);

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
        submitButton.addActionListener(new ActionListener() {
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
        });
    }
}
