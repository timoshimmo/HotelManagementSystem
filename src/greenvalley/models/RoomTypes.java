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
public class RoomTypes {
    
    double price;
    String description;
    
    public RoomTypes(double pr, String dsc) {
        this.price = pr;
        this.description = dsc;
    }
    
    
    public double getPrice() {
        return price;
    }
      
    public String getDescription() {
        return description;
    }
    
}
