/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package packagenou.exerciciclase;
import Recursos.Intents;
import javax.swing.table.AbstractTableModel;
import java.util.List;


public class TaulaIntents extends AbstractTableModel {
    private List<Intents> intentsList;
    private final String[] columnNames = { "IdIntent", "IdUsuari", "Inici" };

    public TaulaIntents(List<Intents> intentsList) {
        this.intentsList = intentsList;
    }

    @Override
    public int getRowCount() {
        return intentsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Intents intent = intentsList.get(rowIndex);
        switch (columnIndex) {
            case 0: return intent.getId();
            case 1: return intent.getIdUsuari();
            case 2: return intent.getInici();
            
            default: return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public void setIntentsList(List<Intents> intentsList) {
        this.intentsList = intentsList;
        fireTableDataChanged(); 
    }
}
