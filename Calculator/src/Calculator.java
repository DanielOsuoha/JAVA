import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;

import static java.awt.Color.*;
import static java.awt.event.KeyEvent.*;

public class Calculator extends JFrame implements KeyListener {
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
    JToggleButton mode = new JToggleButton("DarkM");

    public double answer = 0;
    private boolean user_name_taken=false;
    public boolean numberClicked=false;
    public int user_id;
    //private double value=0;
    private String keep = "";
    char current;
    JTextField screen = new JTextField();
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
    JButton del = new JButton("Del");
    JLabel label = new JLabel();
    JLabel data = new JLabel();
    //creating a tabbed pane
    JTabbedPane pane = new JTabbedPane();
    JPanel his = new JPanel();
    JLabel hisBack = new JLabel();


    @Override
    public void setName(String name) {
        this.name = name;
    }

    private String name;
    DBMS dbms = null;


    public static void main(String[] args) throws SQLException {
        Calculator calc = null;
        try {
            calc = new Calculator();
            calc.setVisible(true);
        } catch (SQLException e) {
            System.out.println("The Connection Failed");
            e.printStackTrace();
        } catch (Exception e) {
            //   new Calculator().setVisible(true);
//            e.printStackTrace();
        }

    }

    public Calculator() throws SQLException {
        try {
            dbms = new DBMS("jdbc:mysql://localhost:3306/calculator");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to DB");
            JOptionPane.showMessageDialog(null, "Connection failure(check if server is running)");
        }
        if(dbms!=null) {
            boolean done = false;
            while (!done) {
                name = JOptionPane.showInputDialog(getParent(), "Enter your username", "");
                if (name == null) {
                    // User clicked Cancel or closed the dialog
                    System.exit(0); // Exit the program
                } else if (name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(getParent(), "Please enter a valid username");
                } else {
                    done = true;
                }
                ResultSet result = dbms.select("users",new String[]{"name"});
                while (result.next()){
                    if (result.getString("name").equals(name)){
                        user_name_taken = true;
                    }
//                    else {
//                        name = JOptionPane.showInputDialog("The username has already been taken ");
//                    }
                }
                if (!user_name_taken) {
                    dbms.insert("users", new String[]{"name"}, new Object[]{name});
                }
            }
            //get user's id from users table
            //Don't forget that you'd have to first check and see if the user exists
            ResultSet set = dbms.selectWhereClause("users", new String[]{"id"}, ("name= '" + name+"'"));
            if (set.next()){
                user_id = Integer.parseInt(set.getString("id"));
            }

        }
//            System.out.println("Current working directory: " + System.getProperty("user.dir"));
        setTitle("My Calculator App");
        setSize(720, 560);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\bankk.jpg").getImage());
        setCursor(Cursor.HAND_CURSOR);

        ImageIcon image = new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\pencil sharpener new.jpg");
        label.setLayout(new GridBagLayout());
        label.setIcon(image);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        screen.setPreferredSize(new Dimension(350, 70));
        constraints.gridwidth = 4;
        screen.setHorizontalAlignment(SwingConstants.RIGHT);
        screen.setFocusable(false);
        screen.setOpaque(true);
        label.add(screen, constraints);
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 0, 10, 5);
        ButtonGroup turn = new ButtonGroup();
        turn.add(on);
        turn.add(off);
        constraints.gridx = 2;
        constraints.gridy = 1;
        on.setFont(new Font("Arial", Font.BOLD, 15));
        on.setText("On");
        on.setForeground(white);
        on.setBackground(new Color(138, 43, 226));
        label.add(on, constraints);
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAction(e);
                //  totalDisable(e);
            }
        });
        constraints.gridx = 3;
        constraints.gridy = 1;
        off.setText("Off");
        off.setSelected(true);
        off.setFont(new Font("Arial", Font.BOLD, 15));
        off.setBackground(new Color(219, 187, 252));
        label.add(off, constraints);
        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offAction(e);
            }
        });
        constraints.insets = new Insets(4, 2, 4, 2);
        constraints.gridx = 0;
        constraints.gridy = 1;
        clear.setText("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAction(e);
            }
        });
        clear.setBackground(new Color(205, 198, 234, 242));
        constraints.insets = new Insets(3, 0, 10, 50);
        label.add(clear, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        one.setText("1");
        one.setFont(new Font("Arial", Font.BOLD, 18));
        two.setFont(new Font("Arial", Font.BOLD, 18));
        three.setFont(new Font("Arial", Font.BOLD, 18));
        four.setFont(new Font("Arial", Font.BOLD, 18));
        five.setFont(new Font("Arial", Font.BOLD, 18));
        six.setFont(new Font("Arial", Font.BOLD, 18));
        seven.setFont(new Font("Arial", Font.BOLD, 18));
        eight.setFont(new Font("Arial", Font.BOLD, 18));
        nine.setFont(new Font("Arial", Font.BOLD, 18));
        zero.setFont(new Font("Arial", Font.BOLD, 18));
        divide.setFont(new Font("Arial", Font.BOLD, 18));
        times.setFont(new Font("Arial", Font.BOLD, 13));
        minus.setFont(new Font("Arial", Font.BOLD, 18));
        plus.setFont(new Font("Arial", Font.BOLD, 18));
        point.setFont(new Font("Arial", Font.BOLD, 18));
        del.setFont(new Font("Arial", Font.BOLD, 12));
        one.setPreferredSize(new Dimension(45, 40));
        two.setPreferredSize(new Dimension(45, 40));
        three.setPreferredSize(new Dimension(45, 40));
        four.setPreferredSize(new Dimension(45, 40));
        five.setPreferredSize(new Dimension(45, 40));
        six.setPreferredSize(new Dimension(45, 40));
        seven.setPreferredSize(new Dimension(45, 40));
        eight.setPreferredSize(new Dimension(45, 40));
        nine.setPreferredSize(new Dimension(45, 40));
        zero.setPreferredSize(new Dimension(45, 40));
        point.setPreferredSize(new Dimension(45, 40));
        divide.setPreferredSize(new Dimension(45, 35));
        times.setPreferredSize(new Dimension(45, 35));
        minus.setPreferredSize(new Dimension(45, 35));
        plus.setPreferredSize(new Dimension(45, 35));
        equal.setPreferredSize(new Dimension(45, 35));
        del.setPreferredSize(new Dimension(52, 37));
        one.setBackground(new Color(243, 224, 198));
//        one = new RoundButton("1");
        label.add(one, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        two.setText("2");
        two.setBackground(new Color(243, 224, 198));
//        two = new RoundButton("2");
        label.add(two, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        three.setText("3");
        three.setBackground(new Color(243, 224, 198));
//        three = new RoundButton("3");
        label.add(three, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        four.setText("4");
        four.setBackground(new Color(243, 224, 198));
//        four = new RoundButton("4");
        label.add(four, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        five.setText("5");
        five.setBackground(new Color(243, 224, 198));
//        five = new RoundButton("5");
        label.add(five, constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        six.setText("6");
        six.setBackground(new Color(243, 224, 198));
//        six = new RoundButton("6");
        label.add(six, constraints);
        constraints.gridx = 0;
        constraints.gridy = 4;
        seven.setText("7");
        seven.setBackground(new Color(243, 224, 198));
//        seven = new RoundButton("7");
        label.add(seven, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        eight.setText("8");
        eight.setBackground(new Color(243, 224, 198));
//        eight = new RoundButton("8");
        label.add(eight, constraints);
        constraints.gridx = 2;
        constraints.gridy = 4;
        nine.setText("9");
        nine.setBackground(new Color(243, 224, 198));
//        nine = new RoundButton("9");
        label.add(nine, constraints);
        constraints.gridy = 5;
        del.setBackground(new Color(215, 180, 37));
        del.setForeground(white);
        del.setBackground(new Color(243, 224, 198));
        label.add(del, constraints);
        constraints.gridx = 1;
//        constraints.gridy=5;
        zero.setText("0");
        zero.setBackground(new Color(243, 224, 198));
//        zero = new RoundButton("0");
        label.add(zero, constraints);
        constraints.gridx = 3;
        constraints.gridy = 2;
        divide.setText("/");
        divide.setBackground(white);
        label.add(divide, constraints);
        constraints.gridx = 3;
        constraints.gridy = 3;
        times.setText("X");
        times.setBackground(white);
        label.add(times, constraints);
        constraints.gridx = 3;
        constraints.gridy = 4;
        minus.setText("-");
        minus.setBackground(white);
        label.add(minus, constraints);
        constraints.gridx = 3;
        constraints.gridy = 5;
        plus.setText("+");
        plus.setBackground(white);
        label.add(plus, constraints);
        constraints.gridx = 3;
        constraints.gridy = 6;
        equal.setText("=");
        equal.setBackground(new Color(148, 132, 67));
        equal.setForeground(white);
        equal.setFont(new Font("Arial", Font.BOLD, 18));
        label.add(equal, constraints);
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 4;
        data.setFont(new Font("ARIAL", Font.BOLD, 16));
        label.add(data, constraints);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        point.setText(".");
        point.setBackground(new Color(243, 224, 198));
        label.add(point, constraints);
        del.setForeground(Color.black);
        constraints.gridx=4;
        constraints.gridy=0;
        mode.setBackground(Color.black);
        mode.setHorizontalAlignment(SwingConstants.LEFT);
        mode.setHorizontalTextPosition(SwingConstants.LEFT);
        mode.setPreferredSize(new Dimension(75,27));
        mode.setFont(new Font("ARIAL",Font.BOLD,11));
        mode.setForeground(white);
        label.add(mode,constraints);
        mode.addMouseListener(new MouseAdapter() {
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delAction(e);
            }
        });
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
        del.setEnabled(false);


        pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    paneStateChanged(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //create a table in the DB

        //pseudocode for how this should actually work
        //check if the user is present in the users table
        //if not add a new record(row) to the users tables

//                dbms.insert("users",new String[]{"name"},new Object[]{name});

        data.setForeground(white);
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
        screen.setBackground(new Color(146, 180, 180));
        screen.setForeground(black);
        JPanel calc = new JPanel();
        calc.add(label);
        pane.addTab("Calculator", calc);
        his.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        his.setLayout(new GridBagLayout());
        hisBack.setLayout(new GridBagLayout());
        ImageIcon icon = new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\learn.jpg");
        hisBack.setIcon(icon);
        hisBack.add(data, constraints);
        his.add(hisBack, constraints);
        constraints.gridx = 1;
        data.setFont(new Font("ARIAL", Font.BOLD, 20));
        data.setForeground(new Color(105, 58, 4));
        pane.addTab("History", his);
        getContentPane().add(pane);

        //toggle button

        mode.addActionListener(e -> toggleDarkMode());

        addKeyListener(this);
        setFocusable(true);
    /* try (ResultSet res = dbms.selectWhereClause("users", new String[]{"id"}, "name=" + name)) {
         user_id = Integer.parseInt(res.getString("id"));
     }catch (SQLException e){
         System.out.println(" ");
     }*/
//        plus.doClick();
//        zero.doClick();
    }
    private void delAction(ActionEvent e){
        String old = screen.getText();
        String newString = old.substring(0,old.length()-1);
        if (newString.length()==0)screen.setText("0");
        else screen.setText(newString);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        int keyCode = e.getKeyCode();
        if (Character.isDigit(keyChar)){
            handleNumericInput(keyChar);
        }else {
            if (keyChar == '-') minus.doClick();
            else if (keyChar == '+') plus.doClick();
            else if (keyChar== '*') times.doClick();
            else if (keyChar == '/') divide.doClick();
            else if (keyChar == '.' ) point.doClick();
            else if (keyChar== '\n') equal.doClick();
            //WHY IS THIS ONE BELOW NOT WORKING
            else if (keyCode == VK_DELETE) del.doClick();
//            handleOtherInput(keyCode);
        }
    }
    private void handleNumericInput(char keyChar){
        if (keyChar=='1') one.doClick();
        else if (keyChar=='2') two.doClick();
        else if (keyChar=='3') three.doClick();
        else if (keyChar=='4') four.doClick();
        else if (keyChar=='5') five.doClick();
        else if (keyChar=='6') six.doClick();
        else if (keyChar=='7') seven.doClick();
        else if (keyChar=='8') eight.doClick();
        else if (keyChar=='9') nine.doClick();
        else if (keyChar=='0') zero.doClick();
    }
    private void handleOtherInput(int keyCode){
        if (keyCode == VK_SUBTRACT) minus.doClick();
        else if (keyCode == VK_PLUS) plus.doClick();
        else if (keyCode == VK_MULTIPLY) times.doClick();
        else if (keyCode == VK_DIVIDE) divide.doClick();
        else if (keyCode == VK_DECIMAL ) point.doClick();
        else if (keyCode==VK_ENTER) equal.doClick();
        else if (keyCode==VK_DELETE) del.doClick();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        char keyChar = e.getKeyChar();
//        int keyCode = e.getKeyCode();
//        System.out.println(keyChar);
//        if (keyCode== keyChar) one.doClick();
    }
    private void toggleDarkMode(){
        if (mode.isSelected()){
            setDarkMode();
        }
        else setLightMode();
    }
    private void setDarkMode(){
            getContentPane().setBackground(new Color(30,30,30));
            screen.setBackground(new Color(35, 35, 35));
            screen.setForeground(white);
            his.setBackground(Color.black);
            mode.setForeground(Color.black);
//            mode.setBackground(Color.white);
            mode.setText("LightM");
            one.setBackground(Color.black);
            two.setBackground(Color.black);
            three.setBackground(Color.black);
            four.setBackground(Color.black);
            five.setBackground(Color.black);
            six.setBackground(Color.black);
            seven.setBackground(Color.black);
            eight.setBackground(Color.black);
            nine.setBackground(Color.black);
            zero.setBackground(Color.black);
            point.setBackground(Color.black);
            clear.setBackground(Color.black);
            divide.setBackground(Color.black);
            times.setBackground(Color.black);
            minus.setBackground(Color.black);
            plus.setBackground(Color.black);
            del.setBackground(Color.black);

            one.setForeground(white);
        two.setForeground(white);
        three.setForeground(white);
        five.setForeground(white);
        four.setForeground(white);
        six.setForeground(white);
        seven.setForeground(white);
        eight.setForeground(white);
        nine.setForeground(white);
        zero.setForeground(white);
        point.setForeground(white);
        clear.setForeground(white);
        divide.setForeground(white);
        times.setForeground(white);
        minus.setForeground(white);
        plus.setForeground(white);
        del.setForeground(white);
            //set background and text colors for other components
        }

    private void setLightMode(){
            getContentPane().setBackground(new Color(245,245,245));
            screen.setBackground(new Color(146, 180, 180,237));
            screen.setForeground(black);
            mode.setForeground(white);
            mode.setBackground(Color.black);
            mode.setText("Dark Mode");
            his.setBackground(white);
            one.setBackground(white);
            two.setBackground(white);
            three.setBackground(white);
            five.setBackground(white);
            four.setBackground(white);
            six.setBackground(white);
            eight.setBackground(white);
            seven.setBackground(white);
            nine.setBackground(white);
            zero.setBackground(white);
            point.setBackground(white);
            clear.setBackground(white);
            divide.setBackground(white);
            times.setBackground(white);
            minus.setBackground(white);
            plus.setBackground(white);
            del.setBackground(white);

        one.setForeground(Color.black);
        two.setForeground(Color.black);
        three.setForeground(Color.black);
        four.setForeground(Color.black);
        five.setForeground(Color.black);
        six.setForeground(Color.black);
        seven.setForeground(Color.black);
        eight.setForeground(Color.black);
        nine.setForeground(Color.black);
        zero.setForeground(Color.black);
        point.setForeground(Color.black);
        clear.setForeground(Color.black);
        divide.setForeground(black);
        times.setForeground(Color.black);
        minus.setForeground(Color.black);
        plus.setForeground(Color.black);
        del.setForeground(Color.black);
            //Set background and text colors for the other components
        }

        private void paneStateChanged (ChangeEvent e) throws SQLException {
            if (pane.getSelectedIndex() == 1) {
                try{
                    dbms =  new DBMS("jdbc:mysql://localhost:3306/calculator");
                }catch (SQLException ex){
                    StringBuilder content = new StringBuilder("<html>");
                    content.append("You are not connected to your database").append("<br>");
                    content.append("Connect to your database to view your calculation history").append("<br>");
                    JOptionPane.showMessageDialog(null,content);
                    pane.setSelectedIndex(0);
                }
               /* ResultSet result = dbms.select(name, new String[]{
                        "calc"
                });*/
                if (dbms != null) {
                    try {
                        ResultSet result = dbms.select("history", new String[]{
                                String.valueOf(user_id), "calc"
                        });
                        StringBuilder labelContent = new StringBuilder("<html>");
                        labelContent.append("RECENT CALCULATIONS").append("<br>");
                        while (result.next()) {
                            String val = result.getString("calc");
                            labelContent.append(val).append("<br>");
                        }
                        labelContent.append("</html>");
                        data.setText(labelContent.toString());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
//                data.setText(String.valueOf(user_id));
            }
        }

        private void mouseExitEvent (MouseEvent e){
            equal.setForeground(white);
            equal.setBackground(new Color(117, 74, 11));
        }

        private void mouseEvent (MouseEvent e){
            equal.setForeground(Color.blue);
            equal.setBackground(white);
        }
    public void onAction (ActionEvent e){
            screen.setFont(new Font("ARIAL", Font.BOLD, 24));
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
    public void offAction (ActionEvent e){
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
            del.setEnabled(false);
        }
    public void totalDisable (ActionEvent e){
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
            del.setEnabled(true);
        }
    private void oneAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "1";
                    keep += hold;
                } else {
                    hold += "1";
                    keep += "1";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
            //immediately after you click a number you can press any of the operator buttons even the equal
        }
    private void twoAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "2";
                    keep += hold;
                } else {
                    hold += "2";
                    keep += "2";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;

        }
    private void threeAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "3";
                    keep += hold;
                } else {
                    hold += "3";
                    keep += "3";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;

        }
    private void fourAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "4";
                    keep += hold;
                } else {
                    hold += "4";
                    keep += "4";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void fiveAction (ActionEvent e){
            String hold = screen.getText();
            if (hold.charAt(0) == '0') {
                hold = "5";
                keep += hold;
            } else {
                hold += "5";
                keep += "5";
            }
            screen.setText(hold);
            signsEnabled();
            numberClicked=true;
        }
    private void sixAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "6";
                    keep += hold;
                } else {
                    hold += "6";
                    keep += "6";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void sevenAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "7";
                    keep += hold;
                } else {
                    hold += "7";
                    keep += "7";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void eightAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "8";
                    keep += hold;
                } else {
                    hold += "8";
                    keep += "8";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void nineAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = "9";
                    keep += hold;
                } else {
                    hold += "9";
                    keep += "9";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void zeroAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    keep += hold;
                    hold = "0";
                } else {
                    hold += "0";
                    keep += "0";
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=true;
        }
    private void pointAction (ActionEvent e){
            String hold = screen.getText();
            if (on.isSelected()) {
                if (hold.charAt(0) == '0') {
                    hold = ".";
                    keep += hold;
                } else {
                    hold += ".";
                    keep += hold;
                }
                screen.setText(hold);
                signsEnabled();
            }
            numberClicked=false;
        }
    /*public void buttonAction(ActionEvent e){
        for (int i=0;i< numbers.length;i++) {
            if (numbers[i].isSelected())screen.setText(String.valueOf((i+1)));
        }*/
    // screen.setText(String.valueOf(numbers[0].isSelected()));
    //}
    public void clearAction (ActionEvent e){
            if (on.isSelected()) {
                on.doClick();
                screen.setText("0");
                keep = "";
                answer = 0;
                //screen.setHorizontalAlignment(SwingConstants.RIGHT);
            }
//            numberClicked=false;
        }
    public void plusAction (ActionEvent e){
                current = '+';
                keep += current;
                calcAction(e);
                screen.setText("0");
                //screen.setText("");
                signsDisable();
                System.out.println(answer);
//                numberClicked=false;
        }
    public void minusAction (ActionEvent e){
//            if (!screen.getText().equals("0")) {
            current = '-';
            keep += current;
            calcAction(e);
            screen.setText("0");
            signsDisable();
//            numberClicked=false;
    }
//        }
    public void divideAction (ActionEvent e){
                current = '/';
                keep += current;
                calcAction(e);
                screen.setText("0");
                signsDisable();
//                numberClicked=false;
        }

        public void timesAction (ActionEvent e){
//            if (!screen.getText().equals("0")) {
                current = '*';
                keep += current;
                calcAction(e);
                screen.setText("0");
                signsDisable();
//                numberClicked = false;
//            }
        }


        public void equalAction (ActionEvent e) throws SQLException {
            calcAction(e);
            keep += " = " + answer;
            screen.setText(String.valueOf(answer));
            disableNumbers();
            if(dbms!=null) {
                dbms = new DBMS("jdbc:mysql://localhost:3306/calculator");
                dbms.insert("history",
                        new String[]{
                                "user_id","calc"
                        },
                        new Object[]{
                               user_id, keep
                        });
            }
            keep = "";
            del.setEnabled(false);
            numberClicked=false;
        }

    private void disableNumbers () {
            one.setEnabled(false);
            two.setEnabled(false);
            three.setEnabled(false);
            four.setEnabled(false);
            five.setEnabled(false);
            six.setEnabled(false);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
            zero.setEnabled(false);
            point.setEnabled(false);
        }

        private void enableNumbers () {
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
        point.setEnabled(true);
        }
    public void calcAction (ActionEvent e){
        String hold = screen.getText();
        double val = Double.parseDouble(hold);

        if (current == '+') {
            if (answer == 0) {
                answer = val;
            } else {
                answer += val;
            }
        } else if (current == '-') {
            // if you use this originally answer=0 so you'll always get a -ve answer
//          answer= answer - Double.parseDouble(hold);
            //instead use this
            if (answer == 0) {
                answer = val;
            } else {
                answer -= val;
            }
        } else if (current == '*') {
            if (answer == 0) {
                answer = val;
            } else {
                answer *= val;
            }
        } else if (current == '/') {
            answer/=val;
        }else {
            answer=val;
        }
        enableNumbers();

    }

    public void signsDisable () {
            plus.setEnabled(false);
            minus.setEnabled(false);
            times.setEnabled(false);
            divide.setEnabled(false);
            equal.setEnabled(false);
            del.setEnabled(false);
        }

        public void signsEnabled () {
            plus.setEnabled(true);
            minus.setEnabled(true);
            times.setEnabled(true);
            divide.setEnabled(true);
            equal.setEnabled(true);
            del.setEnabled(true);
        }
}

