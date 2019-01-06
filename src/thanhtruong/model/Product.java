/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author thanhtruong
 */
public class Product {
    private ObservableList<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public Product(){
        this(0, null, 0.0, 0, 0, 0);
    }

    public Product(int productID,
            String name,
            double price,
            int inStock,
            int min,
            int max) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        
        this.associatedParts = FXCollections.observableArrayList();
    }

    public ObservableList<Part> getPartList() {
        return associatedParts;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }   
        
    public void addAssociatedPart(Part part){
        if(!associatedParts.contains(part)){
            associatedParts.add(part);
        } else{
            //TO DO
        }                
    }
        
    public boolean removeAssociatedPart(int partID){
        Part part = lookupAssociatedPart(partID);
        if(part != null){
            associatedParts.remove(part);
            return true;
        } else{
            return false;
        }
    }
    
    public void deleteAssociatedPart(Part part){
        associatedParts.remove(part);
    }
    
    public Part lookupAssociatedPart(int partID){
        Part partFound = null;
        for(Part part : associatedParts){
            if(part.getPartID() == partID){
                partFound = part;
            }
        }
        return partFound;
    }        
}
