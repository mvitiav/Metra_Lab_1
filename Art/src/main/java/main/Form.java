package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.IOException;

import static main.andrei.Main.buttonClicked;

public class Form extends JFrame{

    private JButton chooseFileButton;
    private JTable table1;
    private JPanel panel1;
    private JLabel Operators;
    private JPanel InfoPanel;
    private MyTableModel tableModel=null;

    public MyTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable1() {
        return table1;
    }

    public Form() {
        tableModel=new MyTableModel();
        table1.setModel(tableModel);
        try {
            chooseFileButton.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("Art/src/main/resources/Red October.ttf")).deriveFont(14f));
        } catch (FontFormatException | IOException e) {
         JOptionPane.showMessageDialog(null,"что - то отвалилося((");
            e.printStackTrace();
        }
        setSize(800, 600);
        setTitle("Моднейшее окно!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        chooseFileButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Какой-то пидор жмякнул кнопку накинь сюда вызов своего файлчузера!!!!");//TODO:сюда вызов своего файлчузера!!!!
//            }
//        });
        chooseFileButton.addActionListener(buttonClicked);



        add(panel1);
        setVisible(true);
    panel1.addComponentListener(new ComponentAdapter() { } );}


}
