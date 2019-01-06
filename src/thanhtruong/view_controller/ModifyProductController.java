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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import thanhtruong.MainApp;
import thanhtruong.model.Part;
import thanhtruong.model.Product;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableView<Part> addPartTable;
    @FXML
    private TableColumn<Part, Integer> addPartID;
    @FXML
    private TableColumn<Part, String> addPartName;
    @FXML
    private TableColumn<Part, Integer> addPartInv;
    @FXML
    private TableColumn<Part, Double> addPartPrice;
    @FXML
    private Button addPart;
    @FXML
    private Button searchPart;
    @FXML
    private TextField partSearch;
    @FXML
    private TableView<Part> deletePartTable;
    @FXML
    private TableColumn<Part, Integer> deletePartID;
    @FXML
    private TableColumn<Part, String> deletePartName;
    @FXML
    private TableColumn<Part, Integer> deletePartInv;
    @FXML
    private TableColumn<Part, Double> deletePartPrice;
    @FXML
    private Button deletePart;
    @FXML
    private TextField productID;
    @FXML
    private TextField productName;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productInvMax;
    @FXML
    private TextField productInvMin;
    @FXML
    private Button saveModifyProduct;
    @FXML
    private Button cancelModifyProduct;
    
    private MainApp mainApp;
    private Stage dialogStage;
    private boolean saveClicked;
    private Product tempProduct;
    
    /**
     * Constructor called from MainApp to set mainApp in controller
     * Methods and field in mainApp can then be accessed here
     * @param mainApp 
     */
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    /**
     * Sets the stage of the "alert.initOwner(dialogStage)". Called by MainApp
     * @param dialogStage 
     */
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setProduct (Product product){
        this.tempProduct = product;
    }
    
    /**
     * Getter for isSaveClicked
     * Call by MainScreen controller after user clicks "Save".
     * MainSreeen controller then save the data
     * @return 
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }
    
    /**
     * Called by mainApp to display data in dialog.
     * Set tempProduct to product to use later on in handleSaveProductModify() below
     * @param product: received from mainApp, which was originally passed by MainScreen controller
     */
    public void modifyProductDialogDisplay(Product product){
        productID.setText(String.valueOf(product.getProductID()));
        productName.setText(product.getName());
        productInv.setText(String.valueOf(product.getInStock()));
        productPrice.setText(String.valueOf(product.getPrice()));
        productInvMax.setText(String.valueOf(product.getMax()));
        productInvMin.setText(String.valueOf(product.getMin()));

        allPartTableDisplay();
        associatedPartTableDisplay();
    }
    
    private void allPartTableDisplay(){
        addPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartTable.setItems(mainApp.getInventory().getAllParts());
        
        FilteredList<Part> filteredPartModifyProduct = new FilteredList<>(mainApp.getInventory().getAllParts(), p -> true);
        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPartModifyProduct.setPredicate(part -> {
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
                
        SortedList<Part> sortedData = new SortedList<>(filteredPartModifyProduct);
        sortedData.comparatorProperty().bind(addPartTable.comparatorProperty());
        addPartTable.setItems(sortedData);
    }
    
    private void associatedPartTableDisplay(){
        
        deletePartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        deletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deletePartInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        deletePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        deletePartTable.setItems(tempProduct.getPartList());
                
    }
    
    @FXML
    void handleAddPart() {
        Part selectedPart = addPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            tempProduct.addAssociatedPart(selectedPart);
            associatedPartTableDisplay();
        } else {
            // TO DO
        }
    }

    @FXML
    void handleCancelProductModify() {
        dialogStage.close();
    }

    @FXML
    void handleDeletePart() {
        Part selectedPart = deletePartTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            tempProduct.deleteAssociatedPart(selectedPart);
            associatedPartTableDisplay();
        }
    }

    @FXML
    void handleSaveProductModify() {
        if(isInputValid()){
            Product newProduct = new Product();
            newProduct.setProductID(Integer.parseInt(productID.getText()));
            newProduct.setName(productName.getText());
            newProduct.setInStock(Integer.parseInt(productInv.getText()));
            newProduct.setPrice(Double.parseDouble(productPrice.getText()));
            newProduct.setMin(Integer.parseInt(productInvMin.getText()));
            newProduct.setMax(Integer.parseInt(productInvMax.getText()));
            
            mainApp.getInventory().replaceProduct(tempProduct, newProduct);
                                    
            // Reset and pass "saveClicked" back to MainApp after "addPartStage.showAndWait().
            // Close dialog
            saveClicked = true;
            dialogStage.close();
        }
    }    
        
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(productID.getText() == null || productID.getText().length() == 0){
            errorMessage += "No valid Product ID!\n";
        } else {
            try {
                Integer.parseInt(productID.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Product ID (must be an integer)!\n";
            }
        }
        if(productName.getText() == null || productName.getText().length() == 0){
            errorMessage += "No valid Product Name!\n";
        }
        if(productInv.getText() == null || productInv.getText().length() == 0){
            errorMessage += "No valid Product Inventory!\n";
        } else {
            try {
                Integer.parseInt(productInv.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Product Inventory (must be a number)!\n";
            }
        }
        if(productPrice.getText() == null || productPrice.getText().length() == 0){
            errorMessage += "No valid Product Price!\n";
        } else {
            try {
                Double.parseDouble(productPrice.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Product Cost/Price (must be a number)!\n";
            }
        }
        if(productInvMin.getText() == null || productInvMin.getText().length() == 0){
            errorMessage += "No valid Min Product Inventory!\n";
        } else {
            try {
                Integer.parseInt(productInvMin.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Min Product Inventory (must be a number)!\n";
            }
        }
        if(productInvMax.getText() == null || productInvMax.getText().length() == 0){
            errorMessage += "No valid Max Product Inventory!\n";
        } else {
            try {
                Integer.parseInt(productInvMax.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Max Product Inventory (must be a number)!\n";
            }
        }
        
        if(errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct invalid input");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }    

    @FXML
    void handleSearchPart() {
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
