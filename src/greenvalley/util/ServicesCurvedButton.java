/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Optional;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author freshfuturesmy
 */
public class ServicesCurvedButton extends JButton {
    
    private static final double ARC_WIDTH  = 1d;
    private static final double ARC_HEIGHT = 1d;
    protected static final int FOCUS_STROKE = 1;
    protected final Color fc = new Color(30, 30, 30);
    protected final Color ac = new Color(55, 55, 55);
    protected final Color rc = new Color(54, 154, 198);
    protected Shape shape;
    protected Shape border;
    protected Shape base;
    
    public ServicesCurvedButton() {
        super();
    }
    public ServicesCurvedButton(Icon icon) {
        super(icon);
    }
    public ServicesCurvedButton(String text) {
        super(text);
    }
    public ServicesCurvedButton(Action a) {
        super(a);
        //setAction(a);
    }
    public ServicesCurvedButton(String text, Icon icon) {
        super(text, icon);
        //setModel(new DefaultButtonModel());
        //init(text, icon);
        //setContentAreaFilled(false);
        //setBackground(new Color(250, 250, 250));
        //initShape();
    }
    
    @Override 
    public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(30, 30, 30));
      //  setOpaque(false);
        initShape();
    }
    protected void initShape() {
        if (!getBounds().equals(base)) {
            base = getBounds();
            shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
            border = new RoundRectangle2D.Double(FOCUS_STROKE, FOCUS_STROKE,
                                                 getWidth() - FOCUS_STROKE,
                                                 getHeight() - 1 - FOCUS_STROKE * 2,
                                                 ARC_WIDTH, ARC_HEIGHT);
            
            
        }
    }
    private void paintFocusAndRollover(Graphics2D g2, Color color) {
        g2.setPaint(new GradientPaint(0, 0, color, getWidth() - 1, getHeight() - 1, color.brighter(), true));
        g2.fill(shape);
        g2.setPaint(getBackground());
        g2.fill(border);
    }

    @Override protected void paintComponent(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setPaint(ac);
            g2.fill(shape);
        } else if (isRolloverEnabled() && getModel().isRollover()) {
            paintFocusAndRollover(g2, rc);
        } else if (hasFocus()) {
            paintFocusAndRollover(g2, fc);
        } else {
            g2.setPaint(getBackground());
            g2.fill(shape);
        }
        g2.dispose();
        super.paintComponent(g);
    }
    @Override 
    protected void paintBorder(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.draw(shape);
        g2.dispose();
    }
    @Override public boolean contains(int x, int y) {
        initShape();
        //return shape != null && shape.contains(x, y);
        //return Optional.ofNullable(shape).filter(s -> s.contains(x, y)).isPresent();
        return Optional.ofNullable(shape).map(s -> s.contains(x, y)).orElse(false);
    }
    
}
