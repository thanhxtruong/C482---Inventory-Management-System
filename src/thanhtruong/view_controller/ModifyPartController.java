
package thanhtruong.view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import thanhtruong.MainApp;
import thanhtruong.model.InhousePart;
import thanhtruong.model.OutsourcedPart;
import thanhtruong.model.Part;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class ModifyPartController implements Initializable {

    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partCost;
    @FXML
    private TextField nameOrIDText;
    @FXML
    private TextField partInvMax;
    @FXML
    private TextField partInvMin;
    @FXML
    private Label nameOrIDLabel;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton outsourced;
    @FXML
    private ToggleGroup modifyPartGroup;
    @FXML
    private Button saveModifyPart;
    @FXML
    private Button cancelModifyPart;
    
    private MainApp mainApp;
    private Stage dialogStage;
    private boolean saveClicked;
    private Part tempPart;
    
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
     * Set tempPart to part to use later on in handleSavePartModify() below
     * @param part: received from mainApp, which was originally passed by MainScreen controller
     */
    public void modifyPartDialogDisplay(Part part){
        tempPart = part;
        partID.setText(String.valueOf(part.getPartID()));
        partName.setText(part.getName());
        partInv.setText(String.valueOf(part.getInStock()));
        partCost.setText(String.valueOf(part.getPrice()));
        partInvMax.setText(String.valueOf(part.getMax()));
        partInvMin.setText(String.valueOf(part.getMin()));
        if(part instanceof InhousePart){
            selectDialogDisplay(true);
            nameOrIDText.setText(String.valueOf(((InhousePart) part).getMachineID()));
        } else if(part instanceof OutsourcedPart){
            selectDialogDisplay(false);
            nameOrIDText.setText(((OutsourcedPart) part).getCompanyName());
        }
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
        else {
            outsourced.setSelected(true);
            nameOrIDLabel.setText("Company Name");
            nameOrIDText.clear();
            nameOrIDText.setPromptText("Company Name");
        }        
    }
    
    @FXML
    void handleCancelPartModify() {
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

    /**
     * Called when user clicks "Save" from ModifyPart screen.
     * Get new data from screen, delete old part from Inventory, and add new part.
     * Close the dialog.
     * 
     * @return saveClicked to MainScreen controller.
     */
    @FXML
    private void handleSavePartModify() {
        
        if(isInputValid()){
            if(inHouse.isSelected()){                
                
                InhousePart newPart = new InhousePart();
                newPart.setPartID(Integer.parseInt(partID.getText()));
                newPart.setName(partName.getText());
                newPart.setInStock(Integer.parseInt(partInv.getText()));
                newPart.setPrice(Double.parseDouble(partCost.getText()));
                newPart.setMin(Integer.parseInt(partInvMin.getText()));
                newPart.setMax(Integer.parseInt(partInvMax.getText()));
                newPart.setMachineID(Integer.parseInt(nameOrIDText.getText()));
                
                mainApp.getInventory().deletePart(tempPart);
                mainApp.getInventory().addPart(newPart);
            } else{
                
                OutsourcedPart newPart = new OutsourcedPart();
                newPart.setPartID(Integer.parseInt(partID.getText()));
                newPart.setName(partName.getText());
                newPart.setInStock(Integer.parseInt(partInv.getText()));
                newPart.setPrice(Double.parseDouble(partCost.getText()));
                newPart.setMin(Integer.parseInt(partInvMin.getText()));
                newPart.setMax(Integer.parseInt(partInvMax.getText()));
                newPart.setCompanyName(nameOrIDText.getText());
                
                mainApp.getInventory().deletePart(tempPart);
                mainApp.getInventory().addPart(newPart);
            }
            
            // Reset and pass "saveClicked" back to MainApp after "addPartStage.showAndWait().
            // Close dialog
            saveClicked = true;
            dialogStage.close();
        }
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
