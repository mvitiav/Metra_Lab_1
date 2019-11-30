package main.andrei;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeAnalysis {
    private static String nextMethod = "";
    public void getMetrics() {
        final String P_GROUP = "P";
        final String M_GROUP = "M";
        final String C_GROUP = "C";
        final String T_GROUP = "T";

        ArrayList<String> methodNames = new ArrayList<>();
        ArrayList<String> allMethodVars = new ArrayList<>();
        HashMap<String, Integer> varSpens = new HashMap<>();
        HashMap<String, Boolean> isControlVar = new HashMap<>();
        HashMap<String, Boolean> wasModified = new HashMap<>();
        HashMap<String, Boolean> isInput = new HashMap<>();
        HashMap<String, Boolean> wasUsed = new HashMap<>();
        HashMap<String, Boolean> isOutput = new HashMap<>();
        HashMap<String, String> varGroup = new HashMap<>();

        if (!checkBrackets(Main.inputText)) { System.err.println("Check your test code, dude."); }
        else { System.out.println("Brackets OK"); }
        cutMultilineComments();
        cutSingleLineComments();
        cutImports();
        cutHighestBrackets();

        while (Main.inputText.contains("{")) {
            nextMethod = getNearestMethod();
            String nextMethodTitle = getNearestMethodTitle();
            String nextMethodName = getTitleName(nextMethodTitle);
            methodNames.add(nextMethodName);
            //String nextMethodBrackets = getTitleBrackets(nextMethodTitle);

            //System.out.println("@@@@@@@@@@> " + nextMethod + " <@@@@@@@@@");
            //System.out.println("Title: " + nextMethodTitle);
            Main.inputText = Main.inputText.replace(nextMethodTitle, "");
            Main.inputText = Main.inputText.replace(nextMethod, "");

            ArrayList<String> methodVars = new ArrayList<>();
            methodVars = getMethodVars(nextMethodTitle);

            allMethodVars.addAll(methodVars);

            final int TASKS_NUM = 6;
            for (int taskNum = 0; taskNum < TASKS_NUM; taskNum++) {
                for (String curVar : methodVars) {
                    switch (taskNum) {
                        case 0:
                            varSpens.put(curVar + " (" + nextMethodName + ")", getVarSpen(curVar));
                            break;
                        case 1:
                            isControlVar.put(curVar + " (" + nextMethodName + ")", checkIfControl(curVar));
                            break;
                        case 2:
                            isInput.put(curVar + " (" + nextMethodName + ")", checkIfInput(curVar));
                            break;
                        case 3:
                            wasModified.put(curVar + " (" + nextMethodName + ")", checkIfModified(curVar));
                            break;
                        case 4:
                            wasUsed.put(curVar + " (" + nextMethodName + ")", checkIfUsed(curVar));
                            break;
                        case 5:
                            isOutput.put(curVar + " (" + nextMethodName + ")", checkIfOutput(curVar));
                            break;
                    }
                }
            }

            String methodName = nextMethodName;
            for (String curVar : methodVars) {
                int varSpen = varSpens.get(curVar + " (" + methodName + ")");
                boolean isVarControl = isControlVar.get(curVar + " (" + methodName + ")");
                boolean isVarInput = isInput.get(curVar + " (" + methodName + ")");
                boolean isVarModified = wasModified.get(curVar + " (" + methodName + ")");
                boolean isVarUsed = wasUsed.get(curVar + " (" + methodName + ")");
                boolean isVarOutput = isOutput.get(curVar + " (" + methodName + ")");

                if (isVarControl) {
                    varGroup.put(curVar + " (" + methodName + ")", C_GROUP);                                //C
                }
                else {
                    if (isVarInput && !isVarModified) {                                                     //P || PT
                        String res = "";
                        res += P_GROUP;
                        if (!isVarUsed) {
                            res += T_GROUP;
                        }
                        varGroup.put(curVar + " (" + methodName + ")", res);
                    }
                    else {
                        if (isVarUsed) {
                            varGroup.put(curVar + " (" + methodName + ")", M_GROUP);
                        }
                        else {
                            if (isVarOutput) {
                                varGroup.put(curVar + " (" + methodName + ")", M_GROUP);
                            }
                            else {
                                varGroup.put(curVar + " (" + methodName + ")", T_GROUP);
                            }
                        }
                    }
                }
                //System.out.println("Var: > " + curVar + " (" + methodName + ")" + " <");
                //System.out.println("=== Spen: " + varSpens.get(curVar + " (" + methodName + ")"));
                //System.out.println("=== Is control: " + isControlVar.get(curVar + " (" + methodName + ")"));
                //System.out.println("=== Is input: " + isInput.get(curVar + " (" + methodName + ")"));
                //System.out.println("=== Was Modified: " + wasModified.get(curVar + " (" + methodName + ")"));
                //System.out.println("=== Was Used: " + wasUsed.get(curVar + " (" + methodName + ")"));
                //System.out.println("=== Is Output: " + isOutput.get(curVar + " (" + methodName + ")"));
            }
            //System.out.println("");
        }

        for (Map.Entry<String, String> entry : varGroup.entrySet()) {
            String var = entry.getKey();
            String group = entry.getValue();
            System.out.println(var + " : " + group);
        }
    }

//FIXME:================================================================================================================
    private boolean checkIfOutput(String curVar) {
        final String printPattern = "(System.*print.*\\([^a-zA-Z0-9]*)" + curVar + "([^a-zA-Z0-9]|[ ]*\\) *;)";
        boolean isOutput = false;
        Pattern pattern = Pattern.compile(printPattern);
        Matcher matcher = pattern.matcher(nextMethod);
        int startPos = 0;
        while (matcher.find(startPos)) {
            nextMethod = nextMethod.replace(nextMethod.substring(matcher.start(), nextMethod.indexOf(";", matcher.start())), "");
            //nextMethod = nextMethod.replaceFirst(nextMethod.substring(matcher.start(), nextMethod.indexOf(";", matcher.start())), "");
            startPos = matcher.start() + 1;
            isOutput = true;
            matcher.reset(nextMethod);
        }
        return isOutput;
    }

    private boolean checkIfUsed(String curVar) {
        final String rightModPattern = "=.*[^a-zA-Z0-9]" + curVar + "[^a-zA-Z0-9]*";
        boolean wasUsed = false;
        Pattern pattern = Pattern.compile(rightModPattern);
        Matcher matcher = pattern.matcher(nextMethod);
        int startPos = 0;
        while (matcher.find(startPos)) {
            //nextMethod = nextMethod.replace(curVar, "");
            nextMethod = nextMethod.replaceFirst(curVar, "");
            startPos = matcher.start() + 1;
            wasUsed = true;
        }
        return wasUsed;
    }

    private boolean checkIfInput(String curVar) {
        //final String scannerPattern = curVar + " *= *scanner";
        final String scannerPattern = curVar + " *= *[a-zA-Z0-9_]*\\.next(Byte|Short|Int|Long|Float|Double|Char|Boolean|String)";
        boolean isInput = false;
        Pattern pattern = Pattern.compile(scannerPattern);
        Matcher matcher = pattern.matcher(nextMethod);
        int startPos = 0;
        while (matcher.find(startPos)) {
            //nextMethod = nextMethod.replace(nextMethod.substring(matcher.start(), nextMethod.indexOf(";", matcher.start())), "");
            nextMethod = nextMethod.replaceFirst(nextMethod.substring(matcher.start(), nextMethod.indexOf(";", matcher.start())), "");
            startPos = matcher.start() + 1;
            isInput = true;
        }
        return isInput;
    }

    private boolean checkIfModified(String curVar) {
        final String leftModPattern = curVar + " *(>>>=|>>=|<<=|%=|\\^=|&=|\\*=|-=|\\+=|=)";
        boolean isModified = false;
        Pattern pattern = Pattern.compile(leftModPattern);
        Matcher matcher = pattern.matcher(nextMethod);
        int startPos = 0;
        while (matcher.find(startPos)) {
            //nextMethod = nextMethod.replace(nextMethod.substring(matcher.start(), nextMethod.indexOf("=", matcher.start()-1)), "");
            nextMethod = nextMethod.replaceFirst(nextMethod.substring(matcher.start(), nextMethod.indexOf("=", matcher.start()-1)), "");
            startPos = matcher.start() + 1;
            isModified = true;
        }

        if (!isModified) {
            final String modNoEqual = curVar + " *(\\+\\+|--)";
            Pattern subPattern = Pattern.compile(modNoEqual);
            Matcher subMatcher = subPattern.matcher(nextMethod);
            startPos = 0;
            while (subMatcher.find(startPos)) {
                nextMethod = nextMethod.replace(nextMethod.substring(subMatcher.start(), nextMethod.indexOf(";", subMatcher.start()-1)), "");
                //nextMethod = nextMethod.replaceFirst(nextMethod.substring(subMatcher.start(), nextMethod.indexOf(";", subMatcher.start()-1)), "");
                startPos = subMatcher.start() + 1;
                isModified = true;
            }
        }
        return isModified;
    }

    private boolean checkIfControl(String curVar) {
        //final String flowControlPattren = "(while|if|for|switch).*\\)";
        final String flowControlPattren = "(while|if|for|switch)(?:(?!(while|if|for|switch)|\\))[\\s\\S])*\\)";
        boolean isControl = false;
        Pattern pattern = Pattern.compile(flowControlPattren);
        Matcher matcher = pattern.matcher(nextMethod);
        int startPos = 0;
        while (matcher.find(startPos)) {
            String probablyMatch = nextMethod.substring(matcher.start(), matcher.end());
            Pattern subPattern = Pattern.compile("[^a-zA-Z]" + curVar.trim() + "[^a-zA-Z]");
            Matcher subMatcher = subPattern.matcher(probablyMatch);
            if (subMatcher.find()) {
                //nextMethod = nextMethod.replace(probablyMatch, "");
                nextMethod = nextMethod.replaceFirst(probablyMatch, "");
                //nextMethod = nextMethod.replaceFirst(nextMethod.substring(subMatcher.start(), nextMethod.indexOf(";", subMatcher.start()-1)), "");
                isControl = true;
            }
            startPos = matcher.start() + 1;
        }

        if (!isControl) {
            final String casePatternRegex = "case *" + curVar + " *:";
            Pattern casePattern = Pattern.compile(casePatternRegex);
            Matcher caseMatcher = casePattern.matcher(nextMethod);
            startPos = 0;
            while (caseMatcher.find(startPos)) {
                nextMethod = nextMethod.replaceFirst(nextMethod.substring(caseMatcher.start(), caseMatcher.end()), "");
                caseMatcher.reset(nextMethod);
                isControl = true;
            }
        }

        return isControl;
    }

    private int getVarSpen(String curVar) {
        int varSpen = 0;
        int startPos = 0;
        final String varPattern = "[^a-zA-Z]" + curVar.trim() + "[^a-zA-Z]";
        Pattern pattern = Pattern.compile(varPattern);
        Matcher matcher = pattern.matcher(nextMethod);
        while (matcher.find(startPos)) {
            varSpen++;
            startPos = matcher.start() + 1;
        }
//        while (nextMethod.indexOf(curVar, startPos) != -1) {
//            varSpen++;
//            startPos = nextMethod.indexOf(curVar, startPos) + 1;
//        }
        return varSpen;
    }

    private ArrayList<String> getMethodVars(String methodTitle) {
        String methodText = methodTitle + nextMethod;
        ArrayList<String> allVars = new ArrayList<>();
        final String allVarNamesPattern = "\\b(byte|short|int|long|float|double|char|boolean|String)\\b.+?[,;)=]";
        Pattern pattern = Pattern.compile(allVarNamesPattern);
        Matcher matcher = pattern.matcher(methodText);
        while (matcher.find()) {
            allVars.add(methodText.substring(methodText.indexOf(' ', matcher.start()), matcher.end()-1).trim());
            Main.inputText = Main.inputText.replace(methodText.substring(matcher.start(), matcher.end()-1), "");
            //Main.inputText = Main.inputText.replaceFirst(methodText.substring(matcher.start(), matcher.end()-1), "");
            methodText = methodText.replace(methodText.substring(matcher.start(), matcher.end()-1), "");
            //methodText = methodText.replaceFirst(methodText.substring(matcher.start(), matcher.end()-1), "");
            matcher.reset(methodText);
        }
        nextMethod = methodText.substring(methodText.indexOf('{'));
        return allVars;
    }

    private String getNearestMethod() {
        int level = 0;
        int i = Main.inputText.indexOf('{');
        int fPos = i, lPos;
        boolean flag = true;
        do {
            if (Main.inputText.toCharArray()[i] == '{') {
                if (flag) { fPos = i; flag = false; }
                level++;
            }
            else if (Main.inputText.toCharArray()[i] == '}') {
                level--;
            }
            i++;
        } while (level != 0);
        lPos = i;
        return Main.inputText.substring(fPos, lPos);
    }

    private String getNearestMethodTitle() {
        final String methodTitlePattern = "\\b(byte|short|int|long|float|double|char|boolean|String|void)\\b.+?[)]";
        Pattern pattern = Pattern.compile(methodTitlePattern);
        Matcher matcher = pattern.matcher(Main.inputText);
        if (matcher.find()) {
            //System.out.println("NextTitle %%%" + Main.inputText.substring(matcher.start(), matcher.end()) + "%%%");
            return Main.inputText.substring(matcher.start(), matcher.end());
        }
        else {
            return "";
        }
    }

    private String getTitleName(String methodTitle) {
        return methodTitle.substring(methodTitle.indexOf(' '), methodTitle.indexOf('(')).trim();
    }

    private String getTitleBrackets(String methodTitle) {
        return methodTitle.substring(methodTitle.indexOf('(')+1, methodTitle.indexOf(')'));
    }

    private void cutMultilineComments() {
        final String multiLineCommentsPattern = "(\\/\\*)([^*]|[\\r\\n]|(\\*+([^*\\/]|[\\r\\n])))*(\\*\\/)";
        Pattern pattern = Pattern.compile(multiLineCommentsPattern);
        Matcher matcher = pattern.matcher(Main.inputText);
        while (matcher.find()) {
            Main.inputText = Main.inputText.substring(0, matcher.start()) + Main.inputText.substring(matcher.end());
            matcher.reset(Main.inputText);
        }
    }

    public void cutSingleLineComments() {
        final String singleLineCommentsPattern = "\\/\\/.*";
        Pattern pattern = Pattern.compile(singleLineCommentsPattern);//Закрыть глаза
        Matcher matcher = pattern.matcher(Main.inputText);//Можно открывать
        while (matcher.find()) {
            Main.inputText = Main.inputText.substring(0, matcher.start()) + Main.inputText.substring(matcher.end());
            matcher.reset(Main.inputText);
        }
    }

    public void cutImports() {
        final String importsPattern = "(package|import).*;";
        Pattern pattern = Pattern.compile(importsPattern);
        Matcher matcher = pattern.matcher(Main.inputText);
        while (matcher.find()) {
            Main.inputText = Main.inputText.substring(0, matcher.start()) + Main.inputText.substring(matcher.end()+1);
            matcher.reset(Main.inputText);
        }
    }

    public void cutHighestBrackets() {
        Main.inputText = Main.inputText.substring(0, Main.inputText.indexOf('{')) + Main.inputText.substring(Main.inputText.indexOf('{') + 1);
        Main.inputText = Main.inputText.substring(0, Main.inputText.lastIndexOf('}')) + Main.inputText.substring(Main.inputText.lastIndexOf('}') + 1);
    }

    public boolean checkBrackets(String analyseText) { //Просто дебаг-метод, т.к. возникло сомнение, что скобки нигде не пропущены
        boolean bracketsOkay = false;
        int openCloses = 0;
        for (int i = 0; i < analyseText.length(); i++) {
            switch (analyseText.charAt(i)) {
                case '{':
                    openCloses++;
                    break;
                case '}':
                    openCloses--;
                    break;
            }
            if (openCloses == 0) {
                bracketsOkay = true;
            }
            else {
                bracketsOkay = false;
            }
        }
        return bracketsOkay;
    }

//FIXME:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //TODO: Какие метрики сдаете?
    //      Что такое спен?
    //      Как и на что он влияет.
    //      Рассказать метрики Чепина
    //      Все формулы и значения коэффициентов.


    public Boolean isCommented(String text, int beg, int end) {
        if (StringOperations.countHits(text.substring(0, beg), "/*") -
                StringOperations.countHits(text.substring(0, beg), "*/") > 0) {
            return true;
        }

        Pattern pattern = Pattern.compile(".*?//.*?\\Q" + text.substring(beg, end) + "\\E.*?$");
        Matcher matcher = pattern.matcher(text);

        for (String s : text.split("\n")) { //если починишь ^ то можно без этого костыля
            matcher.reset(s);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}









































