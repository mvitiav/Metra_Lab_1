package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static main.andrei.Main.buttonClicked;

public class Form extends JFrame{

    private JButton chooseFileButton;
    private JTable table1;
    private JPanel panel1;
    private JLabel Operators;
    private JPanel InfoPanel;
    private MyTableModel tableModel=null;

    public MyTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable1() {
        return table1;
    }

    public Form() {
        tableModel=new MyTableModel();
        table1.setModel(tableModel);

//        table1.setAutoCreateRowSorter(true);
        System.out.println("test");

        TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table1.getModel());

//        sorter1.setComparator(0, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });

        sorter1.setSortsOnUpdates(true);
        table1.setRowSorter(sorter1);

//        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
////        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
//        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter1.setSortKeys(sortKeys);


//        List<RowSorter.SortKey> sortKeys = new ArrayList<>;
//        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
//        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter.setSortKeys(sortKeys);
        try {
            chooseFileButton.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("Art/src/main/resources/Red October.ttf")).deriveFont(14f));
        } catch (FontFormatException | IOException e) {
         JOptionPane.showMessageDialog(null,"что - то отвалилося((");
            e.printStackTrace();
        }
        setSize(800, 600);
        setTitle("Моднейшее окно!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        chooseFileButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Какой-то пидор жмякнул кнопку накинь сюда вызов своего файлчузера!!!!");//TODO:сюда вызов своего файлчузера!!!!
//            }
//        });
        chooseFileButton.addActionListener(buttonClicked);



        add(panel1);
        setVisible(true);
    panel1.addComponentListener(new ComponentAdapter() { } );}

    public void addOperator(String operator)
    {
        tableModel.addOperator(operator);
        table1.revalidate();
        table1.repaint();
        System.err.println("opr:"+operator);
        pack();
    }

    public void addOperand(String operand)
    {
        tableModel.addOperand(operand);
        table1.revalidate();
        table1.repaint();
        System.err.println("opn:"+operand);
        pack();
    }


}
