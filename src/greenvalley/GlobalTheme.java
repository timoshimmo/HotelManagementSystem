/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley;

import javax.swing.plaf.ColorUIResource;
//import javax.swing.plaf.metal.;
import javax.swing.plaf.basic.BasicLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;

/**
 *
 * @author freshfuturesmy
 */
public class GlobalTheme extends MotifLookAndFeel {
    public ColorUIResource getWindowTitleInactiveBackground() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getWindowTitleBackground() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getPrimaryControlHighlight() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getPrimaryControlDarkShadow() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getPrimaryControl() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControlHighlight() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControlDarkShadow() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControl() {
    return new ColorUIResource(java.awt.Color.orange);
  }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNativeLookAndFeel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
