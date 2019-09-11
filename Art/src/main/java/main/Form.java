package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;

import static main.andrei.Main.buttonClicked;

public class Form extends JFrame{

    private JButton chooseFileButton;
    private JTable table1;
    private JPanel panel1;
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
