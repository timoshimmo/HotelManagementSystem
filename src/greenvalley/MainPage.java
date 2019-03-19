/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley;

import greenvalley.database.DatabaseConnection;
import greenvalley.dialogs.ReceiptDialog;
import greenvalley.renderers.MainHoverListCellRenderer;
import greenvalley.renderers.ServicesHoverListCellRenderer;
import greenvalley.renderers.NavBarHoverClickRenderer;
import greenvalley.util.RoundedJTextField;
import greenvalley.util.ShallowCurvedButton;
import greenvalley.util.ServicesCurvedButton;
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
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdesktop.swingx.prompt.PromptSupport;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author freshfuturesmy
 */
public class MainPage extends JFrame {
    
    /* Frame widgets...  */
    private JMenuItem menuHome;
    private JMenuItem menuProfile;
    private JMenuItem menuFavoutites;
    private JMenuItem menuLogout;
    
    private JPanel sideBarPanel;
    private JPanel homePanel;
    private JPanel contentPanel;
    private JPanel dashboardPanel;
    private JPanel profilePanel;
    private JPanel roomServicePanel;
    
    private JPanel restaurantPanel;
    private JPanel carServicesPanel;
    private JPanel barServicesPanel;
    private JPanel laundryPanel;
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu helpMenu;
    private JMenuBar homeMenuBar;
    
    private Color colorTheme;
    private Color colorHome;
    private Color textFieldHighlightColor;
    
    /*Home Panel Widgets */
    private JToolBar homeToolBar;
    private JButton btnBack;
    private JButton btnProfile;
    
    /*Contents Panel Widgets */
    private JLabel lblTitle;
    private JLabel lblUnderline;
    final static String DASHBOARD_PANEL = "DashboardPanel";
    final static String PROFILE_PANEL = "ProfilePanel";
    
    final static String ROOM_SERVICES_PANEL = "RoomServicesPanel";
    final static String CHECKIN_CUSTOMER_PANEL = "CheckinCustomerPanel";
    final static String CHECKOUT_CUSTOMER_PANEL = "CheckoutCustomerPanel";
    
    
    final static String RESTAURANT_SERVICES_PANEL = "RestaurantServicesPanel";
    final static String CAR_RENTAL_SERVICES_PANEL = "CarRentalServicesPanel";
    final static String LAUNDRY_SERVICES_PANEL = "LaundryServicesPanel";
    final static String BAR_SERVICES_PANEL = "BarServicesPanel";
    
    final static String BOOKING_ROOM_PANEL = "BookingPanel";
    
    /* SideBar Widgets  */
    JLabel lblMainTitle;
    JLabel lblServicesTitle;
    JList lisMain;
    JList lisServices;
    
    /*  Room Services Widgets  */
    private JButton btnEconomyRooms;
    private JButton btnExecutiveRooms;
    private JButton btnDeluxeRooms;
    private JButton btnRoyalDeluxeRooms;
    private JPanel roomsPanel;
    private JPanel roomsContentPanel;
    private JList lisNavBar;
    
    private JPanel checkinRoomPanel;
    private JPanel checkoutRoomPanel;
    
    /*  Book Services Widgets  */
    private JPanel bookPanel;
    private JPanel detailsPanel;
    private JPanel customerPanel;
    private JPanel infoPanel;
    private JLabel lblRoomImg;
    private JLabel lblName;
    private JLabel lblPrice;
    private JLabel lblPerDay;
    private JLabel lblDesc;
    private JLabel lblRmAvailable;
    private JLabel lblFormTitle;
    private JLabel currentDate;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    DecimalFormat fmt;
    
    private JTextField txtSurname;
    private JTextField txtFirstname;
    private JTextField txtMobileNo;
    private JTextField txtDaysNo;
    private JComboBox cbNationality;
    private JComboBox cbGender;
    private JComboBox cbRoomNo;  
    private JComboBox cbDiscount;
    private JButton btnBook;

    NavBarHoverClickRenderer navBarHoverClickRenderer;
    
    private ReceiptDialog receiptDialog;
    
    String selectedNationality;
    String selectedGender;
    String selectedRoom;
    String selectedDiscount = "";
    
    String getFirstName;
    String getSurname;
    String getRoomNo;
    
    String strRoomImageSrc = "";
    String strRmTYpe = "";
    String strRmPrice = "";
    String strRmDsc = "";
    
    String strEconomy = "Economy";
    String strExecutive = "Executive";
    String strDeluxe = "Deluxe";
    String strRoyalDeluxe = "Royal Deluxe";
    
    Date checkinDateTime = null;
    Date nowDateTime = null;
    
    JTextField txtckoFirstname; 
    JTextField txtckoSurname;
    JTextField txtckoMobileNo;
    JTextField txtckoGender;
    JTextField txtckoNationality;

    JTextField txtckoRmType;
    JTextField txtckoRmNo;
    JTextField txtckoRmPrice;
    JTextField txtckoStayDays;
    JTextField txtckoTotCost;

    JTextField txtckoCheckinDate;
    JTextField txtckoSetDeparture;
    JTextField txtckoCheckoutDate;

    JLabel lblDaysSpentValue;
    JLabel lblTotAmtValue;
    
    String getUname;
    
    JLabel lblProfileTitle;
    JLabel lblProfileUnderline;

    JPanel ProfileDetailsPanel; 
    JPanel ProfilePasswordPanel; 

    JLabel lblUsername;
    JTextField txtprUserName;

    JLabel lblFName;
    JTextField txtprFirstName;

    JLabel lblLName;
    JTextField txtprLastName;

    JLabel lblProfileChangePwordTitle;
    JLabel lblProfileChangeUnderline;

    JTextField txtprOldPass;
    JTextField txtprNewPass;
    JTextField txtprConfirmPass;
    JButton btnUpdatePass;
  
  //  TableRowSorter<TableModel> rowSorter; 
    LinkedList<String> lisRoomNos, lisAvailableEconomyRooms, lisAvailableExecutiveRooms, 
            lisAvailableDeluxeRooms, lisAvailableRoyalDeluxeRooms;
    
    DatabaseConnection dbc;
    DefaultComboBoxModel dcboRmNo;

    String printCheckout;
    String nairaSymbol;
    
    StringBuilder builder;
    
    private JTextPane mTextPane;
    
    JFrame checkoutFrame;
    
    
    public MainPage(String uname) {
       getUname = uname;
       initComponents();    
    }
    
