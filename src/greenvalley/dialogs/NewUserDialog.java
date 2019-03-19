package greenvalley.dialogs;


import greenvalley.database.DatabaseConnection;
import greenvalley.util.RoundedPasswordField;
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
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdesktop.swingx.prompt.PromptSupport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author freshfuturesmy
 */
public class NewUserDialog extends JDialog {
    
    private final JLabel lblDialogTitle;
    private final JTextField txtUsername;
    private final JTextField txtFirstname;
    private final JTextField txtSurname;
    private final JPasswordField txtPassword;
    private final JPasswordField txtConfirmPassword;
    private final JButton btnAddUser;
    
    DefaultTableModel tableMdl;
    DatabaseConnection dbc;
    
    public NewUserDialog(final Frame frame, boolean modal, DefaultTableModel tableModel) {
        
        dbc = new DatabaseConnection();
        
        setLayout(new GridBagLayout());
        setBounds(400, 100, 500, 550);
        setTitle("User");
        
        tableMdl = tableModel;
        GridBagConstraints nudgbc = new GridBagConstraints(); 
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 0;
        nudgbc.insets = new Insets(15, 10, 0, 0);
        nudgbc.anchor = GridBagConstraints.NORTHWEST;
        lblDialogTitle = new JLabel("New User");
        lblDialogTitle.setFont(new Font("Roboto Medium", 1, 18));
        lblDialogTitle.setForeground(new Color(255, 255, 255));
        add(lblDialogTitle, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 1;
        nudgbc.insets = new Insets(15, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtUsername = new RoundedJTextField();
        txtUsername.setBackground(new Color(27, 27, 27));
        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setPreferredSize(new java.awt.Dimension(250, 30));
        txtUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Username", txtUsername);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtUsername);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtUsername);
        
        add(txtUsername, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 2;
        nudgbc.insets = new Insets(10, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtFirstname = new RoundedJTextField();
        txtFirstname.setBackground(new Color(27, 27, 27));
        txtFirstname.setFont(new Font("SansSerif", 0, 14)); 
        txtFirstname.setForeground(new Color(255, 255, 255));
        txtFirstname.setPreferredSize(new Dimension(250, 30));
        txtFirstname.setCaretColor(new Color(255, 255, 255));
        txtFirstname.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtFirstname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnameFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                fnameFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("First Name", txtFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtFirstname);
        PromptSupport.setForeground(new Color(184, 184, 184), txtFirstname);
        
        add(txtFirstname, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 3;
        nudgbc.insets = new Insets(10, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtSurname = new RoundedJTextField();
        txtSurname.setBackground(new Color(27, 27, 27));
        txtSurname.setFont(new Font("SansSerif", 0, 14)); 
        txtSurname.setForeground(new Color(255, 255, 255));
        txtSurname.setPreferredSize(new Dimension(250, 30));
        txtSurname.setCaretColor(new Color(255, 255, 255));
        txtSurname.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtSurname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                surnameFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                surnameFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Surname", txtSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtSurname);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtSurname);
        
        add(txtSurname, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 4;
        nudgbc.insets = new Insets(10, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;

        txtPassword = new RoundedPasswordField();
        txtPassword.setBackground(new Color(27, 27, 27));
        txtPassword.setFont(new Font("SansSerif", 0, 14)); 
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        txtPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Password", txtPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtPassword);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtPassword);
        
        add(txtPassword, nudgbc);
        
        nudgbc.weighty = 0;
        nudgbc.weightx = 1;
        nudgbc.gridx = 0;
        nudgbc.gridy = 5;
        nudgbc.insets = new Insets(10, 15, 0, 15);
        nudgbc.anchor = GridBagConstraints.NORTH;
        
        txtConfirmPassword = new RoundedPasswordField();
        txtConfirmPassword.setBackground(new Color(27, 27, 27));
        txtConfirmPassword.setFont(new Font("SansSerif", 0, 14)); 
        txtConfirmPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtConfirmPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        txtConfirmPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtConfirmPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtConfirmPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Re-enter Password", txtConfirmPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtConfirmPassword);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtConfirmPassword);
        
        add(txtConfirmPassword, nudgbc);

         
        nudgbc.weighty = 1;
        nudgbc.weightx = 0;
        nudgbc.gridx = 0;
        nudgbc.gridy = 6;
        nudgbc.insets = new Insets(20, 0, 25, 0);
        nudgbc.anchor = GridBagConstraints.NORTH; 
        
        btnAddUser = new RoundedButton("Save");
        btnAddUser.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAddUser.setForeground(new java.awt.Color(255, 255, 255));
        btnAddUser.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        btnAddUser.addActionListener((ActionEvent ae) -> {
            btnAddUserActionPerformed(ae);
        });
        
        add(btnAddUser, nudgbc);
        
        pack();
        setLocationRelativeTo(frame);
        
    }
    
    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtUsername.setBackground(new Color(35, 35, 35));
       
    } 
    
     private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtUsername.setBackground(new Color(27, 27, 27));
       
    } 
     
    private void fnameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtFirstname.setBackground(new Color(35, 35, 35));
       
    } 
    
    private void fnameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtFirstname.setBackground(new Color(27, 27, 27));
       
    } 
    
    private void surnameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtSurname.setBackground(new Color(35, 35, 35));
       
    } 
    
    private void surnameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtSurname.setBackground(new Color(27, 27, 27));
       
    } 
    
    
    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtPassword.setBackground(new Color(35, 35, 35));
       
    } 
    
    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtPassword.setBackground(new Color(27, 27, 27));
       
    } 
    
    
    
    private void confirmPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtConfirmPassword.setBackground(new Color(35, 35, 35));
       
    } 
    
    private void confirmPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtConfirmPassword.setBackground(new Color(27, 27, 27));
       
    } 
    
    
    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {
        
        
        char[] charpass = txtPassword.getPassword();
        String userpass = String.valueOf(charpass);
        
        char[] charcpass = txtConfirmPassword.getPassword();
        String usercpass = String.valueOf(charpass);
        
        if(txtUsername.getText().equals("") || txtFirstname.getText().equals("") || txtSurname.getText().equals("") || 
                userpass.equals("") || usercpass.equals("")) {
            
            JOptionPane.showMessageDialog(this, "<html><font color='white'>All fieldsmust be filled to continue</font></html>", 
                    "Incomplete Input", JOptionPane.ERROR_MESSAGE);
            
        }
        
        else {
            
            if(userpass.equals(usercpass)) {
            String hashedPass =  DigestUtils.sha1Hex(userpass);
            dbc.insertUser(txtUsername.getText(), txtFirstname.getText(), txtSurname.getText(), hashedPass);
            tableMdl.addRow(new Object[]{txtUsername.getText(), txtFirstname.getText(), txtSurname.getText(), 
                new ImageIcon(getClass().getResource("/images/rounded-remove-button.png"))});
            
            this.dispose();
            }
            else {
                 JOptionPane.showMessageDialog(this, "<html><font color='white'>The password and re-type password do not match.</font></html>", 
                    "Wrong Password Entry", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
         
    }
    
}
