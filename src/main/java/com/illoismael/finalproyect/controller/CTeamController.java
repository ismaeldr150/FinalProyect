
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
    private LTeamController parent;
    private Object params;
    private Stage myStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(LTeamController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }
    
    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla CTeam.fxml
     * @param event 
     */
    @FXML
    public void create(ActionEvent event){
        String Nname = this.Tname.getText();


        if (Nname.trim().length() > 0) {

            Team t = new TeamDAO(-1, Nname);
            if (parent != null) {
                parent.doOnCloseModal(t);
            }

            if (this.myStage != null) {
                this.myStage.close();
            }
        } else {
            if (parent != null) {
                Dialog.showWarning("Validation error", "Fix errors", "Name cant be empty");
            }
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

    
}
