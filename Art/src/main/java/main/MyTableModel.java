package main;

import main.andrei.Main;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedHashMap;


public class MyTableModel implements TableModel {
    //public class MyTableModel extends AbstractTableModel {  //думал будет проще но придется ебаться с OBSом тк надо автообновление мутить
    public LinkedHashMap<String, Integer> hashMap1;
    public LinkedHashMap<String, Integer> hashMap2;//maybe SortedMap

    public MyTableModel() {
        hashMap1 = new LinkedHashMap();
        hashMap2 = new LinkedHashMap();
    }

    public int getRowCount() {

        return Math.max(hashMap1.size(), hashMap2.size());
    }

    public int getColumnCount() {
        return 6;
    }

    public String getColumnName(int columnIndex) {
        String s = "";
        switch (columnIndex) {
            case 0:
                s = "J";
                break;
            case 1:
                s = "Operator";
                break;
            case 2:
                s = "F1(j)";
                break;
            case 3:
                s = "i";
                break;
            case 4:
                s = "Operand";
                break;
            case 5:
                s = "F2(i)";
                break;
        }
        return s;
    }

    public void recreate(){hashMap1 = new LinkedHashMap();
        hashMap2 = new LinkedHashMap();}

    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex != 1 && columnIndex != 3) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                if (hashMap1.size() > rowIndex) {
                    return rowIndex + 1;
                } else return null;
            case 3:
                if (hashMap2.size() > rowIndex) {
                    return rowIndex + 1;
                } else return null;
            case 1:
                if (hashMap1.size() > rowIndex) {
                    return hashMap1.keySet().toArray()[rowIndex];
                } else return null;
            case 2:
                if (hashMap1.size() > rowIndex) {
                    return hashMap1.get(hashMap1.keySet().toArray()[rowIndex]);
                } else return null;
            case 4:
                if (hashMap2.size() > rowIndex) {
                    return hashMap2.keySet().toArray()[rowIndex];
                } else return null;
            case 5:
                if (hashMap2.size() > rowIndex) {
                    return hashMap2.get(hashMap2.keySet().toArray()[rowIndex]);
                } else return null;
        }
        return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addTableModelListener(TableModelListener l) {

    }

    public void removeTableModelListener(TableModelListener l) {

    }

    public LinkedHashMap<String, Integer> getHashMap1() {
        return hashMap1;
    }

    public void setHashMap1(LinkedHashMap<String, Integer> hashMap1) {
        this.hashMap1 = hashMap1;
    }

    public LinkedHashMap<String, Integer> getHashMap2() {
        return hashMap2;
    }

    public void setHashMap2(LinkedHashMap<String, Integer> hashMap2) {
        this.hashMap2 = hashMap2;
    }

    public void addOperator(String operator) {
        Form.operatorsCount++;
        Main.window.getoPRL().setText(String.valueOf(Form.operatorsCount));
        Main.window.getuOPRL().setText(String.valueOf(hashMap1.size()));

        Main.window.getpDL().setText(String.valueOf(hashMap1.size()+hashMap2.size()));
        Main.window.getpLL().setText(String.valueOf(Form.operatorsCount+Form.operandsCount));
        Main.window.getpVL().setText(String.valueOf(   (int)   ( (Form.operatorsCount+Form.operandsCount) * (Math.log(hashMap1.size()+hashMap2.size()) / Math.log(2)) )    ));



        if (!hashMap1.containsKey(operator)) {
            hashMap1.put(operator, 0);
        }
        hashMap1.put(operator, hashMap1.get(operator) + 1);
    }

    public void addOperand(String operand) {
        Form.operandsCount++;
        Main.window.getpLL().setText(String.valueOf(Form.operatorsCount+Form.operandsCount));
        Main.window.getpVL().setText(String.valueOf(   (int)   ( (Form.operatorsCount+Form.operandsCount) * (Math.log(hashMap1.size()+hashMap2.size()) / Math.log(2)) )    ));

        Main.window.getpDL().setText(String.valueOf(hashMap1.size()+hashMap2.size()));
        if (!hashMap2.containsKey(operand)) {
            hashMap2.put(operand, 0);
        }
        hashMap2.put(operand, hashMap2.get(operand) + 1);
    }

}
