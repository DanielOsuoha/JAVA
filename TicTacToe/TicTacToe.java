package chapter4.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class TicTacToe extends JFrame {
    boolean comp_played;
    int pos;
    int num;
    Random gen = new Random();
        int count=0;
        JLabel show = new JLabel();
        JButton first = new JButton("*");
        JButton sec = new JButton("*");
        JButton third = new JButton("*");
        JButton four = new JButton("*");
        JButton five = new JButton("*");
        JButton six = new JButton("*");
        JButton seven = new JButton("*");
        JButton eight = new JButton("*");
        JButton nine = new JButton("*");
        JButton exit = new JButton("Exit");
        JButton play = new JButton("New Game");
        char player = 'X';
        private final int rows = 3;
        private final int cols = 3;
        char[][] board = new char[rows][cols];
        String ans = "";
        private char temp='X';
//        char person;
        private int choice;

        public static void main(String[] args) {
            new TestClass().setVisible(true);
        }
        public TicTacToe() {
                choice = JOptionPane.showConfirmDialog(this,"Single Player?","Player Choice",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                reset();
                System.out.println(""+choice);
                if(choice==-1)System.exit(0);
                setIconImage(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\bankk.jpg").getImage());
                setTitle("TicTacToe Game");
                setSize(new Dimension(400, 500));
                getContentPane().setBackground(new Color(200, 182, 206));
                setLayout(new GridBagLayout());
                setLocationRelativeTo(null);
                setResizable(false);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                first.setFont(new Font("Arial", Font.BOLD, 86));
                sec.setFont(new Font("Arial", Font.BOLD, 86));
                third.setFont(new Font("Arial", Font.BOLD, 86));
                four.setFont(new Font("Arial", Font.BOLD, 86));
                five.setFont(new Font("Arial", Font.BOLD, 86));
                six.setFont(new Font("Arial", Font.BOLD, 86));
                seven.setFont(new Font("Arial", Font.BOLD, 86));
                eight.setFont(new Font("Arial", Font.BOLD, 86));
                nine.setFont(new Font("Arial", Font.BOLD, 86));
                first.setOpaque(true);
                first.setBackground(Color.white);
                sec.setOpaque(true);
                sec.setBackground(Color.white);
                third.setOpaque(true);
                third.setBackground(Color.white);
                four.setOpaque(true);
                four.setBackground(Color.white);
                five.setOpaque(true);
                five.setBackground(Color.white);
                six.setOpaque(true);
                six.setBackground(Color.white);
                seven.setOpaque(true);
                seven.setBackground(Color.white);
                eight.setOpaque(true);
                eight.setBackground(Color.white);
                nine.setOpaque(true);
                nine.setBackground(Color.white);
                play.setBackground(new Color(175, 126, 19));
                play.setForeground(Color.white);
                exit.setBackground(new Color(238, 221, 228));
                play.setFont(new Font("ARIAL", Font.BOLD, 14));
                exit.setFont(new Font("ARIAL", Font.BOLD, 15));
                show.setFont(new Font("ARIAL", Font.BOLD, 17));
                add(first, constraints);
                constraints.gridx = 1;
                add(sec, constraints);
                constraints.gridx = 2;
                add(third, constraints);
                constraints.gridy = 1;
                constraints.gridx = 0;
                add(four, constraints);
                constraints.gridx = 1;
                add(five, constraints);
                constraints.gridx = 2;
                add(six, constraints);
                constraints.gridy = 3;
                constraints.gridx = 0;
                add(seven, constraints);
                constraints.gridx = 1;
                add(eight, constraints);
                constraints.gridx = 2;
                add(nine, constraints);
                constraints.gridx = 0;
                constraints.gridwidth = 2;
                constraints.gridy = 5;
                add(play, constraints);
                constraints.gridx = 2;
                constraints.gridwidth = 1;
                add(exit, constraints);
                show.setText(showAction());
                constraints.gridy = 4;
                constraints.gridwidth = 3;
                constraints.gridx = 0;
                add(show, constraints);

                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                exit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        exit.setForeground(new Color(238, 221, 228));
                        exit.setBackground(new Color(2, 119, 119));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        exit.setBackground(new Color(238, 221, 228));
                        exit.setForeground(new Color(2, 119, 119));
                    }
                });
                play.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        play.setForeground(new Color(175, 126, 19));
                        play.setBackground(Color.white);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        play.setBackground(new Color(175, 126, 19));
                        play.setForeground(Color.white);
                    }
                });
                play.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playAction(e);
                    }
                });
                first.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            first.setText("X");
                            board[0][0] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            first.setText("O");
                            board[0][0] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            first.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                sec.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            sec.setText("X");board[1][0] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            sec.setText("O");
                            board[1][0] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            sec.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                third.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            third.setText("X");
                            board[2][0] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            third.setText("O");
                            board[2][0] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            third.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                four.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            four.setText("X");
                            board[0][1] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            four.setText("O");
                            board[0][1] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            four.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                five.addActionListener(new ActionListener() {
                    @Override
                        public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            five.setText("X");
                            board[1][1] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            five.setText("O");
                            board[1][1] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            five.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                six.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            six.setText("X");
                            board[2][1] = 'X';
                            temp = 'O';
                            comp_played=false;
                         } else {
                            six.setText("O");
                            board[2][1] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            six.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                seven.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            seven.setText("X");
                            board[0][2] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            seven.setText("O");
                            board[0][2] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            seven.setEnabled(false);
                            show.setText(showAction());
                        }

                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                eight.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            eight.setText("X");
                            board[1][2] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            eight.setText("O");
                            board[1][2] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                        } else {
                            player = temp;
                            eight.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
                nine.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 'X') {
                            nine.setText("X");
                            board[2][2] = 'X';
                            temp = 'O';
                            comp_played=false;
                        } else {
                            nine.setText("O");
                            board[2][2] = 'O';
                            temp = 'X';
                        }
                        if (checkWin()) {
                            show.setText(player + " has won!!");
                            disableAll();
                            play.requestFocus();
                        } else if (checkFull()) {
                            show.setText("It's a tie");
                            disableAll();
                            play.requestFocus();
                            } else {
                            player = temp;
                            nine.setEnabled(false);
                            show.setText(showAction());
                        }
                        if (choice==0 && !comp_played){
                            checkComp();
                        }
                    }
                });
        }

        private void disableAll() {
            first.setEnabled(false);
            sec.setEnabled(false);
            third.setEnabled(false);
            four.setEnabled(false);
            five.setEnabled(false);
            six.setEnabled(false);
            seven.setEnabled(false);
            eight.setEnabled(false);
            nine.setEnabled(false);
        }
        private void playAction(ActionEvent e) {
            first.setText("*");
            sec.setText("*");
            third.setText("*");
            four.setText("*");
            five.setText("*");
            six.setText("*");
            seven.setText("*");
            eight.setText("*");
            nine.setText("*");
            player='X';
            show.setText(showAction());
            first.setEnabled(true);
            sec.setEnabled(true);
            third.setEnabled(true);
            four.setEnabled(true);
            five.setEnabled(true);
            six.setEnabled(true);
            seven.setEnabled(true);
            eight.setEnabled(true);
            nine.setEnabled(true);
            count=0;
            for (int i = 0; i <rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j]='*';
                }
            }
        }
        private boolean checkWin(){
            for (int i = 0; i < rows; i++) {
                if ((board[i][0]==player && board[i][1] == player && board[i][2]==player)||(board[0][i]==player && board[1][i]==player && board[2][i]==player)) return true;
            }
            return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
        }
        private String showAction(){
            return "Player "+player+", choose your spot!!";
        }
        private boolean checkFull(){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j]=='*') return false;
                }
            }
            return true;
        }
        private void reset(){
            for (int i = 0; i <rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[i][j]='*';
                }
            }
        }

        private void checkComp(){
            comp_played=true;
            //implement algo to determine "smartly where to play as the computer"
            //first check if the corners are empty if they are then play there first
            int val;
            count=0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j]=='*')count++;
                }
            }
            pos = checkWinChance();
            if ((pos==0 || count>6) && count>1) {
                boolean done = false;
                while (!done) {
                    val = gen.nextInt(1, 6);
                    System.out.println(val);
                    if (val == 1) {
//                        System.out.println(first.isEnabled());
                        if (board[0][0] == '*') {
                            first.doClick();
                            done = true;
                        }
                    } else if (val == 2) {
//                        System.out.println(board[2][0] == '*');
                        if (board[2][0] == '*') {
                            third.doClick();
                            done = true;
                        }
                    } else if (val == 3) {
//                        System.out.println(board[0][2] == '*');
                        if (board[0][2] == '*') {
                            seven.doClick();
                            done = true;
                        }
                    } else if (val == 4) {
//                        System.out.println(nine.isEnabled());
                        if (board[2][2] == '*') {
                            nine.doClick();
                            done = true;
                        }
                    } else if (val == 5) {
//                        System.out.println(five.isEnabled());
                        if (board[1][1] == '*') {
                            five.doClick();
                            done = true;
                        }
                    }
                }
            } else{
                if (pos==1) first.doClick();
                else if (pos==2) sec.doClick();
                else if (pos==3) third.doClick();
                else if (pos==4) four.doClick();
                else if (pos==5) five.doClick();
                else if (pos==6) six.doClick();
                else if (pos==7) seven.doClick();
                else if (pos==8) eight.doClick();
                else if (pos==9) nine.doClick();
            }

        }

    private int checkWinChance() {
//            ArrayList<Integer> bd = new ArrayList<>();
        //prioritize the corners and work that way -> <-
        if (!XCanWin()) {
            if (board[0][0] == '*' && first.isEnabled()) {
                if (board[1][1] == board[2][2]) return 1;
                else if (board[2][0] == board[1][0]) return 1;
                else if (board[0][2] == board[0][1]) return 1;
            }
            if (board[1][0] == '*' && sec.isEnabled()) {
                if (board[0][0] == board[2][0]) return 2;
                else if (board[1][2] == board[1][1]) return 2;
            }
            if (board[2][0] == '*') {
                if (board[0][0] == board[1][0]) return 3;
                else if (board[0][2] == board[1][1]) return 3;
                else if (board[2][2] == board[2][1]) return 3;
            }
            if (board[0][1] == '*') {
                if (board[0][0] == board[0][2]) return 4;
                else if (board[2][1] == board[1][1]) return 4;
            }
            if (board[1][1] == '*') {
                if (board[1][0] == board[1][2]) return 5;
                else if (board[0][1] == board[2][1]) return 5;
                else if (board[0][0] == board[2][2]) return 5;
                else if (board[0][2] == board[2][0]) return 5;
            }
            if (board[2][1] == '*') {
                if (board[2][0] == board[2][2]) return 6;
                else if (board[0][1] == board[1][1]) return 6;
            }
            if (board[0][2] == '*') {
                if (board[0][0] == board[0][1]) return 7;
                else if (board[2][0] == board[1][1]) return 7;
                else if (board[2][2] == board[1][2]) return 7;
            }
            if (board[1][2] == '*') {
                if (board[1][0] == board[1][1]) return 8;
                else if (board[0][2] == board[2][2]) return 8;
            }
            if (board[2][2] == '*') {
                if (board[2][0] == board[2][1]) return 9;
                else if (board[0][2] == board[1][2]) return 9;
                else if (board[0][0] == board[1][1]) return 9;
            }
        }
        return num;
    }
    private boolean XCanWin(){
        num = 0;
        if (board[0][0] == '*') {
            if (board[1][1] == board[2][2] && board[1][1]!='*') num = 1;
            else if (board[2][0] == board[1][0] && board[1][0]!='*') {
                num = 1;
                if (board[1][0]=='O') return false;
            }
            else if (board[0][2] == board[0][1] && board[0][1]!='*') {
                num = 1;
                if (board[0][1]=='O') return false;
            }
        }
        if (board[1][0] == '*' && sec.isEnabled()) {
            if (board[0][0] == board[2][0] && board[0][0]!='*') {
                num = 2;
                if (board[0][0]=='O') return false;
            }
            else if (board[1][2] == board[1][1] && board[1][1]!='*'){
                num= 2;
                if (board[1][1]=='O') return false;
            }
        }
        if (board[2][0] == '*') {
            if (board[0][0] == board[1][0] && board[1][0]!='*') {
                num= 3;
                if (board[1][0]=='O') return false;
            }
            else if (board[0][2] == board[1][1] && board[1][1]!='*'){
                num = 3;
                if (board[1][0]=='O') return false;
            }
            else if (board[2][2] == board[2][1] && board[2][1]!='*') {
                num = 3;
                if (board[2][1]=='O') return false;

            }
        }
        if (board[0][1] == '*') {
            if (board[0][0] == board[0][2] && board[0][2]!='*') {
                num = 4;
                if (board[0][2]=='O') return false;
            }
            else if (board[2][1] == board[1][1] && board[1][1]!='*'){
                num = 4;
                if (board[1][1]=='O') return false;
            }
        }
        if (board[1][1] == '*') {
            if (board[1][0] == board[1][2] && board[1][0]=='X') num =  5;
            else if (board[0][1] == board[2][1] && board[2][1]!='*'){
                num =  5;
                if (board[2][1]=='O') return false;
            }
            else if (board[0][0] == board[2][2] && board[2][2]!='*'){
                num =  5;
                if (board[0][0]=='O') return false;
            }
            else if (board[0][2] == board[2][0] && board[2][0]!='*'){
                num =  5;
                if (board[2][0]=='O') return false;
            }
        }
        if (board[2][1] == '*') {
            if (board[2][0] == board[2][2] && board[2][0]!='*'){
                num =  6;
                if (board[2][0]=='O') return false;
            }
            else if (board[0][1] == board[1][1] && board[1][1]!='*'){
                num =  6;
                if (board[1][1]=='O') return false;
            }
        }
        if (board[0][2] == '*') {
            if (board[0][0] == board[0][1] && board[0][0]!='*'){
                num = 7;
                if (board[0][0]=='O') return false;
            }
            else if (board[2][0] == board[1][1] && board[1][1]!='*'){
                num = 7;
                if (board[1][1]=='O') return false;
            }
            else if (board[2][2] == board[1][2] && board[1][2]!='*'){
                num = 7;
                if (board[1][2]=='O') return false;
            }
        }
        if (board[1][2] == '*') {
            if (board[1][0] == board[1][1] && board[1][0]!='*') {
                num =  8;
                if (board[1][0]=='O') return false;
            }
            else if (board[0][2] == board[2][2] && board[2][2]!='*'){
                num = 8;
                if (board[2][2]=='O') return false;
            }
        }
        if (board[2][2] == '*') {
            if (board[2][0] == board[2][1] && board[2][1]!='*') {
                num = 9;
                if (board[2][1]=='O') return false;
            }
            else if (board[0][2] == board[1][2] && board[1][2]!='*'){
                num =  9;
                if (board[1][2]=='O') return false;
        }
            else if (board[0][0] == board[1][1]&& board[0][0]!='*') {
                num = 9;
                if (board[0][0]=='O') return false;
            }
        }
        return num != 0;
    }
}