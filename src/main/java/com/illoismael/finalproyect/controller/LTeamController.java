package com.illoismael.finalproyect.controller;

import static com.illoismael.finalproyect.controller.AppController.loadFXML;
import com.illoismael.finalproyect.dao.TeamDAO;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.utils.MapEntry;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML
    private Button btnDelete;
    
    private ObservableList<Team> teams;
    private Team team;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Método que sirve para abrir una ventana modal (CTeam) 
     * para rellenar los datos del equipo
     */
    public void newTeam() throws IOException {
        
       Stage stage = new Stage();
       MapEntry<Parent, Controllers> m = loadFXML(Scenes.C_TEAM.getUrl());
       Parent modal = m.getKey();

       Scene modalScene = new Scene(modal);
       
       stage.setTitle("Create player...");
       stage.initModality(Modality.WINDOW_MODAL);
       stage.initOwner(this.app.mainStage);
       stage.setScene(modalScene);
       stage.showAndWait();

       //ARREGLAR PARA QUE ACTUALICE LA TABLA
       CTeamController controlador = null;
       controlador.iniAttributte(teams);
       if(team != null){
                this.teams.add(team);
                this.tblTeams.refresh();
                
            }
    }
    
    @FXML
    public void deleteTeam() {
        Team selected = tblTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teams.remove(selected);
            TeamDAO cc = new TeamDAO(selected);
            cc.remove();
        }
    }
        
        
    
    
    @FXML
    public void cancel(ActionEvent event){
        this.team = null;
        Stage stage = (Stage) this.btnNewTeam.getScene().getWindow();
        stage.close();
    }
    
}

