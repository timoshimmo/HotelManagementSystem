/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author freshfuturesmy
 */
public class MyTableRenderers extends DefaultTableCellRenderer implements MouseListener {
    
     @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        setForeground(new Color(180, 180, 180));
        setBackground(new Color(45, 45, 45));
            
        if (isSelected)
        {
            setForeground(new Color(54, 154, 198));
            setBackground(new Color(70, 70, 70));
            setBorder(null);
          //  renderButton.setBackground(new Color(180, 180, 180));
            
        }
        else
        {
            setForeground(new Color(180, 180, 180));
            setBackground(new Color(45, 45, 45));
           // setBorder(null);
        }
        if (hasFocus) {
            setBorder(null);
        }
        
        else {
            setBorder(null);
        }
      
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
         setForeground(new Color(54, 154, 198));
            setBackground(new Color(70, 70, 70));
            setBorder(null);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        setForeground(new Color(255, 255, 255));
        setBackground(new Color(70, 70, 70));
        setBorder(null);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        setForeground(new Color(180, 180, 180));
        setBackground(new Color(45, 45, 45));
        setBorder(null);
    }
    
    
    
}
