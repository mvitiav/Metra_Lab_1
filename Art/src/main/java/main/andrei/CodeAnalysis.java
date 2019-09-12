package main.andrei;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAnalysis {
    public final String[] TYPES_LIST = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "String", "class", "void", "interface"};
    public final String[] AFTER_NAME_SIGNS = {";", "=", "{", "(", ")", ","};//скобка, если метод
    public String getRegisteredOperands(String text){
        String registeredOperands = "";
        Pattern pattern = Pattern.compile("\\b(byte|short|int|long|float|double|char|boolean|String|class|void|interface)\\b.+?[{;(),=]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
        return registeredOperands;
    }

    //метод для выпиливания строковых операндов
    public String getStrings(String text){
        String strings = "";
        //char nullChar = 0;
        Pattern pattern = Pattern.compile("\".+?\"");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
        return strings;
    }

}