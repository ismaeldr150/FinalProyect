
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.model.Player;
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


public class CPlayerController implements Initializable{
    
    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;
    @FXML
    private Label name;
    @FXML
    private Label age;
    @FXML
    private Label phone;
    @FXML
    private Label salary;
    @FXML
    private Label teamName;
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tage;
    @FXML
    private TextField Tphone;
    @FXML
    private TextField Tsalary;
    @FXML
    private TextField TteamName;
    @FXML
    private Button cancel;
    @FXML
    private Button create;
    
    private TableView<Player> playerTable;
    public ObservableList<Player> players;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @FXML
    public void addPlayer() {
        Player newP=new Player();
        PlayerDAO newDao=new PlayerDAO(newP);
        newDao.save();
        newP.setId(newDao.getId());
        players.add(newP);
        
    }
    
    
    @FXML
    public void removePlayer() {
        Player selected = playerTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            players.remove(selected);
            PlayerDAO cc = new PlayerDAO(selected);
            cc.remove();
        }
    }
    
}
