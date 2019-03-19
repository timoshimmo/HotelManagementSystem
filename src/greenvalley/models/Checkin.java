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
public class Checkin {
    
    String username;
    String fullname;
    String roomType;
    String rmNo;
    double prices;
    int sDays;
    String arrDateTime;
   // String dptDateTime;
    double totCost;
    
    public Checkin(String un, String fln, String rt, String rn, double pr, int sd, String at, double tc) {
        
        this.username = un;
        this.fullname = fln;
        this.roomType = rt;
        this.rmNo = rn;
        this.prices = pr;
        this.sDays = sd;
        this.arrDateTime = at;
       // this.dptDateTime = dt;
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
    
    public double getPrices() {
        return prices;
    }
     
    public int getDays() {
        return sDays;
    }
    
    public String getArrivalDateTime() {
        return arrDateTime;
    }
    
  /*  public String getDepartureDateTime() {
        return dptDateTime;
    } */
    
    public double getTotCost() {
        return totCost;
    }
    
}

