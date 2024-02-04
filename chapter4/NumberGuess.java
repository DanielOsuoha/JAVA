package chapter4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuess extends JFrame {
    int maximum = 100;
    int minimum = 1;
    JButton play = new JButton();
    int val;
    Random gen = new Random();
    JLabel show = new JLabel();
    JScrollBar number = new JScrollBar();
    JTextField enter = new JTextField();
    JLabel enter_label = new JLabel();
    JLabel max = new JLabel();
    JLabel min = new JLabel();
    public static void main(String[] args) {
        new NumberGuess().setVisible(true);
    }
    public NumberGuess(){
        setTitle("Guess a number");
        setSize(500,400);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        show.setFont(new Font("Arial",Font.BOLD,18));
        constraints.insets = new Insets(0,1,10,1);
        show.setText("I have a number can you guess what it is?");
        constraints.gridwidth=3;
        getContentPane().add(show,constraints);
        constraints.insets = new Insets(4,4,4,4);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth=1;
        min.setText("Minimum: "+minimum);
        constraints.gridx=0;
        constraints.gridy=1;
        getContentPane().add(min,constraints);
        max.setText("Maximum: "+maximum);
        constraints.gridx=2;
        constraints.gridy=1;
        getContentPane().add(max,constraints);
        number.setOrientation(Adjustable.HORIZONTAL);
        number.setPreferredSize(new Dimension(140,30));
        number.setMaximum(100+number.getVisibleAmount());
        number.setMinimum(1);
        number.setUnitIncrement(1);
        number.setBlockIncrement(5);
        number.setValue(50);
        number.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                numberChanged(e);
            }
        });
        constraints.gridx=1;
        constraints.gridy=1;
        getContentPane().add(number,constraints);
        constraints.gridx =1;
        constraints.gridy = 2;
        enter.setColumns(10);
        enter.setText("50");
        enter.setEditable(false);
        enter.setOpaque(true);
        enter.setForeground(Color.white);
        enter.setBackground(new Color(60, 37, 66, 230));
        enter.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(enter,constraints);
        enter_label.setText("Your number:");
        constraints.gridx=0;
        getContentPane().add(enter_label,constraints);
        constraints.gridx=2;
        getContentPane().add(play,constraints);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAction(e);
            }
        });
        play.setBackground(new Color(133, 116, 62));
        play.setForeground(Color.white);
        play.setText("Play Again");
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(e);
            }
        });
        play.doClick();
//        play.setEnabled(false);

    }

    private void exitMouse(MouseEvent e) {
        play.setBackground(new Color(133, 116, 62));
        play.setForeground(Color.white);
    }

    private void enterMouse(MouseEvent e) {
        play.setForeground(new Color(169, 17, 58));
        play.setBackground(Color.white);
    }

    private void playAction(ActionEvent e){
        val = gen.nextInt(100)+1;
        show.setText("I have a number can you guess what it is?");
    }

    private void numberChanged(AdjustmentEvent e) {
        int num = number.getValue();
        enter.setText(String.valueOf(num));
        if (num==val){
            show.setText("Bravo!! You've gotten it. My number was "+val);
            play.setEnabled(true);
        }else {
            if (num>minimum)
                minimum += (val-minimum)/4;
            maximum -= (maximum-val)/4;
            min.setText("Minimum: "+minimum);
            max.setText("Maximum: "+maximum);
        }
    }
}