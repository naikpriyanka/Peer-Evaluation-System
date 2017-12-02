import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class PeerEvaluation
{
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
        String[] teamMemberOptions = { " ","2","3","4","5","6","7"};
        String[] scoresEnteredOptions = { " ","Yes","No"};

        //Create a labels
        JLabel teamMembersLabel = new JLabel("Choose the number of team members");
        JLabel previousScoresLabel = new JLabel("Are the scores previously entered?");


        //Create the combo boxes.
        JComboBox<String> teamMembers = new JComboBox<String>(teamMemberOptions);
        teamMembers.setSelectedIndex(0);

        JComboBox<String> scoresEntered = new JComboBox<String>(scoresEnteredOptions);
        scoresEntered.setSelectedIndex(0);

        //The submit button
        JButton submitButton = new JButton("Submit");

        //Sizes and Positioning
        teamMembersLabel.setBounds(25,20,280,25);
        previousScoresLabel.setBounds(25,80,250,25);
        teamMembers.setBounds(100,50,80,25);
        scoresEntered.setBounds(100,110,80,25);
        submitButton.setBounds(100,150,100,25);

        //Add the components to panel
        panel.add(teamMembersLabel);
        panel.add(previousScoresLabel);
        panel.add(teamMembers);
        panel.add(scoresEntered);
        panel.add(submitButton);

        //Add the panel to frame
        frame.add(panel);
        frame.setVisible(true);


        //Add an action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamMembersSelectedItem = (String) teamMembers.getSelectedItem();
                String scoresEnteredSelectedItem = (String) scoresEntered.getSelectedItem();

                boolean previousScores;
                if(scoresEnteredSelectedItem.equals("Yes"))
                {
                    previousScores = true;
                }
                else
                {
                    previousScores = false;
                }

                //Checks
                if(teamMembersSelectedItem.equals(" "))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please choose the number of team members.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (scoresEnteredSelectedItem.equals(" "))
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please choose if the scores were entered previously or not", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    //Close the present window
                    frame.setVisible(false);
                    frame.dispose();

                    //Proceed to the next window
                    Evaluation eval = new Evaluation(Integer.parseInt(teamMembersSelectedItem),previousScores);
                    eval.start();
                }
            }
        });
    }
}
