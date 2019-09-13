package main.andrei;

import main.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Main {
    static File chosenFile;
    static String inputText;
    static JFileChooser fileChooser;
    static Form window;
    final static Boolean TEST_MODE=true;
    public static ActionListener buttonClicked;

    public static void main (String[] args) {

        BasicGUI basicGUI = new BasicGUI();
        //basicGUI.setVisible(true);
        final CodeAnalysis codeAnalysis = new CodeAnalysis();
        StringOperations stringOperations = new StringOperations();
         buttonClicked = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

//                fileChooser = new JFileChooser();
//                int retCode = fileChooser.showDialog(null, "Выбрать файл");
//                if (retCode == JFileChooser.APPROVE_OPTION) {
                if ( selectFile()) {
                    if(!TEST_MODE) {

                        chosenFile = fileChooser.getSelectedFile();
                    }else {chosenFile = new File("Art/tests/testFile.java");}

                    basicGUI.chosenFileLabel.setText("Файл: " + chosenFile.toString());
                    window.setTitle(window.getTitle()+ chosenFile.toString());
                    inputText = stringOperations.textFromFile(chosenFile);
                    codeAnalysis.getStrings(inputText);
                    //codeAnalysis.getCasters(inputText);
                    //codeAnalysis.getRegisteredOperators(inputText);
                    //codeAnalysis.getOperatorsList(inputText);

//                    codeAnalysis.isCommented(inputText, "Main(\"Test\")");


                 //       codeAnalysis.isCommented(inputText,"new");

                    //System.out.println(inputText);
                    //String[] linesArray = inputText.split("\n");
                    //linesArray = stringOperations.removeAfter(linesArray, "//");
                    //System.out.println(codeAnalysis.getRegisteredOperators(linesArray));
                    //String singleLine;
                    //for (int i = 0; i < linesArray.length; i++){
                    //    singleLine = i + linesArray[i];
                    //    System.out.println("|" + singleLine + "|");
                    //}
                    //codeAnalysis.operatorsList.forEach(s -> window.getTableModel().addOperator(s));
                  //  window.getTableModel().fireTableDataChanged();
                    window.getTable1().revalidate();
                    window.getTable1().repaint();
                }


            }
        };



                                                                       //moja guiha
        window = new Form();
                                                                 //table test
//        window.getTableModel().addOperator("operator");
//        window.getTableModel().addOperator("operator");
//        window.getTableModel().addOperand("operand");
//        window.getTableModel().addOperand("operand2");


        basicGUI.chooseFileButton.addActionListener(buttonClicked);
    }

    public static boolean selectFile() {
        if(TEST_MODE){return true;}
        fileChooser = new JFileChooser();
        int retCode = fileChooser.showDialog(null, "Выбрать файл");
        return  (retCode == JFileChooser.APPROVE_OPTION);

    }

    //изучиь 3 группы метрик
    //размер сложность упр сложность данных
    //операторы операнцы частоты операторов и операндов общее количетво операторов и операндов

}
