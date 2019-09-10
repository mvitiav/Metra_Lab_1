package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;

public class Form extends JFrame{

    private JButton chooseFileButton;
    private JTable table1;
    private JPanel panel1;


    public Form() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Какой-то пидор жмякнул кнопку накинь сюда вызов своего файлчузера!!!!");//TODO:сюда вызов своего файлчузера!!!!
            }
        });
        add(panel1);
        setVisible(true);
    panel1.addComponentListener(new ComponentAdapter() { } );}


}
