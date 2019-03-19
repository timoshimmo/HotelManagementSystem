/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley;

import greenvalley.models.ButtonColumn;
import greenvalley.database.DatabaseConnection;
import greenvalley.models.Checkin;
import greenvalley.dialogs.NewUserDialog;
import greenvalley.dialogs.NewRoomDialog;
import greenvalley.dialogs.EditRoomTypeDialog;
import greenvalley.renderers.MainHoverListCellRenderer;
import greenvalley.renderers.ServicesHoverListCellRenderer;
import greenvalley.util.ServicesCurvedButton;
import greenvalley.util.RoundedButton;
import static greenvalley.MainPage.CAR_RENTAL_SERVICES_PANEL;
import static greenvalley.MainPage.DASHBOARD_PANEL;
import greenvalley.models.Checkout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author freshfuturesmy
 */
public class AdminPage extends JFrame {
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu helpMenu;
    private JMenuBar homeMenuBar;
    
    private JPanel sideBarPanel;
    private JPanel homePanel;
    
    private Color colorTheme;
    private Color colorHome;
    private Color textFieldHighlightColor;
    
    final static String DASHBOARD_PANEL = "DashboardPanel";
    final static String ROOM_TYPE_PANEL = "RoomsTypePanel";
    final static String ROOM_PANEL = "RoomsPanel";
    
    final static String CHECKIN_REPORT_PANEL = "CheckinReportPanel";
    final static String CHECKOUT_REPORT_PANEL = "CheckoutReportPanel";
    final static String CUSTOMER_PANEL = "CustomerPanel";
    
     /* SideBar Widgets  */
    JLabel lblMainTitle;
    JLabel lblReportsTitle;
    JList lisMain;
    JList lisReports;
    
    private JPanel usersPanel;
    private JPanel roomsTypePanel;
    private JPanel roomsPanel;
    private JPanel checkinPanel;
    private JPanel checkoutPanel;
    private JPanel customerPanel;
    private JPanel roomStatusPanel;
    private JPanel checkinReportsPanel;
    private JPanel checkoutReportsPanel;
    
    /* User Reports Widgets */
    private JScrollPane addUsersScrollPane;
    private JTable addUsersTable;
    private JButton btnAddUser;
    
    private NewUserDialog addUserDialog;
    DefaultTableModel usersModel;
    
      /* Room Type Reports Widgets */
    private JScrollPane roomTypeScrollPane;
    private JTable roomTypeTable;
    DefaultTableModel rmTypeModel;
    private JScrollPane roomsScrollPane;
    private JTable roomsTable;
    DefaultTableModel rmsModel;
    private JButton btnAddRoom;
    
    private EditRoomTypeDialog editRoomDialog;
    private NewRoomDialog newRoomDialog;
    
    /* Check In Reports Widgets */
    private JScrollPane reportsScrollPane;
    DefaultTableModel checkinModel;
    private JTable checkinReportsTable;
    private JComboBox cbCheckinFromDate;
    private JComboBox cbCheckinToDate;
    private JLabel lblCheckinDateFrom;
    private JLabel lblCheckinDateTo;
    private JButton btnPrintCheckinReport;
    private String selectedCheckinFromDate = "";
    private String selectedCheckinToDate = "";
    private JButton btnSearchCheckinReport;

    private JScrollPane reportsOutScrollPane;
    DefaultTableModel checkoutModel;
    private JTable checkoutReportsTable;
    private JComboBox cbCheckoutFromDate;
    private JComboBox cbCheckoutToDate;
    private JLabel lblCheckoutDateFrom;
    private JLabel lblCheckoutDateTo;
    private JButton btnSearchCheckoutReport;
    private JButton btnPrintCheckoutReport;
    private String selectedCheckoutFromDate;
    private String selectedCheckoutToDate;  
    
    private JScrollPane customersScrollPane;
    DefaultTableModel customersModel;
    private JTable customersTable;
    
    private JCheckBox cbSelectAllCheckIn;
    private JCheckBox cbSelectAllCheckOut;
    
    DefaultComboBoxModel dcboCheckinFromDates;
    DefaultComboBoxModel dcboCheckinToDates;
    DefaultComboBoxModel dcboCheckoutFromDates;
    DefaultComboBoxModel dcboCheckoutToDates;
    DatabaseConnection dbc;
    
    JFrame checkinReceipt;
    JFrame checkoutReceipt;
    
    StringBuilder builder;
    
    private JTextPane mInTextPane;
    private JTextPane mOutTextPane;
    
    String nairaSymbol;

    public AdminPage() {
       initComponents();    
    }
    
