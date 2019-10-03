package main.andrei;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAnalysis {
    public String getStrings(String text) {
        Pattern pattern = Pattern.compile("\".+?\"");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (!isCommented(text, matcher.start(), matcher.end())) {
                Main.window.getTableModel().addOperand(text.substring(matcher.start(), matcher.end()));
            }
            text = text.substring(0, matcher.start()) + " " + text.substring(matcher.end());  //TODO:                          ...+"aaa"+...
            matcher.reset(text);                                                              // Этот кусок превратится в '++'    ^^^^^^
        }                                                                                     // Если ничего не втыкать
        return text;
    }

    public String getRegisteredOperators(String text) {
        Pattern pattern = Pattern.compile("\\b(byte|short|int|long|float|double|char|boolean|String|void|interface)\\b.+?[{;(),=]");//TODO: Убрал пока class, пока не выясним, чем он является. Если что, потом на костыль повесим или на старое место поставим.
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String operator = text.substring(matcher.start(), matcher.end());
            operator = operator.substring(operator.indexOf(" "), operator.length()-1).trim();
            Main.window.getTableModel().addOperand(operator);
            text = text.substring(0,matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }


    public ArrayList<Method2> getMethodList(String text)
    {ArrayList<Method2> list = new ArrayList<>();

        Pattern pattern = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");//class(|.+?)[{]
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
        {
            System.out.println(text.substring(matcher.start(),matcher.end()));
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());

        }
    return list;
    }


    public ArrayList<Class2> getClassList(String text)
    {ArrayList<Class2> list = new ArrayList<>();

//        Pattern pattern = Pattern.compile("\\b(byte|short|int|long|float|double|char|boolean|String|void|interface)\\b.+?[{;(),=]");
//        Pattern pattern = Pattern.compile("class.+?[{]");//class(|.+?)[{]
        Pattern pattern = Pattern.compile("(?<=\\n|\\A)(?:public\\s)?(class|interface|enum)\\s([^\\n\\s]*)");//class(|.+?)[{]
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            //System.err.println(text.substring(matcher.start(),matcher.end()));

//            Pattern pattern2 = Pattern.compile("class ");//class(|.+?)[{]
//            Matcher matcher2 = pattern2.matcher(text.substring(matcher.start(),matcher.end()));
//            matcher2.find();
//int nameBeg =matcher2.end();
//            pattern2 = Pattern.compile("class \\w+");
//            matcher2 = pattern2.matcher(text.substring(matcher.start(),matcher.end()));
//            matcher2.find();
//int nameEnd =matcher2.end();
//            System.err.println(" name = "+text.substring(matcher.start(),matcher.end()).substring(nameBeg,nameEnd));

            //            Pattern pattern2 = Pattern.compile("class ");//class(|.+?)[{]
//            Matcher matcher2 = pattern2.matcher(text.substring(matcher.start(),matcher.end()));
//            matcher2.find();
//int nameBeg =matcher2.end();
//            pattern2 = Pattern.compile("class \\w+");
//            matcher2 = pattern2.matcher(text.substring(matcher.start(),matcher.end()));
//            matcher2.find();
//int nameEnd =matcher2.end();
//            System.err.println(" name = "+text.substring(matcher.start(),matcher.end()).substring(nameBeg,nameEnd));

         //   System.out.println("                                        "+matcher.group(2));

//(?<=\n|\A)(?:public\s)?(class|interface|enum)\s([^\n\s]*)

            //todo: ДА-ДА, не удивляйтессь!!! (костыли страшные но если не так то названиями по типу class MainClass можно наебнуть)
//\((?:[^)(]+|\((?:[^)(]+|\([^)(]*\))*\))*\)
//\{(?:[^}{]+|\{(?:[^}{]+|\{[^}{]*\})*\})*\}


            int bodyBegPos,bodyEndPos;
            int begs =0;
            int ends =0;
            int index = matcher.end();
            bodyBegPos = text.indexOf('{', index);

            while (index < text.length() && (index = text.indexOf('}', index)) >= 0) {
               // counter++;
                begs++;
                index ++; //length of '{'\'}'
                if(StringOperations.countHits(text.substring(bodyBegPos-1,index), "{")==begs){
                    break;
                }
            }

            Class2 tempClass = new Class2(matcher.group(2),text.substring(bodyBegPos+1,index-1));
            list.add(tempClass);
           // System.out.println(tempClass.body);


//            String operator = text.substring(matcher.start(), matcher.end());
//            operator = operator.substring(operator.indexOf(" "), operator.length()-1).trim();
//            Main.window.getTableModel().addOperand(operator);
//            //Main.window.getTableModel().addOperand("Op : |" + text.substring(matcher.start(), matcher.end()) + "|");
//            text = text.substring(0,matcher.start()-1)+text.substring(matcher.end()+1);
//            matcher.reset(text);
        }

        return list;
    }

    public String getOperatorsList(String text) {
        Pattern pattern = Pattern.compile(">>>=|>>=|<<=|%=|\\^=|&=|\\|=|&=|/=|\\*=|-=|\\+=|>>>|>>|<<|%|\\^|\\|\\||&&|/|\\*|\\+\\+|--|\\+|\\||&|!=|>=|<=|==|:|\\?|~|!|>|>|= ");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Main.window.getTableModel().addOperator(text.substring(matcher.start(), matcher.end()));
            //text = text.substring(0,matcher.start()-1)+text.substring(matcher.end()+1);
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }

    public String cutImports(String text) {
        Pattern pattern = Pattern.compile(" *(package|import).*;");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }

    public String cutMultilineComments(String text) {
        Pattern pattern = Pattern.compile("(\\/\\*)([^*]|[\\r\\n]|(\\*+([^*\\/]|[\\r\\n])))*(\\*\\/)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }

    public String cutSingleLineComments(String text) {
        Pattern pattern = Pattern.compile("\"[^\"\\\\]*(?:\\\\[\\W\\w][^\"\\\\]*)*\"|(\\/\\/.*)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (text.charAt(matcher.start()) == '/') {
                text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            }
            matcher.reset(text);
        }
        return text;
    }

    public String getCasters(String text) {
        //ArrayList<String> casters = new ArrayList<>();
        //Pattern pattern = Pattern.compile("\\([byte|short|int|long|float|double|char|boolean|String| ]+?\\)");
        Pattern pattern = Pattern.compile("\\( *(byte|short|int|long|float|double|char|boolean|String) *\\)+?");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String casterText;
            casterText = text.substring(matcher.start(), matcher.end());
            casterText = casterText.replace("(", "");
            casterText = casterText.replace(")", "");
            casterText = casterText.trim();
            casterText = "cast (" + casterText + ")";
            Main.window.getTableModel().addOperator(casterText);
            text = text.substring(0,matcher.start() - 1) + text.substring(matcher.end() + 1);
            matcher.reset(text);
        }
        return text;
    }

    public String getConstNums(String text) {
        //Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*[f|l|d]{0,1}");
        //Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
        Pattern pattern = Pattern.compile("\\d{1,}");//just-for-test
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String num;
            num = text.substring(matcher.start(), matcher.end());
            Main.window.getTableModel().addOperand(num);
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }

    public String methodHandler(String text) {
        //Pattern pattern = Pattern.compile("(\\w+ *\\.){1,}( *\\w+ *\\([^()]*\\)\\.*){1,}");
        //Pattern pattern = Pattern.compile("(\\w+ *\\. *\\w+ *)(\\([^()]*\\))");
        //Pattern pattern = Pattern.compile("(\\w+ *)(\\. *\\w+ *\\([^()]*\\)){1,}");
        Pattern pattern = Pattern.compile("(\\.{1,} *\\w+ *\\([^()]*\\))");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String method;
            method = text.substring(matcher.start() + 1, matcher.end());
            String[] methodParts = method.split("\\(");
            System.out.println("Method taken : " + method);
            Main.window.getTableModel().addOperand(methodParts[0]);
            //Main.window.getTableModel().addOperand(methodParts[1]);

            if (methodParts.length > 1
                && methodParts[1].matches(".*[0-9]{1,}.*")
                && !methodParts[1].matches("\\b(byte|short|int|long|float|double|char|boolean|String)\\b")) {
                System.out.println("Cyclepart : |" + methodParts[1] + "|");
                //methodParts[1] = getConstNums(methodParts[1]);
                getConstNums(methodParts[1]);
            }
            text = text.substring(0, matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }

    public String simpleMethodHandler(String text) {
        //Pattern pattern = Pattern.compile("( *\\w+ *\\([^()]*\\)){1,}");
        Pattern pattern = Pattern.compile("( *\\w+ *\\([^()]*\\)){1,}\\;");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String method;
            method = text.substring(matcher.start(), matcher.end());
            String[] methodParts = method.split("\\(");
            Main.window.getTableModel().addOperand(methodParts[0]);
            if (methodParts.length > 1
                    && methodParts[1].matches(".*[0-9]{1,}.*")
                    && !methodParts[1].matches("\\b(byte|short|int|long|float|double|char|boolean|String)\\b")) {
                System.out.println("Cyclepart111 : |" + methodParts[1] + "|");
                //methodParts[1] = getConstNums(methodParts[1]);
                getConstNums(methodParts[1]);
            }
            text = text.substring(0, matcher.start()) + text.substring(matcher.end() - 1);
            matcher.reset(text);
        }
        return text;
    }

    public String cutTypedConstants(String text) {
        Pattern pattern = Pattern.compile("final.+(byte|short|int|long|float|double|char|boolean|String).+[=].+[;]+?");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.substring(0,matcher.start()) + text.substring(matcher.end());
            matcher.reset(text);
        }
        return text;
    }






    //for parsing of expression:
    //priorities (smaller number first):
    //+ 2
    //* 1
    //= 3
    //.
    //a = b + c * d


    public void analyzeExpression(String text)
    {int left = text.length();



    }

    public Boolean isCommented(String text, int beg, int end) {
//        Pattern pattern = Pattern.compile(".*?//.*?"+text.substring(beg,end)+".*?$");
//        Matcher matcher = pattern.matcher(text);
//
//        if (matcher.matches()) {
//            return true;
//        }
        if (StringOperations.countHits(text.substring(0, beg), "/*") -
                StringOperations.countHits(text.substring(0, beg), "*/") > 0) {
            return true;
        }

//        Pattern pattern = Pattern.compile(".*?//.*?" + text.substring(beg, end) + ".*?$");
//        Matcher matcher = pattern.matcher(text);

        Pattern pattern = Pattern.compile(".*?//.*?\\Q" + text.substring(beg, end) + "\\E.*?$");
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
