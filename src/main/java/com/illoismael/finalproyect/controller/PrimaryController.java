package com.illoismael.finalproyect;

import com.illoismael.finalproyect.controller.AppController;
import com.illoismael.finalproyect.controller.CPlayerController;
import com.illoismael.finalproyect.controller.Controllers;
import com.illoismael.finalproyect.controller.Scenes;
import com.illoismael.finalproyect.enums.ComboBoxPrimary;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PrimaryController extends Controllers implements Initializable{
    
    AppController ap = new AppController();

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private Button btn;
    @FXML
    private ComboBox<String> options;
    @FXML
    private Label title;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb();
        
    }

    @Override
    public void onLoad() {
        this.app.controller.title("FINAL PROYECT - D.A.M");
        this.app.controller.enableCon();
    }
    
    public void cb(){
        for (ComboBoxPrimary cbp : ComboBoxPrimary.values()) {
            options.getItems().addAll(cbp.getCombo());
        }
    }
    
    @FXML
    public void prueba() {
        
    }
    
    
}