    public final void initComponents() {
        
        String hotelName = "Green Valley Royal Hotel";
        
        dbc = new DatabaseConnection();
        
        homeMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        editMenu = new JMenu();
        viewMenu = new JMenu();
        helpMenu = new JMenu();
        
        sideBarPanel = new JPanel();
        homePanel = new JPanel();
        
        lblMainTitle = new JLabel();
        lblReportsTitle = new JLabel();
        lisMain = new JList();
        lisReports = new JList();
        
        checkinReceipt = new JFrame();

        loadJSONFromResource();
        
        MainHoverListCellRenderer mainHoverRenderer = new MainHoverListCellRenderer();
        ServicesHoverListCellRenderer servicesHoverRenderer = new ServicesHoverListCellRenderer();
        
        colorTheme = new Color(27, 27, 27);
        colorHome = new Color(45, 45, 45);
        textFieldHighlightColor = new Color(35, 35, 35);
        
        setTitle(hotelName);
        setSize(new Dimension(950, 620));
        setMinimumSize(new java.awt.Dimension(950, 620));
        setMaximumSize(new java.awt.Dimension(950, 620));
        getContentPane().setBackground(colorHome);
        setResizable(false);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        homeMenuBar.setOpaque(true);
        homeMenuBar.setBackground(colorTheme);
        homeMenuBar.setBorder(null);
        homeMenuBar.setFont(new Font("Roboto", 0, 12)); 
        homeMenuBar.setPreferredSize(new Dimension(950, 30));

        fileMenu.setForeground(new Color(255, 255, 255));
        fileMenu.setBorder(null);
        fileMenu.setText("File");
        fileMenu.setFont(new Font("Roboto", 0, 12)); 
        fileMenu.setIconTextGap(12);
        homeMenuBar.add(fileMenu);

        editMenu.setForeground(new Color(255, 255, 255));
        editMenu.setText("Edit");
        editMenu.setBorder(null);
        editMenu.setFont(new Font("Roboto", 0, 12)); 
        editMenu.setIconTextGap(12);
        homeMenuBar.add(editMenu);
    
        viewMenu.setForeground(new Color(255, 255, 255));
        viewMenu.setBorder(null);
        viewMenu.setText("View");
        viewMenu.setFont(new Font("Roboto", 0, 12)); 
        viewMenu.setIconTextGap(12);
        homeMenuBar.add(viewMenu);

        helpMenu.setForeground(new Color(255, 255, 255));
        helpMenu.setText("Help");
        helpMenu.setBorder(null);
        helpMenu.setFont(new Font("Roboto", 0, 12)); 
        helpMenu.setIconTextGap(12);
        homeMenuBar.add(helpMenu);
        
        setJMenuBar(homeMenuBar);
        
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        sideBarPanel.setLayout(new GridBagLayout());
        sideBarPanel.setPreferredSize(new Dimension(200, 560));
        sideBarPanel.setMinimumSize(new java.awt.Dimension(200, 560));
        sideBarPanel.setMaximumSize(new java.awt.Dimension(200, 560));
        sideBarPanel.setBackground(colorTheme);
        
        add(sideBarPanel, gbc);
        
        GridBagConstraints sbgbc = new GridBagConstraints();
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        
        homePanel.setLayout(new CardLayout());
        homePanel.setBackground(colorHome);   
        add(homePanel, gbc);
        
        GridBagConstraints hpgbc = new GridBagConstraints();
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 0;
        sbgbc.gridx = 0;
        sbgbc.gridy = 0;
        sbgbc.anchor = GridBagConstraints.NORTHWEST;
        sbgbc.insets = new Insets(10, 20, 0, 0);
        lblMainTitle.setText("MAIN");
        lblMainTitle.setFont(new Font("SansSerif", 0, 12)); 
        lblMainTitle.setForeground(new Color(180, 180, 180));
        sideBarPanel.add(lblMainTitle, sbgbc);
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 0;
        sbgbc.gridx = 0;
        sbgbc.gridy = 1;
        sbgbc.anchor = GridBagConstraints.NORTH;
        sbgbc.insets = new Insets(5, 0, 15, 0);
        
        lisMain.setFont(new Font("SansSerif", 1, 13)); 
        lisMain.setBackground(colorTheme);
        lisMain.setForeground(new Color(180, 180, 180));
        lisMain.setFixedCellHeight(30);
        lisMain.setFixedCellWidth(200);
        lisMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lisMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lisMain.setCellRenderer(mainHoverRenderer);
        lisMain.setBorder(null);
        lisMain.setPreferredSize(new Dimension(200, 60));
        lisMain.setModel(new AbstractListModel<String>() {
            String[] strings = {"     Users", "     Rooms"};
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        lisMain.addMouseListener(mainHoverRenderer.getHandler(lisMain));
        lisMain.addMouseMotionListener(mainHoverRenderer.getHandler(lisMain));
        lisMain.setSelectedIndex(0);
        lisMain.addListSelectionListener((ListSelectionEvent lse) -> {
             if (lisMain.getSelectedIndex() == 0) {
                 CardLayout cl = (CardLayout)(homePanel.getLayout());
                 cl.show(homePanel, DASHBOARD_PANEL);
             }
             if (lisMain.getSelectedIndex() == 1) {
                 CardLayout cl = (CardLayout)(homePanel.getLayout());
                 cl.show(homePanel, ROOM_TYPE_PANEL);
             }

             
            if(lse.getValueIsAdjusting()) {
               lisReports.clearSelection();
            }  
             
        });
        sideBarPanel.add(lisMain, sbgbc);
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 0;
        sbgbc.gridx = 0;
        sbgbc.gridy = 2;
        sbgbc.anchor = GridBagConstraints.NORTHWEST;
        sbgbc.insets = new Insets(10, 20, 0, 0);
        lblReportsTitle.setText("REPORTS");
        lblReportsTitle.setFont(new Font("SansSerif", 0, 12)); 
        lblReportsTitle.setForeground(new Color(180, 180, 180));
        sideBarPanel.add(lblReportsTitle, sbgbc);
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 1;
        sbgbc.gridx = 0;
        sbgbc.gridy = 3;
        sbgbc.anchor = GridBagConstraints.NORTHWEST;
        sbgbc.insets = new Insets(5, 0, 10, 0);
      
        lisReports.setFont(new Font("SansSerif", 1, 13)); 
        lisReports.setBackground(colorTheme);
        lisReports.setForeground(new Color(180, 180, 180));
        lisReports.setFixedCellHeight(30);
        lisReports.setFixedCellWidth(200);
        lisReports.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lisReports.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lisReports.setCellRenderer(servicesHoverRenderer);
        lisReports.setBorder(null);
        lisReports.setPreferredSize(new Dimension(200, 155));
        lisReports.setModel(new AbstractListModel<String>() {
            String[] strings = { "     ChecknIn Report", "     CheckOut Report", 
                "     Customers Report"};
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        lisReports.addMouseListener(servicesHoverRenderer.getHandler(lisReports));
        lisReports.addMouseMotionListener(servicesHoverRenderer.getHandler(lisReports));
        lisReports.addListSelectionListener((ListSelectionEvent lse) -> {
            if(lisReports.getSelectedIndex() == 0) {
                CardLayout cl = (CardLayout)(homePanel.getLayout());
                cl.show(homePanel, CHECKIN_REPORT_PANEL);
            }
            
            if(lisReports.getSelectedIndex() == 1) {
                CardLayout cl = (CardLayout)(homePanel.getLayout());
                cl.show(homePanel, CHECKOUT_REPORT_PANEL);
            }
            
            if(lisReports.getSelectedIndex() == 2) {
                CardLayout cl = (CardLayout)(homePanel.getLayout());
                cl.show(homePanel, CUSTOMER_PANEL);
            }
            
            if(lse.getValueIsAdjusting()) {
               lisMain.clearSelection();
            } 
            
        });
        sideBarPanel.add(lisReports, sbgbc);
        
        initNewUserPanel();
        initRoomsPanel();
        initCheckInReportPanel(); 
        initCheckOutReportPanel();
        initCustomerPanel();
        
        homePanel.add(usersPanel, DASHBOARD_PANEL);
        homePanel.add(roomsTypePanel, ROOM_TYPE_PANEL);
        homePanel.add(checkinReportsPanel, CHECKIN_REPORT_PANEL); 
        homePanel.add(checkoutReportsPanel, CHECKOUT_REPORT_PANEL);
        homePanel.add(customerPanel, CUSTOMER_PANEL);

        pack();
        
    }
    
            
    private void initNewUserPanel() {
        
        usersPanel = new JPanel();
        JLabel lblUserTitle = new JLabel();
        JLabel lblMpUnderline = new JLabel();
        btnAddUser = new JButton();
        
        addUsersScrollPane = new JScrollPane();
        addUsersTable = new JTable();
        
        JLabel lblactiveUser = new JLabel();
        JLabel lblactiveUserValue = new JLabel();
        
        JLabel lblLoginDateTime = new JLabel();
        JLabel lblLoginDateTimeValue = new JLabel();
        
        JLabel lbllastActive = new JLabel();
        JLabel lbllastActiveValue = new JLabel();
        
        JLabel lblLogoutDateTime = new JLabel();
        JLabel lblLogoutDateTimeValue = new JLabel();
        
        String getActiveUser = dbc.getActiveUser();
        String getLastActiveUser = dbc.getLastActiveUser();
        
        String getActiveUserDateTime = dbc.getActiveUserDateTime();
        String getLastActiveUserDateTime = dbc.getLastActiveUserDateTime();
        
        usersPanel.setOpaque(false);
        usersPanel.setLayout(new GridBagLayout());
        usersPanel.setMinimumSize(new Dimension(732, 550));
        usersPanel.setPreferredSize(new Dimension(732, 550));

        GridBagConstraints upgbc = new GridBagConstraints(); 
        
        upgbc.weightx = 1;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 0;
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        upgbc.insets = new Insets(15, 20, 0, 0);
        lblUserTitle.setText("Users");
        lblUserTitle.setForeground(new Color(255, 255, 255));
        lblUserTitle.setFont(new Font("Roboto Medium", 1, 30));
        usersPanel.add(lblUserTitle, upgbc);
        
        upgbc.weightx = 1;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 0;
        upgbc.insets = new Insets(0, 0, 0, 20);
        upgbc.anchor = GridBagConstraints.SOUTHEAST;
        btnAddUser.setIcon(new ImageIcon(getClass().getResource("/images/default_add.png")));
        btnAddUser.setText("Add User");
        btnAddUser.setMaximumSize(new Dimension(90, 40));
        btnAddUser.setMinimumSize(new Dimension(90, 40));
        btnAddUser.setOpaque(false);
        btnAddUser.setBorder(null);
        btnAddUser.setBorderPainted(false);
        btnAddUser.setFocusPainted(false);
        btnAddUser.setFocusable(false);
        btnAddUser.setContentAreaFilled(false);
        btnAddUser.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddUser.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAddUser.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        btnAddUser.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddUser.setForeground(new Color(180, 180, 180));
        btnAddUser.setIconTextGap(7);
        btnAddUser.setFont(new Font("SansSerif", 1, 12)); 
        btnAddUser.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png"))); 
        btnAddUser.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png"))); 
        btnAddUser.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png")));
        btnAddUser.addActionListener((ActionEvent ae) -> {
            btnAddUserActionPerformed(ae);
        });
        btnAddUser.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddUserMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddUserMouseExited(evt);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddUserMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddUserMouseReleased(evt);
            }
        });
        
        usersPanel.add(btnAddUser, upgbc);
        
        upgbc.weightx = 1;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 1;
        upgbc.insets = new Insets(10, 20, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        
        lblMpUnderline.setBackground(new Color(61, 61, 61));
        lblMpUnderline.setText("");
        lblMpUnderline.setOpaque(true);
        lblMpUnderline.setPreferredSize(new Dimension(712, 1));
        lblMpUnderline.setMinimumSize(new Dimension(712, 1));
        usersPanel.add(lblMpUnderline, upgbc); 

//        sas
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 2;
        upgbc.insets = new Insets(10, 20, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblactiveUser.setForeground(new Color(255, 255, 255));
        lblactiveUser.setText("Active User: ");
        lblactiveUser.setFont(new Font("SansSerif", 0, 13)); 
        usersPanel.add(lblactiveUser, upgbc);
        
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 2;
        upgbc.insets = new Insets(10, 130, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblactiveUserValue.setForeground(new Color(255, 255, 255));
        lblactiveUserValue.setText(getActiveUser);
        lblactiveUserValue.setFont(new Font("SansSerif", 1, 13)); 
        usersPanel.add(lblactiveUserValue, upgbc);
        
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 2;
        upgbc.insets = new Insets(10, 200, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblLoginDateTime.setForeground(new Color(255, 255, 255));
        lblLoginDateTime.setText("Login Date & Time: ");
        lblLoginDateTime.setFont(new Font("SansSerif", 0, 13)); 
        usersPanel.add(lblLoginDateTime, upgbc);
        
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 2;
        upgbc.insets = new Insets(10, 330, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblLoginDateTimeValue.setForeground(new Color(255, 255, 255));
        lblLoginDateTimeValue.setText(getActiveUserDateTime);
        lblLoginDateTimeValue.setFont(new Font("SansSerif", 1, 13)); 
        usersPanel.add(lblLoginDateTimeValue, upgbc);
        
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 3;
        upgbc.insets = new Insets(10, 20, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lbllastActive.setForeground(new Color(255, 255, 255));
        lbllastActive.setText("Last Active User: ");
        lbllastActive.setFont(new Font("SansSerif", 0, 13));
        
        usersPanel.add(lbllastActive, upgbc);
        
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 3;
        upgbc.insets = new Insets(10, 130, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lbllastActiveValue.setForeground(new Color(255, 255, 255));
        lbllastActiveValue.setText(getLastActiveUser);
        lbllastActiveValue.setFont(new Font("SansSerif", 1, 13)); 
        
        usersPanel.add(lbllastActiveValue, upgbc);
    
        upgbc.weightx = 0;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 3;
        upgbc.insets = new Insets(10, 200, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblLogoutDateTime.setForeground(new Color(255, 255, 255));
        lblLogoutDateTime.setText("Logout Date & Time: ");
        lblLogoutDateTime.setFont(new Font("SansSerif", 0, 13));
        
        usersPanel.add(lblLogoutDateTime, upgbc);
        
        upgbc.weightx = 1;
        upgbc.weighty = 0;
        upgbc.gridx = 0;
        upgbc.gridy = 3;
        upgbc.insets = new Insets(10, 330, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTHWEST;
        lblLogoutDateTimeValue.setForeground(new Color(255, 255, 255));
        lblLogoutDateTimeValue.setText(getLastActiveUserDateTime);
        lblLogoutDateTimeValue.setFont(new Font("SansSerif", 1, 13)); 
        
        usersPanel.add(lblLogoutDateTimeValue, upgbc);
        
        
        upgbc.weighty = 1;
        upgbc.weightx = 1;
        upgbc.gridx = 0;
        upgbc.gridy = 4;
        upgbc.insets = new Insets(25, 0, 0, 0);
        upgbc.anchor = GridBagConstraints.NORTH;
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
       
        addUsersScrollPane.setPreferredSize(new Dimension(600, 300));
        addUsersScrollPane.setBorder(BorderFactory.createEmptyBorder());
        addUsersScrollPane.getViewport().setBackground(colorHome);
        
        addUsersTable.setBackground(colorHome);
        addUsersTable.setFont(new Font("SansSerif", 0, 14)); 
        addUsersTable.setForeground(new Color(220, 220, 220));
        addUsersTable.setSelectionBackground(new Color(70, 70, 70));
        addUsersTable.setSelectionForeground(new Color(54, 154, 198));
   
        addUsersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
               "   Username   ", "   Firstname   ", "           Lastname           ", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        addUsersTable.setDefaultRenderer(String.class, centerRenderer);
        addUsersTable.setGridColor(new Color(61, 61, 61));
        addUsersTable.setRowHeight(35);
        addUsersTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        addUsersTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        addUsersTable.setShowVerticalLines(false);
        addUsersTable.getTableHeader().setResizingAllowed(true);
        addUsersTable.getTableHeader().setReorderingAllowed(false);
        addUsersTable.getTableHeader().setOpaque(false);
        addUsersTable.getTableHeader().setBackground(colorHome);
        addUsersTable.getTableHeader().setForeground(new Color(180, 180, 180));
        addUsersTable.getTableHeader().setFont(new Font("SansSerif", 1, 14));
        addUsersTable.setRowSelectionAllowed(true);
        addUsersTable.setColumnSelectionAllowed(false);
        addUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        usersModel = (DefaultTableModel) addUsersTable.getModel();
        dbc.retrieveUsers(usersModel);
        
        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 int response = JOptionPane.showConfirmDialog(null, "<html><font color='white'>Are you sure you want to delete user?</font></html>",
                "Delete User?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {                  
                    String uname = (String) usersModel.getValueAt(addUsersTable.getSelectedRow(), 0);
                    dbc.deleteUser(uname);
                    usersModel.removeRow(addUsersTable.getSelectedRow());
                }
  
            }
        };
 
        ButtonColumn buttonColumn = new ButtonColumn(addUsersTable, delete, 3);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        addUsersScrollPane.setViewportView(addUsersTable);
        usersPanel.add(addUsersScrollPane, upgbc);
        
    } 
    
    private void initRoomsPanel() {
            
        roomsTypePanel = new JPanel();
        JLabel lblrmTitle = new JLabel();
        JLabel lblrmTypeUnderline = new JLabel();
     //   JLabel lblroomsUnderline = new JLabel();
        
        roomTypeScrollPane = new JScrollPane();
        roomsScrollPane  = new JScrollPane();
        
        roomTypeTable = new JTable();
        roomsTable = new JTable();

        btnAddRoom = new JButton();
        
        roomsTypePanel.setOpaque(false);
        roomsTypePanel.setLayout(new GridBagLayout());
        roomsTypePanel.setMinimumSize(new Dimension(732, 550));
        roomsTypePanel.setPreferredSize(new Dimension(732, 550));
        
        GridBagConstraints rmtgbc = new GridBagConstraints(); 
        
        rmtgbc.weightx = 1;
        rmtgbc.weighty = 0;
        rmtgbc.gridx = 0;
        rmtgbc.gridy = 0;
        rmtgbc.anchor = GridBagConstraints.NORTHWEST;
        rmtgbc.insets = new Insets(15, 20, 0, 0);
        lblrmTitle.setText("Rooms");
        lblrmTitle.setForeground(new Color(255, 255, 255));
        lblrmTitle.setFont(new Font("Roboto Medium", 1, 30));
        roomsTypePanel.add(lblrmTitle, rmtgbc);
        
        rmtgbc.weightx = 1;
        rmtgbc.weighty = 0;
        rmtgbc.gridx = 0;
        rmtgbc.gridy = 1;
        rmtgbc.insets = new Insets(5, 20, 0, 0);
        rmtgbc.anchor = GridBagConstraints.NORTHWEST;
        
        lblrmTypeUnderline.setBackground(new Color(61, 61, 61));
        lblrmTypeUnderline.setText("");
        lblrmTypeUnderline.setOpaque(true);
        lblrmTypeUnderline.setPreferredSize(new Dimension(712, 1));
        lblrmTypeUnderline.setMinimumSize(new Dimension(712, 1));
        roomsTypePanel.add(lblrmTypeUnderline, rmtgbc); 
        
        rmtgbc.weighty = 0;
        rmtgbc.weightx = 1;
        rmtgbc.gridx = 0;
        rmtgbc.gridy = 2;
        rmtgbc.insets = new Insets(15, 0, 0, 0);
        rmtgbc.anchor = GridBagConstraints.NORTH;
        
        DefaultTableCellRenderer rmCenterRenderer = new DefaultTableCellRenderer();
        rmCenterRenderer.setHorizontalAlignment(SwingConstants.CENTER);
       
        roomTypeScrollPane.setPreferredSize(new Dimension(690, 165));
        roomTypeScrollPane.setBorder(BorderFactory.createEmptyBorder());
        roomTypeScrollPane.getViewport().setBackground(colorHome);
        
        roomTypeTable.setBackground(colorHome);
        roomTypeTable.setFont(new Font("SansSerif", 0, 14)); 
        roomTypeTable.setForeground(new Color(220, 220, 220));
        roomTypeTable.setSelectionBackground(new Color(70, 70, 70));
        roomTypeTable.setSelectionForeground(new Color(54, 154, 198));
   
        roomTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
               "Room Type", "Price", "Description", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        roomTypeTable.setDefaultRenderer(String.class, rmCenterRenderer);
        roomTypeTable.setDefaultRenderer(Double.class, rmCenterRenderer);
        roomTypeTable.setGridColor(new Color(61, 61, 61));
        roomTypeTable.setRowHeight(30); 
        roomTypeTable.setShowVerticalLines(false);
        roomTypeTable.getTableHeader().setResizingAllowed(true);
        roomTypeTable.getTableHeader().setReorderingAllowed(false);
        roomTypeTable.getTableHeader().setOpaque(false);
        roomTypeTable.getTableHeader().setBackground(colorHome);
        roomTypeTable.getTableHeader().setForeground(new Color(180, 180, 180));
        roomTypeTable.getTableHeader().setFont(new Font("SansSerif", 1, 14));
        roomTypeTable.setRowSelectionAllowed(true);
        roomTypeTable.setColumnSelectionAllowed(false);
        roomTypeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        rmTypeModel = (DefaultTableModel) roomTypeTable.getModel();
        dbc.retrieveRoomTypes(rmTypeModel);
        
        Action edit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            int row = roomTypeTable.getSelectedRow();

            editRoomDialog = new EditRoomTypeDialog(true, rmTypeModel, row); 
            editRoomDialog.setVisible(true); 
  
            }
        };
 
        ButtonColumn buttonColumn = new ButtonColumn(roomTypeTable, edit, 3);
        
        roomTypeScrollPane.setViewportView(roomTypeTable);
        roomsTypePanel.add(roomTypeScrollPane, rmtgbc);
        
        rmtgbc.weightx = 1;
        rmtgbc.weighty = 1;
        rmtgbc.gridx = 0;
        rmtgbc.gridy = 3;
        rmtgbc.insets = new Insets(10, 0, 0, 20);
        rmtgbc.anchor = GridBagConstraints.SOUTHEAST;
        btnAddRoom.setIcon(new ImageIcon(getClass().getResource("/images/default_add.png")));
        btnAddRoom.setText("Add Room");
        btnAddRoom.setMaximumSize(new Dimension(90, 40));
        btnAddRoom.setMinimumSize(new Dimension(90, 40));
        btnAddRoom.setOpaque(false);
        btnAddRoom.setBorder(null);
        btnAddRoom.setBorderPainted(false);
        btnAddRoom.setFocusPainted(false);
        btnAddRoom.setFocusable(false);
        btnAddRoom.setContentAreaFilled(false);
        btnAddRoom.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddRoom.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAddRoom.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        btnAddRoom.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddRoom.setForeground(new Color(180, 180, 180));
        btnAddRoom.setIconTextGap(7);
        btnAddRoom.setFont(new Font("SansSerif", 1, 12)); 
        btnAddRoom.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png"))); 
        btnAddRoom.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png"))); 
        btnAddRoom.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/round_add.png")));
        btnAddRoom.addActionListener((ActionEvent ae) -> {
            btnAddRoomActionPerformed(ae);
        });
        btnAddRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddRoomMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddRoomMouseExited(evt);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddRoomMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAddRoomMouseReleased(evt);
            }
        });
        
        roomsTypePanel.add(btnAddRoom, rmtgbc);
        
        rmtgbc.weighty = 1;
        rmtgbc.weightx = 1;
        rmtgbc.gridx = 0;
        rmtgbc.gridy = 4;
        rmtgbc.insets = new Insets(10, 0, 0, 0);
        rmtgbc.anchor = GridBagConstraints.NORTH;
       
        roomsScrollPane.setPreferredSize(new Dimension(690, 225));
        roomsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        roomsScrollPane.getViewport().setBackground(colorHome);
        
        roomsTable.setBackground(colorHome);
        roomsTable.setFont(new Font("SansSerif", 0, 14)); 
        roomsTable.setForeground(new Color(220, 220, 220));
        roomsTable.setSelectionBackground(new Color(70, 70, 70));
        roomsTable.setSelectionForeground(new Color(54, 154, 198));
   
        roomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
               "Room Type", "Room No.", "Booking Status", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        roomsTable.setDefaultRenderer(String.class, rmCenterRenderer);
        roomsTable.setGridColor(new Color(61, 61, 61));
        roomsTable.setRowHeight(30);
        roomsTable.getColumnModel().getColumn(0).setPreferredWidth(223);
        roomsTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        roomsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        roomsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        roomsTable.setShowVerticalLines(false);
        roomsTable.getTableHeader().setReorderingAllowed(false);
        roomsTable.getTableHeader().setOpaque(false);
        roomsTable.getTableHeader().setBackground(colorHome);
        roomsTable.getTableHeader().setForeground(new Color(180, 180, 180));
        roomsTable.getTableHeader().setFont(new Font("SansSerif", 1, 14));
        roomsTable.setRowSelectionAllowed(true);
        roomsTable.setColumnSelectionAllowed(false);
        roomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        roomsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        rmsModel = (DefaultTableModel) roomsTable.getModel();
        dbc.retrieveRooms(rmsModel);
        
        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int response = JOptionPane.showConfirmDialog(null, 
                         "<html><font color='white'>Are you sure you want to delete this room?</font></html>",
                "Delete Room?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {                  
                    String rooms = (String) rmsModel.getValueAt(roomsTable.getSelectedRow(), 1);
                    dbc.deleteRoom(rooms);
                    rmsModel.removeRow(roomsTable.getSelectedRow());
                }
  
            }
        };
 
        ButtonColumn buttonsColumn = new ButtonColumn(roomsTable, delete, 3);
        buttonsColumn.setMnemonic(KeyEvent.VK_D);
        roomsScrollPane.setViewportView(roomsTable);
        roomsTypePanel.add(roomsScrollPane, rmtgbc);
       
    }
    
    private void initCheckInReportPanel() {
        
        checkinReportsPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel lblCheckinReportTitle = new JLabel();
        JLabel lblMRUnderline = new JLabel();
        JLabel reportCategory = new JLabel();
        
        JPanel ciReport = new JPanel();
        JPanel ciReportBtns = new JPanel();
        
        lblCheckinDateFrom = new JLabel();
        lblCheckinDateTo = new JLabel();
        btnPrintCheckinReport = new ServicesCurvedButton();
        btnSearchCheckinReport = new RoundedButton("Search");
        
        lblCheckoutDateFrom = new JLabel();
        lblCheckoutDateTo = new JLabel();
        btnPrintCheckoutReport = new ServicesCurvedButton();
        btnSearchCheckoutReport = new RoundedButton("Search");
        
        dcboCheckinFromDates = new DefaultComboBoxModel();
        updateDcboCheckinFromDates();
        
        dcboCheckinToDates = new DefaultComboBoxModel();
        updateDcboCheckinToDates();
        
        
        checkinReportsPanel.setOpaque(false);
        checkinReportsPanel.setLayout(new GridBagLayout());
        checkinReportsPanel.setMinimumSize(new Dimension(732, 550));
        checkinReportsPanel.setPreferredSize(new Dimension(732, 550));
        
        GridBagConstraints rptpgbc = new GridBagConstraints(); 

        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 0;
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        rptpgbc.insets = new Insets(15, 20, 0, 0);
        lblCheckinReportTitle.setText("Reports");
        lblCheckinReportTitle.setForeground(new Color(255, 255, 255));
        lblCheckinReportTitle.setFont(new Font("Roboto Medium", 1, 30));
        checkinReportsPanel.add(lblCheckinReportTitle, rptpgbc);
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 0;
        rptpgbc.anchor = GridBagConstraints.SOUTHEAST;
        rptpgbc.insets = new Insets(0, 0, 0, 20);
        reportCategory.setText("Check In");
        reportCategory.setForeground(new Color(255, 255, 255));
        reportCategory.setFont(new Font("Roboto Medium", 1, 21));
        checkinReportsPanel.add(reportCategory, rptpgbc);
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 1;
        rptpgbc.insets = new Insets(0, 20, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        lblMRUnderline.setBackground(new Color(61, 61, 61));
        lblMRUnderline.setText("");
        lblMRUnderline.setOpaque(true);
        lblMRUnderline.setPreferredSize(new Dimension(700, 1));
        lblMRUnderline.setMinimumSize(new Dimension(712, 1));
        checkinReportsPanel.add(lblMRUnderline, rptpgbc); 
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 2;
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        rptpgbc.insets = new Insets(15, 20, 10, 0);
        searchPanel.setLayout(new GridBagLayout()); 
        searchPanel.setBackground(new Color(27, 27, 27));
        searchPanel.setMinimumSize(new Dimension(692, 40));
        searchPanel.setPreferredSize(new Dimension(692, 45));
        checkinReportsPanel.add(searchPanel, rptpgbc);
        
        GridBagConstraints spgbc = new GridBagConstraints(); 
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 0;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        lblCheckinDateFrom.setText("From: ");
        lblCheckinDateFrom.setForeground(new Color(255, 255, 255));
        lblCheckinDateFrom.setFont(new Font("SansSerif", 0, 12));
        searchPanel.add(lblCheckinDateFrom, spgbc);
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 0;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 50, 0, 0);
        cbCheckinFromDate = new JComboBox(dcboCheckinFromDates); 
        cbCheckinFromDate.setBackground(colorTheme);
        cbCheckinFromDate.setFont(new Font("SansSerif", 0, 12)); 
        cbCheckinFromDate.setForeground(new Color(255, 255, 255));
        cbCheckinFromDate.setBorder(null); 
        cbCheckinFromDate.setMaximumRowCount(5);
        cbCheckinFromDate.setPreferredSize(new Dimension(180, 25));
        cbCheckinFromDate.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedCheckinFromDate = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
           
        });
        
