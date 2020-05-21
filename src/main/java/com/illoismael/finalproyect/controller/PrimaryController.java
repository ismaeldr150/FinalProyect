package com.illoismael.finalproyect;

import com.illoismael.finalproyect.controller.AppController;
import static com.illoismael.finalproyect.controller.AppController.loadFXML;
import com.illoismael.finalproyect.controller.CPlayerController;
import com.illoismael.finalproyect.controller.Controllers;
import com.illoismael.finalproyect.controller.Scenes;
import com.illoismael.finalproyect.enums.ComboBoxPrimary;
import com.illoismael.finalproyect.utils.MapEntry;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class PrimaryController extends Controllers implements Initializable{
    
    AppController ap = new AppController();

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private Button btn;
    
    @FXML
    private ComboBox<String> options;
    
    @FXML
    private Label title;
    
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tdescription;

    
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
    public void goTo(){
      if(options.getValue().equals("PLAYER")){
      changeScene(Scenes.L_PLAYER);
      } else if(options.getValue().equals("TEAM")){
      changeScene(Scenes.L_TEAM);
      } else if(options.getValue().equals("VIDEOGAME")){
      changeScene(Scenes.L_VIDEOGAMES);
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
