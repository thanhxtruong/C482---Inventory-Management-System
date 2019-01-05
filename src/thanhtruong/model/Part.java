/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author thanhtruong
 */
public abstract class Part {
    private IntegerProperty partID = new SimpleIntegerProperty(); 
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty price = new SimpleDoubleProperty();
    private IntegerProperty inStock = new SimpleIntegerProperty();
    private IntegerProperty min = new SimpleIntegerProperty();
    private IntegerProperty max = new SimpleIntegerProperty();    
    
    public int getPartID() {
        return partID.get();
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public int getInStock() {
        return inStock.get();
    }

    public int getMin() {
        return min.get();
    }

    public int getMax() {
        return max.get();
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }

    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }          
    
}
