package main.andrei;

import main.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Main {
    final static Boolean TEST_MODE = true;
    public static Form window;
    public static ActionListener buttonClicked;
    public static ArrayList<Method2> globalMethodlist = new ArrayList<>();
    public static boolean isReLoopNeeded = false;
    public static boolean disableFormAdding = false;
    static File chosenFile;
    static String inputText;
    static JFileChooser fileChooser;

    public static int allOperators = 0;

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
                Main.window.getTableModel().recreate();
                Form.operandsCount = 0;
                Form.operatorsCount = 0;
                Form.uniqueOperandsCount = 0;
                Form.uniqueOperandsCount = 0;
                if (selectFile()) {
                    if (!TEST_MODE) {
                        chosenFile = fileChooser.getSelectedFile();
                    } else {
                        chosenFile = new File("Art/tests/testFile.java");
                    }
                    basicGUI.chosenFileLabel.setText("Файл: " + chosenFile.toString());
                    window.setTitle("Метрики НЕ Холстеда: " + chosenFile.toString());
                    inputText = stringOperations.textFromFile(chosenFile);

                    //<DirtyJob>
                    codeAnalysis.cutMultilineComments();
                    codeAnalysis.cutSingleLineComments();
                    codeAnalysis.cutImports();
                    codeAnalysis.replaceArithmetics();
                    System.out.println(allOperators);
                    codeAnalysis.replaceDotMethods();
                    System.out.println(allOperators);
                    codeAnalysis.replaceSimpleMethods();
                    System.out.println(allOperators);
                    System.out.println(inputText);
                    //</DirtyJob>

                    window.getTable1().revalidate();
                    window.getTable1().repaint();
                }
            }
        };
        window = new Form();
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
