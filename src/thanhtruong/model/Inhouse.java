/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author thanhtruong
 */
public class Inhouse extends Part{
    private IntegerProperty machineID = new SimpleIntegerProperty();
    
    public Inhouse(){
        this(0, 0, null, 0.0, 0, 0, 0);        
    }


    public Inhouse(int machineID,
            int partID,
            String name,
            double price,
            int inStock,
            int min,
            int max) {
        super.setPartID(partID);
        super.setName(name);
        super.setPrice(price);
        super.setInStock(inStock);
        super.setMin(min);
        super.setMax(max);
        this.machineID.set(machineID);
    }  
    
    public int getMachineID() {
        return machineID.get();
    }

    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }    
    
        
}
