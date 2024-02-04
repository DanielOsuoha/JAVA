package chapter4.TicTacToe;
import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    JLabel label = new JLabel();
    public SplashScreen(){
        setUndecorated(true);
        label.setIcon(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\filechooser\\swing projects\\src\\chapter4\\TicTacToe\\tech1.jpg"));
        label.setOpaque(true);
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        SplashScreen splashScreen = new SplashScreen();
        System.out.println("Splash screen is alive");
        splashScreen.setVisible(true);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        splashScreen.dispose();
        new TestClass().setVisible(true);
    }
}
