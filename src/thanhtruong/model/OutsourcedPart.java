/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import javafx.beans.property.StringProperty;

/**
 *
 * @author thanhtruong
 */
public class OutsourcedPart extends Part{
    private String companyName;
    
    public OutsourcedPart(){
        this(null);
    }

    public OutsourcedPart(String companyName) {
        super();
        this.companyName = companyName;
    }   
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }   
        
}
