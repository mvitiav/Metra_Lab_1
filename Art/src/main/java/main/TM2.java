package main;

import main.andrei.Main;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Map;

public class TM2 implements TableModel {
    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex)
        {
            case 0:return "Group:";
            case 1:return "P";
            case 2:return "M";
            case 3:return "C";
            case 4:return "T";
        }
        return null;
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
        if(columnIndex ==0)
        {
            if(rowIndex ==0){return "Vars";}else {return "Count";}
        }else

             if(columnIndex ==1) {
                 int count =0;
                    String ret ="";
                    for (Map.Entry<String, String> entry : Main.varGroup.entrySet()) {
                        if(entry.getValue().indexOf("P")>=0){
                            if(rowIndex==0){
                            ret+= entry.getKey()+"; ";}
                            else {count++; ret = String.valueOf(count);}
                        }
                    }
                    return ret;

                }else
                if(columnIndex ==2) {
                    int count =0;
                    String ret ="";
                    for (Map.Entry<String, String> entry : Main.varGroup.entrySet()) {
                        if(entry.getValue().indexOf("M")>=0){
                            if(rowIndex==0){
                            ret+= entry.getKey()+"; ";}
                            else {count++; ret = String.valueOf(count);}
                        }
                    }
                    return ret;
                }else
                if(columnIndex ==3) {
                    int count =0;
                    String ret ="";
                    for (Map.Entry<String, String> entry : Main.varGroup.entrySet()) {
                        if(entry.getValue().indexOf("C")>=0){
                            if(rowIndex==0){
                                ret+= entry.getKey()+"; ";}
                            else {count++; ret = String.valueOf(count);}
                        }
                    }
                    return ret;
                }else
                if(columnIndex ==4) {
                    int count =0;
                    String ret ="";
                    for (Map.Entry<String, String> entry : Main.varGroup.entrySet()) {
                        if(entry.getValue().indexOf("T")>=0){
                            if(rowIndex==0){
                                ret+= entry.getKey()+"; ";
                            }
                        else {count++; ret = String.valueOf(count);}}
                    }
                    return ret;
                }



        return "";

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
