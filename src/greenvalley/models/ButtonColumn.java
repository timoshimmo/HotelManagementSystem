package greenvalley.models;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author freshfuturesmy
 */
public class ButtonColumn extends AbstractCellEditor
	implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {
    
    private JTable table;
    private Action action;
    private int mnemonic;
    private Border originalBorder;
    private Border focusBorder;

    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;
    
    public ButtonColumn(JTable table, Action action, int column) {
        this.table = table;
        this.action = action;

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );
      //  editButton.setIcon(new ImageIcon(getClass().getResource("/images/rounded-remove-button.png")));
        originalBorder = editButton.getBorder();
       // setFocusBorder( new LineBorder(Color.BLUE) );

       //new ImageIcon(getClass().getResource("/images/home_user_default.png"))
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
        table.addMouseListener( this );
    }
    
    /**
    *  Get foreground color of the button when the cell has focus
    *
    *  @return the foreground color
    */
    public Border getFocusBorder() {
           return focusBorder;
    }

   /**
    *  The foreground color of the button when the cell has focus
    *
    *  @param focusBorder the foreground color
    */
    public void setFocusBorder(Border focusBorder) {
           this.focusBorder = focusBorder;
           editButton.setBorder( focusBorder );
    }
    
    public int getMnemonic() {
            return mnemonic;
    }

    /**
     *  The mnemonic to activate the button when the cell has focus
     *
     *  @param mnemonic the mnemonic
     */
    public void setMnemonic(int mnemonic) {
            this.mnemonic = mnemonic;
            renderButton.setMnemonic(mnemonic);
            editButton.setMnemonic(mnemonic);
    }

    @Override
    public Object getCellEditorValue() {
        return editorValue;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected)
        {
           renderButton.setForeground(new Color(54, 154, 198));
           renderButton.setBackground(new Color(70, 70, 70));
           renderButton.setBorder(null);
            
        }
        else
        {
           renderButton.setForeground(table.getForeground());
           renderButton.setBackground(new Color(45, 45, 45));
        }

        if (hasFocus)
        {
            renderButton.setBorder(null);
        }
        else
        {
            renderButton.setBorder(null);
        }
        
        if (value == null)
        {
            renderButton.setText( "" );
            renderButton.setIcon( null );
        }
        else if (value instanceof Icon)
        {
            renderButton.setText( "" );
            renderButton.setIcon( (Icon)value );
        }
        else
        {
            renderButton.setText( value.toString() );
            renderButton.setIcon( null );
        }

        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object value, boolean isSelected, int row, int column) {
        if (value == null) {
            editButton.setText( "" );
            editButton.setIcon( null );
        }
        else if (value instanceof Icon) {
            editButton.setText( "" );
            editButton.setIcon( (Icon)value );
        }
        else {
            editButton.setText( value.toString() );
            editButton.setIcon( null );
        }

        this.editorValue = value;
        return editButton;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int row = table.convertRowIndexToModel( table.getEditingRow() );
            fireEditingStopped();

            //  Invoke the Action
            ActionEvent event = new ActionEvent(
                    table,
                    ActionEvent.ACTION_PERFORMED,
                    "" + row);
            action.actionPerformed(event);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (table.isEditing() &&  table.getCellEditor() == this)
	isButtonColumnEditor = true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (isButtonColumnEditor &&  table.isEditing())
            table.getCellEditor().stopCellEditing();

	isButtonColumnEditor = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        renderButton.setForeground(new Color(255, 255, 255));
        renderButton.setBackground(new Color(70, 70, 70));
        renderButton.setBorder(null);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        renderButton.setForeground(new Color(180, 180, 180));
        renderButton.setBackground(new Color(45, 45, 45));
        renderButton.setBorder(null);
    }
    
}
