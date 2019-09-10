package main.andrei;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BasicGUI extends JFrame {
    private JPanel mainLayout;
    public JButton chooseFileButton;
    public JLabel chosenFileLabel;
    public JTable resultsTable;

    String[][] resultsArray;

    public BasicGUI() {
        super("First ToI Lab");
        setBounds(300, 200, 475, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        chosenFileLabel = new JLabel("Выбранный файл: ничего не выбрано.");
        chooseFileButton = new JButton("Выбрать файл");
        resultsTable = new JTable();
        mainLayout = new JPanel();
        mainLayout.setLayout(null);

        chosenFileLabel.setLocation(5, 5);
        chosenFileLabel.setSize(300, 30);

        chooseFileButton.setLocation(305, 5);
        chooseFileButton.setSize(150, 30);

        mainLayout.add(chooseFileButton);
        mainLayout.add(chosenFileLabel);
        add(mainLayout);
    }
}
