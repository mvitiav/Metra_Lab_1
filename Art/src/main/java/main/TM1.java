package main;

import main.andrei.Main;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TM1 implements TableModel {
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 2+  Main.varSpens.keySet().toArray().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
 //       System.out.println("           updating column name "+columnIndex);
     if(columnIndex==0){return "ID";}else if(
             columnIndex == (
                             getColumnCount()
                             -1)){return "Total:";}
     else
     if(columnIndex> Main.varSpens.keySet().toArray().length){return "-";}
     else
         {
            return (String) Main.varSpens.keySet().toArray()[columnIndex-1];
            // return Main.vars.get(columnIndex-1);
         }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

//        if(rowIndex==0)
//        {
//            return getColumnName(columnIndex);
//        }else
            if(rowIndex==0)
        {
            if(columnIndex==0){return "spen";}else
                if(columnIndex==getColumnCount()-1){

                    int sum=0;

                    for(Object i:Main.varSpens.keySet().toArray())
                    {
                       // System.out.println((String)i);
                        sum+=Main.varSpens.get((String)i);
                    }


                    return sum;


                }
                else
            if(columnIndex> Main.varSpens.keySet().toArray().length){return "-";}
                else return
                    Main.varSpens.get(   (String) Main.varSpens.keySet().toArray()[columnIndex-1])
            ;
                       // Main.varSpens.get(Main.vars.get(columnIndex-1));

        }


        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
