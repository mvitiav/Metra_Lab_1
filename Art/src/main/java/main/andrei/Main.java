package main.andrei;

import main.Farm;
import main.Form;
import main.Furm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static Boolean TEST_MODE = true;
    public static Form window;
    public static Farm window2;
    public static Furm window3;
    public static ActionListener buttonClicked;
    public static ArrayList<Method2> globalMethodlist = new ArrayList<>();

    public static HashMap<String, Integer> varSpens = new HashMap<>();

    public static ArrayList<String> vars = new ArrayList<>();
    public static HashMap<String, String> varGroup = new HashMap<>();
    public static HashMap<String, Boolean> isInput = new HashMap<>();
    public static HashMap<String, Boolean> isOutput = new HashMap<>();

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
                    varSpens = new HashMap<>();

                    vars = new ArrayList<>();
                    varGroup = new HashMap<>();

                    isInput = new HashMap<>();
                    isOutput = new HashMap<>();
                 //   System.err.println(vars.size());
                  //  System.err.println(window3.getTable1().getColumnCount());
                    codeAnalysis.getMetrics();
               //     System.err.println(window3.getTable1().getColumnCount());
               //    System.err.println(vars.size());
                    ////////////////////////////////

//                    window2.repaint();
//                    window2.revalidate();
//                    window3.revalidate();
//                    window3.repaint();

                    window3.notifyT1();


//                    window3.revalidate();
//                    window3.repaint();
                }
            }
        };
      //  window2 = new Farm();
        window3 = new Furm();
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
