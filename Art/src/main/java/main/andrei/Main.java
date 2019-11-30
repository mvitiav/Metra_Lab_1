package main.andrei;

import main.Farm;
import main.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static Boolean TEST_MODE = true;
    public static Form window;
    public static Farm window2;
    public static ActionListener buttonClicked;
    public static ArrayList<Method2> globalMethodlist = new ArrayList<>();
    public static boolean isReLoopNeeded = false;
    public static boolean disableFormAdding = false;
    static File chosenFile;

    static String inputText;
    static JFileChooser fileChooser;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        BasicGUI basicGUI = new BasicGUI();
        StringOperations stringOperations = new StringOperations();
        CodeAnalysis codeAnalysis = new CodeAnalysis();

        buttonClicked = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selectFile()) {
                    if (!TEST_MODE) {
                        chosenFile = fileChooser.getSelectedFile();
                    } else {
                        chosenFile = new File("Art/tests/testFile.java");
                    }
                    basicGUI.chosenFileLabel.setText("Файл: " + chosenFile.toString());
                    inputText = stringOperations.textFromFile(chosenFile);
                    ////////////////////////////////
                    codeAnalysis.getMetrics();
                    ////////////////////////////////

                    window2.repaint();
                    window2.revalidate();
                }
            }
        };
        window2 = new Farm();
        basicGUI.chooseFileButton.addActionListener(buttonClicked);
    }

    public static boolean selectFile() {
        if (TEST_MODE) {
            return true;
        }
        fileChooser = new JFileChooser();
        int retCode = fileChooser.showDialog(null, "Выбрать файл");
        return (retCode == JFileChooser.APPROVE_OPTION);
    }
}
