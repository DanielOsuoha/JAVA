package chapter5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.text.DecimalFormat;

public class ShoppingCart extends JFrame {
    int items = 0;
    JTabbedPane pane = new JTabbedPane();
    JPanel address = new JPanel();
    JScrollPane addressPane = new JScrollPane();
    JTextArea addressArea = new JTextArea();

    JPanel order = new JPanel();
    JPanel cart = new JPanel();
    JTextField costField = new JTextField();
    JScrollPane cartPane = new JScrollPane();
    JTextArea cartArea = new JTextArea();
    JLabel order_label = new JLabel();
    JTextArea o_area = new JTextArea();
    JSpinner order_spinner = new JSpinner();
    JButton add = new JButton();
    JTextField field = new JTextField();
    JButton newButton = new JButton();
    String[] product = new String[10];
    double[] cost = new double[10];
    int[] ordered = new int[10];
    JButton exit = new JButton();
    JLabel background = new JLabel();
    JLabel pBack = new JLabel();
    JLabel cartBack = new JLabel();
    public static void main(String[] args) {
        new ShoppingCart().setVisible(true);
    }
    public ShoppingCart(){
        setIconImage(new ImageIcon("src/chapter5/ecommerce2.jpg").getImage());
        product[0] = "Tricycle" ; cost[0] = 50;
        product[1] = "Skateboard" ; cost[1] = 60; product[2] = "In-Line Skates"
        ; cost[2] = 100; product[3] = "Magic Set" ; cost[3] = 15; product[4] =
                "Video Game" ; cost[4] = 45; product[5] = "Helmet" ; cost[5] = 25;
        product[6] = "Building Kit" ; cost[6] = 35; product[7] = "Artist Set" ;
        cost[7] = 40; product[8] = "Doll Baby" ; cost[8] = 25; product[9] =
                "Bicycle" ; cost[9] = 150;
        setTitle("Shopping Cart Program");
        setSize(750,460);
//        setResizable(false);
        setLayout(new GridBagLayout());
        ImageIcon image = new ImageIcon("src/chapter5/bankk.jpg");
        ImageIcon image2 = new ImageIcon("src/chapter5/bankk.jpg");
        ImageIcon image3 = new ImageIcon("src/chapter5/wooden.jpg");
        pBack.setIcon(image2);
        pBack.setLayout(new GridBagLayout());

        cartBack.setIcon(image3);
        background.setIcon(image);
        background.setLayout(new GridBagLayout());
        background.setPreferredSize(new Dimension(720,480));
        GridBagConstraints constraints = new GridBagConstraints();
        getContentPane().add(pane,constraints);
//        background.add(pane,constraints);
        pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                paneChanged(e);
            }
        });
        order.setPreferredSize(new Dimension(720,460));
        order.setOpaque(true);
        order.setBackground(new Color(211, 234, 238));
        order.add(pBack,constraints);
        pBack.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);
        order_label.setText("Order Address:");
        pBack.add(order_label,constraints);
        constraints.gridx=1;
        constraints.gridy=0;
        constraints.gridwidth=2;
        o_area.setColumns(30);
        o_area.setRows(5);
        pBack.add(o_area,constraints);
        constraints.gridwidth=1;
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.anchor = GridBagConstraints.WEST;
        //array of products is gonna be the label
        order_spinner.setPreferredSize(new Dimension(150,27));
        order_spinner.setModel(new SpinnerListModel(product));
        pBack.add(order_spinner,constraints);
        constraints.gridy=1;
        constraints.gridx=1;
        add.setText("Add to Order");
        pBack.add(add,constraints);
        constraints.gridx=2;
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction(e);
            }
        });
        field.setColumns(17);
        field.setEditable(false);
        field.setBackground(new Color(67, 35, 98));
        field.setForeground(Color.white);
        field.setText("Number of items Ordered: "+items);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        pBack.add(field,constraints);
        newButton.setText("New Order");
        newButton.setBackground(new Color(23, 230, 182));
        newButton.setPreferredSize(new Dimension(150,25));
        newButton.setFont(new Font("ARIAL",Font.BOLD,15));
        constraints.gridx=0;
        constraints.gridy=3;
        pBack.add(newButton,constraints);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButtonAction(e);
            }
        });
        constraints.gridy=3;
        constraints.gridx=2;
        exit.setText("Exit");
        exit.setBackground(new Color(243, 195, 108));
        exit.setFont(new Font("ARIAL",Font.BOLD,18));
        exit.setPreferredSize(new Dimension(150,25));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pBack.add(exit,constraints);
        background.add(order);
        pane.addTab("Order Form",order);
        cartBack.setLayout(new GridBagLayout());
        cartBack.setPreferredSize(new Dimension(500,500));
        cartPane.setPreferredSize(new Dimension(250,150));
        cartArea.setEditable(false);
        cartPane.setViewportView(cartArea);
        constraints.gridx =0;
        constraints.gridy =0;
        cartBack.add(cartPane,constraints);
        constraints = new GridBagConstraints();
        costField.setColumns(20);
        costField.setEditable(false);
        setFocusable(false);
        costField.setBackground(Color.white);
        constraints.gridy=1;
        constraints.insets = new Insets(5,0,5,0);
        cartBack.add(costField,constraints);
        cart.add(cartBack);
        pane.addTab("Shopping Cart", cart);

        address.setLayout(new GridBagLayout());
        addressPane.setPreferredSize(new Dimension(250,150));
        addressPane.setViewportView(addressArea);
        constraints.gridx=0;
        constraints.gridy=0;
        address.add(addressPane,constraints);
        pane.addTab("Mailing Label",address);
        pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int)(0.5*(screen.width-getWidth())),(int)(0.5*(screen.height-getHeight())),getWidth(),getHeight());

    }

    private void newButtonAction(ActionEvent e) {
        //clear the form
        o_area.setText("");
        items =0;
        field.setText("Items Ordered: "+items);
        order_spinner.setValue(product[0]);
        cartArea.setText("");
        costField.setText("");
        ; for (int i = 0; i <cost.length; i++) {
            ordered[i] = 0;
        }
        order_spinner.setValue(product[0]);
    }

    private void addAction(ActionEvent e) {
        items++;
        field.setText("Number of items Ordered: "+items);
//        if (!cartArea.getText().equalsIgnoreCase("")) {
//            String hold = cartArea.getText()+",\n";
//            cartArea.setText(hold+order_spinner.getValue().toString());
//        }else cartArea.setText(order_spinner.getValue().toString());
        int selectedProduct;
// increment selected product by one
        for (selectedProduct = 0; selectedProduct < cost.length; selectedProduct++) {
            if (product[selectedProduct].equals(order_spinner.getValue().toString())) {
                break;
            }
        }
        ordered[selectedProduct]++;
    }

    private void paneChanged(ChangeEvent e) {
        switch (pane.getSelectedIndex()){
            case 0:
//                background.add(order);
//                background.remove(cart);
//                background.remove(address);
//                getContentPane().add(background);
                break;
            case 1:
                if (items==0){
                    JOptionPane.showConfirmDialog(null,"No items have been ordered.","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                    pane.setSelectedIndex(0);
                }else {
                    double total = 0.00;
                    String myOrder="";
                    //load in ordered items;
                    for (int i = 0; i < product.length; i++) {
                        if (ordered[i]!=0){
                            myOrder += ordered[i]+" "+product[i]+"\n";
                            total += ordered[i]*cost[i];
                        }
                    }
                    cartArea.setText(myOrder);
                    costField.setText("Total Cost: GMD"+ new DecimalFormat("0.00").format(total));
//                    background.add(cart);
//                    pane.removeTabAt(1);
//                    pane.addTab("MY Shopping Cart",background);
                }
                break;
            case 2:
                if (o_area.getText().equals("")){
                    JOptionPane.showConfirmDialog(null,"Address is blank.","Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    pane.setSelectedIndex(0);
                }else {
                    //form label
                    addressArea.setText("My Company\n"+"My Address\n"+"My City, State, Zip\n\n\n"+o_area.getText());
                }
                break;
        }
    }

}
