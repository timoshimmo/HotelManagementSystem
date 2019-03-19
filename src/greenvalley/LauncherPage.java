/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley;

import greenvalley.database.DatabaseConnection;
import greenvalley.util.ImageBackgroundPane;
import greenvalley.util.RoundedPasswordField;
import greenvalley.util.RoundedJTextField;
import greenvalley.util.RoundedButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author freshfuturesmy
 */
public class LauncherPage extends JFrame {
    
    final static String LOGIN_PANEL = "LoginPanel";
    final static String ADMIN_PANEL = "AdminPanel";
    final static String RESET_PANEL = "ResetPanel";
    
    /* Frame widgets...  */
    private JMenuItem menuAdminLogin;
    private JMenuItem menuResetPassword;
    private JMenuItem menuExit;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel adminPanel;
    private JPanel resetPanel;
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuBar launchMenuBar;
    private JLabel lblIcon;
    
    private Color colorTheme;
    private Color textFieldHighlightColor;
    
    /* Login Panel...  */
    private JLabel lblRemember;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rdBtnRemember;
    private JButton btnLogin;
    
    /* Admin Panel...  */
    private JTextField txtAdminID;
    private JPasswordField txtAdminPassword;
    private JButton btnAdminLogin;
    private JLabel lblGoBack;
    
    /*Reset Panel... */
    private JTextField txtResetUsername;
    private JLabel lblInfo;
   // private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JButton btnUpdatePassword;
    private JLabel lblResetGoBack;
    
    private String strFullname;

    Font original;
    DatabaseConnection dbc;
    
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public LauncherPage() {
       // initComponents();
	    
    }
	
    private void initComponents() throws IOException {
        
        ImageBackgroundPane background = new ImageBackgroundPane();
        background.setBackground(ImageIO.read(getClass().getResource("/images/green_valley_launch_back_image.jpg")));
        
        String hotelName = "Green Valley Royal Hotel";
        
        dbc = new DatabaseConnection();
        
        mainPanel = new JPanel();
        loginPanel = new JPanel();
        adminPanel = new JPanel();
        resetPanel = new JPanel();
        launchMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        editMenu = new JMenu();
        lblIcon = new JLabel();
        
        menuAdminLogin = new JMenuItem();
        menuResetPassword = new JMenuItem();
        menuExit = new JMenuItem();
        
        colorTheme = new Color(27, 27, 27);
        textFieldHighlightColor = new Color(35, 35, 35);

	setTitle(hotelName);
        setContentPane(background);
        setPreferredSize(new Dimension(850, 600));
        setMinimumSize(new java.awt.Dimension(850, 600));
 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        GridBagConstraints gbc = new GridBagConstraints(); 
        
        launchMenuBar.setOpaque(true);
        launchMenuBar.setBackground(colorTheme);
        launchMenuBar.setBorder(null);
        launchMenuBar.setFont(new Font("Roboto", 0, 12)); 
        launchMenuBar.setPreferredSize(new java.awt.Dimension(56, 30));

        fileMenu.setForeground(new Color(255, 255, 255));
        fileMenu.setBorder(null);
        fileMenu.setText("File");
        fileMenu.setFont(new Font("Roboto", 0, 12)); 
        fileMenu.setIconTextGap(12);
        launchMenuBar.add(fileMenu);

        editMenu.setForeground(new Color(255, 255, 255));
        editMenu.setText("Edit");
        editMenu.setBorder(null);
        editMenu.setFont(new Font("Roboto", 0, 12)); 
        editMenu.setIconTextGap(12);
        launchMenuBar.add(editMenu);
        
        menuAdminLogin.setFont(new Font("Roboto", 0, 12)); 
        menuAdminLogin.setForeground(new Color(25, 25, 25));
        menuAdminLogin.setText("Admin Login");
        menuAdminLogin.setIconTextGap(20);
        menuAdminLogin.setBorder(null);
        menuAdminLogin.setPreferredSize(new java.awt.Dimension(135, 30));
        menuAdminLogin.addActionListener(this::adminLoginActionPerformed);
        fileMenu.add(menuAdminLogin);

        menuResetPassword.setFont(new Font("Roboto", 0, 12)); 
        menuResetPassword.setForeground(new Color(25, 25, 25));
        menuResetPassword.setText("Forgot Password?");
        menuResetPassword.setIconTextGap(20);
        menuResetPassword.setBorder(null);
        menuResetPassword.setPreferredSize(new Dimension(135, 30));
        menuResetPassword.addActionListener(this::resetPasswordActionPerformed);
        fileMenu.add(menuResetPassword);
  
        menuExit.setFont(new Font("Roboto", 0, 12)); 
        menuExit.setForeground(new Color(25, 25, 25));
        menuExit.setText("Exit");
        menuExit.setIconTextGap(20);
        menuExit.setBorder(null);
        menuExit.setPreferredSize(new Dimension(135, 30));
        menuExit.addActionListener(this::exitMenuActionPerformed);
        fileMenu.add(menuExit);
        
        setJMenuBar(launchMenuBar);
        
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        
        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setPreferredSize(new Dimension(500, 170));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/launch_icon_title.png")));
        add(lblIcon, gbc);
        
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH; 
        
        mainPanel.setLayout(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(850, 400));
        mainPanel.setOpaque(false);
        
        initLoginPanel();
        initAdminLoginPanel();
        initResetPanel();
        mainPanel.add(loginPanel, LOGIN_PANEL);
        mainPanel.add(adminPanel, ADMIN_PANEL);
        mainPanel.add(resetPanel, RESET_PANEL);
         
        add(mainPanel, gbc);

        pack();
        
        String getActiveUser = dbc.getActiveUser();
        
        if(getActiveUser.equals("None")) {
            setVisible(true);
        }
        else {
            new MainPage(getActiveUser).setVisible(true);
           // btnProfile.setText(getActiveUser);
            
        }
	    
    }
    
