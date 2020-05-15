package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.enums.ComboBoxPrimary;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GeneralListController extends Controllers implements Initializable{
    
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
    

    
    public void cb(){
        for (ComboBoxPrimary c : ComboBoxPrimary.values()) {
            options.getItems().addAll(c.getCombo());
        }
    }
    
    @FXML
    public void goToList(){
      ap.changeScene(Scenes.L_PLAYER);
    }
    
}
