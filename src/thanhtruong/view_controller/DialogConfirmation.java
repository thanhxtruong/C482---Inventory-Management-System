/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhtruong.view_controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Method used to confirm exit.
 * Inherited by various controllers.
 * 
 * @param mainWindow the parent window to either be closed if confirmed or
 * return to if user clicks "NO"
 * @param resetIndex to reset the index if true
 * @param index to reset if resetIndex is true
 * 
 * @author thanhtruong
 */
public class DialogConfirmation {
    
    public int exitConfirmation(Stage mainWindow, boolean resetIndex, int index){
        Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
        exitConfirmation.setHeaderText("Confirm Exit");
        if(exitConfirmation.showAndWait().orElse(ButtonType.NO) == ButtonType.YES){
            if(resetIndex){
                index--;
                mainWindow.close();
            } else {
                mainWindow.close();
            }            
        } else {
            exitConfirmation.close();
        }
        
        return index;
            
    }
    
    public boolean deleteConfirmation(Stage mainWindow){
        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES, ButtonType.NO);
        deleteConfirmation.setHeaderText("Confirm Delete");
        if(deleteConfirmation.showAndWait().orElse(ButtonType.NO) == ButtonType.YES){
            return true;
        } else {
            deleteConfirmation.close();
            return false;
        }            
    }
    
    public int cancelConfirmation(Stage mainWindow,int index){
        Alert cancelConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
        cancelConfirmation.setHeaderText("Confirm Cancel");
        if(cancelConfirmation.showAndWait().orElse(ButtonType.NO) == ButtonType.YES){
                index--;
                mainWindow.close();            
        } else {
            cancelConfirmation.close();
        }
        
        return index;            
    }
    
    public void cancelConfirmation(Stage mainWindow){
        Alert cancelConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?", ButtonType.YES, ButtonType.NO);
        cancelConfirmation.setHeaderText("Confirm Cancel");
        if(cancelConfirmation.showAndWait().orElse(ButtonType.NO) == ButtonType.YES){
            mainWindow.close();            
        } else {
            cancelConfirmation.close();
        }          
    }
}
