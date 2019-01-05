/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author thanhtruong
 */
public class OutsourcedPart extends Part{
    private StringProperty companyName = new SimpleStringProperty();
    
    public OutsourcedPart(){
        this(null, 0, null, 0.0, 0, 0, 0);        
    }


    public OutsourcedPart(String companyName,
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
        this.companyName.set(companyName);
    }
    public String getCompanyName() {
        return companyName.get();
    }   

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }    
    
}
