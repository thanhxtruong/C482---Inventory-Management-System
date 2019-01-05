/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

/**
 *
 * @author thanhtruong
 */
public abstract class Part {
    private int partID; 
    private String name;
    private Double price;
    private int inStock;
    private int min;
    private int max;    
    
    public int getPartID() {
        return partID;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
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

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
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
    
}
