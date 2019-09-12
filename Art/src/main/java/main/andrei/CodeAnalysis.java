package main.andrei;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAnalysis {
    //public final String[] TYPES_LIST = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "String", "class", "void", "interface"};
    //public final String[] AFTER_NAME_SIGNS = {";", "=", "{", "(", ")", ","};//скобка, если метод

    //метод для выпиливания строковых операндов
    public String getStrings(String text) {
        String strings = "";
        char nullChar = 0;
        // не тот Pattern pattern = Pattern.compile("\\b(byte|short|int|long|float|double|char|boolean|String|class|void|interface)\\b.+?[{;(),=]");
        Pattern pattern = Pattern.compile("\".+?\"");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));

            if (!isCommented(text, matcher.start(), matcher.end())) {
                //System.out.println("                it's also commented!!!!!");
                //Main.window.getTableModel().addOperand(text.substring(matcher.start()+1, matcher.end()-1)); Вариант, чтобы не оставляло скобки
                Main.window.getTableModel().addOperand(text.substring(matcher.start(), matcher.end()));
            }
        }
        return strings;
    }

    public ArrayList<String> getRegisteredOperators(String text) {
        ArrayList<String> registeredOperators = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b(byte|short|int|long|float|double|char|boolean|String|class|void|interface)\\b.+?[{;(),=]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            registeredOperators.add(text.substring(matcher.start(), matcher.end()));
            String regOp = text.substring(matcher.start(), matcher.end());
            //regOp = regOp.substring(regOp.indexOf(" "), regOp.indexOf(" ")+3);
            Main.window.getTableModel().addOperand(regOp);
        }
        return registeredOperators;
    }

    public ArrayList<String> getOperatorsList(String text){
        ArrayList<String> operatorsList = new ArrayList<>();
        Pattern pattern = Pattern.compile(">>>=|>>=|<<=|%=|\\^=|&=|\\|=|&=|/=|\\*=|-=|\\+=|>>>|>>|<<|%|\\^|\\|\\||&&|/|\\*|\\+\\+|--|\\+|\\||&|!=|>=|<=|==|:|\\?|~|!|>|>|= ");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            operatorsList.add(text.substring(matcher.start(), matcher.end()));
            Main.window.getTableModel().addOperator(text.substring(matcher.start(), matcher.end()));
        }
        return operatorsList;
    }

    public ArrayList<String> getCasters(String text){
        ArrayList<String> casters = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\([byte|short|int|long|float|double|char|boolean|String| ]+?\\)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            casters.add(text.substring(matcher.start(), matcher.end()));
            Main.window.getTableModel().addOperator("cast " + text.substring(matcher.start(), matcher.end()));
        }
        return casters;
    }

    public Boolean isCommented(String text
            , int beg, int end
//            , String substr
    ) {

//        Pattern pattern = Pattern.compile(".*?//.*?"+text.substring(beg,end)+".*?$");
//        Matcher matcher = pattern.matcher(text);
//
//        if (matcher.matches()) {
//            return true;
//        }
        if (StringOperations.countHist(text.substring(0, beg), "/*") -
                StringOperations.countHist(text.substring(0, beg), "*/") > 0) {
            return true;
        }


        Pattern pattern = Pattern.compile(".*?//.*?" + text.substring(beg, end) + ".*?$");
        Matcher matcher = pattern.matcher(text);
//        Pattern pattern = Pattern.compile(".*?//.*?"+substr+".*?$");
//        Pattern pattern = Pattern.compile(".*?//.*?" + substr + ".*?$");
//        Pattern pattern = Pattern.compile("^.*?//.*?$");
//        Pattern pattern = Pattern.compile(".*?$");
//        Pattern pattern = Pattern.compile("^.*?$");

//      while (matcher.find()){
//          System.out.println(text.substring(matcher.start(),matcher.end()));
//      }


        for (String s : text.split("\n")) { //TODO:если починишь ^ то можно без этого костыля
            matcher.reset(s);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}












































/*Пускай тут полежит пока
public class CodeAnalysis {
    public final String[] TYPES_LIST = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "String", "class", "void", "interface"};
    public final String[] AFTER_NAME_SIGNS = {";", "=", "{", "(", ")", ","};//скобка, если метод
    public String getRegisteredOperators(String[] codeLines){
        String registeredOperators = "";
        for (int curType = 0; curType < TYPES_LIST.length; curType++){
            for (int curLine = 0; curLine < codeLines.length; curLine++){ //берем одну строку
                if (codeLines[curLine].indexOf(TYPES_LIST[curType]) != -1){
                    //если в анализируемой строке есть объявление типа, класса, метода...
                    // ... то после имени должно быть равно (+инициализация), точка с запятой или скобка (для метода и класса)
                    String sign = "!";
                    int minPos = codeLines[curLine].length()+1; //положение ближайшего "нужного" символа
                    for (int curSign = 0; curSign < AFTER_NAME_SIGNS.length; curSign++){
                        if (codeLines[curLine].indexOf(AFTER_NAME_SIGNS[curSign]) != -1){//если в строке есть-таки "нужный" знак ...
                            if ((codeLines[curLine].indexOf(AFTER_NAME_SIGNS[curSign]) < minPos) && codeLines[curLine].indexOf(AFTER_NAME_SIGNS[curSign]) > codeLines[curLine].indexOf(TYPES_LIST[curType])){ //... то чекаем, какой ближе
                                minPos = codeLines[curLine].indexOf(AFTER_NAME_SIGNS[curSign]);
                                sign = AFTER_NAME_SIGNS[curSign];
                            }
                        }
                    }

                    if (!sign.equals("!")){
                        registeredOperators += codeLines[curLine].substring(codeLines[curLine].indexOf(TYPES_LIST[curType])+TYPES_LIST[curType].length(), codeLines[curLine].indexOf(sign)).trim() + ";";
                    }
                    else {
                        System.out.println("Что-то пошло не так!");
                        System.out.println("Чекнуть строку : " + codeLines[curLine]);
                        System.out.println("{ pos" + codeLines[curLine].indexOf("{"));
                        System.out.println("( pos" + codeLines[curLine].indexOf("("));
                        System.out.println("; pos" + codeLines[curLine].indexOf(";"));
                        System.out.println("= pos" + codeLines[curLine].indexOf("="));
                        System.out.println("len = " + codeLines[curLine].length());
                        System.out.println("sign = " + sign);
                        System.out.println("minPos = " + minPos);
                        System.out.println("curType = " + TYPES_LIST[curType]);
                        System.out.println("FROM = " + codeLines[curLine].indexOf(TYPES_LIST[curType])+TYPES_LIST[curType].length());
                        System.out.println("TO = " +  codeLines[curLine].indexOf(sign));
                    }
                }
            }
        }
        return registeredOperators;
    }
}
 */
