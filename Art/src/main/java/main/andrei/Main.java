package main.andrei;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    static File chosenFile;
    static String inputText;
    static JFileChooser fileChooser;

    public static void main (String[] args) {

        BasicGUI basicGUI = new BasicGUI();
        basicGUI.setVisible(true);
        StringOperations stringOperations = new StringOperations();
        basicGUI.chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                fileChooser = new JFileChooser();
                int retCode = fileChooser.showDialog(null, "Выбрать файл");
                if (retCode == JFileChooser.APPROVE_OPTION) {
                    chosenFile = fileChooser.getSelectedFile();


                    basicGUI.chosenFileLabel.setText("Файл: " + chosenFile.toString());
                    inputText = stringOperations.textFromFile(chosenFile);
                    //System.out.println(inputText);
                    String[] linesArray = inputText.split("\n");
                    linesArray = stringOperations.removeAfter(linesArray, "//");
                    String singleLine;
                    for (int i = 0; i < linesArray.length; i++){
                        singleLine = i + linesArray[i];
                        System.out.println("|" + singleLine + "|");
                    }
                }
            }
        });
    }

    //изучиь 3 группы метрик
    //размер сложность упр сложность данных
    //операторы операнцы частоты операторов и операндов общее количетво операторов и операндов

}
