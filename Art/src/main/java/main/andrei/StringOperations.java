package main.andrei;

import java.io.*;

public class StringOperations {
    public String[] removeAfter(String[] inputLines, String subStr){
        int pos;
        for (int i = 0; i < inputLines.length; i++){
            pos = inputLines[i].indexOf(subStr);
            if (pos != -1){
                inputLines[i] = inputLines[i].substring(0, pos);
            }
        }
        return inputLines;
    }

    public String textFromFile(File file) {//TODO: ответь а чем те простой сцаннер не зашел?
        String inputText = "";
        String line;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "cp1251"));
            while ((line = bufferedReader.readLine()) != null) {
                inputText += line.trim() + '\n';
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inputText;
    }

    public void textToFile(String path, String text){
        File file;
        file = new File(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "cp1251"));
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int countHist(String text, String searchable)
    {int counter = 0;
        int index = 0;
        while (index < text.length() && (index = text.indexOf(searchable, index)) >= 0) {
            counter++;
            index += searchable.length(); //length of 'the'
        }

    return counter;
    }

    public String leaveChars(String text, String alphabet){
        String outText = "";
        text = text.toUpperCase();
        char[] textChars = text.toCharArray();
        for (int i = 0; i < text.length(); i++){
            if (alphabet.indexOf(textChars[i]) != -1){
                outText += textChars[i];
            }
        }
        return outText;
    }
}
