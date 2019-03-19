/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenvalley.models;

/**
 *
 * @author freshfuturesmy
 */
public class Checkout {
    
    String username;
    String fullname;
    String roomType;
    String rmNo;
    double cost;
    String chkInDateTime;
    String chkOutDateTime;
    int sDays;
    double totCost;
    
    public Checkout(String un, String fln, String rt, String rn, double cst, String cint, String cot, int sd, double tc) {
        
        this.username = un;
        this.fullname = fln;
        this.roomType = rt;
        this.rmNo = rn;
        this.cost = cst;
        this.chkInDateTime = cint;
        this.chkOutDateTime = cot;
        this.sDays = sd;
        this.totCost = tc;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getFullname() {
        return fullname;
    }
      
    public String getRoomType() {
        return roomType;
    }
        
    public String getRoomNo() {
        return rmNo;
    }
    
    public double getCost() {
        return cost;
    }
    
     public String getCheckInDateTime() {
        return chkInDateTime;
    }
    
    public String getCheckOutDateTime() {
        return chkOutDateTime;
    } 
     
    public int getDays() {
        return sDays;
    }

    public double getTotCost() {
        return totCost;
    }
    
}
