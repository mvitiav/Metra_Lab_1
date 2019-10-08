package main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
public class Main2 {
    public static String file;
    public static void main(String[] args) {

        //: Что еще нужно сделать:
        // 1. Всякие скобки - подребнее? //fixed
        // 2. А нам нужны вообще кавычки у строковых операндов в табличке? - ДА! //fixed
        // 3. Замутить костыли для обработки кастов и вызовов методов - ты я так понял занимаешься //fixed
        // 4. Определение имени класса как операнда повесить на костыль - а это надо? //нет!
        // 5. Возвращать строку с выпиленными кусками после каждого метода - всм строку того что в методе? //made
        // 6. Допилить регулярку кастера, потому что после типа перед закрывающейся скобкой все еще пропускаются непробелтные символы //made
        // 6. UPD. Я немного промахнулся. Эта регулярка чекает все, где есть такие буквы //madex2

        //TODO: перевести все эксепшены на throws модель и чекать в мейне //пока влом


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
       String s1 = "\n" +
               "\n" +
               "private static Socket connectiuon;\n" +
               "private static ObjectInputStream input;\n" +
               "private static ObjectOutputStream output;\n" +
               "\n" +
               "public void main(String[] args){\n" +
               "byte testByte = 30;\n" +
               "short testShort = 40;\n" +
               "int testInt = 50;\n" +
               "long testLong = 60;\n" +
               "float testFloat = 70.80;\n" +
               "double testDouble = 90.10;\n" +
               "writeTest(1273);\n" +
               "char testChar = 'a';\n" +
               "int neeeewwwwwiiiinnnnttt = 1010;\n" +
               "boolean testBoolean = true;\n" +
               "int testArray[] = {1, 2, 3, 5, 6, 7, 8, 9, 10};\n" +
               "String testString = \"testString\";\n" +
               "//new Main(\"Test\");\n" +
               "new Thread(new Main(\"Term_1\")).start();\n" +
               "new Thread(new Server()).start();\n" +
               "}\n" +
               "\n" +
               "public Main(String name) throws HeadlessException {\n" +
               "super(name);\n" +
               "String s = \"Hello, world!\";\n" +
               "setLayout(new FlowLayout());\n" +
               "setDefaultCloseOperation(EXIT_ON_CLOSE);\n" +
               "setVisible(true);\n" +
               "setSize(800,600);\n" +
               "setLocationRelativeTo(null);\n" +
               "/* adsasfasdf */\n" +
               "final String CONSTANTEXAMPLE = \"1  int i = 23\";\n" +
               "/*\n" +
               "final String CONSTANTEXAMPLE2 = \"Starry coment\";\n" +
               "*/\n" +
               "final JTextField t = new JTextField(10);\n" +
               "final Button b = new Button(\"Se//nd\");\n" +
               "b.addActionListener(new ActionListener()) {\n" +
               "@Override\n" +
               "public void actionPerformed(ActionEvent e) {\n" +
               "if(e.getSource()==b)\n" +
               "{\n" +
               "try {\n" +
               "sendObj(t.getText());\n" +
               "} catch (IOException e) {\n" +
               "e1.printStackTrace();\n" +
               "}\n" +
               "};\n" +
               "}\n" +
               "};\n" +
               "add(t);\n" +
               "add(b);\n" +
               "}\n" +
               "\n" +
               "if (true) {\n" +
               "j--;\n" +
               "}\n" +
               "else {\n" +
               "i++;\n" +
               "}\n" +
               "\n" +
               "@Override\n" +
               "public void run() {\n" +
               "try  {\n" +
               "while (true){\n" +
               "//                Socket socket = connectiuon = new Socket(InetAddress.getByName(\"127.0.0.1\"), 5678);\n" +
               "Socket socket = connectiuon = new Socket1(InetAddress.getByName(\"192.168.1.14\"), 5678);\n" +
               "output = new ObjectOutputStream(connectiuon.getOutputStream());\n" +
               "input = new ObjectInputStream(connectiuon.getInputStream());\n" +
               "JOptionPane.showMessageDialog(null,(  String)input.readObject());\n" +
               "JOptionPane.showMessageDialog(null,(  String   )input.readObject());\n" +
               "JOptionPane.showMessageDialog(null,( String   )input.readObject());// СЌС‚Сѓ РЅРµ РїСЂРѕРїСѓСЃС‚РёС‚\n" +
               "}\n" +
               "} catch (UnknownHostException e) {\n" +
               "e.printStackTrace();\n" +
               "} catch (IOException e) {\n" +
               "e.printStackTrace();\n" +
               "} catch (ClassNotFoundException e) {\n" +
               "e.printStackTrace();\n" +
               "}\n" +
               "}\n" +
               "private void sendObj(Object objject) throws IOException {\n" +
               "output.flush();\n" +
               "output.writebject(objject);\n" +
               "///\n" +
               "Files.write(Paths.get(chosenFile.toString().substring(0, chosenFile.toString().lastIndexOf(\".\")) + \"[CPH]\" + \".txt\"), fileContent);\n" +
               "newChar = currentAlphabet.charAt(textCharShift + keyCharShift + mode * currentAlphabet.length()) % currentAlphabet.length();\n" +
               "///\n" +
               "}\n";
//
        System.out.println("123123123");
        String tempstring = s1;
//       Matcher matcher =Pattern.compile("\\(.*\\)").matcher(tempstring);
       Matcher matcher =Pattern.compile(
//               "=|>|<|!|~|\?|:|==|<=|>=|!=|&&|\\|\\||\\+\\+|\\-\\-|\\+|\\-|\\*|\\/|&\\||^|%|«|»|»>|+=|\\-=|*=|\\/=|&=|\\|=|^=|%=|«=|»=|»>"
//               "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]\\,]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])"
               ".*((public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])).*"
       ).matcher(tempstring);
while (matcher.find())
{
    System.out.println("123                    "+tempstring.substring(matcher.start(),matcher.end()));
    tempstring = tempstring.substring(matcher.end()+1);
    matcher.reset(tempstring);
}
//
//        tempstring = " public static void writeTest(int count=5){for(count=count;count>0;count--){\n" +
//                "        System.out.println(count+\")\"+\"Test\");\n" +
//                "    }}";
//
//
//        matcher =Pattern.compile(
////               "=|>|<|!|~|\?|:|==|<=|>=|!=|&&|\\|\\||\\+\\+|\\-\\-|\\+|\\-|\\*|\\/|&\\||^|%|«|»|»>|+=|\\-=|*=|\\/=|&=|\\|=|^=|%=|«=|»=|»>"
//                "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])"
//
//        ).matcher(tempstring);
//        while (matcher.find())
//        {
//            System.out.println("123                    "+tempstring.substring(matcher.start(),matcher.end()));
////    tempstring = tempstring.substring(matcher.start()+1,matcher.end()-1);
////    matcher.reset(tempstring);
//        }
//      //  System.out.println(A.X>A.Y);

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

