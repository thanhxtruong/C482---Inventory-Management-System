/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import thanhtruong.MainApp;
import thanhtruong.model.Inventory;
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
    
    private void partTableDisplay(){
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(mainApp.getInventory().getAllParts());
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
        
    }

    @FXML
    void handleDeletePart(ActionEvent event) {

    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {

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
    void handleModifyProduct(ActionEvent event) {

    }

    @FXML
    void handleSearchPart(ActionEvent event) {

    }

    @FXML
    void handleSearchProduct(ActionEvent event) {

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