    public final void initComponents() {
        
        String hotelName = "Green Valley Royal Hotel";
        
        dbc = new DatabaseConnection();
        fmt = new DecimalFormat("0.00");

        sideBarPanel = new JPanel();
        homePanel = new JPanel();
        contentPanel = new JPanel();
        
        checkoutFrame = new JFrame();
        
        homeMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        editMenu = new JMenu();
        viewMenu = new JMenu();
        helpMenu = new JMenu();
    
        menuHome = new JMenuItem();
        menuProfile = new JMenuItem();
        menuFavoutites = new JMenuItem();
        menuLogout = new JMenuItem();
        
        homeToolBar = new JToolBar();
        
        menuHome.setFont(new Font("Roboto", 0, 12)); 
        menuHome.setForeground(new Color(25, 25, 25));
        menuHome.setText("Home");
        menuHome.setIconTextGap(20);
        menuHome.setBorder(null);
        menuHome.setPreferredSize(new java.awt.Dimension(135, 30));
        fileMenu.add(menuHome);
        
        menuProfile.setFont(new Font("Roboto", 0, 12)); 
        menuProfile.setForeground(new Color(25, 25, 25));
        menuProfile.setText("Profile");
        menuProfile.setIconTextGap(20);
        menuProfile.setBorder(null);
        menuProfile.setPreferredSize(new java.awt.Dimension(135, 30));
        fileMenu.add(menuProfile);
        
        menuFavoutites.setFont(new Font("Roboto", 0, 12)); 
        menuFavoutites.setForeground(new Color(25, 25, 25));
        menuFavoutites.setText("Favourite");
        menuFavoutites.setIconTextGap(20);
        menuFavoutites.setBorder(null);
        menuFavoutites.setPreferredSize(new java.awt.Dimension(135, 30));
        fileMenu.add(menuFavoutites);
        
        menuLogout.setFont(new Font("Roboto", 0, 12)); 
        menuLogout.setForeground(new Color(25, 25, 25));
        menuLogout.setText("Log Out");
        menuLogout.setIconTextGap(20);
        menuLogout.setBorder(null);
        menuLogout.setPreferredSize(new java.awt.Dimension(135, 30));
        menuLogout.addActionListener((ActionEvent ae) -> {
            menuLogoutActionPerformed(ae);
        });
        fileMenu.add(menuLogout);
        
        btnBack = new JButton();
        btnProfile = new JButton();
        
        lblMainTitle = new JLabel();
        lblServicesTitle = new JLabel();
        lisMain = new JList();
        lisServices = new JList();
        
        MainHoverListCellRenderer mainHoverRenderer = new MainHoverListCellRenderer();
        ServicesHoverListCellRenderer servicesHoverRenderer = new ServicesHoverListCellRenderer();
        
        colorTheme = new Color(27, 27, 27);
        colorHome = new Color(45, 45, 45);
        textFieldHighlightColor = new Color(35, 35, 35);
        
        loadJSONFromResource();
        
        dashboardPanel = new JPanel();
        profilePanel = new JPanel();
        roomServicePanel = new JPanel();
        
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
        sideBarPanel.setPreferredSize(new Dimension(200, 600));
        sideBarPanel.setMinimumSize(new java.awt.Dimension(200, 600));
        sideBarPanel.setMaximumSize(new java.awt.Dimension(200, 600));
        sideBarPanel.setBackground(colorTheme);
        
        add(sideBarPanel, gbc);
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        
        homePanel.setLayout(new GridBagLayout());
        homePanel.setBackground(colorHome);
        
        add(homePanel, gbc);
        
        GridBagConstraints hpgbc = new GridBagConstraints();
        
        hpgbc.weightx = 1;
        hpgbc.weighty = 0;
        hpgbc.gridx = 0;
        hpgbc.gridy = 0;
        hpgbc.anchor = GridBagConstraints.NORTH;
        homeToolBar.setLayout(new GridBagLayout());
        homeToolBar.setBackground(colorHome);
        homeToolBar.setBorder(null);
        homeToolBar.setBorderPainted(false);
        homeToolBar.setFocusable(false);
        homeToolBar.setMinimumSize(new Dimension(800, 45));
        homeToolBar.setPreferredSize(new Dimension(800, 45));
        homePanel.add(homeToolBar, hpgbc);
        
        GridBagConstraints tbgbc = new GridBagConstraints();
        
        tbgbc.weightx = 1;
        tbgbc.weighty = 1;
        tbgbc.gridx = 0;
        tbgbc.gridy = 0;
        tbgbc.anchor = GridBagConstraints.WEST;
        tbgbc.insets = new Insets(0, 15, 0, 0);
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow-default.png")));
        btnBack.setPreferredSize(new Dimension(15, 17));
        btnBack.setMaximumSize(new Dimension(15, 17));
        btnBack.setMinimumSize(new Dimension(15, 17));
        btnBack.setOpaque(false);
        btnBack.setBorder(null);
        btnBack.setFocusable(false);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow-default.png"))); 
        btnBack.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow-hover.png"))); 
        btnBack.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow-default.png")));
        btnBack.addActionListener((ActionEvent ae) -> {
            btnBackActionPerformed(ae);
        });
       
        homeToolBar.add(btnBack, tbgbc);
        btnBack.setVisible(false);
        
        tbgbc.weightx = 1;
        tbgbc.weighty = 1;
        tbgbc.gridx = 1;
        tbgbc.gridy = 0;
        tbgbc.anchor = GridBagConstraints.EAST;
        tbgbc.insets = new Insets(0, 0, 0, 30);
        btnProfile.setText(getUname);
        btnProfile.setIcon(new ImageIcon(getClass().getResource("/images/home_user_default.png")));
        btnProfile.setMaximumSize(new Dimension(180, 30));
        btnProfile.setMinimumSize(new Dimension(120, 30));
        btnProfile.setOpaque(false);
        btnProfile.setBorder(null);
        btnProfile.setBorderPainted(false);
        btnProfile.setFocusPainted(false);
        btnProfile.setFocusable(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        btnProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnProfile.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        btnProfile.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProfile.setForeground(new Color(180, 180, 180));
        btnProfile.setIconTextGap(7);
        btnProfile.setFont(new Font("SansSerif", 1, 12)); 
        btnProfile.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_user_default.png"))); 
        btnProfile.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_user_hover.png"))); 
        btnProfile.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_user_default.png")));
        btnProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               // jButton4MouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProfileMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProfileMouseExited(evt);
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnProfileMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnProfileMouseReleased(evt);
            }
        });
        homeToolBar.add(btnProfile, tbgbc);
        
        hpgbc.weightx = 1;
        hpgbc.weighty = 1;
        hpgbc.gridx = 0;
        hpgbc.gridy = 1;
        hpgbc.anchor = GridBagConstraints.CENTER;
        contentPanel.setLayout(new CardLayout());
        contentPanel.setOpaque(false);
       
        GridBagConstraints sbgbc = new GridBagConstraints();
       
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
            String[] strings = { "     Home", "     Profile"};
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
                 if(homeToolBar.isVisible() == false) {
                     homeToolBar.setVisible(true);
                     
                    if(btnBack.isVisible() == true) {
                        btnBack.setVisible(false);
                    } 
                 } 
                 CardLayout cl = (CardLayout)(contentPanel.getLayout());
                 cl.show(contentPanel, DASHBOARD_PANEL);
             }
             if (lisMain.getSelectedIndex() == 1) {
                if(homeToolBar.isVisible() == false) {
                     homeToolBar.setVisible(true);
                     if(btnBack.isVisible() == true) {
                        btnBack.setVisible(false);
                    } 
                 } 
                 CardLayout cl = (CardLayout)(contentPanel.getLayout());
                 cl.show(contentPanel, PROFILE_PANEL);
             }
             
            if(lse.getValueIsAdjusting()) {
                lisServices.clearSelection();
            } 
             
        });
        sideBarPanel.add(lisMain, sbgbc);
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 0;
        sbgbc.gridx = 0;
        sbgbc.gridy = 2;
        sbgbc.anchor = GridBagConstraints.NORTHWEST;
        sbgbc.insets = new Insets(10, 20, 0, 0);
        lblServicesTitle.setText("SERVICES");
        lblServicesTitle.setFont(new Font("SansSerif", 0, 12)); 
        lblServicesTitle.setForeground(new Color(180, 180, 180));
        sideBarPanel.add(lblServicesTitle, sbgbc);
        
        sbgbc.weightx = 1;
        sbgbc.weighty = 1;
        sbgbc.gridx = 0;
        sbgbc.gridy = 3;
        sbgbc.anchor = GridBagConstraints.NORTHWEST;
        sbgbc.insets = new Insets(5, 0, 10, 0);
      
        lisServices.setFont(new Font("SansSerif", 1, 13)); 
        lisServices.setBackground(colorTheme);
        lisServices.setForeground(new Color(180, 180, 180));
        lisServices.setFixedCellHeight(30);
        lisServices.setFixedCellWidth(200);
        lisServices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lisServices.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lisServices.setCellRenderer(servicesHoverRenderer);
        lisServices.setBorder(null);
        lisServices.setPreferredSize(new Dimension(200, 155));
        lisServices.setModel(new AbstractListModel<String>() {
            String[] strings = { "     Room Service", "     Restaurant Service", 
                "     Bar Service", "     Car Rental", "     Laundry Service"};
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        lisServices.addMouseListener(servicesHoverRenderer.getHandler(lisServices));
        lisServices.addMouseMotionListener(servicesHoverRenderer.getHandler(lisServices));
        lisServices.addListSelectionListener((ListSelectionEvent lse) -> {
            if(lisServices.getSelectedIndex() == 0) {
                
                
                if(homeToolBar.isVisible() == false) {
                    homeToolBar.setVisible(true);
                    
                    if(btnBack.isVisible() == false) {
                        btnBack.setVisible(true);
                    } 
                } 
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, ROOM_SERVICES_PANEL);
            }
            
            if(lisServices.getSelectedIndex() == 1) {
                if(homeToolBar.isVisible() == true) {
                     homeToolBar.setVisible(false);
                } 
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, RESTAURANT_SERVICES_PANEL);
                
            }
            
            if(lisServices.getSelectedIndex() == 2) {
                 if(homeToolBar.isVisible() == true) {
                     homeToolBar.setVisible(false);
                } 
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, BAR_SERVICES_PANEL);
            }
            
            if(lisServices.getSelectedIndex() == 3) {
                 if(homeToolBar.isVisible() == true) {
                     homeToolBar.setVisible(false);
                } 
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, CAR_RENTAL_SERVICES_PANEL);
            }
            
            if(lisServices.getSelectedIndex() == 4) {
                 if(homeToolBar.isVisible() == true) {
                     homeToolBar.setVisible(false);
                } 
                CardLayout cl = (CardLayout)(contentPanel.getLayout());
                cl.show(contentPanel, LAUNDRY_SERVICES_PANEL);
            }
            
            if(lse.getValueIsAdjusting()) {
               lisMain.clearSelection(); 
            }  
            
        });
        
        sideBarPanel.add(lisServices, sbgbc);
     
        navBarHoverClickRenderer = new NavBarHoverClickRenderer();
        
        initDashboardPanel();
        initProfilePanel();
        initRoomServicePanel();
        initCheckoutPanel();
        initRoomBookingPanel();
        initRestaurantServicePanel();
        initbarServicesPanel();
        initcarServicesPanel();
        initlaundryServicesPanel();

        contentPanel.add(dashboardPanel, DASHBOARD_PANEL);
        contentPanel.add(profilePanel, PROFILE_PANEL);
        contentPanel.add(roomServicePanel, ROOM_SERVICES_PANEL);
        contentPanel.add(bookPanel, BOOKING_ROOM_PANEL);
        contentPanel.add(restaurantPanel, RESTAURANT_SERVICES_PANEL);
        contentPanel.add(barServicesPanel, BAR_SERVICES_PANEL); 
        contentPanel.add(carServicesPanel, CAR_RENTAL_SERVICES_PANEL); 
        contentPanel.add(laundryPanel, LAUNDRY_SERVICES_PANEL); 

        homePanel.add(contentPanel, hpgbc);
        
        pack();
  
    }
    
    private void initDashboardPanel() {
        
        JLabel lblHomeTitle = new JLabel();
        JLabel lblHomeUnderline = new JLabel();
        JLabel lblCheckinTitle = new JLabel();
        
        JLabel lblCheckoutTitle = new JLabel();
        JLabel lblNextBodyUnderline = new JLabel();
        
        JScrollPane userCheckinPane = new JScrollPane();
        JTable userCheckinTable = new JTable();
        
        JScrollPane userCheckoutPane = new JScrollPane();
        JTable userCheckoutTable = new JTable();

        DefaultTableModel userCheckinModel;
        DefaultTableModel userCheckoutModel;
        
        dashboardPanel.setOpaque(false);
        dashboardPanel.setLayout(new GridBagLayout());
      
        GridBagConstraints dpgb = new GridBagConstraints(); 
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 0;
        dpgb.anchor = GridBagConstraints.SOUTHWEST;
        dpgb.insets = new Insets(15, 25, 0, 0);
        
        lblHomeTitle.setText("Dashboard");
        lblHomeTitle.setForeground(new Color(255, 255, 255));
        lblHomeTitle.setFont(new Font("Roboto Medium", 1, 30));
        dashboardPanel.add(lblHomeTitle, dpgb);
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 0;
        dpgb.anchor = GridBagConstraints.SOUTHEAST;
        dpgb.insets = new Insets(15, 0, 0, 25);
        
        lblCheckinTitle.setText("Check In");
        lblCheckinTitle.setForeground(new Color(255, 255, 255));
        lblCheckinTitle.setFont(new Font("Roboto Medium", 1, 21));
        dashboardPanel.add(lblCheckinTitle, dpgb);
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 1;
        dpgb.insets = new Insets(5, 25, 0, 0);
        dpgb.anchor = GridBagConstraints.NORTHWEST;
        lblHomeUnderline.setBackground(new Color(61, 61, 61));
        lblHomeUnderline.setText("");
        lblHomeUnderline.setOpaque(true);
        lblHomeUnderline.setPreferredSize(new Dimension(790, 1));
    
        dashboardPanel.add(lblHomeUnderline, dpgb);
        
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 2;
        dpgb.insets = new Insets(10, 0, 0, 0);
        dpgb.anchor = GridBagConstraints.NORTH;
        userCheckinPane.setPreferredSize(new Dimension(770, 180));
        userCheckinPane.setBorder(BorderFactory.createEmptyBorder());
        userCheckinPane.getViewport().setBackground(colorHome);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        userCheckinTable.setBackground(colorHome);
        userCheckinTable.setFont(new Font("SansSerif", 0, 13)); 
        userCheckinTable.setForeground(new Color(220, 220, 220));
        userCheckinTable.setSelectionBackground(new Color(70, 70, 70));
        userCheckinTable.setSelectionForeground(new Color(54, 154, 198));
   
        userCheckinTable.setModel(new DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "Fullname", "Room Type", "Room No.", "Price", "Days", "Checkin", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        
        userCheckinTable.setDefaultRenderer(Double.class, centerRenderer);
        userCheckinTable.setDefaultRenderer(Integer.class, centerRenderer);
        userCheckinTable.setGridColor(new Color(61, 61, 61));
        userCheckinTable.setRowHeight(40);
        userCheckinTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        userCheckinTable.setShowVerticalLines(false);
        userCheckinTable.getTableHeader().setReorderingAllowed(false);
        userCheckinTable.getTableHeader().setOpaque(false);
        userCheckinTable.getTableHeader().setBackground(colorHome);
        userCheckinTable.getTableHeader().setForeground(new Color(180, 180, 180));
        userCheckinTable.getTableHeader().setFont(new Font("SansSerif", 1, 12));
        userCheckinTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userCheckinTable.setRowSelectionAllowed(true);
        userCheckinTable.setColumnSelectionAllowed(false);
        
        userCheckinModel = (DefaultTableModel) userCheckinTable.getModel();
        dbc.retrieveUserCheckIn(userCheckinModel, btnProfile.getText());
        
        userCheckinPane.setViewportView(userCheckinTable);
        dashboardPanel.add(userCheckinPane, dpgb);
        
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 3;
        dpgb.anchor = GridBagConstraints.SOUTHEAST;
        dpgb.insets = new Insets(10, 0, 0, 25);
        
        lblCheckoutTitle.setText("Check Out");
        lblCheckoutTitle.setForeground(new Color(255, 255, 255));
        lblCheckoutTitle.setFont(new Font("Roboto Medium", 1, 21));
        dashboardPanel.add(lblCheckoutTitle, dpgb);
        
        dpgb.weightx = 1;
        dpgb.weighty = 0;
        dpgb.gridx = 0;
        dpgb.gridy = 4;
        dpgb.insets = new Insets(5, 25, 0, 0);
        dpgb.anchor = GridBagConstraints.NORTHWEST;
        lblNextBodyUnderline.setBackground(new Color(61, 61, 61));
        lblNextBodyUnderline.setText("");
        lblNextBodyUnderline.setOpaque(true);
        lblNextBodyUnderline.setPreferredSize(new Dimension(790, 1));
    
        dashboardPanel.add(lblNextBodyUnderline, dpgb);

        dpgb.weightx = 1;
        dpgb.weighty = 1;
        dpgb.gridx = 0;
        dpgb.gridy = 5;
        dpgb.insets = new Insets(10, 0, 0, 0);
        dpgb.anchor = GridBagConstraints.NORTH;
        userCheckoutPane.setPreferredSize(new Dimension(770, 180));
        userCheckoutPane.setBorder(BorderFactory.createEmptyBorder());
        userCheckoutPane.getViewport().setBackground(colorHome);
        
        DefaultTableCellRenderer checkoutCenterRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        userCheckoutTable.setBackground(colorHome);
        userCheckoutTable.setFont(new Font("SansSerif", 0, 13)); 
        userCheckoutTable.setForeground(new Color(220, 220, 220));
        userCheckoutTable.setSelectionBackground(new Color(70, 70, 70));
        userCheckoutTable.setSelectionForeground(new Color(54, 154, 198));
   
        userCheckoutTable.setModel(new DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "Fullname", "Room Type", "Room No.", "Price", "Days Spent", "Checkout", "Total Amt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        
        userCheckoutTable.setDefaultRenderer(Double.class, checkoutCenterRenderer);
        userCheckoutTable.setDefaultRenderer(Integer.class, checkoutCenterRenderer);
        userCheckoutTable.setGridColor(new Color(61, 61, 61));
        userCheckoutTable.setRowHeight(40);
        userCheckoutTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        userCheckoutTable.setShowVerticalLines(false);
        userCheckoutTable.getTableHeader().setReorderingAllowed(false);
        userCheckoutTable.getTableHeader().setOpaque(false);
        userCheckoutTable.getTableHeader().setBackground(colorHome);
        userCheckoutTable.getTableHeader().setForeground(new Color(180, 180, 180));
        userCheckoutTable.getTableHeader().setFont(new Font("SansSerif", 1, 12));
        userCheckoutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userCheckoutTable.setRowSelectionAllowed(true);
        userCheckoutTable.setColumnSelectionAllowed(false);
        
        userCheckoutModel = (DefaultTableModel) userCheckoutTable.getModel();
        dbc.retrieveUserCheckOut(userCheckoutModel, btnProfile.getText());
        
        userCheckoutPane.setViewportView(userCheckoutTable);
        dashboardPanel.add(userCheckoutPane, dpgb);

    }
    
    private void initProfilePanel() {
         
        @SuppressWarnings("UnusedAssignment")
        HashMap<String, String> userMap = new HashMap<>();
        
        lblProfileTitle = new JLabel();
        lblProfileUnderline = new JLabel();
        
        ProfileDetailsPanel = new JPanel(); 
        ProfilePasswordPanel = new JPanel(); 
        
        lblUsername = new JLabel("Username:");
        txtprUserName = new RoundedJTextField();
        
        lblFName = new JLabel("First Name:");
        txtprFirstName = new RoundedJTextField();
        
        lblLName = new JLabel("Last Name:");
        txtprLastName = new RoundedJTextField();
        
        lblProfileChangePwordTitle = new JLabel();
        lblProfileChangeUnderline = new JLabel();
        
        txtprOldPass = new RoundedJTextField();
        txtprNewPass = new RoundedJTextField();
        txtprConfirmPass = new RoundedJTextField();
        btnUpdatePass = new RoundedButton();
        
        userMap = dbc.getAllUsers(btnProfile.getText().trim());
        
        profilePanel.setOpaque(false);
        profilePanel.setLayout(new GridBagLayout());
      
        GridBagConstraints ppgb = new GridBagConstraints(); 

        ppgb.weightx = 1;
        ppgb.weighty = 0;
        ppgb.gridx = 0;
        ppgb.gridy = 0;
        ppgb.anchor = GridBagConstraints.NORTHWEST;
        ppgb.insets = new Insets(15, 25, 0, 0);
        lblProfileTitle.setText("Profile");
        lblProfileTitle.setForeground(new Color(255, 255, 255));
        lblProfileTitle.setFont(new Font("Roboto Medium", 1, 30));
        profilePanel.add(lblProfileTitle, ppgb);
        
        ppgb.weightx = 1;
        ppgb.weighty = 0;
        ppgb.gridx = 0;
        ppgb.gridy = 1;
        ppgb.insets = new Insets(0, 25, 0, 0);
        ppgb.anchor = GridBagConstraints.NORTHWEST;
        lblProfileUnderline.setBackground(new Color(61, 61, 61));
        lblProfileUnderline.setText("");
        lblProfileUnderline.setOpaque(true);
        lblProfileUnderline.setPreferredSize(new Dimension(790, 1));
     
        profilePanel.add(lblProfileUnderline, ppgb);
        
        ppgb.weightx = 1;
        ppgb.weighty = 0;
        ppgb.gridx = 0;
        ppgb.gridy = 2;
        ppgb.anchor = GridBagConstraints.NORTHWEST;
        ppgb.insets = new Insets(0, 25, 0, 0);
        ProfileDetailsPanel.setLayout(new GridBagLayout());
        ProfileDetailsPanel.setPreferredSize(new Dimension(650, 150));
        
        profilePanel.add(ProfileDetailsPanel, ppgb);
        
        GridBagConstraints pdgbc = new GridBagConstraints();
        
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 0;
        pdgbc.gridy = 0;
        pdgbc.anchor = GridBagConstraints.NORTHWEST;
        lblUsername.setFont(new Font("SansSerif", 1, 13));
        lblUsername.setForeground(new Color(180, 180, 180));
        ProfileDetailsPanel.add(lblUsername, pdgbc);
              
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 0;
        pdgbc.gridy = 0;
        pdgbc.anchor = GridBagConstraints.SOUTHWEST;
        pdgbc.insets = new Insets(20, 0, 0, 0);
        
        txtprUserName.setBackground(colorTheme);
        txtprUserName.setEnabled(false);
        txtprUserName.setText(userMap.get("UNAME"));
        txtprUserName.setFont(new Font("SansSerif", 0, 13)); 
        txtprUserName.setForeground(new Color(180, 180, 180));
        txtprUserName.setDisabledTextColor(new Color(180, 180, 180));
        txtprUserName.setPreferredSize(new Dimension(250, 30));
        txtprUserName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ProfileDetailsPanel.add(txtprUserName, pdgbc);
        
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 0;
        pdgbc.gridy = 1;
        pdgbc.anchor = GridBagConstraints.NORTHWEST;
        pdgbc.insets = new Insets(10, 0, 0, 0);
        lblFName.setFont(new Font("SansSerif", 1, 13));
        lblFName.setForeground(new Color(180, 180, 180));
        ProfileDetailsPanel.add(lblFName, pdgbc);
              
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 0;
        pdgbc.gridy = 1;
        pdgbc.anchor = GridBagConstraints.SOUTHWEST;
        pdgbc.insets = new Insets(30, 0, 0, 0);
        
        txtprFirstName.setBackground(colorTheme);
        txtprFirstName.setEnabled(false);
        txtprFirstName.setText(userMap.get("FNAME"));
        txtprFirstName.setFont(new Font("SansSerif", 0, 13)); 
        txtprFirstName.setForeground(new Color(180, 180, 180));
        txtprFirstName.setDisabledTextColor(new Color(180, 180, 180));
        txtprFirstName.setPreferredSize(new Dimension(250, 30));
        txtprFirstName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ProfileDetailsPanel.add(txtprFirstName, pdgbc);
                
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 1;
        pdgbc.gridy = 1;
        pdgbc.anchor = GridBagConstraints.NORTHWEST;
        pdgbc.insets = new Insets(10, 0, 0, 0);
        lblLName.setFont(new Font("SansSerif", 1, 13));
        lblLName.setForeground(new Color(180, 180, 180));
        ProfileDetailsPanel.add(lblLName, pdgbc);
              
        pdgbc.weightx = 1;
        pdgbc.weighty = 0;
        pdgbc.gridx = 1;
        pdgbc.gridy = 1;
        pdgbc.anchor = GridBagConstraints.SOUTHWEST;
        pdgbc.insets = new Insets(30, 0, 0, 0);
        
        txtprLastName.setBackground(colorTheme);
        txtprLastName.setEnabled(false);
        txtprLastName.setText(userMap.get("LNAME"));
        txtprLastName.setFont(new Font("SansSerif", 0, 12)); 
        txtprLastName.setForeground(new Color(180, 180, 180));
        txtprLastName.setDisabledTextColor(new Color(180, 180, 180));
        txtprLastName.setPreferredSize(new Dimension(250, 30));
        txtprLastName.setCaretColor(new Color(255, 255, 255));
        txtprLastName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ProfileDetailsPanel.add(txtprLastName, pdgbc);
        
        ppgb.weightx = 1;
        ppgb.weighty = 0;
        ppgb.gridx = 0;
        ppgb.gridy = 3;
        ppgb.anchor = GridBagConstraints.NORTHWEST;
        ppgb.insets = new Insets(15, 25, 0, 0);
        lblProfileChangePwordTitle.setText("Change Password");
        lblProfileChangePwordTitle.setForeground(new Color(255, 255, 255));
        lblProfileChangePwordTitle.setFont(new Font("Roboto Medium", 1, 21));
        profilePanel.add(lblProfileChangePwordTitle, ppgb);
        
        ppgb.weightx = 1;
        ppgb.weighty = 0;
        ppgb.gridx = 0;
        ppgb.gridy = 4;
        pdgbc.insets = new Insets(0, 25, 0, 0);
        ppgb.anchor = GridBagConstraints.NORTHWEST;
        lblProfileChangeUnderline.setBackground(new Color(61, 61, 61));
        lblProfileChangeUnderline.setText("");
        lblProfileChangeUnderline.setOpaque(true);
        lblProfileChangeUnderline.setPreferredSize(new Dimension(790, 1));
     
        profilePanel.add(lblProfileChangeUnderline, ppgb);
        
        ppgb.weightx = 1;
        ppgb.weighty = 1;
        ppgb.gridx = 0;
        ppgb.gridy = 5;
        pdgbc.insets = new Insets(10, 0, 0, 0);
        ProfilePasswordPanel.setLayout(new GridBagLayout());
        ProfilePasswordPanel.setPreferredSize(new Dimension(750, 220));
        profilePanel.add(ProfilePasswordPanel, ppgb);
        
        GridBagConstraints pppgbc = new GridBagConstraints();
        
        pppgbc.weightx = 1;
        pppgbc.weighty = 0;
        pppgbc.gridx = 0;
        pppgbc.gridy = 0;
        pppgbc.anchor = GridBagConstraints.NORTH;
        pppgbc.insets = new Insets(10, 0, 0, 0);
        
        txtprOldPass.setBackground(colorTheme);
        txtprOldPass.setFont(new Font("SansSerif", 0, 13)); 
        txtprOldPass.setForeground(new Color(255, 255, 255));
        txtprOldPass.setPreferredSize(new Dimension(250, 30));
        txtprOldPass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtprOldPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                oldPassFieldFocusGained(fe);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                oldPassFieldFocusLost(fe);

            }
            
        });
        
        PromptSupport.setPrompt("Old Password", txtprOldPass);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtprOldPass);
        PromptSupport.setForeground(new Color(184, 184, 184), txtprOldPass);
        
        ProfilePasswordPanel.add(txtprOldPass, pppgbc);
        
        pppgbc.weightx = 1;
        pppgbc.weighty = 0;
        pppgbc.gridx = 0;
        pppgbc.gridy = 1;
        pppgbc.anchor = GridBagConstraints.NORTH;
        pppgbc.insets = new Insets(15, 0, 0, 0);
        
        txtprNewPass.setBackground(colorTheme);
        txtprNewPass.setFont(new Font("SansSerif", 0, 13)); 
        txtprNewPass.setForeground(new Color(255, 255, 255));
        txtprNewPass.setPreferredSize(new Dimension(250, 30));
        txtprNewPass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtprNewPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                newPassFieldFocusGained(fe);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                newPassFieldFocusLost(fe);
            }
            
        });
        
        PromptSupport.setPrompt("New Password", txtprNewPass);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtprNewPass);
        PromptSupport.setForeground(new Color(184, 184, 184), txtprNewPass);
        
        ProfilePasswordPanel.add(txtprNewPass, pppgbc);
        
        pppgbc.weightx = 1;
        pppgbc.weighty = 0;
        pppgbc.gridx = 0;
        pppgbc.gridy = 2;
        pppgbc.anchor = GridBagConstraints.NORTH;
        pppgbc.insets = new Insets(5, 0, 0, 0);
        
        txtprConfirmPass.setBackground(colorTheme);
        txtprConfirmPass.setFont(new Font("SansSerif", 0, 13)); 
        txtprConfirmPass.setForeground(new Color(255, 255, 255));
        txtprConfirmPass.setPreferredSize(new Dimension(250, 30));
        txtprConfirmPass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtprConfirmPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                cnfmPassFieldFocusGained(fe);
            }

            @Override
            public void focusLost(FocusEvent fe) {
                cnfmPassFieldFocusLost(fe);
            }
            
        });
        
        PromptSupport.setPrompt("Confirm Password", txtprConfirmPass);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtprConfirmPass);
        PromptSupport.setForeground(new Color(184, 184, 184), txtprConfirmPass);
        
        ProfilePasswordPanel.add(txtprConfirmPass, pppgbc);
        
        pppgbc.weightx = 1;
        pppgbc.weighty = 0;
        pppgbc.gridx = 0;
        pppgbc.gridy = 3;
        pppgbc.anchor = GridBagConstraints.NORTH;
        pppgbc.insets = new Insets(45, 0, 0, 0);
        btnUpdatePass.setForeground(new Color(255, 255, 255));
        btnUpdatePass.setText("Update Password");
        btnUpdatePass.setFont(new Font("Roboto Medium", 0, 14));
        btnUpdatePass.setPreferredSize(new Dimension(170, 35));
        btnUpdatePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdatePass.setBorder(null);
        
        btnUpdatePass.addActionListener((ActionEvent ae) -> {
            btnUpdatePassActionPerformed(ae);
        });
        
        ProfilePasswordPanel.add(btnUpdatePass, pppgbc);
         
    }
     
    private void initRoomServicePanel() {

        btnEconomyRooms = new ServicesCurvedButton();
        btnExecutiveRooms = new ServicesCurvedButton();
        btnDeluxeRooms = new ServicesCurvedButton();
        btnRoyalDeluxeRooms = new ServicesCurvedButton();
        roomsPanel = new JPanel();
        
        roomsContentPanel = new JPanel();
        lisNavBar = new JList();
        
        checkinRoomPanel = new JPanel();
                   
        GridLayout roomLayout = new GridLayout(0,3);
        
        roomServicePanel.setOpaque(false);
        roomServicePanel.setLayout(new GridBagLayout());

        GridBagConstraints rsgb = new GridBagConstraints(); 
        
        rsgb.weightx = 1;
        rsgb.weighty = 0;
        rsgb.gridx = 0;
        rsgb.gridy = 0;
        rsgb.anchor = GridBagConstraints.NORTHWEST;
        rsgb.insets = new Insets(15, 25, 0, 0);
        lisNavBar.setFont(new Font("SansSerif", 1, 14)); 
        lisNavBar.setBackground(colorHome);
        lisNavBar.setForeground(new Color(180, 180, 180));
        lisNavBar.setFixedCellHeight(35);
        lisNavBar.setFixedCellWidth(100);
        lisNavBar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lisNavBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lisNavBar.setCellRenderer(navBarHoverClickRenderer);
        lisNavBar.setPreferredSize(new Dimension(320, 40));
        lisNavBar.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        lisNavBar.setVisibleRowCount(-1);
        lisNavBar.setModel(new AbstractListModel<String>() {
            String[] strings = { "Check In", "Check Out"};
            @Override
            public int getSize() { return strings.length; }
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        lisNavBar.addMouseListener(navBarHoverClickRenderer.getHandler(lisNavBar));
        lisNavBar.addMouseMotionListener(navBarHoverClickRenderer.getHandler(lisNavBar));
        lisNavBar.setSelectedIndex(0);
        lisNavBar.addListSelectionListener((ListSelectionEvent lse) -> {
             if (lisNavBar.getSelectedIndex() == 0) {
                 CardLayout cl = (CardLayout)(roomsContentPanel.getLayout());
                 cl.show(roomsContentPanel, CHECKIN_CUSTOMER_PANEL);
             }
             if (lisNavBar.getSelectedIndex() == 1) {
                 CardLayout cl = (CardLayout)(roomsContentPanel.getLayout());
                 cl.show(roomsContentPanel, CHECKOUT_CUSTOMER_PANEL);
             } 
             
        });
        
        roomServicePanel.add(lisNavBar, rsgb);
        
        rsgb.weightx = 1;
        rsgb.weighty = 1;
        rsgb.gridx = 0;
        rsgb.gridy = 1;
        rsgb.insets = new Insets(20, 25, 0, 25);
        rsgb.anchor = GridBagConstraints.NORTHWEST; 
        roomsContentPanel.setLayout(new CardLayout());
        roomsContentPanel.setOpaque(false);
        
        roomServicePanel.add(roomsContentPanel, rsgb);
         
        checkinRoomPanel.setOpaque(false);
        checkinRoomPanel.setLayout(roomLayout);
        checkinRoomPanel.setPreferredSize(new Dimension(750, 300));
        checkinRoomPanel.setMinimumSize(new Dimension(750, 300));
        roomLayout.setHgap(55);
        roomLayout.setVgap(45);
                
        lisAvailableEconomyRooms = dbc.getRoomNo("Economy");
        lisAvailableExecutiveRooms = dbc.getRoomNo("Executive"); 
        lisAvailableDeluxeRooms = dbc.getRoomNo("Deluxe");
        lisAvailableRoyalDeluxeRooms = dbc.getRoomNo("Royal Deluxe");
        
        btnEconomyRooms.setForeground(new Color(255, 255, 255));
        btnEconomyRooms.setIcon(new ImageIcon(getClass().getResource("/images/btn_economy_room.jpg")));
        btnEconomyRooms.setText("<html><div><font color='white' size=\"4\" font-family=Roboto Medium>"
                + "Economy Rooms</font><br /><font color='green' size=\"3\"><i><center>" 
                + String.valueOf(lisAvailableEconomyRooms.size()) + " rooms available</center></i></font></div></html>");
        btnEconomyRooms.setFont(new Font("Roboto Medium", 0, 14));
        btnEconomyRooms.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEconomyRooms.setIconTextGap(7);
        btnEconomyRooms.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEconomyRooms.setBorder(null);
        btnEconomyRooms.setHorizontalAlignment(SwingConstants.CENTER);
        btnEconomyRooms.setVerticalAlignment(SwingConstants.TOP);
        btnEconomyRooms.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEconomyRooms.addActionListener((ActionEvent ae) -> {
            btnEconomyRoomActionPerformed(ae);
        });
        checkinRoomPanel.add(btnEconomyRooms);
        
        btnExecutiveRooms.setForeground(new Color(255, 255, 255));
        btnExecutiveRooms.setIcon(new ImageIcon(getClass().getResource("/images/btn_executive_rooms.jpg")));
        btnExecutiveRooms.setText("<html><div><font color='white' size=\"4\" font-family=Roboto Medium>"
                + "Executive Rooms</font><br /><font color='green' size=\"3\"><i><center>" 
                + String.valueOf(lisAvailableExecutiveRooms.size()) + " rooms available</center></i></font></div></html>");
        btnExecutiveRooms.setFont(new Font("Roboto Medium", 0, 14));
        btnExecutiveRooms.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExecutiveRooms.setIconTextGap(7);
        btnExecutiveRooms.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExecutiveRooms.setBorder(null);
        btnExecutiveRooms.setHorizontalAlignment(SwingConstants.CENTER);
        btnExecutiveRooms.setVerticalAlignment(SwingConstants.TOP);
        btnExecutiveRooms.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExecutiveRooms.addActionListener((ActionEvent ae) -> {
            btnExecutiveRoomActionPerformed(ae);
        });
        checkinRoomPanel.add(btnExecutiveRooms);
        
        btnDeluxeRooms.setForeground(new Color(255, 255, 255));
        btnDeluxeRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_deluxe_rooms.jpg")));
        btnDeluxeRooms.setText("<html><div><font color='white' size=\"4\" font-family=Roboto Medium>"
                + "Deluxe Rooms</font><br /><font color='green' size=\"3\"><i><center>" 
                + String.valueOf(lisAvailableDeluxeRooms.size()) + " rooms available</center></i></font></div></html>");
        btnDeluxeRooms.setFont(new Font("Roboto Medium", 0, 14));
        btnDeluxeRooms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeluxeRooms.setIconTextGap(7);
        btnDeluxeRooms.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeluxeRooms.setBorder(null);
        btnDeluxeRooms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeluxeRooms.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnDeluxeRooms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeluxeRooms.addActionListener((ActionEvent ae) -> {
            btnDeluxeRoomActionPerformed(ae);
        });
        checkinRoomPanel.add(btnDeluxeRooms);
        
        btnRoyalDeluxeRooms.setForeground(new Color(255, 255, 255));
        btnRoyalDeluxeRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_royal_deluxe_rooms.jpg")));
        btnRoyalDeluxeRooms.setText("<html><div><font color='white' size=\"4\" font-family=Roboto Medium>"
                + "Royal Deluxe Rooms</font><br /><font color='green' size=\"3\"><i><center>" 
                + String.valueOf(lisAvailableRoyalDeluxeRooms.size()) + " rooms available</center></i></font></div></html>");
        btnRoyalDeluxeRooms.setFont(new Font("Roboto Medium", 0, 14));
        btnRoyalDeluxeRooms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRoyalDeluxeRooms.setIconTextGap(7);
        btnRoyalDeluxeRooms.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRoyalDeluxeRooms.setBorder(null);
        btnRoyalDeluxeRooms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRoyalDeluxeRooms.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnRoyalDeluxeRooms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRoyalDeluxeRooms.addActionListener((ActionEvent ae) -> {
            btnRoyalDeluxeRoomActionPerformed(ae);
        });
        checkinRoomPanel.add(btnRoyalDeluxeRooms);

        roomsContentPanel.add(checkinRoomPanel, CHECKIN_CUSTOMER_PANEL);
        
    }
    
    private void initCheckoutPanel() {
        
        checkoutRoomPanel = new JPanel();
        
        lblTitle = new JLabel();
        JLabel lblRMUnderline = new JLabel();
        JPanel checkoutDataPanel = new JPanel();
        JPanel customerDataPanel = new JPanel();
        JPanel roomDataPanel = new JPanel();
        JPanel timeDataPanel = new JPanel();
        JPanel balanceDataPanel = new JPanel();
        
        txtckoFirstname = new RoundedJTextField();
        txtckoSurname = new RoundedJTextField();
        txtckoMobileNo = new RoundedJTextField();
        txtckoGender = new RoundedJTextField();
        txtckoNationality = new RoundedJTextField();
        
        txtckoRmType = new RoundedJTextField();
        txtckoRmNo = new RoundedJTextField();
        txtckoRmPrice = new RoundedJTextField();
        txtckoStayDays = new RoundedJTextField();
        txtckoTotCost = new RoundedJTextField();
        
        txtckoCheckinDate = new RoundedJTextField();
        txtckoSetDeparture = new RoundedJTextField();
        txtckoCheckoutDate = new RoundedJTextField();
        
        JLabel lblCheckinDate = new JLabel();
        JLabel lblSetDeparture = new JLabel();
        JLabel lblCheckoutDate = new JLabel();
        
        JLabel lblDaysSpent = new JLabel();
        lblDaysSpentValue = new JLabel();
        
        JLabel lblTotAmt = new JLabel();
        lblTotAmtValue = new JLabel();
        
        JScrollPane checkinPane = new JScrollPane();
        JTable checkinTable = new JTable();
        
        JButton btnCheckOut = new ShallowCurvedButton();
       
        
        DefaultTableModel checkinModel;

        checkoutRoomPanel.setOpaque(false);
        checkoutRoomPanel.setLayout(new GridBagLayout());
        checkoutRoomPanel.setPreferredSize(new Dimension(770, 450));
        checkoutRoomPanel.setMinimumSize(new Dimension(770, 450));
        
        GridBagConstraints chogb = new GridBagConstraints(); 
        
        chogb.weightx = 1;
        chogb.weighty = 0;
        chogb.gridx = 0;
        chogb.gridy = 0;
        chogb.anchor = GridBagConstraints.NORTH;
        chogb.insets = new Insets(0, 0, 10, 0);
        checkoutDataPanel.setLayout(new GridBagLayout());
        checkoutDataPanel.setPreferredSize(new Dimension(770, 170));
        checkoutDataPanel.setOpaque(false);
        checkoutDataPanel.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        checkoutRoomPanel.add(checkoutDataPanel, chogb);
        
        GridBagConstraints chodpgb = new GridBagConstraints();
        
        chodpgb.weighty = 0;
        chodpgb.weightx = 0;
        chodpgb.gridx = 0;
        chodpgb.gridy = 0;
        chodpgb.insets = new Insets(0, 10, 0, 0);
        chodpgb.anchor = GridBagConstraints.NORTHWEST;
        
        customerDataPanel.setLayout(new GridBagLayout());
        customerDataPanel.setPreferredSize(new Dimension(225, 140));
        customerDataPanel.setOpaque(false);
        customerDataPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(60, 60, 60)), 
                "Customer", 0, 0, new Font("Roboto Medium", 0, 12), new Color(180, 180, 180)));
        checkoutDataPanel.add(customerDataPanel, chodpgb);
        
        GridBagConstraints cusdpgb = new GridBagConstraints();
        
        cusdpgb.weighty = 0;
        cusdpgb.weightx = 0;
        cusdpgb.gridx = 0;
        cusdpgb.gridy = 0;
        cusdpgb.insets = new Insets(0, 0, 3, 0);
        cusdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoFirstname.setBackground(colorTheme);
        txtckoFirstname.setFont(new Font("SansSerif", 0, 12)); 
        txtckoFirstname.setForeground(new Color(255, 255, 255));
        txtckoFirstname.setPreferredSize(new Dimension(210, 25));
        txtckoFirstname.setCaretColor(new Color(255, 255, 255));
        txtckoFirstname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("First Name", txtckoFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoFirstname);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoFirstname);
        
        customerDataPanel.add(txtckoFirstname, cusdpgb);
        
        cusdpgb.weighty = 0;
        cusdpgb.weightx = 0;
        cusdpgb.gridx = 0;
        cusdpgb.gridy = 1;
        cusdpgb.insets = new Insets(0, 0, 3, 0);
        cusdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoSurname.setBackground(colorTheme);
        txtckoSurname.setFont(new Font("SansSerif", 0, 12)); 
        txtckoSurname.setForeground(new Color(255, 255, 255));
        txtckoSurname.setPreferredSize(new Dimension(210, 25));
        txtckoSurname.setCaretColor(new Color(255, 255, 255));
        txtckoSurname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Surname", txtckoSurname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoSurname);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoSurname);
        
        customerDataPanel.add(txtckoSurname, cusdpgb);
        
        cusdpgb.weighty = 0;
        cusdpgb.weightx = 0;
        cusdpgb.gridx = 0;
        cusdpgb.gridy = 2;
        cusdpgb.insets = new Insets(0, 0, 3, 5);
        cusdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoNationality.setBackground(colorTheme);
        txtckoNationality.setFont(new Font("SansSerif", 0, 12)); 
        txtckoNationality.setForeground(new Color(255, 255, 255));
        txtckoNationality.setPreferredSize(new Dimension(110, 25));
        txtckoNationality.setCaretColor(new Color(255, 255, 255));
        txtckoNationality.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Nationality", txtckoNationality);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoNationality);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoNationality);
        
        customerDataPanel.add(txtckoNationality, cusdpgb);
        
        cusdpgb.weighty = 0;
        cusdpgb.weightx = 0;
        cusdpgb.gridx = 0;
        cusdpgb.gridy = 2;
        cusdpgb.insets = new Insets(0, 0, 3, 0);
        cusdpgb.anchor = GridBagConstraints.NORTHEAST;
        
        txtckoGender.setBackground(colorTheme);
        txtckoGender.setFont(new Font("SansSerif", 0, 12)); 
        txtckoGender.setForeground(new Color(255, 255, 255));
        txtckoGender.setPreferredSize(new Dimension(95, 25));
        txtckoGender.setCaretColor(new Color(255, 255, 255));
        txtckoGender.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Gender", txtckoGender);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoGender);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoGender);
        
        customerDataPanel.add(txtckoGender, cusdpgb); 
        
        cusdpgb.weighty = 0;
        cusdpgb.weightx = 0;
        cusdpgb.gridx = 0;
        cusdpgb.gridy = 3;
        cusdpgb.anchor = GridBagConstraints.NORTHWEST;
                   
        txtckoMobileNo.setBackground(colorTheme);
        txtckoMobileNo.setFont(new Font("SansSerif", 0, 12)); 
        txtckoMobileNo.setForeground(new Color(255, 255, 255));
        txtckoMobileNo.setPreferredSize(new Dimension(210, 25));
        txtckoMobileNo.setCaretColor(new Color(255, 255, 255));
        txtckoMobileNo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Mobile No.", txtckoMobileNo);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoMobileNo);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoMobileNo);
        
        customerDataPanel.add(txtckoMobileNo, cusdpgb); 
        
        chodpgb.weightx = 0;
        chodpgb.weighty = 0;
        chodpgb.gridx = 1;
        chodpgb.gridy = 0;
        chodpgb.anchor = GridBagConstraints.NORTHWEST;
        chodpgb.insets = new Insets(0, 0, 0, 2);
        roomDataPanel.setLayout(new GridBagLayout());
        roomDataPanel.setPreferredSize(new Dimension(170, 140));
        roomDataPanel.setOpaque(false);
        roomDataPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(60, 60, 60)), 
                "Room", 0, 0, new Font("Roboto Medium", 0, 12), new Color(180, 180, 180)));
        
        checkoutDataPanel.add(roomDataPanel, chodpgb);  
        
        GridBagConstraints rmdpgb = new GridBagConstraints();
        
        rmdpgb.weighty = 0;
        rmdpgb.weightx = 0;
        rmdpgb.gridx = 0;
        rmdpgb.gridy = 0;
        rmdpgb.insets = new Insets(0, 0, 3, 0);
        rmdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoRmType.setBackground(colorTheme);
        txtckoRmType.setFont(new Font("SansSerif", 0, 12)); 
        txtckoRmType.setForeground(new Color(255, 255, 255));
        txtckoRmType.setPreferredSize(new Dimension(150, 25));
        txtckoRmType.setCaretColor(new Color(255, 255, 255));
        txtckoRmType.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Room Type", txtckoRmType);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoRmType);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoRmType);
        
        roomDataPanel.add(txtckoRmType, rmdpgb);
        
        rmdpgb.weighty = 0;
        rmdpgb.weightx = 0;
        rmdpgb.gridx = 0;
        rmdpgb.gridy = 1;
        rmdpgb.insets = new Insets(0, 0, 3, 0);
        rmdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoRmNo.setBackground(colorTheme);
        txtckoRmNo.setFont(new Font("SansSerif", 0, 12)); 
        txtckoRmNo.setForeground(new Color(255, 255, 255));
        txtckoRmNo.setPreferredSize(new Dimension(100, 25));
        txtckoRmNo.setCaretColor(new Color(255, 255, 255));
        txtckoRmNo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Room No.", txtckoRmNo);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoRmNo);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoRmNo);
        
        roomDataPanel.add(txtckoRmNo, rmdpgb);
        
        rmdpgb.weighty = 0;
        rmdpgb.weightx = 0;
        rmdpgb.gridx = 0;
        rmdpgb.gridy = 2;
        rmdpgb.insets = new Insets(0, 0, 3, 5);
        rmdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoRmPrice.setBackground(colorTheme);
        txtckoRmPrice.setFont(new Font("SansSerif", 0, 12)); 
        txtckoRmPrice.setForeground(new Color(255, 255, 255));
        txtckoRmPrice.setPreferredSize(new Dimension(80, 25));
        txtckoRmPrice.setCaretColor(new Color(255, 255, 255));
        txtckoRmPrice.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Price", txtckoRmPrice);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoRmPrice);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoRmPrice);
        
        roomDataPanel.add(txtckoRmPrice, rmdpgb);
        
        rmdpgb.weighty = 0;
        rmdpgb.weightx = 0;
        rmdpgb.gridx = 0;
        rmdpgb.gridy = 2;
        rmdpgb.insets = new Insets(0, 0, 3, 0);
        rmdpgb.anchor = GridBagConstraints.NORTHEAST;
        
        txtckoStayDays.setBackground(colorTheme);
        txtckoStayDays.setFont(new Font("SansSerif", 0, 12)); 
        txtckoStayDays.setForeground(new Color(255, 255, 255));
        txtckoStayDays.setPreferredSize(new Dimension(65, 25));
        txtckoStayDays.setCaretColor(new Color(255, 255, 255));
        txtckoStayDays.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Days", txtckoStayDays);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoStayDays);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoStayDays);
        
        roomDataPanel.add(txtckoStayDays, rmdpgb);
        
        rmdpgb.weighty = 1;
        rmdpgb.weightx = 0;
        rmdpgb.gridx = 0;
        rmdpgb.gridy = 3;
        rmdpgb.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoTotCost.setBackground(colorTheme);
        txtckoTotCost.setFont(new Font("SansSerif", 0, 12)); 
        txtckoTotCost.setForeground(new Color(255, 255, 255));
        txtckoTotCost.setPreferredSize(new Dimension(150, 25));
        txtckoTotCost.setCaretColor(new Color(255, 255, 255));
        txtckoTotCost.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        PromptSupport.setPrompt("Total Cost", txtckoTotCost);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtckoTotCost);
        PromptSupport.setForeground(new Color(184, 184, 184), txtckoTotCost);
        
        roomDataPanel.add(txtckoTotCost, rmdpgb);
        
                
        chodpgb.weightx = 1;
        chodpgb.weighty = 0;
        chodpgb.gridx = 2;
        chodpgb.gridy = 0;
        chodpgb.anchor = GridBagConstraints.NORTHWEST;
        chodpgb.insets = new Insets(0, 0, 0, 2);
        timeDataPanel.setLayout(new GridBagLayout());
        timeDataPanel.setPreferredSize(new Dimension(225, 110));
        timeDataPanel.setOpaque(false);
        timeDataPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(60, 60, 60)), 
                "Dates & Time", 0, 0, new Font("Roboto Medium", 0, 12), new Color(180, 180, 180)));
        
        checkoutDataPanel.add(timeDataPanel, chodpgb);  
        
        GridBagConstraints dtpgbc = new GridBagConstraints();
        
        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 0;
        dtpgbc.gridy = 0;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.WEST;
        
        lblCheckinDate.setText("Check In: ");
        lblCheckinDate.setForeground(new Color(180, 180, 180));
        lblCheckinDate.setFont(new Font("SansSerif", 1, 10));
        
        timeDataPanel.add(lblCheckinDate, dtpgbc);
        
        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 1;
        dtpgbc.gridy = 0;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoCheckinDate.setBackground(colorTheme);
        txtckoCheckinDate.setFont(new Font("SansSerif", 0, 12)); 
        txtckoCheckinDate.setForeground(new Color(255, 255, 255));
        txtckoCheckinDate.setPreferredSize(new Dimension(150, 25));
        txtckoCheckinDate.setCaretColor(new Color(255, 255, 255));
        txtckoCheckinDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        timeDataPanel.add(txtckoCheckinDate, dtpgbc);

        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 0;
        dtpgbc.gridy = 1;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.WEST;
        
        lblSetDeparture.setText("Departure: ");
        lblSetDeparture.setForeground(new Color(180, 180, 180));
        lblSetDeparture.setFont(new Font("SansSerif", 1, 10));
        
        timeDataPanel.add(lblSetDeparture, dtpgbc);
        
        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 1;
        dtpgbc.gridy = 1;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoSetDeparture.setBackground(colorTheme);
        txtckoSetDeparture.setFont(new Font("SansSerif", 0, 12)); 
        txtckoSetDeparture.setForeground(new Color(255, 255, 255));
        txtckoSetDeparture.setPreferredSize(new Dimension(150, 25));
        txtckoSetDeparture.setCaretColor(new Color(255, 255, 255));
        txtckoSetDeparture.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        timeDataPanel.add(txtckoSetDeparture, dtpgbc);

        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 0;
        dtpgbc.gridy = 2;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.WEST;
        
        lblCheckoutDate.setText("Check Out: ");
        lblCheckoutDate.setForeground(new Color(180, 180, 180));
        lblCheckoutDate.setFont(new Font("SansSerif", 1, 10));
        
        timeDataPanel.add(lblCheckoutDate, dtpgbc);
        
        dtpgbc.weighty = 0;
        dtpgbc.weightx = 0;
        dtpgbc.gridx = 1;
        dtpgbc.gridy = 2;
        dtpgbc.insets = new Insets(0, 0, 3, 0);
        dtpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtckoCheckoutDate.setBackground(colorTheme);
        txtckoCheckoutDate.setFont(new Font("SansSerif", 0, 12)); 
        txtckoCheckoutDate.setForeground(new Color(255, 255, 255));
        txtckoCheckoutDate.setPreferredSize(new Dimension(150, 25));
        txtckoCheckoutDate.setCaretColor(new Color(255, 255, 255));
        txtckoCheckoutDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        timeDataPanel.add(txtckoCheckoutDate, dtpgbc);
        
        chodpgb.weightx = 1;
        chodpgb.weighty = 0;
        chodpgb.gridx = 3;
        chodpgb.gridy = 0;
        chodpgb.anchor = GridBagConstraints.EAST;
        chodpgb.insets = new Insets(5, 0, 0, 10);
        
        btnCheckOut.setForeground(new Color(255, 255, 255));
        btnCheckOut.setText("Check Out");
        btnCheckOut.setFont(new Font("Roboto Medium", 0, 14));
        btnCheckOut.setPreferredSize(new Dimension(90, 95));
        btnCheckOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCheckOut.setBorder(null);
        
        btnCheckOut.addActionListener((ActionEvent ae) -> {
            btnCheckOutActionPerformed(ae);
        });
        
        checkoutDataPanel.add(btnCheckOut, chodpgb); 
        
        
        chodpgb.weightx = 1;
        chodpgb.weighty = 0;
        chodpgb.gridx = 2;
        chodpgb.gridy = 0;
        chodpgb.anchor = GridBagConstraints.SOUTHWEST;

        balanceDataPanel.setLayout(new GridBagLayout());
        balanceDataPanel.setPreferredSize(new Dimension(223, 29));
        balanceDataPanel.setBackground(colorTheme);
       
        checkoutDataPanel.add(balanceDataPanel, chodpgb);  
        
        GridBagConstraints bdpgbc = new GridBagConstraints();
        
        bdpgbc.weightx = 1;
        bdpgbc.weighty = 0;
        bdpgbc.gridx = 0;
        bdpgbc.gridy = 0;
        bdpgbc.insets = new Insets(1, 5, 0, 0);
        bdpgbc.anchor = GridBagConstraints.NORTHWEST;
        lblDaysSpent.setText("Days Spent: ");
        lblDaysSpent.setForeground(new Color(180, 180, 180));
        lblDaysSpent.setFont(new Font("SansSerif", 1, 8));
        
        balanceDataPanel.add(lblDaysSpent, bdpgbc);
        
        bdpgbc.weightx = 1;
        bdpgbc.weighty = 1;
        bdpgbc.gridx = 0;
        bdpgbc.gridy = 1;
        bdpgbc.insets = new Insets(0, 20, 0, 0);
        bdpgbc.anchor = GridBagConstraints.NORTHWEST;
        lblDaysSpentValue.setForeground(new Color(255, 255, 255));
        lblDaysSpentValue.setFont(new Font("SansSerif", 1, 13));
        
        balanceDataPanel.add(lblDaysSpentValue, bdpgbc);
        
        
        bdpgbc.weightx = 1;
        bdpgbc.weighty = 0;
        bdpgbc.gridx = 1;
        bdpgbc.gridy = 0;
        bdpgbc.insets = new Insets(1, 0, 0, 5);
        bdpgbc.anchor = GridBagConstraints.NORTHEAST;
        lblTotAmt.setText("Total Amount: ");
        lblTotAmt.setForeground(new Color(180, 180, 180));
        lblTotAmt.setFont(new Font("SansSerif", 1, 8));
        
        balanceDataPanel.add(lblTotAmt, bdpgbc);
        
        bdpgbc.weightx = 1;
        bdpgbc.weighty = 1;
        bdpgbc.gridx = 1;
        bdpgbc.gridy = 1;
        bdpgbc.insets = new Insets(0, 0, 0, 5);
        bdpgbc.anchor = GridBagConstraints.NORTHEAST;
        lblTotAmtValue.setForeground(new Color(255, 255, 255));
        lblTotAmtValue.setFont(new Font("SansSerif", 1, 13));
        
        balanceDataPanel.add(lblTotAmtValue, bdpgbc);
        
        chogb.weightx = 1;
        chogb.weighty = 1;
        chogb.gridx = 0;
        chogb.gridy = 1;
        chogb.anchor = GridBagConstraints.NORTH;
        
        checkinPane.setPreferredSize(new Dimension(770, 250));
        checkinPane.setBorder(BorderFactory.createEmptyBorder());
        checkinPane.getViewport().setBackground(colorHome);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        checkinTable.setBackground(colorHome);
        checkinTable.setFont(new Font("SansSerif", 0, 13)); 
        checkinTable.setForeground(new Color(220, 220, 220));
        checkinTable.setSelectionBackground(new Color(70, 70, 70));
        checkinTable.setSelectionForeground(new Color(54, 154, 198));
   
        checkinTable.setModel(new DefaultTableModel(
            new Object [][] {
              
            },
            new String [] {
                "First name", "Surname", "Nationality", "Gender", "Mobile", "Room Type", "Room No.", "Price("+nairaSymbol+")", "Stay", "Checkin", "Departure", "TotCost("+nairaSymbol+")"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class,
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
        
        checkinTable.setDefaultRenderer(Double.class, centerRenderer);
        checkinTable.setDefaultRenderer(Integer.class, centerRenderer);
        checkinTable.setGridColor(new Color(61, 61, 61));
        checkinTable.setRowHeight(40);
        checkinTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        checkinTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        checkinTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        checkinTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        checkinTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        checkinTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        checkinTable.getColumnModel().getColumn(9).setPreferredWidth(170);
        checkinTable.getColumnModel().getColumn(10).setPreferredWidth(170);
        checkinTable.setShowVerticalLines(false);
       // checkinTable.getTableHeader().setResizingAllowed(true);
        checkinTable.getTableHeader().setReorderingAllowed(false);
        checkinTable.getTableHeader().setOpaque(false);
        checkinTable.getTableHeader().setBackground(colorHome);
        checkinTable.getTableHeader().setForeground(new Color(180, 180, 180));
        checkinTable.getTableHeader().setFont(new Font("SansSerif", 1, 12));
        checkinTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        checkinTable.setRowSelectionAllowed(true);
        checkinTable.setColumnSelectionAllowed(false);
        checkinTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        checkinModel = (DefaultTableModel) checkinTable.getModel();
        dbc.retrieveCheckInToCheckout(checkinModel);

        checkinTable.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
                   
            int row = checkinTable.getSelectedRow();
        
            txtckoFirstname.setText((String)checkinTable.getValueAt(row, 0));
            txtckoSurname.setText((String)checkinTable.getValueAt(row, 1));
            txtckoNationality.setText((String)checkinTable.getValueAt(row, 2));
            txtckoGender.setText((String)checkinTable.getValueAt(row, 3));
            txtckoMobileNo.setText((String)checkinTable.getValueAt(row, 4));
            
            txtckoRmType.setText((String)checkinTable.getValueAt(row, 5));
            txtckoRmNo.setText((String)checkinTable.getValueAt(row, 6));
            txtckoRmPrice.setText(String.valueOf(checkinTable.getValueAt(row, 7)));
            txtckoStayDays.setText(String.valueOf(checkinTable.getValueAt(row, 8)));
            txtckoTotCost.setText(String.valueOf(checkinTable.getValueAt(row, 11)));
            
            txtckoCheckinDate.setText((String)checkinTable.getValueAt(row, 9));
            txtckoSetDeparture.setText((String)checkinTable.getValueAt(row, 10));
            txtckoCheckoutDate.setText(nowDate());
            
            SimpleDateFormat cdf = new SimpleDateFormat(DATE_FORMAT);
            Calendar calendar = Calendar.getInstance();
          
            try {
                checkinDateTime = cdf.parse(txtckoCheckinDate.getText());
                nowDateTime = cdf.parse(txtckoCheckoutDate.getText());
                
                long diff = nowDateTime.getTime() - checkinDateTime.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                
                calendar.setTime(nowDateTime);
                
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                
                double rmCost = Double.valueOf(txtckoRmPrice.getText());
                
                if(hours > 12) {
                    long newDayDiff = diffDays + 1;
                    lblDaysSpentValue.setText(String.valueOf(newDayDiff));
                    
                    double totalAmt = rmCost * (double) newDayDiff;
                    fmt.format(totalAmt);
                    lblTotAmtValue.setText(nairaSymbol+String.valueOf(totalAmt));
                }
                else {
                    lblDaysSpentValue.setText(String.valueOf(diffDays));
                    
                    double totalAmt = rmCost * (double) diffDays;
                    fmt.format(totalAmt);
                    lblTotAmtValue.setText(nairaSymbol+String.valueOf(totalAmt));
                }
            } catch (ParseException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        checkinPane.setViewportView(checkinTable);
        checkoutRoomPanel.add(checkinPane, chogb);
       
        roomsContentPanel.add(checkoutRoomPanel, CHECKOUT_CUSTOMER_PANEL);
        
    }
    
    private void initRoomBookingPanel() {
        
        bookPanel = new JPanel();
        infoPanel = new JPanel();
        detailsPanel = new JPanel();
        customerPanel = new JPanel();

        lblUnderline = new JLabel();
        lblRoomImg = new JLabel();
        lblName = new JLabel();
        lblPrice = new JLabel();
        lblPerDay = new JLabel();
        lblDesc = new JLabel();
        lblRmAvailable = new JLabel();
        lblFormTitle = new JLabel();
       // currentDate = new JLabel();
        LocalDateTime now = LocalDateTime.now();
        
        txtFirstname = new RoundedJTextField();
        txtSurname = new RoundedJTextField();
        txtMobileNo = new RoundedJTextField();
        txtDaysNo = new RoundedJTextField();
        cbNationality = new JComboBox();
        cbGender = new JComboBox();
        cbDiscount = new JComboBox();
        cbRoomNo = new JComboBox();
        btnBook = new RoundedButton("Book");
        
        bookPanel.setOpaque(false);
        bookPanel.setLayout(new GridBagLayout());
        bookPanel.setMinimumSize(new Dimension(800, 550));
        bookPanel.setPreferredSize(new Dimension(800, 550));
        
        GridBagConstraints bpgb = new GridBagConstraints(); 
        
        bpgb.weightx = 1;
        bpgb.weighty = 0;
        bpgb.gridx = 0;
        bpgb.gridy = 0;
        bpgb.anchor = GridBagConstraints.NORTHWEST; 
        bpgb.insets = new Insets(15, 30, 0, 0);
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new GridBagLayout());
       
        bookPanel.add(infoPanel, bpgb);
        GridBagConstraints ipgb = new GridBagConstraints();

        ipgb.weightx = 0;
        ipgb.weighty = 0;
        ipgb.gridx = 0;
        ipgb.gridy = 0;
        ipgb.anchor = GridBagConstraints.NORTHWEST;
        ipgb.insets = new Insets(0, 0, 0, 30);
        
        lblRoomImg.setPreferredSize(new Dimension(220, 150));
        lblRoomImg.setMinimumSize(new Dimension(220, 150));
        infoPanel.add(lblRoomImg, ipgb);
        
        ipgb.weightx = 0;
        ipgb.weighty = 0;
        ipgb.gridx = 1;
        ipgb.gridy = 0;
        ipgb.anchor = GridBagConstraints.NORTH;
        detailsPanel.setOpaque(false);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
        detailsPanel.setMinimumSize(new Dimension(450, 170));
        detailsPanel.setPreferredSize(new Dimension(450, 170));
        infoPanel.add(detailsPanel, ipgb);
      
        lblName.setForeground(new Color(255, 255, 255));
        lblName.setFont(new Font("Roboto Medium", 1, 18));
        
        lblPrice.setForeground(new Color(255, 255, 255));
        lblPrice.setFont(new Font("Roboto Medium", 0, 16));
        
        lblPerDay.setText("Naira per night");
        lblPerDay.setForeground(new Color(255, 255, 255));
        lblPerDay.setFont(new Font("Roboto ", 0, 11));
        
        lblDesc.setForeground(new Color(255, 255, 255));
        lblDesc.setFont(new Font("Roboto ", 0, 11));
        
        lblRmAvailable.setForeground(new Color(0, 133, 0));
        lblRmAvailable.setFont(new Font("Roboto ", 2, 12));
        
        detailsPanel.add(lblName);
        detailsPanel.add(Box.createRigidArea(new Dimension(0,5)));
        detailsPanel.add(lblPrice);
        detailsPanel.add(Box.createRigidArea(new Dimension(0,1)));
        detailsPanel.add(lblPerDay);
        detailsPanel.add(Box.createRigidArea(new Dimension(0,5)));
        detailsPanel.add(lblDesc);
        detailsPanel.add(Box.createRigidArea(new Dimension(0,7)));
        detailsPanel.add(lblRmAvailable);
        
        bpgb.weightx = 1;
        bpgb.weighty = 0;
        bpgb.gridx = 0;
        bpgb.gridy = 1;
        bpgb.anchor = GridBagConstraints.NORTH;
        bpgb.insets = new Insets(0, 30, 0, 0);
        
        lblUnderline.setBackground(new Color(61, 61, 61));
        lblUnderline.setText("");
        lblUnderline.setOpaque(true);
        lblUnderline.setPreferredSize(new Dimension(790, 1));
        lblUnderline.setMinimumSize(new Dimension(790, 1));
        lblUnderline.setMaximumSize(new Dimension(1000, 1));
        bookPanel.add(lblUnderline, bpgb);
        
        bpgb.weighty = 0;
        bpgb.weightx = 1;
        bpgb.gridx = 0;
        bpgb.gridy = 2;
        bpgb.insets = new Insets(10, 30, 10, 0);
        bpgb.anchor = GridBagConstraints.NORTHWEST;
        lblFormTitle.setText("Guest Form");
        lblFormTitle.setPreferredSize(new Dimension(300, 30));
        lblFormTitle.setForeground(new Color(255, 255, 255));
        lblFormTitle.setFont(new Font("Roboto Medium", 1, 20));
        bookPanel.add(lblFormTitle, bpgb);
        
        bpgb.weightx = 1;
        bpgb.weighty = 0;
        bpgb.gridx = 0;
        bpgb.gridy = 3;
        bpgb.insets = new Insets(5, 30, 0, 0);
        bpgb.anchor = GridBagConstraints.NORTHWEST;
        
        customerPanel.setOpaque(false);
        customerPanel.setLayout(new GridBagLayout());
        
        bookPanel.add(customerPanel, bpgb);
        
        GridBagConstraints cpgbc = new GridBagConstraints();
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 0;
        cpgbc.insets = new Insets(5, 0, 10, 75);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtFirstname.setBackground(colorTheme);
        txtFirstname.setFont(new Font("SansSerif", 0, 14)); 
        txtFirstname.setForeground(new Color(255, 255, 255));
        txtFirstname.setPreferredSize(new Dimension(250, 30));
        txtFirstname.setCaretColor(new Color(255, 255, 255));
        txtFirstname.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtFirstname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstnameFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstnameFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("First Name", txtFirstname);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtFirstname);
        PromptSupport.setForeground(new Color(184, 184, 184), txtFirstname);
        
        customerPanel.add(txtFirstname, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 1;
        cpgbc.gridy = 0;
        cpgbc.insets = new Insets(5, 0, 10, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtSurname.setBackground(colorTheme);
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
        
        customerPanel.add(txtSurname, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 1;
        cpgbc.insets = new Insets(0, 0, 10, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        
        txtMobileNo.setBackground(colorTheme);
        txtMobileNo.setFont(new Font("SansSerif", 0, 14)); 
        txtMobileNo.setForeground(new Color(255, 255, 255));
        txtMobileNo.setPreferredSize(new Dimension(250, 30));
        txtMobileNo.setCaretColor(new Color(255, 255, 255));
        txtMobileNo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        txtMobileNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                mobileFieldFocusGained(evt);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobileFieldFocusLost(evt);
            }
        
        });
        
        PromptSupport.setPrompt("Mobile No.", txtMobileNo);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtMobileNo);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtMobileNo);
        
        customerPanel.add(txtMobileNo, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 2;
        cpgbc.insets = new Insets(0, 0, 10, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        cbNationality.setBackground(colorTheme);
        cbNationality.setFont(new Font("SansSerif", 0, 14)); 
        cbNationality.setForeground(new Color(255, 255, 255));
        cbNationality.setModel(new DefaultComboBoxModel<>(new String[] { "  Select Nationality", "  Nigerian", "  Ghanaian", "  American", "  British" }));
        cbNationality.setBorder(null); 
        cbNationality.setMaximumRowCount(5);
        cbNationality.setPreferredSize(new Dimension(210, 30));
        cbNationality.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedNationality = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
        });
        
        customerPanel.add(cbNationality, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 1;
        cpgbc.gridy = 3;
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        cbDiscount.setBackground(colorTheme);
        cbDiscount.setFont(new Font("SansSerif", 0, 14)); 
        cbDiscount.setForeground(new Color(255, 255, 255));
        cbDiscount.setModel(new DefaultComboBoxModel<>(new String[] { "  Select Discount", "  10", "  25", "  50", "  75"}));
        cbDiscount.setBorder(null); 
        cbDiscount.setMaximumRowCount(5);
        cbDiscount.setPreferredSize(new Dimension(150, 30));
        cbDiscount.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedDiscount = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
        });
        
        customerPanel.add(cbDiscount, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 1;
        cpgbc.gridy = 2;
        cpgbc.insets = new Insets(0, 0, 10, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        cbGender.setBackground(colorTheme);
        cbGender.setFont(new Font("SansSerif", 0, 14)); 
        cbGender.setForeground(new Color(255, 255, 255));
        cbGender.setModel(new DefaultComboBoxModel<>(new String[] { "  Select Gender", "  Male", "  Female"}));
        cbGender.setBorder(null); 
        cbGender.setMaximumRowCount(3); //selectedGender
        cbGender.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedGender = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
        });
        cbGender.setPreferredSize(new Dimension(150, 30));
        
        customerPanel.add(cbGender, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 3;
        cpgbc.insets = new Insets(0, 0, 10, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        cbRoomNo.setBackground(colorTheme);
        cbRoomNo.setFont(new Font("SansSerif", 0, 14)); 
        cbRoomNo.setForeground(new Color(255, 255, 255));
        cbRoomNo.setBorder(null); 
        cbRoomNo.setMaximumRowCount(6);
        cbRoomNo.setPreferredSize(new Dimension(100, 30)); 
        cbRoomNo.addItemListener((ItemEvent ie) -> {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
                selectedRoom = (String)((JComboBox)ie.getSource())
                        .getSelectedItem();
                
            }
        });
        customerPanel.add(cbRoomNo, cpgbc);
        
        cpgbc.weighty = 0;
        cpgbc.gridx = 0;
        cpgbc.gridy = 4;
        cpgbc.insets = new Insets(0, 0, 25, 0);
        cpgbc.anchor = GridBagConstraints.NORTHWEST;
        txtDaysNo.setBackground(colorTheme);
        txtDaysNo.setFont(new Font("SansSerif", 0, 14)); 
        txtDaysNo.setForeground(new Color(255, 255, 255));
        txtDaysNo.setPreferredSize(new Dimension(120, 30));
        txtDaysNo.setCaretColor(new Color(255, 255, 255));
        txtDaysNo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        PromptSupport.setPrompt("No. of Days", txtDaysNo);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtDaysNo);
        PromptSupport.setForeground(new java.awt.Color(184, 184, 184), txtDaysNo);
        customerPanel.add(txtDaysNo, cpgbc);
        
        bpgb.weightx = 1;
        bpgb.weighty = 1;
        bpgb.gridx = 0;
        bpgb.gridy = 4;
        bpgb.anchor = GridBagConstraints.NORTH;

        btnBook.setPreferredSize(new Dimension(180, 35));
        btnBook.setForeground(new Color(255, 255, 255));
        btnBook.setFont(new java.awt.Font("SansSerif", 0, 15)); 
        
        btnBook.addActionListener((ActionEvent ae) -> {
            btnBookActionPerformed(ae);
        });
        
        bookPanel.add(btnBook, bpgb);
        
    }
    
    private void initRestaurantServicePanel() {
        
        restaurantPanel = new JPanel();
        
        restaurantPanel.setLayout(new GridLayout(0,1));
        restaurantPanel.setOpaque(false);
        
        final Image image = requestRestaurantImage();
        
        JPanel restaurantImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        restaurantImagePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints rtipgbc = new GridBagConstraints();
        
        
        
        rtipgbc.weighty = 0;
        rtipgbc.gridx = 1;
        rtipgbc.gridy = 0;
        rtipgbc.anchor = GridBagConstraints.CENTER;
        
        JLabel lblResComingSoon = new JLabel();
        lblResComingSoon.setForeground(new Color(255, 255, 255));
        lblResComingSoon.setFont(new Font("Roboto Medium", 0, 50));
        lblResComingSoon.setText("Coming Soon");
        restaurantImagePanel.add(lblResComingSoon, rtipgbc);
        
        restaurantPanel.add(restaurantImagePanel);
        
    }
    
    private void initcarServicesPanel() {
        
        carServicesPanel = new JPanel();
        
        carServicesPanel.setLayout(new GridLayout(0,1));
        carServicesPanel.setOpaque(false);
        
        final Image image = requestCarServiceImage();
        
        JPanel carImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        carImagePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints cspgbc = new GridBagConstraints();
        
        
        
        cspgbc.weighty = 0;
        cspgbc.gridx = 1;
        cspgbc.gridy = 0;
        cspgbc.anchor = GridBagConstraints.CENTER;
        
        JLabel lblResComingSoon = new JLabel();
        lblResComingSoon.setForeground(new Color(255, 255, 255));
        lblResComingSoon.setFont(new Font("Roboto Medium", 0, 50));
        lblResComingSoon.setText("Coming Soon");
        carImagePanel.add(lblResComingSoon, cspgbc);
        
        carServicesPanel.add(carImagePanel);
        
    }
    
    private void initbarServicesPanel() {
        
        barServicesPanel = new JPanel();
        
        barServicesPanel.setLayout(new GridLayout(0,1));
        barServicesPanel.setOpaque(false);
        
        final Image image = requestBarServiceImage();
        
        JPanel barImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        barImagePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints bspgbc = new GridBagConstraints();
        
        bspgbc.weighty = 0;
        bspgbc.gridx = 1;
        bspgbc.gridy = 0;
        bspgbc.anchor = GridBagConstraints.CENTER;
        
        JLabel lblResComingSoon = new JLabel();
        lblResComingSoon.setForeground(new Color(255, 255, 255));
        lblResComingSoon.setFont(new Font("Roboto Medium", 0, 50));
        lblResComingSoon.setText("Coming Soon");
        barImagePanel.add(lblResComingSoon, bspgbc);
        
        barServicesPanel.add(barImagePanel);
     
    }
     
    private void initlaundryServicesPanel() {
        
        laundryPanel = new JPanel();
        
        laundryPanel.setLayout(new GridLayout(0,1));
        laundryPanel.setOpaque(false);
        
        final Image image = requestLaundryServiceImage();
        
        JPanel laundryImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        laundryImagePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints bspgbc = new GridBagConstraints();
        
        bspgbc.weighty = 0;
        bspgbc.gridx = 1;
        bspgbc.gridy = 0;
        bspgbc.anchor = GridBagConstraints.CENTER;
        
        JLabel lblResComingSoon = new JLabel();
        lblResComingSoon.setForeground(new Color(255, 255, 255));
        lblResComingSoon.setFont(new Font("Roboto Medium", 0, 50));
        lblResComingSoon.setText("Coming Soon");
        laundryImagePanel.add(lblResComingSoon, bspgbc);
        
        laundryPanel.add(laundryImagePanel);
       
    }

    private void btnProfileMouseEntered(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnProfile.setForeground(new Color(255, 255, 255));
    }    
    

    private void btnProfileMouseExited(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        btnProfile.setForeground(new Color(180, 180, 180));
    }   
    
    private void btnProfileMousePressed(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        btnProfile.setForeground(new Color(180, 180, 180));
    }  
    
    private void btnProfileMouseReleased(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        btnProfile.setForeground(new Color(255, 255, 255));
    }      
     
    private void listMainSelection(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        btnProfile.setForeground(new Color(255, 255, 255));
    }   
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) { 
        
        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, ROOM_SERVICES_PANEL);
      //  layout.show(parent, prevTab);
    }
    
    private void btnEconomyRoomActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        lblRoomImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/economy_image_large.jpg")));
        lblName.setText(strEconomy);
        
        dbc.getRoomTypesData(strEconomy);
        
        lblPrice.setText(String.valueOf(fmt.format(dbc.getPrice())));
        lblPerDay.setText("per night");
        
        lblDesc.setText("<html><div>" + dbc.getDescription() + "</div></html>");
       
        lblRmAvailable.setText(String.valueOf(lisAvailableEconomyRooms.size()) + " rooms available");
        
        dcboRmNo = new DefaultComboBoxModel();
        lisRoomNos = new LinkedList<>();
        updateDcboRooms(strEconomy);
        cbRoomNo.setModel(dcboRmNo);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, BOOKING_ROOM_PANEL);
        
       
    }  
    
    private void btnExecutiveRoomActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        lblRoomImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/executive_images_large.jpg")));
        lblName.setText(strExecutive);
        
        dbc.getRoomTypesData(strExecutive);
        
        lblPrice.setText(String.valueOf(dbc.getPrice()));
        lblPerDay.setText("per night");
        
        lblDesc.setText("<html><div>" + dbc.getDescription() + "</div></html>");
        
        lblRmAvailable.setText(String.valueOf(lisAvailableExecutiveRooms.size()) + " rooms available");
        
        dcboRmNo = new DefaultComboBoxModel();
        lisRoomNos = new LinkedList<>();
        updateDcboRooms(strExecutive);
        cbRoomNo.setModel(dcboRmNo);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, BOOKING_ROOM_PANEL);
        
        
    }  
    
    private void btnDeluxeRoomActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        lblRoomImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deluxe_images_large.jpg")));
        lblName.setText(strDeluxe);
        
        dbc.getRoomTypesData(strDeluxe);
        
        lblPrice.setText(String.valueOf(dbc.getPrice()));
        lblPerDay.setText("per night");
        
        lblDesc.setText("<html><div>" + dbc.getDescription() + "</div></html>");
        
        lblRmAvailable.setText(String.valueOf(lisAvailableDeluxeRooms.size()) + " rooms available");
        
        dcboRmNo = new DefaultComboBoxModel();
        lisRoomNos = new LinkedList<>();
        updateDcboRooms(strDeluxe);
        cbRoomNo.setModel(dcboRmNo);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, BOOKING_ROOM_PANEL);
    }  
    
      private void btnRoyalDeluxeRoomActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        lblRoomImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/royal_deluxe_images_large.jpg")));
        lblName.setText(strRoyalDeluxe);
        
        dbc.getRoomTypesData(strRoyalDeluxe);
        
        lblPrice.setText(String.valueOf(dbc.getPrice()));
        lblPerDay.setText("per night");
        
        lblDesc.setText("<html><div>" + dbc.getDescription() + "</div></html>");
        
        lblRmAvailable.setText(String.valueOf(lisAvailableRoyalDeluxeRooms.size()) + " rooms available");
        
        dcboRmNo = new DefaultComboBoxModel();
        lisRoomNos = new LinkedList<>();
        updateDcboRooms(strRoyalDeluxe);
        cbRoomNo.setModel(dcboRmNo);

        CardLayout cl = (CardLayout)(contentPanel.getLayout());
        cl.show(contentPanel, BOOKING_ROOM_PANEL);
    } 
    
    private void firstnameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtFirstname.setBackground(textFieldHighlightColor);
       
    }       
    
    private void firstnameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtFirstname.setBackground(colorTheme);
       
    } 
    
    private void surnameFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtSurname.setBackground(textFieldHighlightColor);
    }       
    
    private void surnameFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtSurname.setBackground(colorTheme);   
    } 
    
    private void mobileFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtMobileNo.setBackground(textFieldHighlightColor);
    }       
    
    private void mobileFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtMobileNo.setBackground(colorTheme);   
    } 
    
    private void oldPassFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprOldPass.setBackground(textFieldHighlightColor);
    }       
    
    private void oldPassFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprOldPass.setBackground(colorTheme);   
    }
    
    private void newPassFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprNewPass.setBackground(textFieldHighlightColor);
    }       
    
    private void newPassFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprNewPass.setBackground(colorTheme);   
    }
    
     private void cnfmPassFieldFocusGained(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprConfirmPass.setBackground(textFieldHighlightColor);
    }       
    
    private void cnfmPassFieldFocusLost(java.awt.event.FocusEvent evt) {                                            
        // TODO add your handling code here:
       txtprConfirmPass.setBackground(colorTheme);   
    }
    
    private void btnUpdatePassActionPerformed(java.awt.event.ActionEvent evt) {
        
        if(txtprOldPass.getText().equals("") || txtprNewPass.getText().equals("") || 
                txtprConfirmPass.getText().equals("")) {
            
            JOptionPane.showMessageDialog(this, "<html><font color='white'>All fields must be filled to update password</font></html>", 
                    "Missing Input", JOptionPane.ERROR_MESSAGE);
            
        }
        else {
            
            if(!txtprNewPass.getText().equals(txtprConfirmPass.getText())) {
                JOptionPane.showMessageDialog(this, "<html><font color='white'>The new password and confirm password are not the same</font></html>", 
                    "Wrong Inputs", JOptionPane.ERROR_MESSAGE);
            }
            else {
                //Do something!
                String NewPass = txtprNewPass.getText();
                String hashedNewPass =  DigestUtils.sha1Hex(NewPass);
                
                String OldPass = txtprOldPass.getText();
                String hashedOldPass =  DigestUtils.sha1Hex(OldPass);
                
                String getOldPassword = dbc.retrieveOldPass(btnProfile.getText());
                
               // System.out.println("Database Password: " + getOldPassword + "\n" + "Derived Password: "+ hashedOldPass);
                
                if(!getOldPassword.equals(hashedOldPass)) {
                    JOptionPane.showMessageDialog(this, "<html><font color='white'>You entered the wrong old password!</font></html>", 
                    "Wrong Password", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    dbc.changePassword(hashedNewPass, btnProfile.getText());
                    txtprNewPass.setText("");
                    txtprConfirmPass.setText("");
                    txtprOldPass.setText("");
                    JOptionPane.showMessageDialog(this, "<html><font color='white'>Your password has been successfully updated</font></html>", 
                    "Sucessful", JOptionPane.INFORMATION_MESSAGE);
                    
                }
              
            }
        }
        
    }
            

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        String username = btnProfile.getText().trim();
        String firstName = txtFirstname.getText().trim();
        String surName = txtSurname.getText().trim();
        String mobile = txtMobileNo.getText().trim();
        String sDays = txtDaysNo.getText().trim();
        String rmType = lblName.getText().trim();
        String rmNo = selectedRoom.trim();
       
        double price = Double.valueOf(lblPrice.getText().trim());
        int stayDuraion = Integer.valueOf(txtDaysNo.getText().trim());
        double totAmt = price * stayDuraion;
        int dcnt;
        int styDays = Integer.valueOf(sDays.trim());
        if(selectedDiscount.trim().equals("Select Discount") || selectedDiscount.equals("")) {
            dcnt = 0;
        }
        else {
            dcnt = Integer.valueOf(selectedDiscount.trim());  
            if(dcnt > 0) {
                double dcntAmt = totAmt * dcnt / 100;
                totAmt = totAmt - dcntAmt;
            }
        }

        receiptDialog = new ReceiptDialog(this, true, 
                firstName, surName, mobile, sDays, selectedNationality.trim(), selectedGender.trim(), 
                selectedDiscount.trim(), rmType, rmNo, String.valueOf(price), 
                nowDate(), departureDate(styDays), String.valueOf(totAmt), contentPanel);
        
        dbc.insertCheckin(username, firstName, surName, mobile, selectedNationality.trim(), selectedGender.trim(), 
                rmType, rmNo, price, stayDuraion, totAmt, nowDate(), departureDate(styDays), dcnt);
        
        dbc.setBookingStatus(1, rmNo);
        
        receiptDialog.setVisible(true);
        
        txtFirstname.setText("");
        txtSurname.setText("");
        txtMobileNo.setText("");
        txtDaysNo.setText("");
        
        cbGender.setSelectedIndex(0);
        cbRoomNo.setSelectedIndex(0);
        cbNationality.setSelectedIndex(0);
        cbDiscount.setSelectedIndex(0);

    }  
    
    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) { 
        
        String username = btnProfile.getText();
        String firstName = txtckoFirstname.getText();
        String surName = txtckoSurname.getText();
        String mobile = txtckoMobileNo.getText();
        String nationality = txtckoNationality.getText();
        String gender = txtckoGender.getText();
        
        String rmType = txtckoRmType.getText();
        String rmNo = txtckoRmNo.getText();
        String rmPrice = txtckoRmPrice.getText();
        String checkinDateTimes = txtckoCheckinDate.getText();
        String drptDateTime = txtckoSetDeparture.getText();
        String checkoutDateTimes = txtckoCheckoutDate.getText();
        String daysSpent = lblDaysSpentValue.getText();
        String totalAmt = lblTotAmtValue.getText();
        
        String total = totalAmt.substring(1);
        
        int response = JOptionPane.showConfirmDialog(null, "<html><font color='white'>Do you want to checkout?</font></html>",
                "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (response == JOptionPane.YES_OPTION) {
            
            txtckoFirstname.setText("");
            txtckoSurname.setText("");
            txtckoMobileNo.setText("");
            txtckoNationality.setText("");
            txtckoGender.setText("");
        
            txtckoRmType.setText("");
            txtckoRmNo.setText("");
            txtckoRmPrice.setText("");
            txtckoStayDays.setText("");
            txtckoTotCost.setText("");
            txtckoCheckinDate.setText("");
            txtckoSetDeparture.setText("");
            txtckoCheckoutDate.setText("");
            lblDaysSpentValue.setText("0");
            lblTotAmtValue.setText("0.00");
            
            dbc.insertCheckout(username, firstName, surName, mobile, nationality, gender, rmType, rmNo, 
                    Double.valueOf(rmPrice), checkinDateTimes, drptDateTime, checkoutDateTimes,
                    Integer.valueOf(daysSpent), Double.valueOf(total));
            dbc.updateBookingStatus(0, rmNo);
            
            dbc.deleteCheckInForCheckOut(rmNo);
            
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, ROOM_SERVICES_PANEL);
            
            checkoutFrame.setTitle("Receipt");
            checkoutFrame.setSize(300, 500);
            checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());

            mTextPane = new JTextPane();
            mTextPane.setContentType("text/html");
            
            builder = new StringBuilder();
            builder.append("<h1>CHECKOUT FORM</h1><table width=\"100%\">");
            builder.append("<tr><td>Receptionist  :</td><td>").append(username).append("</td></tr>");
            builder.append("<tr><td>First Name    :</td><td>").append(firstName).append("</td></tr>");
            builder.append("<tr><td>Last Name     :</td><td>").append(surName).append("</td></tr>");
            builder.append("<tr><td>Room Type     :</td><td>").append(rmType).append("</td></tr>");
            builder.append("<tr><td>Room No.      :</td><td>").append(rmNo).append("</td></tr>");
            builder.append("<tr><td>Room Price    :</td><td>").append(nairaSymbol).append(rmPrice).append("</td></tr>");
            builder.append("<tr><td>Checkin       :</td><td>").append(checkinDateTimes).append("</td></tr>");
            builder.append("<tr><td>Checkout      :</td><td>").append(checkoutDateTimes).append("</td></tr>");
            builder.append("<tr><td>Days Spent    :</td><td>").append(daysSpent).append("</td></tr>");
            builder.append("<tr><td></td><td></td></tr>");
            builder.append("<tr><td>Total Amount  :</td><td>").append(nairaSymbol).append(total).append("</td></tr>");
            builder.append("</table>");

            mTextPane.setText(builder.toString());

            JButton previewButton = new JButton("Preview");
            previewButton.addActionListener((ActionEvent ae) -> {
                printActionPerformed(ae);
            });

            panel.add(new JScrollPane(mTextPane), BorderLayout.CENTER);
            panel.add(previewButton, BorderLayout.SOUTH);
            checkoutFrame.getContentPane().add(panel);
            
            checkoutFrame.setVisible(true);

        }    
    }
   
    public String getNationality(String nationality) {
        return this.selectedNationality = nationality;
    }
    
    private static String nowDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }
    
    private static String departureDate(int sDays) {
        Calendar calr = Calendar.getInstance();
        SimpleDateFormat ddf = new SimpleDateFormat(DATE_FORMAT);
        calr.set(Calendar.HOUR_OF_DAY, 12);
        calr.set(Calendar.MINUTE, 0);
        calr.set(Calendar.SECOND, 0);
        
        calr.add(Calendar.DATE, sDays);
      
        return ddf.format(calr.getTime());
    }
    
    private void updateDcboRooms(String rmType) {

        lisRoomNos = dbc.getRoomNo(rmType);
        dcboRmNo.removeAllElements();
        dcboRmNo.addElement("  Room No.");
        lisRoomNos.forEach((lisGetRoomNos) -> {
            dcboRmNo.addElement(lisGetRoomNos);
        });
    }
    
    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) { 
        dbc.setUserLogoutStatus(0, 1, nowDate(), btnProfile.getText());
      //  this.dispose();
      //  new LauncherPage().setVisible(true);
        System.exit(0);
    }
    
    private Image requestRestaurantImage() {
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResource("/images/restaurant_image.jpg"));
        } catch (IOException e) {
        }

        return image;
    }
    
    private Image requestCarServiceImage() {
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResource("/images/car_services_image.jpg"));
        } catch (IOException e) {
        }

        return image;
    }
    
    private Image requestBarServiceImage() {
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResource("/images/bar_image.jpg"));
        } catch (IOException e) {
        }

        return image;
    }
    
    private Image requestLaundryServiceImage() {
        
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResource("/images/laundry_image.jpg"));
        } catch (IOException e) {
        }

        return image;
    }
    
    public void loadJSONFromResource() {
        
        JSONParser parser = new JSONParser();
        
        try {
            //"resources/json/green_valley_common_currency.json"
            Object obj = parser.parse(new FileReader(getClass().getResource("/json/common_currency.json").getFile()));
            JSONObject jsonObject = (JSONObject) obj;
              
            JSONObject currencyObj = (JSONObject) jsonObject.get("NGN");
            nairaSymbol = (String) currencyObj.get("symbol");
           
        } catch (IOException ex) {
           
        } catch (org.json.simple.parser.ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  

   private void printActionPerformed(java.awt.event.ActionEvent evt) { 
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(700, 400);
        dialog.setLayout(new BorderLayout());
        
        checkoutFrame.dispose();

        HashPrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        set.add(MediaSizeName.ISO_A4);
        set.add(OrientationRequested.PORTRAIT);
        
        PageFormat pf = PrinterJob.getPrinterJob().getPageFormat(set);

        final PrintPreview preview = new PrintPreview(mTextPane.getPrintable(null, null), pf);

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

}