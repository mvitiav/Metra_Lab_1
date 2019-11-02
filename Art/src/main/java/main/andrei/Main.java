package main.andrei;

import main.Farm;
import main.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

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
    static String maxNestingTextPart;
    static int maxNesting = 0;
    static int allOperators = 0;
    static int allConditionalOperators = 0;

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
                //Main.window.getTableModel().recreate();


              //  Form.operandsCount = 0;
               // Form.operatorsCount = 0;
              //  Form.uniqueOperandsCount = 0;
              //  Form.uniqueOperandsCount = 0;


                if (selectFile()) {
                    if (!TEST_MODE) {
                        chosenFile = fileChooser.getSelectedFile();
                    } else {
                        chosenFile = new File("Art/tests/testFile.java");
                    }
                    basicGUI.chosenFileLabel.setText("Файл: " + chosenFile.toString());
              //      window.setTitle("Метрики НЕ Холстеда: " + chosenFile.toString());
                    inputText = stringOperations.textFromFile(chosenFile);

                    //<DirtyJob>
                    if (codeAnalysis.checkBrackets(inputText)) System.out.println("Brackets okay.");
                    else System.out.println("Wrong brackets!");

                    codeAnalysis.cutMultilineComments();
                    codeAnalysis.cutSingleLineComments();
                    codeAnalysis.cutImports();

                    //сначала берем все как есть, без выпиленных кусков
                    codeAnalysis.annulNesting();
                    codeAnalysis.switchReplace(0);
                    codeAnalysis.getMaxNesting(0);
                    maxNesting--;
                    System.out.println("================ Max nesting part [TRANSFORMED!!!] ================= \n" + '\n' + maxNestingTextPart + '\n' + "==============================================================");

                    codeAnalysis.replaceArithmetics();
                    codeAnalysis.replaceDotMethods();
                    codeAnalysis.replaceSimpleMethods();
                    codeAnalysis.countConditionalOperators();
                    //System.out.println("Input Text" + inputText);
                    System.out.println("All operators number: " + allOperators);
                    System.out.println("All conditional operators number : " + allConditionalOperators);
window2.setAbsolete(String.valueOf(allConditionalOperators));
window2.setRelative(String.format("%.3f", (double)allConditionalOperators/allOperators)  );

                    System.out.println("Max nesting : " + maxNesting);
                    window2.setMax(String.valueOf(maxNesting));
                    //System.out.println("Input Text" + inputText);
                    //</DirtyJob>

                 //   window.getTable1().revalidate();
                 //   window.getTable1().repaint();
                    //window2.set
                    window2.repaint();
                    window2.revalidate();
                }
            }
        };
//        window = new Form();
//        window2.setMax(String.valueOf(123));
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
