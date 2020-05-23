package com.illoismael.finalproyect.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialog {
    public static void showError(String title, String header,String description){
        showDialog(Alert.AlertType.ERROR, title, header, description);
    }
    public static void showWarning(String title, String header,String description){
        showDialog(Alert.AlertType.WARNING, title, header, description);
    }
    public static void showConfirm(String title, String header,String description){
        showDialog(Alert.AlertType.CONFIRMATION, title, header, description);
    }
    public static boolean showConfirm2(String title){
       Alert a = new Alert(Alert.AlertType.CONFIRMATION);
       a.setTitle("Confirm");
       a.setHeaderText("About to delete");
       a.setContentText("Do you want delete? "+title);
       
       Optional<ButtonType> result = a.showAndWait();
       if(result.get() == ButtonType.OK){
           return true;
       }else{
           return false;
       }
    }
    public static void showInformation(String title, String header, String description){
        showDialog(Alert.AlertType.INFORMATION, title, header, description);
    }
    public static void showDialog(Alert.AlertType type, String title, String header,String description){
        Alert alert =new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
        
    }
}
