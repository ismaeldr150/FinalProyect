
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.TeamDAO;
import com.illoismael.finalproyect.model.Team;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CTeamController implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;
    @FXML
    private Label teamName;
    @FXML
    private TextField tname;
    @FXML
    private Button cancel;
    @FXML
    private Button create;
    
    
    private TableView<Team> teamTable;
    public ObservableList<Team> teams;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
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
        Team selected = teamTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teams.remove(selected);
            TeamDAO cc = new TeamDAO(selected);
            cc.remove();
        }
    }
    
}