        searchPanel.add(cbCheckinFromDate, spgbc);
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 1;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        lblCheckinDateTo.setText("To:");
        lblCheckinDateTo.setForeground(new Color(255, 255, 255));
        lblCheckinDateTo.setFont(new Font("SansSerif", 0, 13));
        searchPanel.add(lblCheckinDateTo, spgbc);
         
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 1;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 30, 0, 0);
        cbCheckinToDate = new JComboBox(dcboCheckinToDates);
        cbCheckinToDate.setBackground(colorTheme);
        cbCheckinToDate.setFont(new Font("SansSerif", 0, 13)); 
        cbCheckinToDate.setForeground(new Color(255, 255, 255));
        cbCheckinToDate.setBorder(null); 
        cbCheckinToDate.setMaximumRowCount(5);
        cbCheckinToDate.setPreferredSize(new Dimension(180, 25));
        cbCheckinToDate.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedCheckinToDate = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
            
        });
        
        searchPanel.add(cbCheckinToDate, spgbc);
        
        spgbc.weightx = 1;
        spgbc.weighty = 0;
        spgbc.gridx = 2;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        btnSearchCheckinReport.setPreferredSize(new Dimension(80, 30));
        btnSearchCheckinReport.setForeground(new Color(255, 255, 255));
        btnSearchCheckinReport.setFont(new java.awt.Font("SansSerif", 0, 12)); 
        btnSearchCheckinReport.addActionListener((ActionEvent ae) -> {
             if (selectedCheckinFromDate.equals("Select Date") || selectedCheckinToDate.equals("Select Date") || 
                     selectedCheckinFromDate.equals("") || selectedCheckinToDate.equals("")) {
                    JOptionPane.showMessageDialog(this, "<html><font color='white'>Dates must be selected!</font></html>", 
                "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                } else {
                    searchCheckinReport();
                  
              }  
        });
        searchPanel.add(btnSearchCheckinReport, spgbc);
        
        reportsScrollPane = new JScrollPane();
        checkinReportsTable = new JTable();
        
        rptpgbc.weighty = 0;
        rptpgbc.weightx = 1;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 3;
        rptpgbc.insets = new Insets(5, 0, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTH;
       
        reportsScrollPane.setPreferredSize(new Dimension(692, 345));
        reportsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        reportsScrollPane.getViewport().setBackground(colorHome);

        checkinReportsTable.setBackground(colorHome);
        checkinReportsTable.setFont(new Font("SansSerif", 0, 13)); 
        checkinReportsTable.setForeground(new Color(220, 220, 220));
        checkinReportsTable.setSelectionBackground(new Color(70, 70, 70));
        checkinReportsTable.setSelectionForeground(new Color(54, 154, 198));
   
        checkinReportsTable.setModel(new DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "Receptionist", "Customer", "Room Type", "Room No.", "Price", "Days", "Arrival Date", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        checkinReportsTable.setGridColor(new Color(61, 61, 61));
        checkinReportsTable.setRowHeight(40);
       // reportsTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        checkinReportsTable.setShowVerticalLines(false);
        checkinReportsTable.getTableHeader().setResizingAllowed(true);
        checkinReportsTable.getTableHeader().setReorderingAllowed(false);
        checkinReportsTable.getTableHeader().setOpaque(false);
        checkinReportsTable.getTableHeader().setBackground(colorHome);
        checkinReportsTable.getTableHeader().setForeground(new Color(180, 180, 180));
        checkinReportsTable.getTableHeader().setFont(new Font("SansSerif", 1, 12));
        checkinReportsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        checkinReportsTable.setRowSelectionAllowed(true);
        checkinReportsTable.setColumnSelectionAllowed(false);

        checkinModel = (DefaultTableModel) checkinReportsTable.getModel();
        dbc.retrieveCheckIn(checkinModel);

        reportsScrollPane.setViewportView(checkinReportsTable);
        checkinReportsPanel.add(reportsScrollPane, rptpgbc);
        
        rptpgbc.weighty = 1;
        rptpgbc.weightx = 1;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 4;
        rptpgbc.insets = new Insets(15, 0, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTH;
        ciReport.setLayout(new GridLayout(0, 1));
        ciReport.setPreferredSize(new Dimension(690, 50));
        ciReport.setBackground(colorTheme);
        ciReport.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(180, 180, 180)), 
                "Report Operations", 0, 0, new Font("Roboto Medium", 0, 12), new Color(180, 180, 180)));
        checkinReportsPanel.add(ciReport, rptpgbc);
        
       
        ciReportBtns.setLayout(new GridBagLayout());
        ciReportBtns.setOpaque(true);
        ciReportBtns.setBackground(colorTheme);
        ciReport.add(ciReportBtns);
        
        GridBagConstraints cibgbc = new GridBagConstraints(); 
        
        cibgbc.weightx = 0;
        cibgbc.weighty = 0;
        cibgbc.gridx = 0;
        cibgbc.gridy = 0;
        cibgbc.anchor = GridBagConstraints.WEST;
        cibgbc.insets = new Insets(0, 20, 0, 0);
        btnPrintCheckinReport.setForeground(new Color(255, 255, 255));
        btnPrintCheckinReport.setIcon(new ImageIcon(getClass().getResource("/images/printer.png")));
        btnPrintCheckinReport.setText("Print");
        btnPrintCheckinReport.setFont(new Font("Roboto Medium", 0, 11));
        btnPrintCheckinReport.setIconTextGap(1);
        btnPrintCheckinReport.setMinimumSize(new Dimension(80, 30));
        btnPrintCheckinReport.setMaximumSize(new Dimension(80, 30));
        btnPrintCheckinReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrintCheckinReport.setBorder(null);
        btnPrintCheckinReport.setHorizontalAlignment(SwingConstants.LEADING);
        btnPrintCheckinReport.setVerticalAlignment(SwingConstants.CENTER);
        btnPrintCheckinReport.setVerticalTextPosition(SwingConstants.CENTER);
        btnPrintCheckinReport.setHorizontalTextPosition(SwingConstants.TRAILING);
        btnPrintCheckinReport.addActionListener((ActionEvent ae) -> {
            btnPrintCheckInReport(ae);
        });
        ciReportBtns.add(btnPrintCheckinReport, cibgbc);
        
        cibgbc.weightx = 1;
        cibgbc.weighty = 0;
        cibgbc.gridx = 1;
        cibgbc.gridy = 0;
        cibgbc.anchor = GridBagConstraints.WEST;
        cibgbc.insets = new Insets(0, 30, 0, 0);
        cbSelectAllCheckIn = new JCheckBox("Select All");
        cbSelectAllCheckIn.setBackground(colorTheme);
        cbSelectAllCheckIn.setForeground(new Color(180, 180, 180));
        cbSelectAllCheckIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbSelectAllCheckIn.addItemListener((ItemEvent ie) -> {
            if(ie.getStateChange() == ItemEvent.SELECTED) {
                checkinReportsTable.selectAll(); 
            }
            else {
                checkinReportsTable.clearSelection();
            }
        });
        
        ciReportBtns.add(cbSelectAllCheckIn, cibgbc);

    }
    
    
     private void initCheckOutReportPanel() {
        
        checkoutReportsPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JLabel lblCheckoutReportTitle = new JLabel();
        JLabel lblMRUnderline = new JLabel();
        JLabel reportCategory = new JLabel();
        
        JPanel ciReport = new JPanel();
        JPanel ciReportBtns = new JPanel();
      
        lblCheckoutDateFrom = new JLabel();
        lblCheckoutDateTo = new JLabel();
        btnPrintCheckoutReport = new ServicesCurvedButton();
        btnSearchCheckoutReport = new RoundedButton("Search");
        
        lblCheckoutDateFrom = new JLabel();
        lblCheckoutDateTo = new JLabel();
        btnPrintCheckoutReport = new ServicesCurvedButton();
        btnSearchCheckoutReport = new RoundedButton("Search");
        
        dcboCheckoutFromDates = new DefaultComboBoxModel();
        updateDcboCheckoutFromDates();
        
        dcboCheckoutToDates = new DefaultComboBoxModel();
        updateDcboCheckoutToDates();
        
        checkoutReportsPanel.setOpaque(false);
        checkoutReportsPanel.setLayout(new GridBagLayout());
        checkoutReportsPanel.setMinimumSize(new Dimension(732, 550));
        checkoutReportsPanel.setPreferredSize(new Dimension(732, 550));
        
        GridBagConstraints rptpgbc = new GridBagConstraints(); 

        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 0;
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        rptpgbc.insets = new Insets(15, 20, 0, 0);
        lblCheckoutReportTitle.setText("Reports");
        lblCheckoutReportTitle.setForeground(new Color(255, 255, 255));
        lblCheckoutReportTitle.setFont(new Font("Roboto Medium", 1, 30));
        checkoutReportsPanel.add(lblCheckoutReportTitle, rptpgbc);
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 0;
        rptpgbc.anchor = GridBagConstraints.SOUTHEAST;
        rptpgbc.insets = new Insets(0, 0, 0, 20);
        reportCategory.setText("Check Out");
        reportCategory.setForeground(new Color(255, 255, 255));
        reportCategory.setFont(new Font("Roboto Medium", 1, 21));
        checkoutReportsPanel.add(reportCategory, rptpgbc);
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 1;
        rptpgbc.insets = new Insets(0, 20, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        lblMRUnderline.setBackground(new Color(61, 61, 61));
        lblMRUnderline.setText("");
        lblMRUnderline.setOpaque(true);
        lblMRUnderline.setPreferredSize(new Dimension(700, 1));
        lblMRUnderline.setMinimumSize(new Dimension(712, 1));
        checkoutReportsPanel.add(lblMRUnderline, rptpgbc); 
        
        rptpgbc.weightx = 1;
        rptpgbc.weighty = 0;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 2;
        rptpgbc.anchor = GridBagConstraints.NORTHWEST;
        rptpgbc.insets = new Insets(15, 20, 10, 0);
        searchPanel.setLayout(new GridBagLayout()); 
        searchPanel.setBackground(new Color(27, 27, 27));
        searchPanel.setMinimumSize(new Dimension(692, 40));
        searchPanel.setPreferredSize(new Dimension(692, 45));
        checkoutReportsPanel.add(searchPanel, rptpgbc);
        
        GridBagConstraints spgbc = new GridBagConstraints(); 
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 0;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        lblCheckoutDateFrom.setText("From: ");
        lblCheckoutDateFrom.setForeground(new Color(255, 255, 255));
        lblCheckoutDateFrom.setFont(new Font("SansSerif", 0, 12));
        searchPanel.add(lblCheckoutDateFrom, spgbc);
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 0;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 50, 0, 0);
        cbCheckoutFromDate = new JComboBox(dcboCheckoutFromDates);
        cbCheckoutFromDate.setBackground(colorTheme);
        cbCheckoutFromDate.setFont(new Font("SansSerif", 0, 12)); 
        cbCheckoutFromDate.setForeground(new Color(255, 255, 255));
        cbCheckoutFromDate.setBorder(null); 
        cbCheckoutFromDate.setMaximumRowCount(5);
        cbCheckoutFromDate.setPreferredSize(new Dimension(180, 25));
        cbCheckoutFromDate.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedCheckoutFromDate = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
           
        });
        
        searchPanel.add(cbCheckoutFromDate, spgbc);
        
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 1;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        lblCheckoutDateTo.setText("To:");
        lblCheckoutDateTo.setForeground(new Color(255, 255, 255));
        lblCheckoutDateTo.setFont(new Font("SansSerif", 0, 13));
        searchPanel.add(lblCheckoutDateTo, spgbc);
         
        spgbc.weightx = 0;
        spgbc.weighty = 0;
        spgbc.gridx = 1;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 30, 0, 0);
        cbCheckoutToDate = new JComboBox(dcboCheckoutToDates);
        cbCheckoutToDate.setBackground(colorTheme);
        cbCheckoutToDate.setFont(new Font("SansSerif", 0, 13)); 
        cbCheckoutToDate.setForeground(new Color(255, 255, 255));
        cbCheckoutToDate.setBorder(null); 
        cbCheckoutToDate.setMaximumRowCount(5);
        cbCheckoutToDate.setPreferredSize(new Dimension(180, 25));
        cbCheckoutToDate.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedCheckoutToDate = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
            
        });
        
        searchPanel.add(cbCheckoutToDate, spgbc);
        
        spgbc.weightx = 1;
        spgbc.weighty = 0;
        spgbc.gridx = 2;
        spgbc.gridy = 0;
        spgbc.anchor = GridBagConstraints.WEST;
        spgbc.insets = new Insets(0, 10, 0, 0);
        btnSearchCheckoutReport.setPreferredSize(new Dimension(80, 30));
        btnSearchCheckoutReport.setForeground(new Color(255, 255, 255));
        btnSearchCheckoutReport.setFont(new java.awt.Font("SansSerif", 0, 12)); 
        btnSearchCheckoutReport.addActionListener((ActionEvent ae) -> {
             if (selectedCheckoutFromDate.equals("Select Date") || selectedCheckoutToDate.equals("Select Date") || 
                     selectedCheckoutFromDate.equals("") || selectedCheckoutToDate.equals("")) {
                    JOptionPane.showMessageDialog(this, "<html><font color='white'>Dates must be selected!</font></html>", 
                "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                } else {
                    searchCheckoutReport();
                  
              }  
        });
        searchPanel.add(btnSearchCheckoutReport, spgbc);
        
        reportsOutScrollPane = new JScrollPane();
        checkoutReportsTable = new JTable();
        
        rptpgbc.weighty = 0;
        rptpgbc.weightx = 1;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 3;
        rptpgbc.insets = new Insets(5, 0, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTH;
       
        reportsOutScrollPane.setPreferredSize(new Dimension(692, 345));
        reportsOutScrollPane.setBorder(BorderFactory.createEmptyBorder());
        reportsOutScrollPane.getViewport().setBackground(colorHome);

        checkoutReportsTable.setBackground(colorHome);
        checkoutReportsTable.setFont(new Font("SansSerif", 0, 13)); 
        checkoutReportsTable.setForeground(new Color(220, 220, 220));
        checkoutReportsTable.setSelectionBackground(new Color(70, 70, 70));
        checkoutReportsTable.setSelectionForeground(new Color(54, 154, 198));
   
        checkoutReportsTable.setModel(new DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "Receptionist", "Customer", "Room Type", "Room No.", "Cost", "Check In", "Check Out", "Days", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
                java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        checkoutReportsTable.setGridColor(new Color(61, 61, 61));
        checkoutReportsTable.setRowHeight(40);
       // reportsTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        checkoutReportsTable.setShowVerticalLines(false);
        checkoutReportsTable.getTableHeader().setResizingAllowed(true);
        checkoutReportsTable.getTableHeader().setReorderingAllowed(false);
        checkoutReportsTable.getTableHeader().setOpaque(false);
        checkoutReportsTable.getTableHeader().setBackground(colorHome);
        checkoutReportsTable.getTableHeader().setForeground(new Color(180, 180, 180));
        checkoutReportsTable.getTableHeader().setFont(new Font("SansSerif", 1, 12));
        checkoutReportsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        checkoutReportsTable.setRowSelectionAllowed(true);
        checkoutReportsTable.setColumnSelectionAllowed(false);

        checkoutModel = (DefaultTableModel) checkoutReportsTable.getModel(); 
        dbc.retrieveCheckOut(checkoutModel);

        reportsOutScrollPane.setViewportView(checkoutReportsTable);
        checkoutReportsPanel.add(reportsOutScrollPane, rptpgbc);
        
        rptpgbc.weighty = 1;
        rptpgbc.weightx = 1;
        rptpgbc.gridx = 0;
        rptpgbc.gridy = 4;
        rptpgbc.insets = new Insets(15, 0, 0, 0);
        rptpgbc.anchor = GridBagConstraints.NORTH;
        ciReport.setLayout(new GridLayout(0, 1));
        ciReport.setPreferredSize(new Dimension(690, 50));
        ciReport.setBackground(colorTheme);
        ciReport.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(180, 180, 180)), 
                "Report Operations", 0, 0, new Font("Roboto Medium", 0, 12), new Color(180, 180, 180)));
        checkoutReportsPanel.add(ciReport, rptpgbc);
        
       
        ciReportBtns.setLayout(new GridBagLayout());
        ciReportBtns.setOpaque(true);
        ciReportBtns.setBackground(colorTheme);
        ciReport.add(ciReportBtns);
        
        GridBagConstraints cibgbc = new GridBagConstraints(); 
        
        cibgbc.weightx = 0;
        cibgbc.weighty = 0;
        cibgbc.gridx = 0;
        cibgbc.gridy = 0;
        cibgbc.anchor = GridBagConstraints.WEST;
        cibgbc.insets = new Insets(0, 20, 0, 0);
        btnPrintCheckoutReport.setForeground(new Color(255, 255, 255));
        btnPrintCheckoutReport.setIcon(new ImageIcon(getClass().getResource("/images/printer.png")));
        btnPrintCheckoutReport.setText("Print");
        btnPrintCheckoutReport.setFont(new Font("Roboto Medium", 0, 11));
        btnPrintCheckoutReport.setIconTextGap(1);
        btnPrintCheckoutReport.setMinimumSize(new Dimension(80, 30));
        btnPrintCheckoutReport.setMaximumSize(new Dimension(80, 30));
        btnPrintCheckoutReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrintCheckoutReport.setBorder(null);
        btnPrintCheckoutReport.setHorizontalAlignment(SwingConstants.LEADING);
        btnPrintCheckoutReport.setVerticalAlignment(SwingConstants.CENTER);
        btnPrintCheckoutReport.setVerticalTextPosition(SwingConstants.CENTER);
        btnPrintCheckoutReport.setHorizontalTextPosition(SwingConstants.TRAILING);
        btnPrintCheckoutReport.addActionListener((ActionEvent ae) -> {
            btnPrintCheckOutReport(ae); //sds
        });
        ciReportBtns.add(btnPrintCheckoutReport, cibgbc);
        
        cibgbc.weightx = 1;
        cibgbc.weighty = 0;
        cibgbc.gridx = 1;
        cibgbc.gridy = 0;
        cibgbc.anchor = GridBagConstraints.WEST;
        cibgbc.insets = new Insets(0, 30, 0, 0);
        cbSelectAllCheckOut = new JCheckBox("Select All"); 
        cbSelectAllCheckOut.setBackground(colorTheme);
        cbSelectAllCheckOut.setForeground(new Color(180, 180, 180));
        cbSelectAllCheckOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbSelectAllCheckOut.addItemListener((ItemEvent ie) -> {
            if(ie.getStateChange() == ItemEvent.SELECTED) {
                checkoutReportsTable.selectAll(); 
            }
            else {
                checkoutReportsTable.clearSelection();
            }
        });
        
        ciReportBtns.add(cbSelectAllCheckOut, cibgbc);

    }
     
