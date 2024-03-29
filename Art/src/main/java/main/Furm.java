package main;

import main.andrei.Main;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Furm extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JButton chooseFileButton;
    private JLabel cmLabel;
    private JLabel iocm;
    private JScrollPane sp3;
    public static TM1 tm1;

    public JTable getTable1() {
        return table1;
    }

    public Furm()
    {


        setSize(800, 600);
        setTitle("Chepin metrics: ");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
table1.setModel(new TM1());
table2.setModel(new TM2());
table3.setModel(new TM3());

        add(panel1);
        chooseFileButton.addActionListener(Main.buttonClicked);
        setVisible(true);
    }

    private  void updateRowHeights()
    {
        for (int row = 0; row < table3.getRowCount(); row++)
        {
            int rowHeight = table3.getRowHeight();

            for (int column = 0; column < table3.getColumnCount(); column++)
            {
                Component comp = table3.prepareRenderer(table3.getCellRenderer(row, column), row, column);
                rowHeight =Math.max( 16*8,Math.max(rowHeight, comp.getPreferredSize().height));
            }

            table3.setRowHeight(row, rowHeight);
        }
    }

    public void notifyT1()
    {
  //      System.err.println(
    //table1.getColumnCount()
  //  );
        //tm1.fireTableDataChanged()
    //  table1.getTableHeader().revalidate();
     // sp2.getHorizontalScrollBar().setSize(new Dimension(50000,sp2.getHorizontalScrollBar().getPreferredSize().height));
        table1.setModel(new TM1());
table1.revalidate();
table1.repaint();

        //table2.setSize(new Dimension(50000,500));
      //  table2.setModel(new TM2());

table2.setModel(new TM2());

     // updateRowHeights();
        table3.setModel(new TM3());
      //sp2.setPreferredSize(new Dimension(5000, 32));

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("Consolas", table1.getFont().getStyle(), table1.getFont().getSize());

        String text1 = "";

        for(int col=0;col<table2.getColumnCount();col++)
        {
            text1= (String) table2.getValueAt(0,col);
        int textwidth = (int)(font.getStringBounds(text1, frc).getWidth());
        table2.getColumnModel().getColumn(col).setPreferredWidth((int) (textwidth*1.4));
        table2.getColumnModel().getColumn(col).setWidth((int) (textwidth*1.4));
        }
        for(int col=0;col<table3.getColumnCount();col++)
        {
            text1= (String) table3.getValueAt(0,col);
            int textwidth = (int)(font.getStringBounds(text1, frc).getWidth());
            table3.getColumnModel().getColumn(col).setPreferredWidth((int) (textwidth*1.4));
            table3.getColumnModel().getColumn(col).setWidth((int) (textwidth*1.4));
        }


        //table2.revalidate();
       // table2.repaint();

        pack();
        revalidate();
        repaint();
        double cm = 0;
       String newText ="Chepin metrics = ";
            cm+=Integer.parseInt(String.valueOf(table2.getValueAt(1,1)));
            newText+="1 * "+Integer.parseInt(String.valueOf(table2.getValueAt(1,1)));
            cm+=Integer.parseInt(String.valueOf(table2.getValueAt(1,2)))*2;
            newText+="+ 2 * "+Integer.parseInt(String.valueOf(table2.getValueAt(1,2)));
            cm+=Integer.parseInt(String.valueOf(table2.getValueAt(1,3)))*3;
            newText+="+ 3 * "+Integer.parseInt(String.valueOf(table2.getValueAt(1,3)));
            cm+=Integer.parseInt(String.valueOf(table2.getValueAt(1,4)))*0.5;
            newText+="+ 0.5 * "+Integer.parseInt(String.valueOf(table2.getValueAt(1,4)));
            newText+=" = "+cm;
        cmLabel.setText(newText);

         cm = 0;
         newText ="I/O Chepin metrics = ";
        cm+=Integer.parseInt(String.valueOf(table3.getValueAt(1,1)));
        newText+="1 * "+Integer.parseInt(String.valueOf(table3.getValueAt(1,1)));
        cm+=Integer.parseInt(String.valueOf(table3.getValueAt(1,2)))*2;
        newText+="+ 2 * "+Integer.parseInt(String.valueOf(table3.getValueAt(1,2)));
        cm+=Integer.parseInt(String.valueOf(table3.getValueAt(1,3)))*3;
        newText+="+ 3 * "+Integer.parseInt(String.valueOf(table3.getValueAt(1,3)));
        cm+=Integer.parseInt(String.valueOf(table3.getValueAt(1,4)))*0.5;
        newText+="+ 0.5 * "+Integer.parseInt(String.valueOf(table3.getValueAt(1,4)));
        newText+=" = "+cm;
        iocm.setText(newText);



        //  table2.getColumnModel().getColumn(1).setPreferredWidth(5000);
        //sp2.setSize(new Dimension(50000,sp2.getPreferredSize().height));
       // table2.revalidate();
       // table2.repaint();
        //TableCellRenderer renderer = table2.getCellRenderer(1,1);
        //Component comp table2.prepareRenderer(renderer, 1, 1);
       // pack();
      //  System.err.println(
     //           table1.getColumnCount()
     //   );
    }
}
