
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.PrimaryController;
import com.illoismael.finalproyect.model.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LPlayerController extends Controllers implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private TableView<Player> tblPlayers;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn age;
    @FXML
    private TableColumn phone;
    @FXML
    private TableColumn team;
    @FXML
    private TableColumn salary;
    @FXML
    private Button cancel;
    @FXML
    private Button btNew;
    @FXML
    private Label title;

    
    private ObservableList<Player> players;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    /**
     * Método que sirve para abrir una ventana modal (CPlayer) 
     * para rellenar los datos del jugador
     */
    public void newPlayer() {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CPlayer"));
            
            Parent root = loader.load();
            
            CPlayerController controlador = loader.getController();
            controlador.iniAttributte(players);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Player p = controlador.getPlayer();
            if(p != null){
                this.players.add(p);
                this.tblPlayers.refresh();
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    
    
}
