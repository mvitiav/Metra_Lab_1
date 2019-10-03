package main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
public class Main {
    public static String file;
    public static void main(String[] args) {
        //TODO: \b(String|int|char|boolean|void|class)\b.+?[{;(),=]    <- тут слов в скобки еще добавить надо
        //TODO: ".+?" строки
        //TODO: \/\/.*?$ одинарные комменты

        //TODO: Что еще нужно сделать:
        //TODO: 1. Всякие скобки - подребнее?
        //TODO: 2. А нам нужны вообще кавычки у строковых операндов в табличке? - ДА!
        //TODO: 3. Замутить костыли для обработки кастов и вызовов методов - ты я так понял занимаешься
        //TODO: 4. Определение имени класса как операнда повесить на костыль - а это надо?
        //TODO: 5. Возвращать строку с выпиленными кусками после каждого метода - всм строку того что в методе?
        //TODO: 6. Допилить регулярку кастера, потому что после типа перед закрывающейся скобкой все еще пропускаются непробелтные символы
        //TODO: 6. UPD. Я немного промахнулся. Эта регулярка чекает все, где есть такие буквы
        //TODO: перевести все эксепшены на throws модель и чекать в мейне


        //       System.out.println("test");
//        Form window = new Form();
//
//        window.getTableModel().addOperator("operator");
//        window.getTableModel().addOperator("operator");
//        window.getTableModel().addOperand("operand");
//        window.getTableModel().addOperand("operand2");
//        //window.setContentPane(new Form().panel1);
//        JFileChooser fileChooser = new JFileChooser();
//        File selectedFile = null;
//        int returnVal;

       // Matcher matcher = new Matcher(Pattern.compile("\\({1}"));
       String s1 = "gs==df'!=g]l>>>=o-=&=k,sdf*=gp=o[|=s/=d++kg&?=>!~:==";
//
        String tempstring = s1;
//       Matcher matcher =Pattern.compile("\\(.*\\)").matcher(tempstring);
       Matcher matcher =Pattern.compile(
//               "=|>|<|!|~|\?|:|==|<=|>=|!=|&&|\\|\\||\\+\\+|\\-\\-|\\+|\\-|\\*|\\/|&\\||^|%|«|»|»>|+=|\\-=|*=|\\/=|&=|\\|=|^=|%=|«=|»=|»>"
               ">>>=|>>=|<<=|%=|\\^=|&=|\\|=|&=|/=|\\*=|-=|\\+=|>>>|>>|<<|%|\\^|\\|\\||&&|/|-\\*|\\+\\+|--|\\+|\\||&|!=|>=|<=|==|:|\\?|~|!|>|>|="
       ).matcher(tempstring);
while (matcher.find())
{
    System.out.println("123                    "+tempstring.substring(matcher.start(),matcher.end()));
//    tempstring = tempstring.substring(matcher.start()+1,matcher.end()-1);
//    matcher.reset(tempstring);
}
      //  System.out.println(A.X>A.Y);

//        do {     returnVal =  fileChooser.showOpenDialog(null);
//        //System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
//            selectedFile = fileChooser.getSelectedFile();
//           try {
//               Thread.sleep(50);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
//       }while((returnVal != JFileChooser.APPROVE_OPTION)||(!fileChooser.getSelectedFile().exists()));
//
//
//        try {
//            Scanner filescanner = new Scanner(selectedFile);
//            while(filescanner.hasNext()){
//                System.out.println(filescanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}

//плеыер - биграмный шифр тк ассматриваются 2 сисвола - надо генерировать подстановочную таблицу размер которой равен ол-ву букв алфавита например для англа 5 на 5 = 25 а ваще найс - ключ в начале таблички а оставшиеся дальше тк шифр биграмны надо рассматривать2 символа сначала текст разбивается по парам и буквы шифруются исходя из своей позиции а 2 одинаковые подряд разбиваются например иксом как редкими если текст нечетной длины то добавить вконец че-то еще например тот же символ
//






//eserved.add("abstract");reserved.add("assert");reserved.add("boolean");
//        reserved.add("break");reserved.add("byte");reserved.add("case");
//        reserved.add("catch");reserved.add("char");reserved.add("class");
//        reserved.add("const");reserved.add("continue");reserved.add("default");
//        reserved.add("do");reserved.add("double");reserved.add("else");
//        reserved.add("enum");reserved.add("extends");reserved.add("false");
//        reserved.add("final");reserved.add("finally");reserved.add("float");
//        reserved.add("for");reserved.add("if");reserved.add("goto");
//        reserved.add("implements");reserved.add("import");reserved.add("instanceof");
//        reserved.add("int");reserved.add("interface");reserved.add("long");
//        reserved.add("native");reserved.add("new");reserved.add("null");
//        reserved.add("package");reserved.add("private");reserved.add("protected");
//        reserved.add("public");reserved.add("return");reserved.add("short");
//        reserved.add("static");reserved.add("strictfp");reserved.add("super");
//        reserved.add("switch");reserved.add("synchronized");reserved.add("this");
//        reserved.add("throw");reserved.add("throws");reserved.add("transient");
//        reserved.add("true");reserved.add("try");reserved.add("void");
//        reserved.add("volatile");reserved.add("while");

