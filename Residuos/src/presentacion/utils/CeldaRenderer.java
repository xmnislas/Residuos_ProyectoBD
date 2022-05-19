/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.utils;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author xmnislas
 */
public class CeldaRenderer extends DefaultTableCellRenderer{
    private int col = -1;
    
    public CeldaRenderer(int col) {
        this.col = col;
    }
      
    /**
     * Metodo para obtener TableCellRendererComponent 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        //retorna un combo con el valor seleccionado
        if (col == column) {//la columna que tiene el JComboBox
            JComboBox comboBox = new JComboBox();            
            comboBox.addItem(value);                        
            return comboBox;            
        }

        return cellComponent;
    }
}
