/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thanhtruong.model.Inventory;
import thanhtruong.model.Part;
import thanhtruong.model.Product;
import thanhtruong.view_controller.AddPartController;
import thanhtruong.view_controller.AddProductController;
import thanhtruong.view_controller.MainScreenController;
import thanhtruong.view_controller.ModifyPartController;
import thanhtruong.view_controller.ModifyProductController;

/**
 *
 * @author thanhtruong
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Inventory inventory = new Inventory();

    public Inventory getInventory() {
        return inventory;
    }
            
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory MS");
        
        showMainScreen();
      
    }
    
    
    public void showMainScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give controller access to the main app
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(primaryStage);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Called from handleAddPart() in MainScreenController.
     * End with showAndWait and pass it to AddPartController.
     * 
     * FXMLLoader is used to load the fxml GUI and set up the scene and stage
     * initModality() and initOwner() methods are used to display the AddPart screen
     * on top of the MainScreen (parent-child relationship) and prevent the user from
     * closing the MainScreen until the AddPart screen is closed.
     * 
     * getController() for the fxml in order to pass mainApp, addPartStage, and call setPartID()
     * Controller can then access methods and fields in mainApp and close the dialog/stage
     * 
     * @return isSaveClicked: true/false passed to controller
     */
    public boolean showAddPartDialog(){
        
        try {
            // Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AddPart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog Stage
            Stage addPartStage = new Stage();
            addPartStage.setTitle("Inventory MS - Add Part");
            addPartStage.initModality(Modality.WINDOW_MODAL);
            addPartStage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            addPartStage.setScene(scene);
            
            // Set the controller in order to pass the stage and call setPartID()
            AddPartController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(addPartStage);
            controller.setExitConfirmation();
            controller.setPartID();           
                        
            addPartStage.showAndWait();
            
            // Return "saveClicked" from AddPartController as soon as user clickes "Save"
            return controller.isSaveClicked();
            
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Called from handleAddProduct() in MainScreenController.
     * End with showAndWait and pass it to AddProductController.
     * 
     * FXMLLoader is used to load the fxml GUI and set up the scene and stage
     * initModality() and initOwner() methods are used to display the AddPart screen
     * on top of the MainScreen (parent-child relationship) and prevent the user from
     * closing the MainScreen until the AddPart screen is closed.
     * 
     * getController() for the fxml in order to pass mainApp, addProductStage, and call setProductID()
     * Controller can then access methods and fields in mainApp and close the dialog/stage
     * 
     * @return isSaveClicked: true/false passed to controller
     */
    public boolean showAddProductDialog(){
        
        try {
            // Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/AddProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog Stage
            Stage addProductStage = new Stage();
            addProductStage.setTitle("Inventory MS - Add Product");
            addProductStage.initModality(Modality.WINDOW_MODAL);
            addProductStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            addProductStage.setScene(scene);
            
            // Set the controller in order to pass the stage and call setProductID()
            AddProductController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(addProductStage);
            controller.setProductID();            
            controller.addProductDialogDisplay();
            controller.setExitConfirmation();
            
            addProductStage.showAndWait();
            
            // Return "saveClicked" from AddProductController as soon as user clickes "Save"
            return controller.isSaveClicked();
            
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /**
     * Called from handleModifyPart() in MainScreenController
     * End with showAndWait and pass it to ModifyPartController
     * @return isSaveClicked
     */
    public boolean showModifyPartDialog(Part part){
        
        try {
            // Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/ModifyPart.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog Stage
            Stage modifyPartStage = new Stage();
            modifyPartStage.setTitle("Inventory MS - Modify Part");
            modifyPartStage.initModality(Modality.WINDOW_MODAL);
            modifyPartStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            modifyPartStage.setScene(scene);
            
            // Set the controller in order to pass the stage and call setPartID()
            ModifyPartController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(modifyPartStage);
            controller.modifyPartDialogDisplay(part);
            
            modifyPartStage.showAndWait();
            
            // Return "saveClicked" from AddPartController as soon as user clickes "Save"
            return controller.isSaveClicked();
            
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Called from handleModifyPart() in MainScreenController
     * End with showAndWait and pass it to ModifyPartController
     * @return isSaveClicked
     */
    public boolean showModifyProductDialog(Product product){
        
        try {
            // Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view_controller/ModifyProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog Stage
            Stage modifyProductStage = new Stage();
            modifyProductStage.setTitle("Inventory MS - Modify Product");
            modifyProductStage.initModality(Modality.WINDOW_MODAL);
            modifyProductStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            modifyProductStage.setScene(scene);
            
            // Set the controller in order to pass the stage and call setProductID()
            // Pass and set selected product in controller
            ModifyProductController controller = loader.getController();
            controller.setMainApp(this);
            controller.setProduct(product);
            controller.setDialogStage(modifyProductStage);
            controller.modifyProductDialogDisplay(product);
            
            modifyProductStage.showAndWait();
            
            // Return "saveClicked" from AddProductController as soon as user clickes "Save"
            return controller.isSaveClicked();
            
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      
    }
    
}
