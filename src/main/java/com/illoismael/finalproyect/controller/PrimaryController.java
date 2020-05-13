package com.illoismael.finalproyect;

import com.illoismael.finalproyect.controller.AppController;
import com.illoismael.finalproyect.controller.Controllers;
import com.illoismael.finalproyect.controller.Scenes;
import com.illoismael.finalproyect.enums.ComboBoxPrimary;
import com.illoismael.finalproyect.model.Coach;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


public class PrimaryController extends Controllers implements Initializable{
    
    AppController ap = new AppController();

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private Button boton;
    @FXML
    private ComboBox<String> options;
    @FXML
    private Label title;
    
    //LISTAS OBSERVABLES DE OBJETOS//
    public ObservableList<Coach> coachs;
    public ObservableList<Team> teams;
    public ObservableList<Videogame> videogames;
    public ObservableList<Player> players;
    
    ObservableList<String> list = FXCollections.observableArrayList("Player","Coach","Team","Videogame");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Cargando Controlador Primario");

        cb();
        
        
    }


    
    @Override
    public void onLoad() {
        this.app.controller.title("FINAL PROYECT - D.A.M");
        this.app.controller.enableCon();
    }
    
    @FXML
    public void goTo(){
        ap.changeScene(Scenes.CREATE);
    }
    @FXML
    public void list(){
        ap.changeScene(Scenes.L_PLAYER);
    }
    
    public void cb(){
        for (ComboBoxPrimary cbp : ComboBoxPrimary.values()) {
            options.getItems().addAll(cbp.getCombo());
        }
    }
    
    
}
