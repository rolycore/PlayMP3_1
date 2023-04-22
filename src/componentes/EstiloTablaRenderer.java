/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rojeru San CL
 */
public class EstiloTablaRenderer extends DefaultTableCellRenderer {

    private Component componenete;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componenete = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.       
        this.setHorizontalAlignment(0);
        this.setBorder(null);
//        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(190,190,190)));
        
        if (row % 2 == 0) {
            componenete.setForeground(new Color(9, 129, 169));
            componenete.setBackground(new Color(0, 153, 204));
        } else {
            componenete.setForeground(new Color(0, 153, 204));
            componenete.setBackground(new Color(9, 129, 169));
        }
        if (isSelected) {
            componenete.setForeground(new Color(255,255,255));
            componenete.setBackground(Color.BLACK);
        }
        
//        if(value instanceof JLabel){
//            JLabel lbl = (JLabel)value;
//            return lbl;
//        }
        return componenete;

    }

}
