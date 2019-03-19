/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.dialogs;

import greenvalley.database.DatabaseConnection;
import greenvalley.util.RoundedJTextField;
import greenvalley.util.RoundedTextArea;
import greenvalley.util.RoundedButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author freshfuturesmy
 */
public class EditRoomTypeDialog extends JDialog {
    
    private final JLabel lblDialogTitle;
    private final JTextField txtRmType;
    private final JTextField txtRmPrice;
    private final JTextArea txtRmDesc;
    
    private final JButton btnEditRmType;
    
    String getRmType;
    double getRoomPrice;
    String getDesc;
    
    DefaultTableModel RmTableModel;
    DatabaseConnection dbc;
    int selectedRow;
    
    DecimalFormat fmt;
    
    public EditRoomTypeDialog(boolean modal, DefaultTableModel tableModel, int row) {
         
        dbc = new DatabaseConnection();
        
        setLayout(new GridBagLayout());
        setBounds(400, 100, 500, 400);
        setTitle("Rooms");
        
        RmTableModel = tableModel;
        selectedRow = row;
        
        fmt = new DecimalFormat("0.00");
        
        
        GridBagConstraints ertgbc = new GridBagConstraints(); 
        
        getRmType = (String) RmTableModel.getValueAt(row, 0);
        getRoomPrice = (Double) RmTableModel.getValueAt(row, 1);
        getDesc = (String) RmTableModel.getValueAt(row, 2);

        ertgbc.weighty = 0;
        ertgbc.weightx = 1;
        ertgbc.gridx = 0;
        ertgbc.gridy = 0;
        ertgbc.insets = new Insets(15, 10, 0, 0);
        ertgbc.anchor = GridBagConstraints.NORTHWEST;
        lblDialogTitle = new JLabel("Edit Room");
        lblDialogTitle.setFont(new Font("Roboto Medium", 1, 18));
        lblDialogTitle.setForeground(new Color(255, 255, 255));
        add(lblDialogTitle, ertgbc);
        
        ertgbc.weighty = 0;
        ertgbc.weightx = 1;
        ertgbc.gridx = 0;
        ertgbc.gridy = 1;
        ertgbc.insets = new Insets(15, 15, 0, 15);
        ertgbc.anchor = GridBagConstraints.NORTH;
        
        txtRmType = new RoundedJTextField();
        txtRmType.setEnabled(false);
        txtRmType.setBackground(new Color(27, 27, 27));
        txtRmType.setFont(new Font("SansSerif", 0, 14)); 
        txtRmType.setForeground(new Color(255, 255, 255));
        txtRmType.setDisabledTextColor(new Color(255, 255, 255));
        txtRmType.setPreferredSize(new Dimension(200, 30));
        txtRmType.setCaretColor(new Color(255, 255, 255));
        txtRmType.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtRmType.setText(getRmType);
        
        txtRmType.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                rmTypeFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                rmTypeFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Room Type", txtRmType);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtRmType);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtRmType);
        
        add(txtRmType, ertgbc);
        
        ertgbc.weighty = 0;
        ertgbc.weightx = 1;
        ertgbc.gridx = 0;
        ertgbc.gridy = 2;
        ertgbc.insets = new Insets(10, 15, 0, 15);
        ertgbc.anchor = GridBagConstraints.NORTH;
        
        txtRmPrice = new RoundedJTextField();
        txtRmPrice.setBackground(new Color(27, 27, 27));
        txtRmPrice.setFont(new Font("SansSerif", 0, 14)); 
        txtRmPrice.setForeground(new Color(255, 255, 255));
        txtRmPrice.setPreferredSize(new Dimension(100, 30));
        txtRmPrice.setCaretColor(new Color(255, 255, 255));
        txtRmPrice.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        fmt.format(getRoomPrice);
        txtRmPrice.setText(String.valueOf(getRoomPrice));
        
        txtRmPrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                rmPriceFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                rmPriceFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Cost", txtRmPrice);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtRmPrice);
        PromptSupport.setForeground(new Color(184, 184, 184), txtRmPrice);
        
        add(txtRmPrice, ertgbc);
        
        ertgbc.weighty = 0;
        ertgbc.weightx = 1;
        ertgbc.gridx = 0;
        ertgbc.gridy = 3;
        ertgbc.insets = new Insets(10, 15, 0, 15);
        ertgbc.anchor = GridBagConstraints.NORTH;
        
        txtRmDesc = new RoundedTextArea();
        txtRmDesc.setBackground(new Color(27, 27, 27));
        txtRmDesc.setFont(new Font("SansSerif", 0, 13)); 
        txtRmDesc.setForeground(new Color(255, 255, 255));
        txtRmDesc.setPreferredSize(new Dimension(250, 100));
        txtRmDesc.setCaretColor(new Color(255, 255, 255));
        txtRmDesc.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtRmDesc.setLineWrap(true);
        txtRmDesc.setRows(4);
        
        txtRmDesc.setText(getDesc);
        txtRmDesc.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                rmDescFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                rmDescFieldFocusLost(evt);
            }
        
        });
        
        add(txtRmDesc, ertgbc);
        
        ertgbc.weighty = 1;
        ertgbc.weightx = 0;
        ertgbc.gridx = 0;
        ertgbc.gridy = 6;
        ertgbc.insets = new Insets(20, 0, 25, 0);
        ertgbc.anchor = GridBagConstraints.NORTH; 
        
        btnEditRmType = new RoundedButton("Update");
        btnEditRmType.setPreferredSize(new java.awt.Dimension(120, 35));
        btnEditRmType.setForeground(new java.awt.Color(255, 255, 255));
        btnEditRmType.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        btnEditRmType.addActionListener((ActionEvent ae) -> {
            btnUpdateRoomTypeActionPerformed(ae);
        });
        
        add(btnEditRmType, ertgbc);
        
        pack();
       // setLocationRelativeTo(frame);
    }
    
    private void rmTypeFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmType.setBackground(new Color(35, 35, 35)); 
    } 
    
    private void rmTypeFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmType.setBackground(new Color(27, 27, 27));
    } 
    
    private void rmPriceFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmPrice.setBackground(new Color(35, 35, 35)); 
    } 
    
    private void rmPriceFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmPrice.setBackground(new Color(27, 27, 27));
    } 
    
    private void rmDescFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmDesc.setBackground(new Color(35, 35, 35)); 
    } 
    
    private void rmDescFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRmDesc.setBackground(new Color(27, 27, 27));
    } 
    
    private void btnUpdateRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {
        
        String rmType;
        double roomPrice;
        String desc;
        
        rmType = txtRmType.getText();
        roomPrice = Double.valueOf(txtRmPrice.getText());
        desc = txtRmDesc.getText();
        
        dbc.updateRoomTypes(RmTableModel, selectedRow, roomPrice, desc, rmType);
       
        
        this.dispose();
    }
}
