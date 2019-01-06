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
public class AddProductController extends DialogConfirmation implements Initializable {

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
    private Button saveAddProduct;
    @FXML
    private Button cancelAddProduct;
    
    private static int productIDIndex = 0;
    private Stage dialogStage;
    private boolean saveClicked = false;
    Product newProduct = new Product();
    
    private MainApp mainApp;
    
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
    
    public void setExitConfirmation(){
        dialogStage.setOnCloseRequest(evt -> {
                evt.consume();
                productIDIndex = exitConfirmation(dialogStage, true, productIDIndex);
        });
    }
    
    public void setProductID(){
        productIDIndex++;
        productID.setText(String.valueOf(productIDIndex));
    }
    
    /**
     * Return to mainApp
     * @return 
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }
    
    /**
     * Display all parts in the addPart TableView
     */
    public void addProductDialogDisplay(){
        allPartTableDisplay();
        associatedPartTableDisplay();
    }
    
    /**
     * Display/update data in TableView and search part
     */
    private void allPartTableDisplay(){
        addPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        addPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartTable.setItems(mainApp.getInventory().getAllParts());
        
        FilteredList<Part> filteredPartAddProduct = new FilteredList<>(mainApp.getInventory().getAllParts(), p -> true);
        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPartAddProduct.setPredicate(part -> {
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
                
        SortedList<Part> sortedData = new SortedList<>(filteredPartAddProduct);
        sortedData.comparatorProperty().bind(addPartTable.comparatorProperty());
        addPartTable.setItems(sortedData);
    }
    
    private void associatedPartTableDisplay(){
        
        deletePartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        deletePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deletePartInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        deletePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        deletePartTable.setItems(newProduct.getPartList());
                
    }
    
     @FXML
    void handleAddPart() {
        Part selectedPart = addPartTable.getSelectionModel().getSelectedItem();
        if(selectedPart != null){
            newProduct.addAssociatedPart(selectedPart);
            associatedPartTableDisplay();
        } else {
            // TO DO
        }
        
    }

    /**
     * Reset the productIDIndex and close the dialog when user cancels adding a product.
     * @param event 
     */
    @FXML
    void handleCancelProductAdd() {
        cancelConfirmation(dialogStage, productIDIndex);
    }

    @FXML
    void handleDeletePart() {
        Part selectedPart = deletePartTable.getSelectionModel().getSelectedItem();                
        boolean deleteConfirm = deleteConfirmation(dialogStage);
        if(selectedPart != null && deleteConfirm){
            newProduct.deleteAssociatedPart(selectedPart);
            associatedPartTableDisplay();
        }
    }

    @FXML
    void handlePartSearch() {
        // Part filtering and sorted is already executed by allPartTableDisplay() above.
    }

    @FXML
    void handleSaveProductAdd() {
        if(isInputValid()){
            newProduct.setProductID(Integer.parseInt(productID.getText()));
            newProduct.setName(productName.getText());
            newProduct.setInStock(Integer.parseInt(productInv.getText()));
            newProduct.setPrice(Double.parseDouble(productPrice.getText()));
            newProduct.setMin(Integer.parseInt(productInvMin.getText()));
            newProduct.setMax(Integer.parseInt(productInvMax.getText()));

            mainApp.getInventory().addProduct(newProduct);
                        
            // Reset and pass "saveClicked" back to MainApp after "addPartStage.showAndWait().
            // Close dialog
            saveClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid(){
        String errorMessage = "";
        int minInvCheck = 0;
        int maxInvCheck = 0;
        int inputInventory = 0;
        
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
        if(productInvMin.getText() == null || productInvMin.getText().length() == 0){
            errorMessage += "No valid Min Product Inventory!\n";
        } else {
            try {
                minInvCheck = Integer.parseInt(productInvMin.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Min Product Inventory (must be a number)!\n";
            }
        }
        if(productInvMax.getText() == null || productInvMax.getText().length() == 0){
            errorMessage += "No valid Max Product Inventory!\n";
        } else {
            try {
                maxInvCheck = Integer.parseInt(productInvMax.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Max Product Inventory (must be a number)!\n";
            }
        }
        if(productInv.getText() == null || productInv.getText().length() == 0){
            errorMessage += "No valid Product Inventory!\n";
        } else {
            try {
                inputInventory = Integer.parseInt(productInv.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Product Inventory (must be a number)!\n";
            } finally {
                if(inputInventory < minInvCheck){
                    errorMessage += "Inventory is less than minimum requirement! \n";
                } else if (inputInventory > maxInvCheck) {
                    errorMessage += "Inventory exceeds the maximum allowed! \n";
                }
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url,
            ResourceBundle rb) {
        // TODO
    }    
    
}
