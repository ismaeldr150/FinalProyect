
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.model.Player;
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


public class CPlayerController extends Controllers implements Initializable{
    
    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;
    
    //Labels
    @FXML
    private Label name;
    @FXML
    private Label age;
    @FXML
    private Label salary;

    //TextFields
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tage;
    @FXML
    private TextField Tsalary;

    //Botones
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
    
    private Player player;
    
    private TableView<Player> playerTable;
    public ObservableList<Player> players;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void iniAttributte(ObservableList<Player> players){
        this.players = players;
    }
    
    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla CPlayer.fxml
     * @param event 
     */
    @FXML
    public void create(ActionEvent event){
        String name = this.Tname.getText();
        int age = Integer.parseInt(this.Tage.getText());
        int salary = Integer.parseInt(this.salary.getText());
        
        Player p = new Player(name, age, salary);
        
        if(!players.contains(p)){
            this.player = p;
            Dialog.showInformation("Información", null, "Se ha añadido correctamente");
            
            Stage stage = (Stage) this.btnCreate.getScene().getWindow();
            stage.close();
        } else {
            Dialog.showError("ERROR", null, "La persona ya existe");
        }
    }
    
    @FXML
    public void cancel(ActionEvent event){
        this.player = null;
        Stage stage = (Stage) this.btnCreate.getScene().getWindow();
        stage.close();
    }
    
    public Player getPlayer(){
        return player;
    }
    
    
    
    
    
    
    
    
    
    
    
    /*
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
    
    */
    
}
