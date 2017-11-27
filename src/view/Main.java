package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main implements ActionListener {

    static Map<String, List<Integer>> data;
    private JPanel jPanel;

    public Main() {
        initMap();
    }

    private void initMap() {
        data = new LinkedHashMap<>();
        data.put("ABC", Arrays.asList(0, 0, 0));
        data.put("DEF", Arrays.asList(0, 0, 0));
        data.put("PQR", Arrays.asList(0, 0, 0));
        data.put("XYZ", Arrays.asList(0, 0, 0));
        data.put("LMN", Arrays.asList(0, 0, 0));
        data.put("UVW", Arrays.asList(0, 0, 0));
        data.put("STU", Arrays.asList(0, 0, 0));
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("InputDialog Example #1");
        String name = JOptionPane.showInputDialog(frame, "What's your name?");
        System.out.printf("The user's name is '%s'.\n", name);
        System.exit(0);


//        Main main = new Main();
//        main.startApp();
    }

    private void startApp() {
        JFrame mainContent = new JFrame();
        mainContent.setSize(500, 500);
        mainContent.setLayout(null);
        mainContent.setVisible(true);

        jPanel = new JPanel();
        jPanel.setSize(500, 500);
        jPanel.setLayout(null);
        jPanel.setVisible(true);

        JLabel label = getMemberLabel();
        jPanel.add(label);

        JComboBox selectTeamMembers = getMemberCountComboBox();
        selectTeamMembers.addActionListener(this);
        jPanel.add(selectTeamMembers);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        jPanel.add(submitButton);

        mainContent.add(jPanel);
        mainContent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTable getScoreTable(String members) {
        int memberCount = Integer.parseInt(members);
        String[] columns = {"Names", "Professionalism", "Meeting Participation", "Work Evaluation"};
        String[][] scoreData = getScoreData(memberCount);
        JTable scoreTable = new JTable(scoreData, columns);
        scoreTable.setVisible(true);
        scoreTable.setBounds(0, 70, 400, 115);
        scoreTable.setGridColor(Color.BLACK);
        return scoreTable;
    }

    private String[][] getScoreData(int memberCount) {
        String[][] scoreData = new String[memberCount][4];
        int i = 0;
        for (Map.Entry<String, List<Integer>> entry : data.entrySet()) {
            if (i == memberCount) break;
            List<Integer> value = entry.getValue();
            scoreData[i][0] = entry.getKey();
            scoreData[i][1] = value.get(0).toString();
            scoreData[i][2] = value.get(1).toString();
            scoreData[i][3] = value.get(2).toString();
            i++;
        }
        return scoreData;
    }

    private JComboBox getMemberCountComboBox() {
        String members[] = {"2", "3", "4", "5", "6", "7"};
        JComboBox selectTeamMembers = new JComboBox(members);
        selectTeamMembers.setVisible(true);
        selectTeamMembers.setBounds(190, 0, 60, 30);
        return selectTeamMembers;
    }

    private JLabel getMemberLabel() {
        JLabel label = new JLabel();
        label.setText("Number of team members : ");
        label.setBounds(0,0, 180,30);
        label.setVisible(true);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox selectTeamMembers = (JComboBox) e.getSource();
        JTable scoreTable = getScoreTable((String) selectTeamMembers.getItemAt(selectTeamMembers.getSelectedIndex()));
        JScrollPane scrollPane = new JScrollPane(scoreTable);
        scrollPane.setBounds(0, 70, 400, 115);
        scrollPane.setSize(400,135);
        jPanel.add(scrollPane);
    }

    class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}