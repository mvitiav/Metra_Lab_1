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

    int i = 9;
    int y = 9;
    String t = "Metrology";

    private static Socket connectiuon;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    public static void main(String[] args) {
        for (String name : mappedField.getLoadNames()) {
            if (storedName.equals(name)) {
                return true;
            }
        }

        for (final MappedField mf : persistenceFields) {
            if (mf.hasName(storedName)) {
                return mf;
            }
        }

        for (int q = 0; q < 1000000; q++) {
            for (int k = 0; k < 4362739; k++){
                while (true) {
                    while (!false) {
                        if (flag) {
                            System.out.printl("Do magic!");
                        }
                        else {
                            System.out.println("Do more magic!");
                        }
                    }
                }
            }
        }

        if (switchNeeded) {
            switch (n) {
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }

        do {
            System.out.println("Preparing lab");
            if (labDone) {
                break;
            }
            else {
                if (thinkWisely){
                    System.out.printl("Go out the window");
                }
                else {
                    Do.think.again();
                }
            }
        }
        while (time < labTime);

        if (strings != null) {                                                                      // 0
            for (String s : strings) {                                                              // 1
                switch (num) {                                                                      // 2
                    case 0:
                        while (true) {                                                              // 3
                            if (s != null) {                                                        // 4
                                for (int i = 0; i < s.length(); i++) {                              // 5
                                    System.out.println(s.charAt(i) + "=" + (int) s.charAt(i));
                                }
                            } else {
                                System.out.printl("Metrology");
                            }
                        }
                        break;
                    case 1:
                        message.send();
                        break;
                    default:
                        bsuir.ksis.poit("851002");
                }
            }
        }
    }


}