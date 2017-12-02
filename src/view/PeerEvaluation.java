import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeerEvaluation {

    public static void main(String args[])
    {
        //Create a frame. This is the window.
        JFrame frame = new JFrame("Peer Evaluation System");
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add elements to the newly created window
        addElements(frame);
    }

    private static void addElements(JFrame frame)
    {
        //Create a panel. Java's equivalent of HTML's div tag
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Integer[] teamMemberOptions = {2, 3, 4, 5, 6, 7};

        //Create a labels
        JLabel teamMembersLabel = new JLabel("Choose the number of team members");
        JLabel previousScoresLabel = new JLabel("Are the scores previously entered?");

        //Create the combo boxes.
        JComboBox<Integer> teamMembersCombo = new JComboBox<>(teamMemberOptions);
        teamMembersCombo.setSelectedIndex(0);

        JRadioButton yesOption = new JRadioButton("Yes");
        JRadioButton noOption = new JRadioButton("No", true);
        ButtonGroup decisionGroup = new ButtonGroup();
        decisionGroup.add(yesOption);
        decisionGroup.add(noOption);

        //The submit button
        JButton submitButton = new JButton("Submit");

        //Sizes and Positioning
        teamMembersLabel.setBounds(25,20,280,25);
        previousScoresLabel.setBounds(25,80,250,25);
        teamMembersCombo.setBounds(100,50,80,25);
        yesOption.setBounds(100, 100, 100, 25);
        noOption.setBounds(200, 100, 100, 25);
        submitButton.setBounds(100,150,100,25);

        //Add the components to panel
        panel.add(teamMembersLabel);
        panel.add(previousScoresLabel);
        panel.add(teamMembersCombo);
        panel.add(yesOption);
        panel.add(noOption);
        panel.add(submitButton);

        //Add the panel to frame
        frame.add(panel);
        frame.setVisible(true);


        //Add an action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int teamMembers = (int) teamMembersCombo.getSelectedItem();
                boolean previousScoresEntered = false;
                if (yesOption.isSelected()) {
                    previousScoresEntered = true;
                } else if (noOption.isSelected()) {
                    previousScoresEntered = false;
                }
                //Close the present window
                frame.setVisible(false);
                frame.dispose();

                //Proceed to the next window
                Evaluation eval = new Evaluation(teamMembers, previousScoresEntered);
                eval.start();
            }
        });
    }
}
