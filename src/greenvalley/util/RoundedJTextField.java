/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.util;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

/**
 *
 * @author freshfuturesmy
 */
public class RoundedJTextField extends JTextField {
    
    private Shape shape;
    public RoundedJTextField() {
       // super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    
    }
    @Override
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 3, 3);
         super.paintComponent(g);
    }
    @Override
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 3, 3);
    }
    @Override
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 3, 3);
         }
         return shape.contains(x, y);
    }

    
}
