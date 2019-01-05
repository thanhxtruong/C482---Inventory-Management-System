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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import thanhtruong.model.Part;
import thanhtruong.model.Product;

/**
 * FXML Controller class
 *
 * @author thanhtruong
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableView<Product> addPartTable;
    @FXML
    private TableColumn<Product, Integer> addPartID;
    @FXML
    private TableColumn<Product, String> addPartName;
    @FXML
    private TableColumn<Product, Integer> addPartInv;
    @FXML
    private TableColumn<Product, Double> addPartPrice;
    @FXML
    private Button addPart;
    @FXML
    private Button searchPart;
    @FXML
    private TextField partSearch;
    @FXML
    private TableView<Product> deletePartTable;
    @FXML
    private TableColumn<Product, Integer> deletePartID;
    @FXML
    private TableColumn<Product, String> deletePartName;
    @FXML
    private TableColumn<Product, Integer> deletePartInv;
    @FXML
    private TableColumn<Product, Double> deletePartPrice;
    @FXML
    private Button deletePart;
    @FXML
    private Label productID;
    @FXML
    private Label productName;
    @FXML
    private Label productInv;
    @FXML
    private Label productPrice;
    @FXML
    private Label productInvMax;
    @FXML
    private Label productInvMin;
    @FXML
    private Button saveModifyProduct;
    @FXML
    private Button cancelModifyProduct;
    
    @FXML
    void handleAddPart(ActionEvent event) {

    }

    @FXML
    void handleCancelProductModify(ActionEvent event) {

    }

    @FXML
    void handleDeletePart(ActionEvent event) {

    }

    @FXML
    void handleSaveProductModify(ActionEvent event) {

    }

    @FXML
    void handleSearchPart(ActionEvent event) {

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
