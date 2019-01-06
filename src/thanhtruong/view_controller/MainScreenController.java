/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import thanhtruong.MainApp;
import thanhtruong.model.Part;
import thanhtruong.model.Product;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partCostColumn;
    @FXML
    private TextField partSearch;
    @FXML
    private Button partSearchButton;
    @FXML
    private Button partAddButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button partDeleteButton;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    @FXML
    private TextField productSearch;
    @FXML
    private Button productSearchButton;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button productDeleteButton;
    
    private MainApp mainApp;
        
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    /**
     * Display/update data in TableView and search part
     */
    private void partTableDisplay(){
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(mainApp.getInventory().getAllParts());
        
        FilteredList<Part> filteredPartMain = new FilteredList<>(mainApp.getInventory().getAllParts(), p -> true);
        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPartMain.setPredicate(part -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
                
        SortedList<Part> sortedData = new SortedList<>(filteredPartMain);
        sortedData.comparatorProperty().bind(partTable.comparatorProperty());
        partTable.setItems(sortedData);
    }
    
    /**
     * Display/update data in TableView and search product
     */
    private void productTableDisplay(){
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(mainApp.getInventory().getProducts());
        
        FilteredList<Product> filteredProduct = new FilteredList<>(mainApp.getInventory().getProducts(), p -> true);
        productSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProduct.setPredicate(product -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
                
        SortedList<Product> sortedData = new SortedList<>(filteredProduct);
        sortedData.comparatorProperty().bind(productTable.comparatorProperty());
        productTable.setItems(sortedData);
    }
    
    /**
     * Called when user clicks the "Add" button. Opens the "AddPartScreen"
     */
    @FXML
    private void handleAddPart() {
        boolean saveClicked = mainApp.showAddPartDialog();
        if(saveClicked){
            partTableDisplay(); 
        }
    }        

    @FXML
    private void handleAddProduct() {
        boolean saveClicked = mainApp.showAddProductDialog();
        if(saveClicked){
            productTableDisplay(); 
        }
    }

    @FXML
    void handleDeletePart() {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            mainApp.getInventory().deletePart(selectedPart);
        } else {
            // TO DO
        }
        partTableDisplay();
    }

    @FXML
    void handleDeleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if(selectedProduct != null){
            mainApp.getInventory().deleteProduct(selectedProduct);
        } else {
            // TO DO
        }
        productTableDisplay();
    }

    /**
     * Called when user clicks "Modify" from the Main screen.
     * Get and pass the selectedPart to showModifyPartDialog() in mainApp.
     * Display TableView once received saveClicked from showModifyPartDialog().
     * 
     */
    @FXML
    public void handleModifyPart() {
        boolean saveClicked = false;
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            saveClicked = mainApp.showModifyPartDialog(selectedPart);
        } else {
            // TO DO
        }
        
        if(saveClicked){
            partTableDisplay();
        }        
    }

    @FXML
    void handleModifyProduct() {
        boolean saveClicked = false;
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if(selectedProduct != null){
            saveClicked = mainApp.showModifyProductDialog(selectedProduct);
        } else {
            // TO DO
        }
        
        if(saveClicked){
            productTableDisplay();
        }  
    }

    @FXML
    void handleSearchPart() {
        // Implemented via filtered and sorted list above
    }

    @FXML
    void handleSearchProduct() {
        // Implemented via filtered and sorted list above
    }
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url,
            ResourceBundle rb) {
        // TODO
    }    
    
}
