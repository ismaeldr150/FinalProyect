
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.TeamDAO;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.utils.Dialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CTeamController extends Controllers implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;
    
    //Labels
    @FXML
    private Label teamName;
    //TextFields
    @FXML
    private TextField Tname;
    //Botones
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
    
    
    private TableView<Team> tblTeams;
    public ObservableList<Team> teams;
    
    private Team team;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void iniAttributte(ObservableList<Team> players){
        this.teams = players;
    }
    
    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla CTeam.fxml
     * @param event 
     */
    @FXML
    public void create(ActionEvent event){
        String name = this.Tname.getText();
        
        Team t = new Team(name);
        
        if(!teams.contains(t)){
            this.team = t;
            Dialog.showInformation("Información", null, "Se ha añadido correctamente");
            
            Stage stage = (Stage) this.btnCreate.getScene().getWindow();
            stage.close();
        } else {
            Dialog.showError("ERROR", null, "La persona ya existe");
        }
    }
    
    @FXML
    public void cancel(ActionEvent event){
        this.team = null;
        Stage stage = (Stage) this.btnCreate.getScene().getWindow();
        stage.close();
    }
    
    public Team getTeam(){
        return team;
    }
    
    
    
    
    
    
    
    
    /*
    @FXML
    public void addTeam() {
        Team newT=new Team();
        TeamDAO newDao=new TeamDAO(newT);
        newDao.save();
        newT.setCodTeam(newDao.getCodTeam());
        teams.add(newT);
        
    }
    
    
    @FXML
    public void removeTeam() {
        Team selected = tblTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teams.remove(selected);
            TeamDAO cc = new TeamDAO(selected);
            cc.remove();
        }
    }
    */
    
}
