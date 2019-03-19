/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;

/**
 *
 * @author freshfuturesmy
 */
public class NavBarHoverClickRenderer extends DefaultListCellRenderer {
    
    private static final Color HOVER_BACKGROUND_COLOR = new Color(60, 60, 60);
    private static final Color HOVER_FOREGROUND_COLOR = new Color(255, 255, 255);
    private static final Color HOVER_FOREGROUND_DEFAULT_COLOR = new Color(180, 180, 180);
    private static final Color SELECTED_FOREGROUND_COLOR = new Color(93, 191, 230);
    private int hoverIndex = -1;
    private int selectedIndex = -1;
    private MouseAdapter handler;
    private ItemListener selectedHandler;
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
 
        if (!isSelected) {
            setBackground(index == hoverIndex
              ? HOVER_BACKGROUND_COLOR : list.getBackground()); 
            
            setForeground(index == hoverIndex
              ? HOVER_FOREGROUND_COLOR : HOVER_FOREGROUND_DEFAULT_COLOR); 
         
           // setBorder(index == hoverIndex ? BorderFactory.createMatteBorder(0, 0, 3, 0, SELECTED_FOREGROUND_COLOR): 
           //         BorderFactory.createEmptyBorder(0, 0, 0, 0));
         
        }
        
        if (isSelected)  {
             setBackground(index == selectedIndex
              ? list.getBackground() : HOVER_BACKGROUND_COLOR); 
            
            setForeground(index == selectedIndex
              ? HOVER_FOREGROUND_DEFAULT_COLOR : SELECTED_FOREGROUND_COLOR);
            
            setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, SELECTED_FOREGROUND_COLOR));
            
        }
        
        setHorizontalAlignment(SwingConstants.CENTER);
        
        return this;
    }
 
    public MouseAdapter getHandler(JList list) {
        if (handler == null) {
            handler = new HoverMouseHandler(list);
        }
        return handler;
    }
    
    public ItemListener getSelectedHandler(JList mList) {
        if (selectedHandler == null) {
            selectedHandler = new ItemSelectionHandler(mList);
        }
        return selectedHandler;
    }
    
    class HoverMouseHandler extends MouseAdapter {
 
        private final JList list;
 
        public HoverMouseHandler(JList list) {
            this.list = list;
        }
 
        @Override
        public void mouseExited(MouseEvent e) {
            setHoverIndex(-1);
        }
 
        @Override
        public void mouseMoved(MouseEvent e) {
            int index = list.locationToIndex(e.getPoint());
            setHoverIndex(list.getCellBounds(index, index).contains(e.getPoint())
              ? index : -1);
        }
 
        private void setHoverIndex(int index) {
            if (hoverIndex == index) return;
            hoverIndex = index;
            list.repaint();
        }
    }
    
    class ItemSelectionHandler implements ItemListener {
        
        private final JList mList;
 
        public ItemSelectionHandler(JList list) {
            this.mList = list;
        }

        @Override
        public void itemStateChanged(ItemEvent ie) {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                    selectedIndex = mList.getSelectedIndex();
                    mList.repaint();
                 
            }
        
        }
        
    }
}
