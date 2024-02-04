import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calculator2 extends JFrame {

//    class RoundButton extends JButton {
//        public RoundButton(String label) {
//            super(label);
//            setOpaque(false);
//            setContentAreaFilled(false);
//            setBorderPainted(false);
//            setFocusPainted(false);
//            setForeground(Color.black);
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            if (getModel().isArmed()) {
//                g.setColor(Color.lightGray);
//            } else {
//                g.setColor(getBackground());
//            }
//            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
//            super.paintComponent(g);
//        }
//
//        @Override
//        protected void paintBorder(Graphics g) {
//            g.setColor(getForeground());
//            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
//        }
//    }
    public double answer=0;
    //private double value=0;
    char current;
    JTextField screen  = new JTextField();
    JRadioButton on = new JRadioButton();
    JRadioButton off = new JRadioButton();
//    JButton[] numbers = new JButton[10];
    JButton one = new JButton();
    JButton two = new JButton();
    JButton three = new JButton();
    JButton four = new JButton();
    JButton five = new JButton();
    JButton six = new JButton();
    JButton seven = new JButton();
    JButton eight = new JButton();
    JButton nine = new JButton();
    JButton zero = new JButton();
    JButton point = new JButton();
//    JButton[] signs = new JButton[5];
    JButton clear = new JButton();
    JButton divide = new JButton();
    JButton times = new JButton();
    JButton minus = new JButton();
    JButton plus = new JButton();
    JButton equal = new JButton();
    JLabel label = new JLabel();
    JLabel data = new JLabel("RECENT CALCULATIONS");
    DBMS dbms = new DBMS("jdbc:mysql://localhost:3306/calculator");


    public static void main(String[] args) {
        try{
            new Calculator().setVisible(true);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

    }
    public Calculator2() throws SQLException {
        System.out.println("Current working directory: "+System.getProperty("user.dir"));
//        System.out.println("Image path: "+ imageP);
        setTitle("My Calculator App");
        setSize(720,560);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\bankk.jpg").getImage());
        setCursor(Cursor.HAND_CURSOR);
        getContentPane().setBackground(new Color(245,245,245));
       /* setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundImage = ImageIO.read(new File("phys.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
        ImageIcon image = new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\pencil sharpener new.jpg");
        label.setLayout(new GridBagLayout());
        label.setIcon(image);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.anchor = GridBagConstraints.WEST;
        screen.setPreferredSize(new Dimension(350,70));
        constraints.gridwidth = 4;
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setFocusable(false);
        screen.setOpaque(true);
        label.add(screen,constraints);
        constraints.gridwidth=1;
        constraints.insets= new Insets(5,0,10,5);
        ButtonGroup turn = new ButtonGroup();
        turn.add(on);
        turn.add(off);
        constraints.gridx=2;
        constraints.gridy=1;
        on.setFont(new Font("Arial",Font.BOLD,15));
        on.setText("On");
        on.setForeground(Color.white);
        on.setBackground(new Color(138, 43, 226));
        label.add(on,constraints);
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e);
              //  totalDisable(e);
            }
        });
        constraints.gridx=3;
        constraints.gridy=1;
        off.setText("Off");
        off.setSelected(true);
        off.setFont(new Font("Arial",Font.BOLD,15));
        off.setBackground(new Color(219, 187, 252));
        label.add(off,constraints);
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offAction(e);
            }
        });
        constraints.insets = new Insets(4,2,4,2);
        constraints.gridx=0;
        constraints.gridy=1;
        clear.setText("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAction(e);
            }
        });
        clear.setBackground(new Color(198, 243, 230));
        constraints.insets= new Insets(3,0,10,50);
        label.add(clear,constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx=0;
        constraints.gridy=2;
        one.setText("1");
        one.setFont(new Font("Arial",Font.BOLD,18));
        two.setFont(new Font("Arial",Font.BOLD,18));
        three.setFont(new Font("Arial",Font.BOLD,18));
        four.setFont(new Font("Arial",Font.BOLD,18));
        five.setFont(new Font("Arial",Font.BOLD,18));
        six.setFont(new Font("Arial",Font.BOLD,18));
        seven.setFont(new Font("Arial",Font.BOLD,18));
        eight.setFont(new Font("Arial",Font.BOLD,18));
        nine.setFont(new Font("Arial",Font.BOLD,18));
        zero.setFont(new Font("Arial",Font.BOLD,18));
        divide.setFont(new Font("Arial",Font.BOLD,18));
        times.setFont(new Font("Arial",Font.BOLD,13));
        minus.setFont(new Font("Arial",Font.BOLD,18));
        plus.setFont(new Font("Arial",Font.BOLD,18));
        point.setFont(new Font("Arial",Font.BOLD,18));
        one.setForeground(new Color(51, 93, 76));
        one.setPreferredSize(new Dimension(45,40));
        two.setPreferredSize(new Dimension(45,40));
        three.setPreferredSize(new Dimension(45,40));
        four.setPreferredSize(new Dimension(45,40));
        five.setPreferredSize(new Dimension(45,40));
        six.setPreferredSize(new Dimension(45,40));
        seven.setPreferredSize(new Dimension(45,40));
        eight.setPreferredSize(new Dimension(45,40));
        nine.setPreferredSize(new Dimension(45,40));
        zero.setPreferredSize(new Dimension(45,40));
        point.setPreferredSize(new Dimension(45,40));
        divide.setPreferredSize(new Dimension(45,35));
        times.setPreferredSize(new Dimension(45,35));
        minus.setPreferredSize(new Dimension(45,35));
        plus.setPreferredSize(new Dimension(45,35));
        equal.setPreferredSize(new Dimension(45,35));
        one.setBackground(new Color(243, 224, 198));
//        one = new RoundButton("1");
        label.add(one,constraints);
        constraints.gridx=1;
        constraints.gridy=2;
        two.setText("2");
        two.setBackground(new Color(243, 224, 198));
//        two = new RoundButton("2");
        label.add(two,constraints);
        constraints.gridx=2;
        constraints.gridy=2;
        three.setText("3");
        three.setBackground(new Color(243, 224, 198));
//        three = new RoundButton("3");
        label.add(three,constraints);
        constraints.gridx=0;
        constraints.gridy=3;
        four.setText("4");
        four.setBackground(new Color(243, 224, 198));
//        four = new RoundButton("4");
        label.add(four,constraints);
        constraints.gridx=1;
        constraints.gridy=3;
        five.setText("5");
        five.setBackground(new Color(243, 224, 198));
//        five = new RoundButton("5");
        label.add(five,constraints);
        constraints.gridx=2;
        constraints.gridy=3;
        six.setText("6");
        six.setBackground(new Color(243, 224, 198));
//        six = new RoundButton("6");
        label.add(six,constraints);
        constraints.gridx=0;
        constraints.gridy=4;
        seven.setText("7");
        seven.setBackground(new Color(243, 224, 198));
//        seven = new RoundButton("7");
        label.add(seven,constraints);
        constraints.gridx=1;
        constraints.gridy=4;
        eight.setText("8");
        eight.setBackground(new Color(243, 224, 198));
//        eight = new RoundButton("8");
        label.add(eight,constraints);
        constraints.gridx=2;
        constraints.gridy=4;
        nine.setText("9");
        nine.setBackground(new Color(243, 224, 198));
//        nine = new RoundButton("9");
        label.add(nine,constraints);
        constraints.gridx=1;
        constraints.gridy=5;
        zero.setText("0");
        zero.setBackground(new Color(243, 224, 198));
//        zero = new RoundButton("0");
        label.add(zero,constraints);
        constraints.gridx=3;
        constraints.gridy=2;
        divide.setText("/");
        divide.setBackground(Color.white);
        label.add(divide,constraints);
        constraints.gridx=3;
        constraints.gridy=3;
        times.setText("X");
        times.setBackground(Color.white);
        label.add(times,constraints);
        constraints.gridx=3;
        constraints.gridy=4;
        minus.setText("-");
        minus.setBackground(Color.white);
        label.add(minus,constraints);
        constraints.gridx=3;
        constraints.gridy=5;
        plus.setText("+");
        plus.setBackground(Color.white);
        label.add(plus,constraints);
        constraints.gridx=3;
        constraints.gridy=6;
        equal.setText("=");
        equal.setBackground(new Color(215, 180, 37));
        equal.setForeground(Color.white);
        equal.setFont(new Font("Arial",Font.BOLD,18));
        label.add(equal,constraints);
        constraints.gridx=0;
        constraints.gridy=7;
        constraints.gridwidth = 4;
        data.setFont(new Font("ARIAL",Font.BOLD,16));
        label.add(data,constraints);
        constraints.gridwidth=1;
        constraints.gridx=0;
        constraints.gridy=5;
        point.setText(".");
        point.setBackground(new Color(243, 224, 198));
        label.add(point,constraints);
        equal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseExitEvent(e);
            }
        });
//        point = new RoundButton(".");
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
        divide.setEnabled(false);
        times.setEnabled(false);
        zero.setEnabled(false);
        clear.setEnabled(false);
        off.setEnabled(false);
        plus.setEnabled(false);
        minus.setEnabled(false);
        equal.setEnabled(false);
        point.setEnabled(false);
        one.setFocusable(false);
        two.setFocusable(false);
        getContentPane().add(label);



        //create a table in the DB
        dbms.createTable("Daniel",
                new String[]{
                    "id INT AUTO_INCREMENT PRIMARY KEY",
                    "calc VARCHAR(400)"
                });
        data.setForeground(Color.white);
        point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pointAction(e);
            }
        });
        //setting everything to false
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAction(e);
            }
        });
        //button actions
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oneAction(e);
            }
        });
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoAction(e);
            }
        });
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threeAction(e);
            }
        });
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fourAction(e);
            }
        });
        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fiveAction(e);
            }
        });
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sixAction(e);
            }
        });
        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sevenAction(e);
            }
        });
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eightAction(e);
            }
        });
        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nineAction(e);
            }
        });
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zeroAction(e);
            }
        });
        /*for ( JButton num: numbers) {
            num.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonAction(e);
                }
            });
        }*/
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plusAction(e);
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minusAction(e);
            }
        });
        times.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timesAction(e);
            }
        });
        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                divideAction(e);
            }
        });
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    equalAction(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offAction(e);
            }
        });
        screen.setBackground(new Color(110, 41, 3, 237));
        screen.setForeground(Color.white);
    }

    private void mouseExitEvent(MouseEvent e) {
        equal.setForeground(Color.white);
        equal.setBackground(new Color(117, 74, 11));
    }

    private void mouseEvent(MouseEvent e) {
        equal.setForeground(Color.blue);
        equal.setBackground(Color.white);
    }

    public void onAction(ActionEvent e){
        screen.setFont(new Font("ARIAL",Font.BOLD,24));
        screen.setText("0");
//        screen.setBackground(new Color(147, 42, 123));
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);
        five.setEnabled(true);
        six.setEnabled(true);
        seven.setEnabled(true);
        eight.setEnabled(true);
        nine.setEnabled(true);
        zero.setEnabled(true);
        clear.setEnabled(true);
        off.setEnabled(true);
        point.setEnabled(true);
    }
    public void offAction(ActionEvent e){
        screen.setText("");
        totalDisable(e);
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
        divide.setEnabled(false);
        times.setEnabled(false);
        zero.setEnabled(false);
        clear.setEnabled(false);
        off.setEnabled(false);
        plus.setEnabled(false);
        minus.setEnabled(false);
        equal.setEnabled(false);
        point.setEnabled(false);
    }
    public void totalDisable(ActionEvent e){
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
        divide.setEnabled(false);
        times.setEnabled(false);
        zero.setEnabled(false);
        clear.setEnabled(false);
        off.setEnabled(false);
        plus.setEnabled(false);
        minus.setEnabled(false);
        equal.setEnabled(false);
        point.setEnabled(false);
        one.setFocusable(false);
        two.setFocusable(false);
    }
    private void oneAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "1";
            } else {
                hold += "1";
            }
            screen.setText(hold);
            signsEnabled();
        }
        //immediately after you click a number you can press any of the operator buttons even the equal
    }
    private void twoAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "2";
            } else {
                hold += "2";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void threeAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "3";
            } else {
                hold += "3";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void fourAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "4";
            } else {
                hold += "4";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void fiveAction(ActionEvent e) {
        String hold = screen.getText();
        if (hold.charAt(0)=='0'){
            hold = "5";
        }else {
            hold +="5";
        }
        screen.setText(hold);
        signsEnabled();
    }
    private void sixAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "6";
            } else {
                hold += "6";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void sevenAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "7";
            } else {
                hold += "7";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void eightAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "8";
            } else {
                hold += "8";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void nineAction(ActionEvent e) {
        String hold = screen.getText();
        if (hold.charAt(0)=='0'){
            hold = "9";
        }else {
            hold +="9";
        }
        screen.setText(hold);
        signsEnabled();
    }
    private void zeroAction(ActionEvent e) {
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = "0";
            } else {
                hold += "0";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    private void pointAction(ActionEvent e){
        String hold = screen.getText();
        if (on.isSelected()) {
            if (hold.charAt(0) == '0') {
                hold = ".";
            } else {
                hold += ".";
            }
            screen.setText(hold);
            signsEnabled();
        }
    }
    /*public void buttonAction(ActionEvent e){
        for (int i=0;i< numbers.length;i++) {
            if (numbers[i].isSelected())screen.setText(String.valueOf((i+1)));
        }*/
       // screen.setText(String.valueOf(numbers[0].isSelected()));
    //}
    public void clearAction(ActionEvent e){
        if (on.isSelected()) {
            screen.setText("0");
            answer=0;
            //screen.setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }
    public void plusAction(ActionEvent e){
        current = '+';
        calcAction(e);
        screen.setText("0");
        //screen.setText("");
        signsDisable();
    }
    public void minusAction(ActionEvent e){
        current = '-';
        calcAction(e);
        screen.setText("0");
        signsDisable();
    }
    public void divideAction(ActionEvent e){
        current = '/';
        calcAction(e);
        screen.setText("0");
        signsDisable();
    }
    public void timesAction(ActionEvent e){
        current = '*';
        calcAction(e);
        screen.setText("0");
        signsDisable();
    }
    public void equalAction(ActionEvent e) throws SQLException {
        calcAction(e);
        screen.setText(String.valueOf(answer));
        dbms.insert("Daniel",
                new String[]{
                        "calc"
                },
                new Object[]{
                        answer
                });
        ResultSet result = dbms.select("Daniel",new String[]{
                "calc"
        });
        try {
            while (result.next()) {
                data.setText(result.getString("calc"));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        //remember to clear the screen immediately after calculations
    }
    public void calcAction(ActionEvent e){
       String hold = screen.getText();
       double val = Double.parseDouble(hold);
       if (current=='+'){
           if (answer==0){
               answer= val;
           }else {
               answer+=val;
           }
       }
       else if (current=='-') {
           // if you use this originally answer=0 so you'll always get a -ve answer
//           answer= answer - Double.parseDouble(hold);
           //instead use this
           if (answer==0){
               answer= val;
           }else {
               answer-=val;
           }
       }
       else if (current=='*') {
           if (answer==0){
               answer= val;
           }else {
               answer*=val;
           }
       } else if (current=='/') {
           if (answer==0){
               answer= val;
           }else {
               answer/=val;
           }
       }
    }
    public void signsDisable(){
        plus.setEnabled(false);
        minus.setEnabled(false);
        times.setEnabled(false);
        divide.setEnabled(false);
        equal.setEnabled(false);
    }
    public void signsEnabled(){
        plus.setEnabled(true);
        minus.setEnabled(true);
        times.setEnabled(true);
        divide.setEnabled(true);
        equal.setEnabled(true);
    }
}
