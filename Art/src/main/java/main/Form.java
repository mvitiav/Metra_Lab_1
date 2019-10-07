package main;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.IOException;

import static main.andrei.Main.buttonClicked;

public class Form extends JFrame{

    public static int operandsCount=0;
    public static int operatorsCount =0;
    public static int uniqueOperandsCount=0;
    public static int uniqueOperatorsCount =0;

    private JButton chooseFileButton;
    private JTable table1;
    private JPanel panel1;
    private JLabel Operators;
    private JPanel InfoPanel;
    private JLabel uOPRL;
    private JLabel uOPNDL;
    private JLabel oPRL;
    private JLabel oPNDL;
    private JLabel pDL;
    private JLabel pLL;
    private JLabel pVL;
    private MyTableModel tableModel=null;

    public MyTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable1() {
        return table1;
    }

    public JLabel getuOPRL() {
        return uOPRL;
    }

    public JLabel getpDL() {
        return pDL;
    }

    public JLabel getpLL() {
        return pLL;
    }

    public JLabel getpVL() {
        return pVL;
    }

    public JLabel getuOPNDL() {
        return uOPNDL;
    }

    public JLabel getoPRL() {
        return oPRL;
    }

    public JLabel getoPNDL() {
        return oPNDL;
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
      //  operandsCount++;
        tableModel.addOperand(operand);
        table1.revalidate();
        table1.repaint();
        System.err.println("opn:"+operand);
        pack();
    }


}
