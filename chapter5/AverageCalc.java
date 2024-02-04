package chapter5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class AverageCalc extends JFrame {
    JLabel label = new JLabel();
    JTextField enter = new JTextField();
    JButton add = new JButton();
    JScrollPane pane = new JScrollPane();
    DefaultListModel myModel = new DefaultListModel();
    JList list = new JList();
    JButton clear = new JButton();
    JTextField avg = new JTextField();
    JButton calc = new JButton();
    ImageIcon background;
    JLabel backLabel = new JLabel();
    public static void main(String[] args) {
        new AverageCalc().setVisible(true);
    }
    public AverageCalc(){
        setTitle("Average Value");
        setSize(350,500);
        getContentPane().setBackground(new Color(234, 162, 162));
        //the easiest way to center the whole thing
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
//        background = new ImageIcon("src/chapter5/bankk.jpg");
//        backLabel.setIcon(background);
//        backLabel.setSize(
//      30,50);
        label.setText("Enter Number");
        add(label,constraints);
        constraints.gridy=1;
        enter.setBackground(Color.white);
        enter.setColumns(15);
//        enter.setBounds(50,50,100,50);
//        backLabel.add(enter);
        getContentPane().add(enter,constraints);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterAction(e);
            }
        });
        constraints.insets = new Insets(10,5,1,5);
        constraints.gridy=2;
        getContentPane().add(backLabel);
        add.setText("Add to List");
        getContentPane().add(add,constraints);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction(e);
            }
        });
        list.setModel(myModel);
        list.setBackground(Color.white);
        pane.setPreferredSize(new Dimension(150,150));
        pane.setViewportView(list);
        list.setPreferredSize(new Dimension(200,150));
        constraints.gridy=3;
        getContentPane().add(pane,constraints);
        clear.setText("Clear List");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAction(e);
            }
        });
        constraints.gridy=4;
        getContentPane().add(clear,constraints);
        avg.setBackground(Color.white);
        avg.setColumns(15);
        avg.setFocusable(false);
        constraints.gridy=5;
        avg.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(avg,constraints);
        calc.setText("Compute Average");
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                calculate(e);
            }
        });
        constraints.gridy=6;
        getContentPane().add(calc,constraints);
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate(e);
            }
        });
    }

    private void addAction(ActionEvent e) {
         //first check for a valid number
        if(!validateDecimalNumber(enter)){
            return;
        }
        myModel.addElement(enter.getText());
        enter.setText("");
        enter.requestFocus();
    }

    private void enterAction(ActionEvent e) {
        add.doClick();
    }

    private void calculate(ActionEvent e) {

        int count = myModel.getSize();
        //make sure numbers have been entered
        if (count!=0){
            double[] values = new double[count];
            double average;
            //load values in array and compute average
            for (int i = 0; i < count; i++) {
                values[i] = Double.parseDouble(myModel.getElementAt(i).toString());
            }
            average = average(count,values);
            avg.setText(new DecimalFormat("0.00").format(average));
            enter.requestFocus();
        }
    }

    private void clearAction(ActionEvent e) {
        //resets form for another average
        myModel.removeAllElements();
        avg.setText("");
        enter.requestFocus();
    }
    public double average(int vals, double[] values){
        double sum = 0;
        for (int i = 0; i < vals; i++) {
            sum+=values[i];
        }
        return sum/vals;
    }
    public boolean validateDecimalNumber(JTextField tf) {
// checks to see if text field contains
// valid decimal number with only digits and a single decimal point or negative sign
        String s = tf.getText().trim();
        boolean hasDecimal = false;
        boolean valid = true;
        if (s.length() == 0)
        {
            valid = false;
        }
        else
        {
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if ((c >= '0' && c <= '9') || (c == '-' && i == 0)) {
                    continue;
                } else if (c == '.' && !hasDecimal) {
                    hasDecimal = true;
                 } else{
                    // invalid character found
                    valid = false;
                 }
            }
        }
        if (valid)
        {
         tf.setText(s);
        }
        else
        {
            tf.setText("");
            tf.requestFocus();
        }
        return (valid);
    }

}
