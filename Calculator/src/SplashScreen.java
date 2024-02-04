import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SplashScreen extends JFrame {
    JLabel label = new JLabel();
    public SplashScreen(){
        setUndecorated(true);
        label.setIcon(new ImageIcon("C:\\Users\\hp\\IdeaProjects\\Calculator\\src\\bankk.jpg"));
        label.setOpaque(true);
//        getContentPane().setPreferredSize(new Dimension(800,600));
//        getContentPane().setBackground(new Color(101, 8, 64));
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) throws SQLException {
            SplashScreen splashScreen = new SplashScreen();
            System.out.println("Splash screen is alive");
            splashScreen.setVisible(true);
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            splashScreen.dispose();
//            SwingUtilities.invokeLater(()->{
//                new Calculator().setVisible(true);
//            });
            new Calculator().setVisible(true);
    }
}
