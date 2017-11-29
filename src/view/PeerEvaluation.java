import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class PeerEvaluation
{
	public static void main(String args[])
	{
		//Create a frame. This is the window.
		JFrame frame = new JFrame("Peer Evaluation System");
		frame.setSize(300,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add elements to the newly created window
		addElements(frame);
	}
	
	private static void addElements(JFrame frame)
	{
		//Create a panel. Java's equivalent of HTML's div tag
		JPanel panel = new JPanel();
		panel.setLayout(null);
		String[] numbers = { "0","1","2","3","4","5","6","7","8","9","10", };
		
		//Create a label
		JLabel label = new JLabel("Choose the number of team members");
		label.setBounds(25,20,250,25);
		
		//Create the combo box.
		JComboBox<String> teamMembers = new JComboBox<String>(numbers);
		teamMembers.setSelectedIndex(0);
		teamMembers.setBounds(100,50,80,25);
		
		//Add to the panel
		panel.add(label);
		panel.add(teamMembers);
		
		//Add the panel to frame
		frame.add(panel);
		frame.setVisible(true);
		
				
		//Add an action listener
		teamMembers.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String selection = (String)cb.getSelectedItem();
		}
		});
	}
}