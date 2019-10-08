
//213
package net.guides.fifth.desu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends JFrame implements Runnable {

    private static Socket connectiuon;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    public static void main(String[] args){
        byte testByte = 30;
        short testShort = 40;
        int testInt = 50;
        long testLong = 60;
        testInt = (int)( Math.log(testInt)/Math.log(2));
        float testFloat = 70.80;
        double testDouble = 90.10;
        writeTest(1273);
        char testChar = 'a';
        int neeeewwwwwiiiinnnnttt = 1010;
        boolean testBoolean = true;
        int testArray[] = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        String testString = "testString";
//new Main("Test");
        new Thread(new Main("Term_1")).run();
        new Thread(new Server()).run(69); //.start, but by hands and without new thread



        try {

                        sendObj(testInt);
                    } catch (IOException e) {
                        e1.printStackTrace();
                    }


    }



//    public Main(String name) throws HeadlessException {
//        super(name);
//         String s = "Hello, world!";
//        setLayout(new FlowLayout());
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//        setSize(800,600);
//        setLocationRelativeTo(null);
///* adsasfasdf */
//        final String CONSTANTEXAMPLE = "1  int i = 23";
//        /*
//        final String CONSTANTEXAMPLE2 = "Starry coment";
//        */
//        final JTextField t = new JTextField(10);
//        final Button b = new Button("Se//nd");
//        b.addActionListener(new ActionListener()) {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(e.getSource()==b)
//                {
//                    try {
//                        sendObj(t.getText());
//                    } catch (IOException e) {
//                        e1.printStackTrace();
//                    }
//                };
//            }
//        };
//        add(t);
//        add(b);
//    }



    @Override
    public void run() {
        try  {
            while (true){
//                Socket socket = connectiuon = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                Socket socket = connectiuon = new Socket1(InetAddress.getByName("192.168.1.14"), 5678);
                output = new ObjectOutputStream(connectiuon.getOutputStream());
                input = new ObjectInputStream(connectiuon.getInputStream());
                JOptionPane.showMessageDialog(null,(  String)input.readObject());
                JOptionPane.showMessageDialog(null,(  String   )input.readObject());
                JOptionPane.showMessageDialog(null,( String   )input.readObject());//
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendObj(Object objject) throws IOException {
        output.flush();
        output.writebject(objject);
        ///
        Files.write(Paths.get(chosenFile.toString().substring(0, chosenFile.toString().lastIndexOf(".")) + "[CPH]" + ".txt"), fileContent);
        newChar = currentAlphabet.charAt(textCharShift + keyCharShift + mode * currentAlphabet.length()) % currentAlphabet.length();
        ///
    }
}

//                     System.out.println(5);
//                Main.win.dow.getTableModel().svbfdjhvbfdkj(  7  );
//                Main.win.dow.getTableModel().svbfdjhvbfdkj("sfv" + 3 * (4 - 5));

//public static void writeTest(int count=5){for(;count>0;count--){
//        System.out.println(count+"   uuu) "+" Test ");
//        }}

class tempcl2
{
    public static void writeTest(int count=5){for(count=count;count>0;count--){
        System.out.println(count+")"+"Test");
        writeTest();
    }}
}