package main;

import main.andrei.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Farm extends JFrame{
    private JButton chooseFileButton;
    private JPanel panel2;
    private JLabel absd;
    private JLabel reld;
    private JLabel maxd;

    public void setAbsolete(String s){
        absd.setText("Absolute difficulty: "+s);
        pack();
    }
    public void setRelative(String s){
        reld.setText("Relative difficulty: "+s);
        pack();
    }
    public void setMax(String s){
        maxd.setText("Max depth: "+s);
        pack();
    }

    public Farm() {

        try {
            chooseFileButton.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("Art/src/main/resources/Red October.ttf")).deriveFont(16f));
            chooseFileButton.setIcon(new ImageIcon("Art/src/main/resources/document-16.png"));
            chooseFileButton.setBorderPainted(false);
            chooseFileButton.setFocusPainted(false);
            chooseFileButton.setContentAreaFilled(false);
        } catch (FontFormatException | IOException e) {
            JOptionPane.showMessageDialog(null,"что - то отвалилося((");
            e.printStackTrace();
        }

        setSize(800, 600);
        setTitle("Laba 3: ");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel2);
        pack();
        setVisible(true);
        chooseFileButton.addActionListener(Main.buttonClicked);
    }

}
