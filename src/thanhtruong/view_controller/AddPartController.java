/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import thanhtruong.MainApp;
import thanhtruong.model.InhousePart;
import thanhtruong.model.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class AddPartController {

    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partCost;
    @FXML
    private TextField partInvMax;
    @FXML
    private TextField partInvMin;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton outsourced;
    @FXML
    private ToggleGroup addPartGroup;
    @FXML
    private Button saveAddPart;
    @FXML
    private Button cancelAddPart;
    @FXML
    private Label nameOrIDLabel;
    @FXML
    private TextField nameOrIDText;
    
    private static int partIDIndex = 0;
    private Stage dialogStage;
    private InhousePart newInhousePart;
    private OutsourcedPart newOutsourcedPart; 
    private boolean saveClicked = false;
    
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
        
    @FXML
    public void handleCancelAddPart() {
        partIDIndex--;
        dialogStage.close();
    }

    @FXML
    public void handleInHouse() {
        selectDialogDisplay(true);
    }

    @FXML
    void handleOutsource() {
        selectDialogDisplay(false);
    }

    @FXML
    private void handleSaveAddPart() {
        if(isInputValid()){
            if(inHouse.isSelected()){
                newInhousePart = new InhousePart();
                
                newInhousePart.setPartID(Integer.parseInt(partID.getText()));
                newInhousePart.setName(partName.getText());
                newInhousePart.setInStock(Integer.parseInt(partInv.getText()));
                newInhousePart.setPrice(Double.parseDouble(partCost.getText()));
                newInhousePart.setMin(Integer.parseInt(partInvMin.getText()));
                newInhousePart.setMax(Integer.parseInt(partInvMax.getText()));
                newInhousePart.setMachineID(Integer.parseInt(nameOrIDText.getText()));
                
                mainApp.getInventory().addPart(newInhousePart);
            } else{
                newOutsourcedPart = new OutsourcedPart();
                
                newOutsourcedPart.setPartID(Integer.parseInt(partID.getText()));
                newOutsourcedPart.setName(partName.getText());
                newOutsourcedPart.setInStock(Integer.parseInt(partInv.getText()));
                newOutsourcedPart.setPrice(Double.parseDouble(partCost.getText()));
                newOutsourcedPart.setMin(Integer.parseInt(partInvMin.getText()));
                newOutsourcedPart.setMax(Integer.parseInt(partInvMax.getText()));
                newOutsourcedPart.setCompanyName(nameOrIDText.getText());
                
                mainApp.getInventory().addPart(newOutsourcedPart);
            }
            
            // Reset and pass "saveClicked" back to MainApp after "addPartStage.showAndWait().
            // Close dialog
            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Return to mainApp
     * @return 
     */
    public boolean isSaveClicked() {
        return saveClicked;
    }   
        
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(partID.getText() == null || partID.getText().length() == 0){
            errorMessage += "No valid Part ID!\n";
        } else {
            try {
                Integer.parseInt(partID.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Part ID (must be an integer)!\n";
            }
        }
        if(partName.getText() == null || partName.getText().length() == 0){
            errorMessage += "No valid Part Name!\n";
        }
        if(partInv.getText() == null || partInv.getText().length() == 0){
            errorMessage += "No valid Part Inventory!\n";
        } else {
            try {
                Integer.parseInt(partInv.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Part Inventory (must be a number)!\n";
            }
        }
        if(partCost.getText() == null || partCost.getText().length() == 0){
            errorMessage += "No valid Part Price/Cost!\n";
        } else {
            try {
                Double.parseDouble(partCost.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Part Cost/Price (must be a number)!\n";
            }
        }
        if(partInvMin.getText() == null || partInvMin.getText().length() == 0){
            errorMessage += "No valid Min Part Inventory!\n";
        } else {
            try {
                Integer.parseInt(partInvMin.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Min Part Inventory (must be a number)!\n";
            }
        }
        if(partInvMax.getText() == null || partInvMax.getText().length() == 0){
            errorMessage += "No valid Max Part Inventory!\n";
        } else {
            try {
                Integer.parseInt(partInvMax.getText());
            } catch(NumberFormatException e) {
                errorMessage += "No valid Max Part Inventory (must be a number)!\n";
            }
        }
        if(nameOrIDText.getText() == null || nameOrIDText.getText().length() == 0){
            errorMessage += "No valid Machine ID/Customer Name!\n";
        } else {
            try {
                if(inHouse.isSelected()){
                    Integer.parseInt(nameOrIDText.getText());
                }                
            } catch(NumberFormatException e) {
                errorMessage += "No valid Machine ID (must be a number)!\n";
            }
        }
        
        if(errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please correct invalid input");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    /**
     * Sets the stage of the "alert.initOwner(dialogStage)". Called by MainApp
     * @param dialogStage 
     */
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    /**
     * Set the label and prompt text to Customer Name or Machine ID
     * depending on whether Inhouse or Outsourced radio button is selected.
     * Function is called by even handlers for Inhouse and Outsourced radio buttons
     * The default display with Inhouse selected is called in initialized() below
     * @param inHouseSelected: a true/false value determined by even handlers
     */
    private void selectDialogDisplay(boolean inHouseSelected){        
        if(inHouseSelected){
            inHouse.setSelected(true);
            nameOrIDLabel.setText("Machine ID");
            nameOrIDText.clear();
            nameOrIDText.setPromptText("Machine ID");
        }
        else{
            outsourced.setSelected(true);
            nameOrIDLabel.setText("Company Name");
            nameOrIDText.clear();
            nameOrIDText.setPromptText("Company Name");
        }        
    }
    
    public void setPartID(){
        partIDIndex++;
        partID.setText(String.valueOf(partIDIndex));
    }   
    
    /**
     * Initializes the controller class, automatically called after successful 
     * loading of fxml file
     */
    @FXML
    private void initialize() {
        
        selectDialogDisplay(true);
    }

    
    
}
