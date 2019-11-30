package net.guides.fifth.desu;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends JFrame implements Runnable {
    /*
        multiline
        comment
     */

    public static void main(String[] args) {
        final char mainConstVar = 'A';          //T
        int inVar;                              //P   IO
        Scanner scanner = new Scanner(System.in);
        inVar = scanner.nextInt();

        boolean isNothing;                      //PT
        isNothing = scanner.nextBoolean();

        String trashVar = "I'm trash variable.";//T

        String iString = "I AM STRING";         //M   IO
        char vChar = 'A';                       //C
        vChar = scanner.nextChar();
        if (vChar == 'A') {
            System.out.println(iString + " Yes!");
        }
        else {
            System.out.println(iString + " No :(");
        }
    }
    //single-line comment

    private void someVoid(int a, int b) {       //T; T
        int ioVar;                              //P
        int fgInt = 31;                         //C
        while (fgInt < 55) {
            fgInt++;
        }
        ioVar = scanner.nextInt();              //P IO
        System.out.println(ioVar);

        int tVar;                               //T
        int gInt = 8;                           //T
        gInt+=10;
        int ii = 10;
        int jj = 15;
        jj = jj + ii;
    }

    private void forVoid(char c) {
        for (int d = 0; d < 11; d++) {
            System.out.println(d);
        }
        int e = 9;
        e--;
    }

    private static void getParams(String textLine, int paramsNum) {
        boolean tVar;
        tVar = scanner.nextBoolean();
        System.out.println(tVar);
        textLine = textLine + "AddLine";
        String strParams;
        strParams = paramsNum.toString;
        String allText;
        allText = Math.getEquation(textLine + strParams);
        System.out.println("Default" + allText);

        while (!tVar) {
            System.out.println(allText);
            System.out.println(allText);
        }
    }

    void switchVoid(boolean switchVar) {
        if (switchVar) {
            int value = 5;
            int one = 1;
            int two = 2;
            int three = 3;

            switch (value) {
                case one:
                    System.out.println(one);
                    break;
                case two:
                    System.out.println(two);
                    break;
                case three:
                    System.out.println(three);
                    break;
            }
        }
    }
}