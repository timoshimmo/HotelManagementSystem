/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.database;

import greenvalley.models.RoomTypes;
import greenvalley.models.Checkin;
import greenvalley.models.Checkout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author freshfuturesmy
 */
public class DatabaseConnection {
    
    private Connection con = null;
    private Statement s = null;
    PreparedStatement pst;
    ResultSet rs;
    int responseId = 0;
    int adminLoginId = 0;
    int adminMasterLoginId = 0;
    private LinkedList<String> lisDates, lisRoomNo;
    RoomTypes roomTypes;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    DecimalFormat fmt;
    
    public DatabaseConnection() {
         
        //Connection to Database 
    	try {
            String UName = "root";
            String PassWord = "";
            String url = "jdbc:mysql://localhost:3306/greenvalley";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, UName, PassWord);
        }
        catch (ClassNotFoundException | SQLException e) {
           System.out.println("Connection Error: Could not connect to database \n" + e.getMessage());
        }
 
    }
    
    public void userValidation(String uname, String pword) {

        try {

            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);

            try (ResultSet rss = s.executeQuery("SELECT * FROM users WHERE username = '" + uname + "' AND password = '" + pword + "'")) {
                if (rss.next()) {
                    responseId = 1;         
                }
                else {
                    responseId = 0;
                }
            }
            s.close();
       
        }
        catch (SQLException e) {
            System.err.println("Error Connecting to Table users: \n" + e.getMessage());
        }

    }
    
    public void adminValidation(String userId, String pword) {

        try {

            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);

            try (ResultSet rss = s.executeQuery("SELECT * FROM admin WHERE userId = '" + userId + "' AND password = '" + pword + "'")) {
                if (rss.next()) {
                    adminLoginId = 1;         
                }
                else {
                    adminLoginId = 0;
                }
            }
            s.close();
       
        }
        catch (SQLException e) {
            System.err.println("Error Connecting to Table admin: \n" + e.getMessage());
        }

    }
    
    public void adminMasterValidation(String userId, String mPword) {

        //Password = backentrance.
        try {
            s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);

            try (ResultSet rss = s.executeQuery("SELECT * FROM admin WHERE userId = '" + userId + "' AND master_pass = '" + mPword + "'")) {
                if (rss.next()) {
                    adminMasterLoginId = 1;         
                }
                else {
                    adminMasterLoginId = 0;
                }
            }
            s.close();
      
        }
        catch (SQLException e) {
            System.err.println("Error Connecting to Table admin: \n" + e.getMessage());
        }
    }
    
    public void setuserPassword(JFrame f, String password, String username) {
        
        try {
                String query = "UPDATE users SET password = ? WHERE username = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString(1, password);
                preparedStmt.setString(2, username);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(f, "<html><font color='white'>This username was not found. Retype the correct username and try again.</font></html>", 
                    "Wrong Username", JOptionPane.ERROR_MESSAGE);
            }
            con.close();    
        }
        catch(SQLException e) {
            System.err.println("Error in updating password in user table: \n" + e.getMessage());
        }
    }
     
    public void setUserLoginStatus(int login_status, int logout_status, String dateTime, String username) {
        
        try {
                String query = "UPDATE users SET login_status = ?, logout_status = ?, login_time = ? WHERE username = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setInt(1, login_status);
                preparedStmt.setInt(2, logout_status);
                preparedStmt.setString(3, dateTime);
                preparedStmt.setString(4, username);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
             
        }
        catch(SQLException e) {
            System.err.println("Error in updating login status in user table: \n" + e.getMessage());
        }
    }
    
    public void setUserLogoutStatus(int login_status, int logout_status, String dateTime, String username) {
        
        try {
                String query = "UPDATE users SET login_status = ?, logout_status = ?, logout_time = ? WHERE username = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setInt(1, login_status);
                preparedStmt.setInt(2, logout_status);
                preparedStmt.setString(3, dateTime);
                preparedStmt.setString(4, username);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
               
        }
        catch(SQLException e) {
            System.err.println("Error in updating logout status in user table: \n" + e.getMessage());
        }
    }
    
    public void updateBookingStatus(int bkStatus, String rmNo) {
  
        try {
            String query = "UPDATE rooms SET booking_status = ? WHERE room_no = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setDouble(1, bkStatus);
                preparedStmt.setString(2, rmNo);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
                
            }
          //  con.close();    
        }
        catch(SQLException e) {
            System.err.println("Error in updating booking status in rooms table: \n" + e.getMessage());
        }
    } 
    
    public String getActiveUser() {
        
        String activeUserName = "";
        
        try  {
            s = con.createStatement();
            String sql = "SELECT username FROM users WHERE login_status = 1";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) { 
                if(!rst.next()) {
                    activeUserName = "None";
                }
                else {
                    activeUserName = rst.getString("username");
                }
            	
            }
           
            s.close();
        }
        catch (SQLException e) {
            System.out.println("Get Active User > SQL ERROR : " + e.getMessage());
        }
        
        return activeUserName;
    }
    
    public String getLastActiveUser() {
        
        String lastActiveUserName = "";
        
        try  {
            s = con.createStatement();
            String sql = "SELECT username FROM users WHERE logout_status = 1";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) {
                rst.next();
            	lastActiveUserName = rst.getString("username");
            }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("Get Last Active User > SQL ERROR : " + e.getMessage());
        }
        
        return lastActiveUserName;
    }
    
    public String getActiveUserDateTime() {
        
        String activeUsersDateTime = "";
        
        try  {
            s = con.createStatement();
            String sql = "SELECT login_time FROM users WHERE login_status = 1 ORDER BY login_time LIMIT 1";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) { 
                if(!rst.next()) {
                    activeUsersDateTime = "-";
                }
                else {
                    activeUsersDateTime = rst.getString("login_time");
                }
            	
            }
           
            s.close();
        }
        catch (SQLException e) {
            System.out.println("Get Active User Date & Time > SQL ERROR : " + e.getMessage());
        }
        
        return activeUsersDateTime;
    }
    
    public String getLastActiveUserDateTime() {
        
        String lastActiveUsersDateTime = "";
        
        try  {
            s = con.createStatement();
            String sql = "SELECT logout_time FROM users WHERE logout_status = 1 ORDER BY logout_time LIMIT 1";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) { 
                if(!rst.next()) {
                    lastActiveUsersDateTime = "-";
                }
                else {
                    lastActiveUsersDateTime = rst.getString("logout_time");
                }
            }
           
            s.close();
        }
        catch (SQLException e) {
            System.out.println("Get Last Active User Date & Time > SQL ERROR : " + e.getMessage());
        }
        
        return lastActiveUsersDateTime;
    }
     
    
    public void insertUser(String uname, String Fname, String Sname, String pWord) {
        
        try {
            String query = "INSERT INTO users (username, firstname, lastname, password, login_status)"
                    + " values (?, ?, ?, ?, ?)";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString (1, uname);
                preparedStmt.setString (2, Fname);
                preparedStmt.setString (3, Sname);
                preparedStmt.setString (4, pWord);
                preparedStmt.setInt (5, 0);
                
                preparedStmt.executeUpdate();
            }
       //     con.close();
              
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table checkin: \n" + e.getMessage());
        }
    }
    
    public void deleteUser(String uname) {
        
        try {
            String query = "DELETE FROM users WHERE username = ?";
              
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString (1, uname);

            preparedStmt.execute();
            con.close();
              
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table checkin: \n" + e.getMessage());
        }
    }
    
    public HashMap<String, String> getAllUsers(String user) {
        
        HashMap<String, String> map = new HashMap<>();
       
        try {
            s = con.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + user + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
                   
                   map.put("UNAME", rst.getString("username"));
                   map.put("FNAME", rst.getString("firstname"));
                   map.put("LNAME", rst.getString("lastname"));
                   
                //   System.out.println("USERNAME: ");

               }
            s.close();
        }
        
        catch (SQLException e) {
            System.out.println("retrieve users for profile > SQL ERROR : " + e.getMessage());
        }
        
      //  System.out.println(map.get("UNAME"));
         
        return map;
    }
     
    public void retrieveUsers(DefaultTableModel model) {
        
        String username;
        String fname;
        String lname;
        
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT username, firstname, lastname FROM users";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
            	   username = rst.getString("username");
                   fname = rst.getString("firstname");
                   lname = rst.getString("lastname");
                   model.addRow(new Object[]{username, fname, lname, new ImageIcon(getClass().getResource("/images/rounded-remove-button.png"))});
               }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve users > SQL ERROR : " + e.getMessage());
        }
    }
    
  // ada
            
    public void retrieveCustomers(DefaultTableModel model) {
        
        String fullname;
        String mobile;
        String nationality;
        String gender;
        String room;
        int days;
        
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT customer_fname, customer_surname, customer_mobile, customer_nationality, customer_gender, room_no, stay_days FROM checkin";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
            	   fullname = rst.getString("customer_fname") + " " + rst.getString("customer_surname");
                   mobile = rst.getString("customer_mobile");
                   nationality = rst.getString("customer_nationality");
                   gender = rst.getString("customer_gender");
                   room = rst.getString("room_no");
                   days = rst.getInt("stay_days");
                   model.addRow(new Object[]{fullname, mobile, nationality, gender, room, days});
               }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve customers > SQL ERROR : " + e.getMessage());
        }
    }
    
    
    public String retrieveOldPass(String user) {
        
         String password = "";
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT password FROM users WHERE username = '" + user + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) {
                rst.next();
            	password = rst.getString("password"); 
            }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve password to compare old password > SQL ERROR : " + e.getMessage());
        }
         
        return password;
    }
    
    public void changePassword(String newPass, String username) {
        
        
        try {
            String query = "UPDATE users SET password = ? WHERE username = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString(1, newPass);
                preparedStmt.setString(2, username);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
                
            } 
        }
        catch(SQLException e) {
            System.err.println("Error in updating password in users table: \n" + e.getMessage());
        }
    } 
    
    public void retrieveRoomTypes(DefaultTableModel model) {
        
        String rmTYpe;
        double price;
        String desc;
        
        try {
            s = con.createStatement();
            String sql = "SELECT * FROM roomtypes";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
            	   rmTYpe = rst.getString("room_type");
                   price = rst.getDouble("price");
                   desc = rst.getString("description");
                   model.addRow(new Object[]{rmTYpe, price, desc, new ImageIcon(getClass().getResource("/images/edit.png"))});
               }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve all room types > SQL ERROR : " + e.getMessage());
        }
    } 
    
    public void retrieveRooms(DefaultTableModel model) {
        
        String rmTYpe;
        String rmNo;
        String bookingStatus;
        int bkStatus;
        
        try {
            s = con.createStatement();
            String sql = "SELECT * FROM rooms";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
                   rmNo = rst.getString("room_no");
            	   rmTYpe = rst.getString("room_type");
                   bkStatus = rst.getInt("booking_status");
                   
                   if(bkStatus == 0) {
                      bookingStatus = "Available"; 
                   }
                   else {
                       bookingStatus = "Occupied";
                   }
                   model.addRow(new Object[]{rmTYpe, rmNo, bookingStatus, 
                       new ImageIcon(getClass().getResource("/images/rounded-remove-button.png"))});
               }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve all rooms > SQL ERROR : " + e.getMessage());
        }
    } 
    
    public void updateRoomTypes(DefaultTableModel mdl, int row, double pr, String desc, String rmType) {
        
        
        try {
            String query = "UPDATE roomtypes SET price = ?, description = ? WHERE room_type = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setDouble(1, pr);
                preparedStmt.setString(2, desc);
                preparedStmt.setString(3, rmType);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
                
                mdl.setValueAt(pr, row, 1);
                mdl.setValueAt(desc, row, 2);
            }
        //    con.close();    
        }
        catch(SQLException e) {
            System.err.println("Error in updating price and description in roomtypes table: \n" + e.getMessage());
        }
    } 
    
    public void insertRoom(String rmNo, String rmType, int bkStatus) {
        
        try {
            String query = "INSERT INTO rooms (room_no, room_type, booking_status)"
                    + " values (?, ?, ?)";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString (1, rmNo);
                preparedStmt.setString (2, rmType);
                preparedStmt.setInt (3, bkStatus);
                
                preparedStmt.executeUpdate();
               
            }
        //    con.close();
              
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table rooms to insert values: \n" + e.getMessage());
        }
    }
    
    public void deleteRoom(String rmNo) {
        
        try {
            String query = "DELETE FROM rooms WHERE room_no = ?";
              
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString (1, rmNo);

            preparedStmt.execute();
         //   con.close();
              
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table rooms to delete values: \n" + e.getMessage());
        }
    }
    
    
    public void getRoomTypesData(String roomType) {
        
	double price;
        String desc;
        
        try  {
            s = con.createStatement();
            String sql = "SELECT * FROM roomtypes WHERE room_type = '" + roomType + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) {
                rst.next();
            	price = rst.getDouble("price");
                desc = rst.getString("description");
                
                roomTypes = new RoomTypes(price, desc);
            }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve room type > SQL ERROR : " + e.getMessage());
        }
        
    }
    
    public LinkedList<String> getRoomNo(String roomType) {
        
        LinkedList<String> availableRooms = new LinkedList<>();
        try {
            s = con.createStatement();
            String sql = "SELECT room_no FROM rooms WHERE room_type = '" + roomType + "' AND booking_status = 0";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null) {
               while (rst.next()) {
                   availableRooms.add(rst.getString("room_no"));
               }
            }
            s.close();
            
        }
        catch (SQLException e) {
            System.out.println("retrieve room type and get room count> SQL ERROR : " + e.getMessage());
        }
        
        
        return availableRooms;
    }
    
     public void setBookingStatus(int status, String rmNo) {
        
        try {
                String query = "UPDATE rooms SET booking_status = ? WHERE room_no = ?";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setInt(1, status);
                preparedStmt.setString(2, rmNo);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
        //    con.close();    
        }
        catch(SQLException e) {
            System.err.println("Error in updating user table: \n" + e.getMessage());
        }
    }
    
    public void insertCheckin(String uname, String cFname, String cSname, String cMno, String cNlty, String cGndr, 
            String rmType, String rmNo, double rmPr, int sDays, double totAmt, 
            String arrDateTime, String dptDateTime, int dcnt) {
        
        try {
            String query = "INSERT INTO checkin (username, customer_fname, customer_surname, customer_mobile, "
                    + "customer_nationality, customer_gender, room_type, room_no, room_price, stay_days, "
                    + "cost, arrival_date, departure_date, discount)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString (1, uname);
                preparedStmt.setString (2, cFname);
                preparedStmt.setString (3, cSname);
                preparedStmt.setString (4, cMno);
                preparedStmt.setString (5, cNlty);
                preparedStmt.setString (6, cGndr);
                preparedStmt.setString (7, rmType);
                preparedStmt.setString (8, rmNo);
                preparedStmt.setDouble(9, rmPr);
                preparedStmt.setInt(10, sDays);
                preparedStmt.setDouble(11, totAmt);
                preparedStmt.setString(12, arrDateTime);
                preparedStmt.setString(13, dptDateTime);
                preparedStmt.setInt(14, dcnt);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
         //   con.close();  
        }
        catch(SQLException e) {
             System.err.println("Error inserting into checkin: \n" + e.getMessage());
        }
    }
    
    public void insertCheckout(String uname, String cFname, String cSname, String cMno, String cNlty, String cGndr, 
            String rmType, String rmNo, double rmPr, String arrDateTime, String dptDateTime, String checkoutDateTime, 
            int daysSpent, double totAmt) {
        
        try {
            String query = "INSERT INTO checkout (username, firstname, surname, mobileNo, "
                    + "nationality, gender, roomType, roomNo, roomPrice, arrival, "
                    + "departure, checkout, daysSpent, totAmt)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
              
            try (PreparedStatement preparedStmt = con.prepareStatement(query)) {
                preparedStmt.setString (1, uname);
                preparedStmt.setString (2, cFname);
                preparedStmt.setString (3, cSname);
                preparedStmt.setString (4, cMno);
                preparedStmt.setString (5, cNlty);
                preparedStmt.setString (6, cGndr);
                preparedStmt.setString (7, rmType);
                preparedStmt.setString (8, rmNo);
                preparedStmt.setDouble(9, rmPr);
                preparedStmt.setString(10, arrDateTime);
                preparedStmt.setString(11, dptDateTime);
                preparedStmt.setString(12, checkoutDateTime);
                preparedStmt.setInt(13, daysSpent);
                preparedStmt.setDouble(14, totAmt);
                
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
         //   con.close();  
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table checkout: \n" + e.getMessage());
        }
    }
    
    public void retrieveCheckIn(DefaultTableModel model) {
        
        String username;
        String fullname;
        String rmType;
        String rmNo;
        double price;
        int days;
        String aDate;
        double cost;
        
        fmt = new DecimalFormat("0.00");
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkin";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
            	   username = rst.getString("username");
                   fullname = rst.getString("customer_fname") + " " + rst.getString("customer_surname");
                   rmType = rst.getString("room_type");
                   rmNo = rst.getString("room_no");
                   price = rst.getDouble("room_price");
                   days = rst.getInt("stay_days");
                   aDate = rst.getString("arrival_date");
                   cost = rst.getDouble("cost");
                   
                   fmt.format(price);
                   fmt.format(cost);
                   
                   model.addRow(new Object[]{username, fullname, rmType, rmNo, price, days, aDate, cost});
                  
               }
            s.close();
         //   con.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve checkin > SQL ERROR : " + e.getMessage());
        }
    }
    
    public void retrieveCheckInToCheckout(DefaultTableModel model) {
        
       // String username;
        String fname;
        String sname;
        String nationality;
        String gender;
        String mobile;
        String rmType;
        String rmNo;
        double price;
        int days;
        String aDate;
        String dDate;
        double cost;
        
        SimpleDateFormat ddf = new SimpleDateFormat(DATE_FORMAT);
        fmt = new DecimalFormat("0.00");
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkin";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
                   fname = rst.getString("customer_fname");
                   sname = rst.getString("customer_surname");
                   nationality = rst.getString("customer_nationality");
                   gender = rst.getString("customer_gender");
                   mobile = rst.getString("customer_mobile");
                   rmType = rst.getString("room_type");
                   rmNo = rst.getString("room_no");
                   price = rst.getDouble("room_price");
                   days = rst.getInt("stay_days");
                   aDate = rst.getString("arrival_date");
                   dDate = rst.getString("departure_date");
                   cost = rst.getDouble("cost");
                   
                   fmt.format(price);
                   fmt.format(cost);
                   
                   model.addRow(new Object[]{fname, sname, nationality, gender, mobile, rmType, rmNo, price, days, aDate, dDate, cost});
                  
               }
            s.close();
         //   con.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve checkin to checkout > SQL ERROR : " + e.getMessage());
        }
    }
     
    public void retrieveUserCheckIn(DefaultTableModel model, String user) {
        
        String fullname;
        String rmType;
        String rmNo;
        double price;
        int days;
        String aDate;
        double cost;
        
        fmt = new DecimalFormat("0.00");
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkin WHERE username = '" + user + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
                   fullname = rst.getString("customer_fname") + " " + rst.getString("customer_surname");
                   rmType = rst.getString("room_type");
                   rmNo = rst.getString("room_no");
                   price = rst.getDouble("room_price");
                   days = rst.getInt("stay_days");
                   aDate = rst.getString("arrival_date");
                   cost = rst.getDouble("cost");
                   
                   fmt.format(price);
                   fmt.format(cost);
                   
                   model.addRow(new Object[]{fullname, rmType, rmNo, price, days, aDate, cost});
                  
               }
            s.close();
            
        }
        catch (SQLException e) {
            System.out.println("retrieve user checkin > SQL ERROR : " + e.getMessage());
        }
    }
    
    public void retrieveCheckOut(DefaultTableModel model) {
        
        String username;
        String fullname;
        String rmType;
        String rmNo;
        double price;
        String checkin;
        String checkout;
        int days;
        double total;
        
        fmt = new DecimalFormat("0.00");
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkout";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
            	   username = rst.getString("username");
                   fullname = rst.getString("firstname") + " " + rst.getString("surname");
                   rmType = rst.getString("roomType");
                   rmNo = rst.getString("roomNo");
                   price = rst.getDouble("roomPrice");
                   checkin = rst.getString("arrival");
                   checkout = rst.getString("checkout");
                   days = rst.getInt("daysSpent");
                   total = rst.getDouble("totAmt");
                   
                   fmt.format(price);
                   fmt.format(total);
                   
                   model.addRow(new Object[]{username, fullname, rmType, rmNo, price, checkin, checkout, days, total});
                  
               }
            s.close();
         //   con.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve checkout > SQL ERROR : " + e.getMessage());
        }
    }
    
    public void deleteCheckInForCheckOut(String rmNo) {
        
         try {
            String query = "DELETE FROM checkin WHERE room_no = ?";
              
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString (1, rmNo);

            preparedStmt.execute();
              
        }
        catch(SQLException e) {
             System.err.println("Error Connecting To Table checkin to delete checkin for checkout: \n" + e.getMessage());
        }
    }
    
    public void retrieveUserCheckOut(DefaultTableModel model, String user) {
        
        String fullname;
        String rmType;
        String rmNo;
        double price;
        int days;
        String ckoDate;
        double cost;
        
        fmt = new DecimalFormat("0.00");
    	 
         try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkout WHERE username = '" + user + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
               while (rst.next()) {
                   fullname = rst.getString("firstname") + " " + rst.getString("surname");
                   rmType = rst.getString("roomType");
                   rmNo = rst.getString("roomNo");
                   price = rst.getDouble("roomPrice");
                   days = rst.getInt("daysSpent");
                   ckoDate = rst.getString("checkout");
                   cost = rst.getDouble("totAmt");
                   
                   fmt.format(price);
                   fmt.format(cost);
                   
                   model.addRow(new Object[]{fullname, rmType, rmNo, price, days, ckoDate, cost});
                  
               }
            s.close();
        }
        catch (SQLException e) {
            System.out.println("retrieve user checkout > SQL ERROR : " + e.getMessage());
        }
    }
    
    public LinkedList<String> getCheckInDates() {
        
        lisDates = new LinkedList<>();
        lisDates.add("Select Date");
        try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkin";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
                while (rst.next()) {
                 //  Vector eachDate = new Vector();
                   lisDates.add(rst.getString("arrival_date"));
                }
            s.close();
        //    con.close();
        }
        catch (SQLException e) {
            System.out.println("Get Active Checkin Dates > SQL ERROR : " + e.getMessage());
        }
        return lisDates;
    }
    
    public LinkedList<String> getCheckOutDates() {
        
        lisDates = new LinkedList<>();
        lisDates.add("Select Date");
        try {
            s = con.createStatement();
            String sql = "SELECT * FROM checkout";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            if (rst != null)
                while (rst.next()) {
                 //  Vector eachDate = new Vector();
                   lisDates.add(rst.getString("checkout"));
                }
            s.close();
        //    con.close();
        }
        catch (SQLException e) {
            System.out.println("Get Active Checkout Dates > SQL ERROR : " + e.getMessage());
        }
        return lisDates;
    }
    
    public ArrayList<Checkin> searchCheckin(String frm, String to) {
        
        ArrayList<Checkin> searchList = new ArrayList<>();
        
        try {
            
            s = con.createStatement();
            String sql = "SELECT * FROM checkin WHERE arrival_date BETWEEN '" + frm + "' AND '" + to + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            
            Checkin checkin;
            
            
            if (rst != null) {
                while (rst.next()) {
                    checkin = new Checkin(rst.getString("username"),
                            rst.getString("customer_fname") + " " + rst.getString("customer_surname"),
                            rst.getString("room_type"),
                            rst.getString("room_no"),
                            rst.getDouble("room_price"),
                            rst.getInt("stay_days"),
                            rst.getString("arrival_date"),
                            rst.getDouble("cost")
                    ); 
                    searchList.add(checkin);
                }
                s.close();
                
            }
         //   con.close();
        }
        
        catch (SQLException e) {
            System.out.println("Get Search Results > SQL ERROR : " + e.getMessage());
        }
        
        return searchList;
    }
    
    
    public ArrayList<Checkout> searchCheckout(String frm, String to) {
        
        ArrayList<Checkout> searchList = new ArrayList<>();
        
        try {
            
            s = con.createStatement();
            String sql = "SELECT * FROM checkout WHERE checkout BETWEEN '" + frm + "' AND '" + to + "'";
            s.execute(sql);
            ResultSet rst = s.getResultSet();
            
            Checkout checkout;
            
            
            if (rst != null) {
                while (rst.next()) {
                    checkout = new Checkout(rst.getString("username"),
                            rst.getString("firstname") + " " + rst.getString("surname"),
                            rst.getString("roomType"),
                            rst.getString("roomNo"),
                            rst.getDouble("roomPrice"),
                            rst.getString("arrival"),
                            rst.getString("checkout"),
                            rst.getInt("daysSpent"),
                            rst.getDouble("totAmt")
                    ); 
                    searchList.add(checkout);
                }
                s.close();
                
            }
        }
        
        catch (SQLException e) {
            System.out.println("Get Search Results > SQL ERROR : " + e.getMessage());
        }
        
        return searchList;
    }
    
     
    public int getResponseID() {
        return responseId;
    }
    
    public int getAdminResponseID() {
        return adminLoginId;
    }
    
     public int getAdminMasterResponseID() {
        return adminMasterLoginId;
    }
    
    public double getPrice() {
        return roomTypes.getPrice();
    }
      
    public String getDescription() {
        return roomTypes.getDescription();
    }
    
     /** Close the database connection **/
    public void closeLink() {

    try {
           con.close();
        }
        catch (SQLException e) {
        }
    } 
  
}
