/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.dialogs;

import greenvalley.database.DatabaseConnection;
import greenvalley.util.RoundedJTextField;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author freshfuturesmy
 */
public class NewRoomDialog extends JDialog {
    
    private final JLabel lblDialogTitle;
    private final JTextField txtRoomNo;
    private final JTextField txtRoomType;
    
    private final JButton btnAddRoom;
    
    DefaultTableModel RmTableModel;
    DatabaseConnection dbc;
    
    public NewRoomDialog(final Frame frame, boolean modal, DefaultTableModel tableModel) {
        
        dbc = new DatabaseConnection();
        
        setLayout(new GridBagLayout());
        setBounds(400, 100, 420, 200);
        setTitle("Room");
        
        RmTableModel = tableModel;
        GridBagConstraints nudgbc = new GridBagConstraints();
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 0;
        nudgbc.insets = new Insets(15, 10, 0, 0);
        nudgbc.anchor = GridBagConstraints.NORTHWEST;
        lblDialogTitle = new JLabel("New Room");
        lblDialogTitle.setFont(new Font("Roboto Medium", 1, 18));
        lblDialogTitle.setForeground(new Color(255, 255, 255));
        add(lblDialogTitle, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 1;
        nudgbc.insets = new Insets(15, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtRoomNo = new RoundedJTextField();
        txtRoomNo.setBackground(new Color(27, 27, 27));
        txtRoomNo.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtRoomNo.setForeground(new java.awt.Color(255, 255, 255));
        txtRoomNo.setPreferredSize(new java.awt.Dimension(100, 30));
        txtRoomNo.setCaretColor(new java.awt.Color(255, 255, 255));
        txtRoomNo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtRoomNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                roomNoFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                roomNoFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Room No.", txtRoomNo);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtRoomNo);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtRoomNo);
        
        add(txtRoomNo, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 2;
        nudgbc.insets = new Insets(7, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtRoomType = new RoundedJTextField();
        txtRoomType.setBackground(new Color(27, 27, 27));
        txtRoomType.setFont(new Font("SansSerif", 0, 14)); 
        txtRoomType.setForeground(new Color(255, 255, 255));
        txtRoomType.setPreferredSize(new Dimension(200, 30));
        txtRoomType.setCaretColor(new Color(255, 255, 255));
        txtRoomType.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtRoomType.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                roomTypeFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                roomTypeFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Room Type", txtRoomType);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtRoomType);
        PromptSupport.setForeground(new Color(184, 184, 184), txtRoomType);
        
        add(txtRoomType, nudgbc);
        
        nudgbc.weighty = 1;
        nudgbc.weightx = 0;
        nudgbc.gridx = 0;
        nudgbc.gridy = 3;
        nudgbc.insets = new Insets(15, 0, 25, 0);
        nudgbc.anchor = GridBagConstraints.NORTH; 
        
        btnAddRoom = new RoundedButton("Save");
        btnAddRoom.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAddRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRoom.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        btnAddRoom.addActionListener((ActionEvent ae) -> {
            btnAddRoomActionPerformed(ae);
        });
        
        add(btnAddRoom, nudgbc);
        
        pack();
        setLocationRelativeTo(frame);
        
    }
    
     private void roomNoFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRoomNo.setBackground(new Color(35, 35, 35));
       
    } 
    
     private void roomNoFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRoomNo.setBackground(new Color(27, 27, 27));
       
    } 
     
      private void roomTypeFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRoomType.setBackground(new Color(35, 35, 35));
       
    } 
    
     private void roomTypeFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtRoomType.setBackground(new Color(27, 27, 27));
       
    } 
     
    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {
        
        String rmNum;
        String roomType;
         
        rmNum = txtRoomNo.getText();
        roomType = txtRoomType.getText();
         
        dbc.insertRoom(rmNum, roomType, 0);
        RmTableModel.addRow(new Object[]{roomType, rmNum, "Available", new ImageIcon(getClass().getResource("/images/rounded-remove-button.png"))});
     }
}
