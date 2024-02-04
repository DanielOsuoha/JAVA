package chapter4;

import javax.swing.*;
import java.awt.*;

public class StudentDatabase extends JFrame {
    JTextField name = new JTextField();
    JLabel nLabel = new JLabel("Enter name: ");
    JSpinner grade = new JSpinner();
    JLabel g_label = new JLabel("Select grade: ");
    JPanel gPanel = new JPanel();
    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");
    ButtonGroup gender = new ButtonGroup();
    public static void main(String[] args) {
        new StudentDatabase().setVisible(true);
    }
    public StudentDatabase(){
        setTitle("Student Database");
        setSize(400,400);
        getContentPane().setBackground(Color.white);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        add(nLabel,constraints);
        constraints.insets = new Insets(7,7,7,7);
        constraints.gridx=1;
        name.setColumns(20);
        add(name,constraints);
        gender.add(male);
        gender.add(female);
        gPanel.setLayout(new GridBagLayout());
        constraints.gridx=0;
        gPanel.add(male,constraints);
        constraints.gridx=1;
        gPanel.add(female,constraints);
        gPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
        constraints.gridwidth=2;
        constraints.gridx=0;
        gPanel.setPreferredSize(new Dimension(270,50));
        gPanel.setBackground(new Color(200, 114, 218));
        add(gPanel,constraints);
        constraints.gridwidth=1;
        constraints.gridy=2;
        constraints.gridx =0;
        add(g_label,constraints);
        grade.setModel(new SpinnerNumberModel(3,1,6,1));
//        grade.setToolTipText("Select Grade");
        constraints.gridx=1;
        grade.setPreferredSize(new Dimension(200,30));
        grade.setAlignmentX(Component.CENTER_ALIGNMENT);
        grade.setFont(new Font("Arial",Font.BOLD,16));
        grade.setFocusable(false);
        add(grade,constraints);
    }
}