    private void initLoginPanel() {
       // fdfd
        txtUsername = new RoundedJTextField();
        txtPassword = new RoundedPasswordField();
        rdBtnRemember = new JRadioButton();
        lblRemember = new JLabel();
        
        btnLogin = new RoundedButton("Login");
        
        GridBagConstraints gbcs = new GridBagConstraints(); 
        
       // loginPanel.setPreferredSize(new Dimension(850, 300));
        loginPanel.setOpaque(false);
        loginPanel.setLayout(new GridBagLayout());
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 0;
        gbcs.anchor = GridBagConstraints.NORTH;
        
        txtUsername.setBackground(colorTheme);
        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setPreferredSize(new java.awt.Dimension(300, 33));
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
        
        loginPanel.add(txtUsername, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 1;
        gbcs.insets = new Insets(10, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH;

        txtPassword.setBackground(colorTheme);
        txtPassword.setFont(new Font("SansSerif", 0, 14)); 
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setPreferredSize(new java.awt.Dimension(300, 33));
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
        
        loginPanel.add(txtPassword, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 2;
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        lblRemember.setText("Remember me");
        lblRemember.setFont(new Font("SansSerif", 0, 15)); 
        lblRemember.setForeground(new java.awt.Color(255, 255, 255));
        lblRemember.setPreferredSize(new java.awt.Dimension(300, 40));
        
        loginPanel.add(lblRemember, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 2;
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        rdBtnRemember.setFont(new Font("SansSerif", 0, 15)); 
        rdBtnRemember.setForeground(new java.awt.Color(255, 255, 255));
        rdBtnRemember.setBorder(null);
        rdBtnRemember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/switchOff.png"))); 
        rdBtnRemember.setIconTextGap(15);
        rdBtnRemember.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/switchOn.png"))); 
        rdBtnRemember.setOpaque(false);
        rdBtnRemember.setContentAreaFilled(false);
        rdBtnRemember.setBorderPainted(false);
        rdBtnRemember.setPreferredSize(new java.awt.Dimension(300, 40));
        rdBtnRemember.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        
        loginPanel.add(rdBtnRemember, gbcs);
        
        gbcs.weighty = 1;
        gbcs.gridx = 0;
        gbcs.gridy = 3;
        gbcs.insets = new Insets(40, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        btnLogin.setPreferredSize(new java.awt.Dimension(150, 35));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        
        loginPanel.add(btnLogin, gbcs);
  
    }
    
    private void initAdminLoginPanel() {
        
        txtAdminID = new RoundedJTextField();
        txtAdminPassword = new RoundedPasswordField();
        btnAdminLogin = new RoundedButton("Login");
        lblGoBack = new JLabel();

        GridBagConstraints gbcs = new GridBagConstraints(); 
        
        adminPanel.setOpaque(false);
        adminPanel.setLayout(new GridBagLayout());
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 0;
        gbcs.anchor = GridBagConstraints.NORTH;
        
        txtAdminID.setBackground(colorTheme);
        txtAdminID.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtAdminID.setForeground(new java.awt.Color(255, 255, 255));
        txtAdminID.setPreferredSize(new java.awt.Dimension(300, 33));
        txtAdminID.setCaretColor(new java.awt.Color(255, 255, 255));
        txtAdminID.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtAdminID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminIDFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminIDFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Admin ID", txtAdminID);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtAdminID);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtAdminID);
        
        adminPanel.add(txtAdminID, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 1;
        gbcs.insets = new Insets(10, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH;

        txtAdminPassword.setBackground(colorTheme);
        txtAdminPassword.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtAdminPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtAdminPassword.setPreferredSize(new java.awt.Dimension(300, 33));
        txtAdminPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtAdminPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtAdminPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                adminPasswordFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                adminPasswordFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Password", txtAdminPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtAdminPassword);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtAdminPassword);
        
        adminPanel.add(txtAdminPassword, gbcs);
 
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 2;
        gbcs.insets = new Insets(40, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        btnAdminLogin.setPreferredSize(new java.awt.Dimension(150, 35));
        btnAdminLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnAdminLogin.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        
        btnAdminLogin.addActionListener((ActionEvent ae) -> {
            btnAdminLoginActionPerformed(ae);
        });
        
        adminPanel.add(btnAdminLogin, gbcs);
        
        gbcs.weighty = 1;
        gbcs.gridx = 0;
        gbcs.gridy = 3;
        gbcs.insets = new Insets(70, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH; 
        lblGoBack.setText("Back To Login");
        lblGoBack.setFont(new java.awt.Font("SansSerif", 0, 16)); 
        lblGoBack.setForeground(new java.awt.Color(255, 255, 255));
        lblGoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoBackMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGoBackMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGoBackMouseExited(evt);
            }
        });
        
        adminPanel.add(lblGoBack, gbcs);

    }
    
    
     private void initResetPanel() {
        
        txtResetUsername = new RoundedJTextField();
        txtNewPassword = new RoundedPasswordField();
        lblInfo = new JLabel();
        lblResetGoBack = new JLabel();
        
        btnUpdatePassword = new RoundedButton("Update Password");

        GridBagConstraints gbcs = new GridBagConstraints(); 
        
        resetPanel.setOpaque(false);
        resetPanel.setLayout(new GridBagLayout());
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 0;
        gbcs.anchor = GridBagConstraints.NORTH;
        
        lblInfo.setText("<html><div style='text-align: center;'>Enter your username, your old password (previous password) and new password "
                + "to reset password settings.<br> If you do not remember your old password, refer to admin for help.</div></html>");
        lblInfo.setFont(new java.awt.Font("SansSerif", 1, 12)); 
        lblInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblInfo.setPreferredSize(new java.awt.Dimension(500, 50));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        resetPanel.add(lblInfo, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 1;
        gbcs.insets = new Insets(20, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH;
        
        
        txtResetUsername.setBackground(colorTheme);
        txtResetUsername.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtResetUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtResetUsername.setPreferredSize(new java.awt.Dimension(300, 33));
        txtResetUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        txtResetUsername.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtResetUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                resetUsernameFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                resetUsernameFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Username", txtResetUsername);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtResetUsername);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtResetUsername);
        
        resetPanel.add(txtResetUsername, gbcs);
        
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 2;
        gbcs.anchor = GridBagConstraints.NORTH; 
        gbcs.insets = new Insets(5, 0, 0, 0);
        
        txtNewPassword.setBackground(colorTheme);
        txtNewPassword.setFont(new java.awt.Font("SansSerif", 0, 14)); 
        txtNewPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtNewPassword.setPreferredSize(new java.awt.Dimension(300, 33));
        txtNewPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNewPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtNewPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                resetNewPasswordFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                resetNewPasswordFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("New Password", txtNewPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtNewPassword);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtNewPassword);
        
        resetPanel.add(txtNewPassword, gbcs);
        
        gbcs.weighty = 0;
        gbcs.gridx = 0;
        gbcs.gridy = 3;
        gbcs.insets = new Insets(30, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        btnUpdatePassword.setPreferredSize(new java.awt.Dimension(250, 35));
        btnUpdatePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdatePassword.setFont(new java.awt.Font("SansSerif", 0, 15));
        btnUpdatePassword.addActionListener((ActionEvent ae) -> {
            btnUpdatePasswordActionPerformed(ae);
        });
        
        resetPanel.add(btnUpdatePassword, gbcs);
        
        gbcs.weighty = 1;
        gbcs.gridx = 0;
        gbcs.gridy = 5;
        gbcs.insets = new Insets(30, 0, 0, 0);
        gbcs.anchor = GridBagConstraints.NORTH; 
        
        lblResetGoBack.setText("Back To Login");
        lblResetGoBack.setFont(new java.awt.Font("SansSerif", 0, 16)); 
        lblResetGoBack.setForeground(new java.awt.Color(255, 255, 255));
        lblResetGoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGoBackMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblResetGoBackMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblResetGoBackMouseExited(evt);
            }
        });
        
        resetPanel.add(lblResetGoBack, gbcs);
        
        
    }
    
    
    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtPassword.setBackground(textFieldHighlightColor);
       
    }       
    
    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtPassword.setBackground(colorTheme);
       
    } 
    
    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtUsername.setBackground(textFieldHighlightColor);
       
    }       
    
    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtUsername.setBackground(colorTheme);
       
    } 
    
    private void adminIDFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtAdminID.setBackground(textFieldHighlightColor);
       
    }      
     
