/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class ModifyPartController implements Initializable {

    @FXML
    private Label partID;
    @FXML
    private Label partName;
    @FXML
    private Label partInv;
    @FXML
    private Label partCost;
    @FXML
    private Label companyName;
    @FXML
    private Label partInvMax;
    @FXML
    private Label partInvMin;
    @FXML
    private Label machineID;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton Outsourced;
    @FXML
    private Button saveModifyPart;
    @FXML
    private Button cancelModifyPart;
    
    @FXML
    void handleCancelPartModify(ActionEvent event) {

    }

    @FXML
    void handleInHouse(ActionEvent event) {

    }

    @FXML
    void handleOutsource(ActionEvent event) {

    }

    @FXML
    void handleSavePartModify(ActionEvent event) {

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
