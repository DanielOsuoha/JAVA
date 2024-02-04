package chapter5;

import javax.swing.*;
import java.awt.*;

public class NoteEditor extends JFrame {
    JScrollPane pane = new JScrollPane();
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem first = new JMenuItem("New");
    JMenuItem exit = new JMenuItem("Exit");
    JMenu format = new JMenu("Format");
    JCheckBoxMenuItem bold = new JCheckBoxMenuItem("Bold");
    JCheckBoxMenuItem italic = new JCheckBoxMenuItem("Italic");
    JMenu size = new JMenu("Size");
    JRadioButtonMenuItem small = new JRadioButtonMenuItem("Small");
    JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Medium");

    JRadioButtonMenuItem large = new JRadioButtonMenuItem("Large");
    ButtonGroup group = new ButtonGroup();
    JTextArea area = new JTextArea();
    public static void main(String[] args) {
        new NoteEditor().setVisible(true);
    }
    public NoteEditor(){
        setTitle("Note Editor");
        setSize(400,320);
        setResizable(false);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        area.setBackground(Color.white);
        area.setPreferredSize(new Dimension(370,250));
        area.setLineWrap(true);
        pane.setViewportView(area);
        getContentPane().add(pane);
        setJMenuBar(menu);
        menu.add(file);
        menu.add(format);
        file.setMnemonic('F');
        file.add(first);
        file.addSeparator();
        file.add(exit);
        format.setMnemonic('O');
        format.add(bold);
        format.add(italic);
        size.add(small);
        size.add(medium);
        size.add(large);
        format.add(size);
        group.add(small);
        group.add(medium);
        group.add(large);
    }
}