    private void adminIDFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtAdminID.setBackground(colorTheme);
       
    } 
    
    private void adminPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtAdminPassword.setBackground(textFieldHighlightColor);
       
    }  
    
    private void adminPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtAdminPassword.setBackground(colorTheme);
       
    } 
    
    
    private void resetUsernameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtResetUsername.setBackground(textFieldHighlightColor);
       
    }      
     
    private void resetUsernameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtResetUsername.setBackground(colorTheme);
       
    } 
   
    
     private void resetNewPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtNewPassword.setBackground(textFieldHighlightColor);
       
    }  
    
    private void resetNewPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtNewPassword.setBackground(colorTheme);
       
    } 

    
     private void lblGoBackMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
         CardLayout cl = (CardLayout)(mainPanel.getLayout());
         cl.show(mainPanel, LOGIN_PANEL);
        
    }      
    
     private void lblGoBackMouseEntered(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        original = evt.getComponent().getFont();
        Map attributes = original.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        evt.getComponent().setFont(original.deriveFont(attributes));
        
        lblGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
    }                                    

    private void lblGoBackMouseExited(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        lblGoBack.setFont(new java.awt.Font("SansSerif", 0, 16)); 
        lblGoBack.setForeground(new java.awt.Color(255, 255, 255));
        
        lblGoBack.setCursor(Cursor.getDefaultCursor());
       
    }      
    
    private void lblResetGoBackMouseEntered(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        original = evt.getComponent().getFont();
        Map attributes = original.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        evt.getComponent().setFont(original.deriveFont(attributes));
        
        lblResetGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }                                    

    private void lblResetGoBackMouseExited(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        lblResetGoBack.setFont(new java.awt.Font("SansSerif", 0, 16)); 
        lblResetGoBack.setForeground(new java.awt.Color(255, 255, 255));
        
        lblResetGoBack.setCursor(Cursor.getDefaultCursor());
    }      
    
    private void adminLoginActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
         CardLayout cl = (CardLayout)(mainPanel.getLayout());
         cl.show(mainPanel, ADMIN_PANEL);
        
    } 
    
    private void resetPasswordActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
         CardLayout cl = (CardLayout)(mainPanel.getLayout());
         cl.show(mainPanel, RESET_PANEL);
        
    } 
    
    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        System.exit(0);
        
    } 
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:

        String username = txtUsername.getText();
        char[] charpass = txtPassword.getPassword();
        String userpass = String.valueOf(charpass);
        String hashedPass =  DigestUtils.sha1Hex(userpass);
        
        if(username.equals("") || userpass.equals("")) {
            JOptionPane.showMessageDialog(this, "<html><font color='white'>Username and password required!</font></html>", 
                "Empty Username/Password", JOptionPane.WARNING_MESSAGE);
        }
        else {
            dbc.userValidation(username, hashedPass);
            int responseValue = dbc.getResponseID();
            
            if(responseValue < 1) {
                 JOptionPane.showMessageDialog(this, "<html><font color='white'>Incorrect Username or Password</font></html>", 
                "Wrong Entry", JOptionPane.ERROR_MESSAGE);
            }
            else {
                
                new MainPage(username).setVisible(true);
                dbc.setUserLoginStatus(1, 0, nowDate(), username);
                this.dispose();
            }
            
        }
        
    }
    
    private void btnAdminLoginActionPerformed(java.awt.event.ActionEvent evt) { 
                
        String userId = txtAdminID.getText();
        char[] charpass = txtAdminPassword.getPassword();
        String userpass = String.valueOf(charpass);
        String hashedPass =  DigestUtils.sha1Hex(userpass);
        
        if(userId.equals("") || userpass.equals("")) {
            JOptionPane.showMessageDialog(this, "<html><font color='white'>AdminId and password required!</font></html>", 
                "Empty Username/Password", JOptionPane.WARNING_MESSAGE);
        }
        else {
            dbc.adminValidation(userId, hashedPass);
            int responseValue = dbc.getAdminResponseID();
            
            
            if(responseValue < 1) {
                
                dbc.adminMasterValidation(userId, hashedPass);
                int masterResValue = dbc.getAdminMasterResponseID();
                
                if(masterResValue < 1) {
                    JOptionPane.showMessageDialog(this, "<html><font color='white'>Incorrect AdminId or Password</font></html>", 
                    "Wrong Entry", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    new AdminPage().setVisible(true);
                    this.dispose();
                }
                
            }
            else {
                new AdminPage().setVisible(true);
                this.dispose();
            }
        }
         
    }
    
    private void btnUpdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {
        
        char[] charNewPass = txtNewPassword.getPassword();
        String NewPass = String.valueOf(charNewPass);
        
        if(txtResetUsername.getText().equals("") || NewPass.equals("")) {
            
            JOptionPane.showMessageDialog(this, "<html><font color='white'>Incomplete field inputs. Fill all the fields and try again</font></html>", 
                    "Incomplete Input", JOptionPane.ERROR_MESSAGE);
            
        }
        
        else {
            
            String hashedPass =  DigestUtils.sha1Hex(NewPass);
            
            int response = JOptionPane.showConfirmDialog(null, "<html><font color='white'>Do you want to checkout?</font></html>",
                "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (response == JOptionPane.YES_OPTION) {
              dbc.setuserPassword(this, NewPass, txtResetUsername.getText());  
            }

        }
    }
     
    public String setFullName(String name) {
        return this.strFullname = name;
    } 
    
    public String getFullName() {
        return this.strFullname;
    }
    
    private static String nowDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }
    
    public static void main(String args[]) throws IOException {
        
        UIManager.put("MenuItem.selectionBackground", new Color(82, 176, 218));
	UIManager.put("MenuItem.selectionForeground", new Color(25, 25, 25));
	UIManager.put("Menu.selectionBackground", new Color(45, 45, 45));
	UIManager.put("Menu.selectionForeground", new Color(255, 255, 255));
	UIManager.put("MenuBar.selectionBackground", new Color(68, 68, 68));
	UIManager.put("MenuBar.selectionForeground", new Color(255, 255, 255));
        UIManager.put("Frame.background", new Color(27, 27, 27));
        UIManager.put("OptionPane.background", new Color(45, 45, 45));
        UIManager.put("OptionPane.foreground", new Color(255, 255, 255));
        UIManager.put("Panel.background", new Color(45, 45, 45));
         
        LauncherPage eui = new LauncherPage();
        eui.initComponents();
         
    }
    
}
