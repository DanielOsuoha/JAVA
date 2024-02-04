package chapter4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileTimes extends JFrame {
    JFileChooser choose = new JFileChooser();
    JScrollPane pane = new JScrollPane();
    JTextArea area = new JTextArea("");
    ArrayList<Long> times = new ArrayList<>();
    public static void main(String[] args) {
        new FileTimes().setVisible(true);
    }
    public FileTimes(){
        setTitle("File Times");
        setSize(700,400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        area.setPreferredSize(new Dimension(600,300));
        pane.setViewportView(area);
        constraints.insets = new Insets(5,5,5,5);
        if(choose.showOpenDialog(null)==JFileChooser.OPEN_DIALOG){
           times.add(choose.getSelectedFile().lastModified());
           area.setText(area.getText()+"\n"+choose.getSelectedFile().toString());
           area.setText(area.getText()+times);
        }
        add(pane,constraints);
    }
}
