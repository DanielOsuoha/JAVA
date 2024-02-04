package chapter5;

import javax.swing.*;

public class Menus extends JFrame {
    JMenuBar bar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem new_l = new JMenuItem("New");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenu edit = new JMenu("Edit");
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenu format = new JMenu("Format");
    /*
    JMenuItem bold = new JMenuItem("Bold");
    JMenuItem italic = new JMenuItem("Italic");*/
    //note we need some indication if any of these has been selected so we use checkboxes
    JCheckBoxMenuItem bold = new JCheckBoxMenuItem("Bold",false);
    JCheckBoxMenuItem italic = new JCheckBoxMenuItem("Italic",false);
    JMenu size = new JMenu("Size");
    JRadioButtonMenuItem ten = new JRadioButtonMenuItem("10",true);
    JRadioButtonMenuItem fifteen = new JRadioButtonMenuItem("15",false);
    JRadioButtonMenuItem twenty = new JRadioButtonMenuItem("20",false);
    ButtonGroup group = new ButtonGroup();

    public static void main(String[] args) {
        new Menus().setVisible(true);
    }
    public Menus(){
        setTitle("Menu Example");
        setSize(400,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        bar.add(file);
        bar.add(edit);
        bar.add(format);
        setJMenuBar(bar);
       file.add(new_l);
       file.add(open);
       file.add(save);
       file.addSeparator();
       JMenuItem exit = new JMenuItem("Exit");
       file.add(exit);

       edit.add(cut);
       edit.add(copy);
       edit.add(paste);

       format.add(bold);
       format.add(italic);
       format.add(size);
       size.add(ten);
       size.add(fifteen);
       size.add(twenty);
       group.add(ten);
       group.add(fifteen);
       group.add(twenty);
        //to add shortcuts we use the setMnemonic method
        file.setMnemonic('F');
        edit.setMnemonic('E');
        format.setMnemonic('O');
        //to add an accelerator which is a key combination that lets you immediately invoke a menu item without navigatingthe menu structure
        //the ctrl key is used
    }
}
