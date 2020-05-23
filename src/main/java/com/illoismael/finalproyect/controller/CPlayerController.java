
package com.illoismael.finalproyect.controller;

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

    public ObservableList<Player> players;
    
    private LPlayerController parent;
    private Object params;
    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }
    
    public void setParent(LPlayerController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }
    
    
    
    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla CPlayer.fxml
     * @param event 
     */
    @FXML
    public void create(ActionEvent event){
        String Nname = this.name.getText();
        
        
        
        int Nage = Integer.parseInt(this.age.getText());
        int Nsalary = Integer.parseInt(this.salary.getText());
        

        if (Nname.trim().length() > 0) {

            Player p= new Player(-1, Nname, Nage, Nsalary);
            if (parent != null) {
                parent.doOnCloseModal(p);
            }

            if (this.myStage != null) {
                this.myStage.close();
            }
        } else {
            if (parent != null) {
                Dialog.showWarning("Validation error", "Fix errors", "Name, age and salary cant be empty");
            }
        }
        
            
        
    }
    
    @FXML
    public void cancel(ActionEvent event){
        
        if (parent != null) {
            parent.doOnModalClosed(null);
        }
        this.player = null;
        Stage stage = (Stage) this.btnCreate.getScene().getWindow();
        stage.close();
    }
    
    public Player getPlayer(){
        return player;
    }

    
}
