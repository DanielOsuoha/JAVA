package chapter4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RGBPane extends JFrame{
    int red =0;
    int green =0;
    int blue =0;
    JLabel show = new JLabel();
    JSlider first = new JSlider();
    JSlider second = new JSlider();
    JSlider third = new JSlider();
    JLabel label = new JLabel();
    JLabel background = new JLabel();

    public static void main(String[] args) {
        new RGBPane().setVisible(true);
    }
    public RGBPane(){
        setTitle("The Color Pane");
        setSize(720,480);
        setResizable(false);
        //quickly setting the whole thing to the middle of the page
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        ImageIcon image = new ImageIcon("src/chapter5/bankk.jpg");
        background.setIcon(image);
        background.setPreferredSize(new Dimension(720,480));
        add(background,constraints);
        background.setLayout(new GridBagLayout());
        constraints.insets = new Insets(10,10,10,10);
        label.setPreferredSize(new Dimension(450,40));
        constraints.gridwidth=3;
        label.setOpaque(true);
        label.setBackground(Color.black);
        background.add(label,constraints);
        constraints.gridwidth=1;
        constraints.gridy=1;
        first.setValue(0);
        first.setMaximum(255);
        first.setMinimum(0);
        first.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                firstChanged(e);
            }
        });
        background.add(first,constraints);
        constraints.gridx=1;
        second.setValue(0);
        second.setMaximum(255);
        second.setMinimum(0);
        second.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                secondChanged(e);
            }
        });
        background.add(second,constraints);
        constraints.gridx=2;
        third.setValue(0);
        third.setMaximum(255);
        third.setMinimum(0);
        third.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                thirdChanged(e);
            }
        });
        background.add(third,constraints);
        show.setText("The sliders represent the RED, GREEN and BLUE values in that order");
        show.setForeground(new Color(101, 42, 133));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth=3;
        background.add(show,constraints);
    }

    private void firstChanged(ChangeEvent e) {
        red = first.getValue();
        determineColor();
    }
    private void secondChanged(ChangeEvent e){
        green = second.getValue();
        determineColor();
    }
    private void thirdChanged(ChangeEvent e) {
        blue = third.getValue();
        determineColor();
    }
    private void determineColor(){
        label.setBackground(new Color(red,green,blue));
    }
}
