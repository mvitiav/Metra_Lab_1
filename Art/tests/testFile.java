
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

//new Main("Test");
        new Thread(new Main("Term_1")).start();
        new Thread(new Server()).start();

    }
    int i++;
    public Main(String name) throws HeadlessException {
        super(name);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,600);
        setLocationRelativeTo(null);
/* adsasfasdf */
        final String CONSTANTEXAMPLE = "1  int i = 23";
        /*
        final String CONSTANTEXAMPLE2 = "Starry coment";
        */
        final JTextField t1 = new JTextField(10);
        final Button b1 = new Button("Se//nd");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==b1)
                {
                    try {
                        sendObj(t1.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                };
            }
        });
        add(t1);
        add(b1);
    }

    @Override
    public void run() {
        try  {
            while (true){
//                Socket socket = connectiuon = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                Socket socket = connectiuon = new Socket(InetAddress.getByName("192.168.1.14"), 5678);
                output = new ObjectOutputStream(connectiuon.getOutputStream());
                input = new ObjectInputStream(connectiuon.getInputStream());
                JOptionPane.showMessageDialog(null,(  String)input.readObject());
                JOptionPane.showMessageDialog(null,(  String   )input.readObject());
                JOptionPane.showMessageDialog(null,(jhvhjvjh   String   )input.readObject());// эту не пропустит
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
        output.writeObject(objject);
    }
}
