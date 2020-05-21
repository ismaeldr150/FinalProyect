package com.illoismael.finalproyect.controller;

import static com.illoismael.finalproyect.controller.AppController.loadFXML;
import com.illoismael.finalproyect.enums.ComboBoxPrimary;
import com.illoismael.finalproyect.utils.Dialog;
import com.illoismael.finalproyect.utils.MapEntry;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
      if(options.getValue().equals("PLAYER")){
      changeScene(Scenes.PLAYERS_LIST);
      } else if(options.getValue().equals("TEAM")){
      changeScene(Scenes.TEAMS_LIST);
      } else if(options.getValue().equals("VIDEOGAME")){
      changeScene(Scenes.VIDEOGAMES_LIST);
      } else {
          // changeScene(Scenes.PRIMARY);
      }
        
    }
    
    
    
    
    public Scenes backScene;
    public Scenes currentScene;
    
    public void changeScene(Scenes scene) {
        try {
            MapEntry<Parent, Controllers> m = loadFXML(scene.getUrl());
            this.app.rootLayout.setCenter(m.getKey());
            if (m.getValue() != null) {
                m.getValue().setMainApp(this.app);
            }
            if (backScene != currentScene) {
                backScene = currentScene;
            }
            this.currentScene = scene;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
