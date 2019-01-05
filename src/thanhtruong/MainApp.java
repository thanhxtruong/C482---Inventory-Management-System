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
import thanhtruong.model.InhousePart;
import thanhtruong.model.Inventory;
import thanhtruong.model.Part;
import thanhtruong.view_controller.AddPartController;
import thanhtruong.view_controller.MainScreenController;

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
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
      
    }
    
}
