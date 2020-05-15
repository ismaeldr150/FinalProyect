package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.model.Team;
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

public class LTeamController extends Controllers implements Initializable{
    
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label label;
    @FXML
    private TableView<Team> tblTeams;
    @FXML
    private TableColumn C_name;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNewTeam;
    
    private ObservableList<Team> teams;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Método que sirve para abrir una ventana modal (CTeam) 
     * para rellenar los datos del equipo
     */
    public void newTeam() {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CTeam"));
            
            Parent root = loader.load();
            
            CTeamController con = loader.getController();
            con.iniAttributte(teams);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Team t = con.getTeam();
            if(t != null){
                this.teams.add(t);
                this.tblTeams.refresh();
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
