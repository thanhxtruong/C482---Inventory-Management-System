/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author thanhtruong
 */
public class Inventory {
    private static List<Product> products;
    private static List<Part> allParts;
       

    public Inventory() {
        this.products = new ArrayList<>();
        this.allParts  = new ArrayList<>();
    } 
    
    public List<Product> getProducts() {
        return products;
    }

    public List<Part> getAllParts() {
        return allParts;
    }   
            
    public void addProduct(Product product){
        if(!products.contains(product)){
            products.add(product);
        } else{
            //TO DO
        }
    }
    
    public boolean removeProduct(int productID){
        Product product = queryProduct(productID);
        if(product != null){
            products.remove(product);
            return true;
        } else{
            return false;
        }
    }
    
    private Product queryProduct(int productID){
        Product productFound = null;
        for(Product product : products){
            if(product.getProductID() == productID){
                productFound = product;
            }
        }
        return productFound;
    }
    
    public Product lookupProduct(int productID){
        Product product = queryProduct(productID);
        if(product != null){           
            return product;
        } else{
            return null;
        }
    }
    
    public void updateProduct(int productID){
//        Product product = queryProduct(productID);
//        products.set(products.indexOf(product), product)
    }
    
    public void addPart(Part part){
        allParts.add(part);
    }
    
//    public boolean deletePart(Part part){
//        //TO DO
//    }
    
//    public Part lookupPart(int partID){
//        Part partFound = null;
//        for(Part part : allParts){
//            if(part.getPartID() == partID){
//                partFound = part;
//            }
//        }
//        return partFound;
//    }
    
    public void updatePart(int partID){
        // TO DO
    }
}