//     dsfs
             
    private void initCustomerPanel() {
                 
        customerPanel = new JPanel();
        JLabel lblCustomerTitle = new JLabel();
        JLabel lblCpUnderline = new JLabel();
        
        customersScrollPane = new JScrollPane();
        customersTable = new JTable();
        
        customerPanel.setOpaque(false);
        customerPanel.setLayout(new GridBagLayout());
        customerPanel.setMinimumSize(new Dimension(732, 550));
        customerPanel.setPreferredSize(new Dimension(732, 550));

        GridBagConstraints cpgbc = new GridBagConstraints(); 
      
        cpgbc.weightx = 1;
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 0;
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        cpgbc.insets = new Insets(15, 20, 0, 0);
        lblCustomerTitle.setText("Customers");
        lblCustomerTitle.setForeground(new Color(255, 255, 255));
        lblCustomerTitle.setFont(new Font("Roboto Medium", 1, 30));
        customerPanel.add(lblCustomerTitle, cpgbc);

        cpgbc.weightx = 1;
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 1;
        cpgbc.insets = new Insets(10, 20, 0, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        lblCpUnderline.setBackground(new Color(61, 61, 61));
        lblCpUnderline.setText("");
        lblCpUnderline.setOpaque(true);
        lblCpUnderline.setPreferredSize(new Dimension(712, 1));
        lblCpUnderline.setMinimumSize(new Dimension(712, 1));
        customerPanel.add(lblCpUnderline, cpgbc); 
        
        cpgbc.weighty = 1;
        cpgbc.weightx = 1;
        cpgbc.gridx = 0;
        cpgbc.gridy = 2;
        cpgbc.insets = new Insets(25, 0, 0, 0);
        cpgbc.anchor = GridBagConstraints.NORTH;
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
       
        customersScrollPane.setPreferredSize(new Dimension(712, 350));
        customersScrollPane.setBorder(BorderFactory.createEmptyBorder());
        customersScrollPane.getViewport().setBackground(colorHome);
        
        customersTable.setBackground(colorHome);
        customersTable.setFont(new Font("SansSerif", 0, 14)); 
        customersTable.setForeground(new Color(220, 220, 220));
        customersTable.setSelectionBackground(new Color(70, 70, 70));
        customersTable.setSelectionForeground(new Color(54, 154, 198));
   
        customersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
               "Name", "Mobile", "Nationality", "Gender", "Room", "Days"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        customersTable.setDefaultRenderer(String.class, centerRenderer);
        customersTable.setGridColor(new Color(61, 61, 61));
        customersTable.setRowHeight(35);
  //      customersTable.getColumnModel().getColumn(3).setPreferredWidth(30);
  //      customersTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        customersTable.setShowVerticalLines(false);
        customersTable.getTableHeader().setResizingAllowed(true);
        customersTable.getTableHeader().setReorderingAllowed(false);
        customersTable.getTableHeader().setOpaque(false);
        customersTable.getTableHeader().setBackground(colorHome);
        customersTable.getTableHeader().setForeground(new Color(180, 180, 180));
        customersTable.getTableHeader().setFont(new Font("SansSerif", 1, 14));
        customersTable.setRowSelectionAllowed(true);
        customersTable.setColumnSelectionAllowed(false);
        customersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        customersModel = (DefaultTableModel) customersTable.getModel();
        dbc.retrieveCustomers(customersModel);
 
        customersScrollPane.setViewportView(customersTable);
        customerPanel.add(customersScrollPane, cpgbc);
    
    }
    
    private void updateDcboCheckinFromDates() {

        LinkedList<String> lisDt = new LinkedList<>();
        lisDt = dbc.getCheckInDates();
        dcboCheckinFromDates.removeAllElements();
        for (String lisGetDates : lisDt) {
            dcboCheckinFromDates.addElement(lisGetDates);
        }
    }
    
    private void updateDcboCheckinToDates() {

        LinkedList<String> lisDt = new LinkedList<>();
        lisDt = dbc.getCheckInDates();
        dcboCheckinToDates.removeAllElements();
        for (String lisGetDates : lisDt) {
            dcboCheckinToDates.addElement(lisGetDates);
        }
    }
    
     private void updateDcboCheckoutFromDates() {

        LinkedList<String> lisDt = new LinkedList<>();
        lisDt = dbc.getCheckOutDates();
        dcboCheckoutFromDates.removeAllElements();
        for (String lisGetDates : lisDt) {
            dcboCheckoutFromDates.addElement(lisGetDates);
        }
    }
    
    private void updateDcboCheckoutToDates() {

        LinkedList<String> lisDt = new LinkedList<>();
        lisDt = dbc.getCheckOutDates();
        dcboCheckoutToDates.removeAllElements();
        for (String lisGetDates : lisDt) {
            dcboCheckoutToDates.addElement(lisGetDates);
        }
    }

    
    private void btnAddUserMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnAddUser.setForeground(new Color(255, 255, 255));
    } 
    
     private void btnAddUserMouseExited(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        btnAddUser.setForeground(new Color(180, 180, 180));
    }   
     
     
    private void btnAddUserMousePressed(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnAddUser.setForeground(new Color(180, 180, 180));
    }  
     
    private void btnAddUserMouseReleased(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        btnAddUser.setForeground(new Color(255, 255, 255));
    }
            
            
    private void btnAddRoomMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnAddRoom.setForeground(new Color(255, 255, 255));
    } 
    
     private void btnAddRoomMouseExited(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        btnAddRoom.setForeground(new Color(180, 180, 180));
    }   
     
     
    private void btnAddRoomMousePressed(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnAddRoom.setForeground(new Color(180, 180, 180));
    }  
     
    private void btnAddRoomMouseReleased(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        btnAddRoom.setForeground(new Color(255, 255, 255));
    }
    
    
    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) { 
        addUserDialog = new NewUserDialog(this, true, usersModel); 
        addUserDialog.setVisible(true);
    }
    
    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) { 
        newRoomDialog = new NewRoomDialog(this, true, rmsModel); 
        newRoomDialog.setVisible(true);

    }
    
    
    private void searchCheckinReport() {
        
        ArrayList<Checkin> searchData = dbc.searchCheckin(selectedCheckinFromDate, selectedCheckinToDate);
        
        checkinModel.getDataVector().removeAllElements();
        checkinModel.fireTableDataChanged();
        
        Object[] row = new Object[8];
        
        for(int i=0; i < searchData.size(); i++) {
            
            row[0] = searchData.get(i).getUsername();
            row[1] = searchData.get(i).getFullname();
            row[2] = searchData.get(i).getRoomType();
            row[3] = searchData.get(i).getRoomNo();
            row[4] = searchData.get(i).getPrices();
            row[5] = searchData.get(i).getDays();
            row[6] = searchData.get(i).getArrivalDateTime();
            row[7] = searchData.get(i).getTotCost();
            
            checkinModel.addRow(row);
            
        }
    }
    
     private void searchCheckoutReport() {
        
       //  sds
        ArrayList<Checkout> searchData = dbc.searchCheckout(selectedCheckoutFromDate, selectedCheckoutToDate);
        
        checkoutModel.getDataVector().removeAllElements();
        checkoutModel.fireTableDataChanged();
        
        Object[] row = new Object[9];
        
        for(int i=0; i < searchData.size(); i++) {
            
            row[0] = searchData.get(i).getUsername();
            row[1] = searchData.get(i).getFullname();
            row[2] = searchData.get(i).getRoomType();
            row[3] = searchData.get(i).getRoomNo();
            row[4] = searchData.get(i).getCost();
            row[5] = searchData.get(i).getCheckInDateTime();
            row[6] = searchData.get(i).getCheckOutDateTime();
            row[7] = searchData.get(i).getDays();
            row[8] = searchData.get(i).getTotCost();
            
            checkoutModel.addRow(row);
            
        }
    }
    
    private void btnPrintCheckInReport(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        int[] allRows = checkinReportsTable.getSelectedRows();
        checkinReceipt.setTitle("Receipt");
        checkinReceipt.setSize(300, 500);
        checkinReceipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        mInTextPane = new JTextPane();
        mInTextPane.setContentType("text/html");
        
        builder = new StringBuilder();
        builder.append("<h1>CHECKIN REPORT</h1><table width=\"100%\">");
        
        for(int i=0; i < allRows.length; i++) {
            
            builder.append("<tr><td>Receptionist  :</td><td>").append((String)checkinReportsTable.getValueAt(i, 0)).append("</td></tr>");
            builder.append("<tr><td>Customer      :</td><td>").append((String)checkinReportsTable.getValueAt(i, 1)).append("</td></tr>");
            builder.append("<tr><td>Room Type     :</td><td>").append((String)checkinReportsTable.getValueAt(i, 2)).append("</td></tr>");
            builder.append("<tr><td>Room No.      :</td><td>").append((String)checkinReportsTable.getValueAt(i, 3)).append("</td></tr>");
            builder.append("<tr><td>Price         :</td><td>").append(nairaSymbol).append(String.valueOf(checkinReportsTable.getValueAt(i, 4))).append("</td></tr>");
            builder.append("<tr><td>Days          :</td><td>").append(String.valueOf(checkinReportsTable.getValueAt(i, 5))).append("</td></tr>");
            builder.append("<tr><td>Checkin       :</td><td>").append((String)checkinReportsTable.getValueAt(i, 6)).append("</td></tr>");
            builder.append("<tr><td></td><td></td></tr>");
            builder.append("<tr><td>Cost          :</td><td>").append(nairaSymbol).append(String.valueOf(checkinReportsTable.getValueAt(i, 7))).append("</td></tr>");
            builder.append("<tr><td></td><td></td></tr>");
           
        }
        
        mInTextPane.setText(builder.toString());

        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener((ActionEvent ae) -> {
            printCheckinReportActionPerformed(ae);
        });

        panel.add(new JScrollPane(mInTextPane), BorderLayout.CENTER);
        panel.add(previewButton, BorderLayout.SOUTH);
        checkinReceipt.getContentPane().add(panel);

        checkinReceipt.setVisible(true);
            
    }  
    
    private void btnPrintCheckOutReport(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        int[] allRows = checkoutReportsTable.getSelectedRows();
        checkoutReceipt.setTitle("Receipt");
        checkoutReceipt.setSize(300, 500);
        checkoutReceipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        mOutTextPane = new JTextPane();
        mOutTextPane.setContentType("text/html");
        
        builder = new StringBuilder();
        builder.append("<h1>CHECKOUT REPORT</h1><table width=\"100%\">");
        
        for(int i=0; i < allRows.length; i++) {
            builder.append("<tr><td>Receptionist  :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 0)).append("</td></tr>");
            builder.append("<tr><td>Customer      :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 1)).append("</td></tr>");
            builder.append("<tr><td>Room Type     :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 2)).append("</td></tr>");
            builder.append("<tr><td>Room No.      :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 3)).append("</td></tr>");
            builder.append("<tr><td>Cost          :</td><td>").append(nairaSymbol).append((String)checkoutReportsTable.getValueAt(i, 4)).append("</td></tr>");
            builder.append("<tr><td>Checkin       :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 5)).append("</td></tr>");
            builder.append("<tr><td>Checkout      :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 6)).append("</td></tr>");
            builder.append("<tr><td>Days          :</td><td>").append((String)checkoutReportsTable.getValueAt(i, 7)).append("</td></tr>");
            builder.append("<tr><td></td><td></td></tr>");
            builder.append("<tr><td>Total         :</td><td>").append(nairaSymbol).append((String)checkoutReportsTable.getValueAt(i, 8)).append("</td></tr>");
            builder.append("<tr><td></td><td></td></tr>");
        }
        
        mOutTextPane.setText(builder.toString());

        JButton previewButton = new JButton("Preview");
        previewButton.addActionListener((ActionEvent ae) -> {
            printCheckoutReportActionPerformed(ae);
        });

        panel.add(new JScrollPane(mOutTextPane), BorderLayout.CENTER);
        panel.add(previewButton, BorderLayout.SOUTH);
        checkoutReceipt.getContentPane().add(panel);

        checkoutReceipt.setVisible(true);
    }  
    
    private void printCheckinReportActionPerformed(java.awt.event.ActionEvent evt) { 
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(700, 400);
        dialog.setLayout(new BorderLayout());
        
        checkinReceipt.dispose();

        HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        set.add(MediaSizeName.ISO_A4);
        set.add(OrientationRequested.PORTRAIT);
        
        PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

        final PrintPreview preview = new PrintPreview(mInTextPane.getPrintable(null, null), pf);

        JButton printButton = new JButton("Print!");
        printButton.addActionListener((ActionEvent arg0) -> {
            preview.print();
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(printButton);
        buttonsPanel.add(preview.getControls());

        dialog.getContentPane().add(preview, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
    
    private void printCheckoutReportActionPerformed(java.awt.event.ActionEvent evt) { 
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(700, 400);
        dialog.setLayout(new BorderLayout());
        
        checkoutReceipt.dispose();

        HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        set.add(MediaSizeName.ISO_A4);
        set.add(OrientationRequested.PORTRAIT);
        
        PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

        final PrintPreview preview = new PrintPreview(mOutTextPane.getPrintable(null, null), pf);

        JButton printButton = new JButton("Print!");
        printButton.addActionListener((ActionEvent arg0) -> {
            preview.print();
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(printButton);
        buttonsPanel.add(preview.getControls());

        dialog.getContentPane().add(preview, BorderLayout.CENTER);
        dialog.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
    
    public void loadJSONFromResource() {
        
        JSONParser parser = new JSONParser();
        
        try {
            //"resources/json/green_valley_common_currency.json"
            Object obj = parser.parse(new FileReader(getClass().getResource("/json/common_currency.json").getFile()));
            JSONObject jsonObject = (JSONObject) obj;
              
            JSONObject currencyObj = (JSONObject) jsonObject.get("NGN");
            nairaSymbol = (String) currencyObj.get("symbol");
            
            System.out.println(nairaSymbol);
           
        } catch (IOException ex) {
           
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
    
}