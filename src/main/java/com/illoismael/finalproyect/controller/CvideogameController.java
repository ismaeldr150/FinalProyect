
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.enums.VideogameType;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.Dialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CvideogameController extends Controllers implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label type;
    @FXML
    private TextField tname;
    @FXML
    private TextField tdescription;
    @FXML
    private ComboBox<String> options;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
    
    public ObservableList<Videogame> videogames;
    
    private Videogame videogame;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb();
    }
    
    
    public void cb(){
        for (VideogameType cbp : VideogameType.values()) {
            options.getItems().addAll(cbp.getCombo());
        }
    }
    
    
    public void iniAttributte(ObservableList<Videogame> videogames){
        this.videogames = videogames;
    }
    
    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla CVideogame.fxml
     * @param event 
     */
    @FXML
    public void create(ActionEvent event){
        String name = this.name.getText();
        String description = this.description.getText();
        String type = this.type.getText();
        
        Videogame v = new Videogame(name, description, type);
        
        if(!videogames.contains(v)){
            this.videogames = (ObservableList<Videogame>) v;
            Dialog.showInformation("Información", null, "Se ha añadido correctamente");
            
            Stage stage = (Stage) this.btnCreate.getScene().getWindow();
            stage.close();
        } else {
            Dialog.showError("ERROR", null, "La persona ya existe");
        }
    }
    
    @FXML
    public void cancel(ActionEvent event){
        this.videogame = null;
        Stage stage = (Stage) this.btnCreate.getScene().getWindow();
        stage.close();
    }
    
    public Videogame getVideogame(){
        return videogame;
    }
    
    
    
    
    
    
    
    
    /*
    @FXML
    public void addVideogame() {
        Videogame newV=new Videogame();
        VideogameDAO newDao=new VideogameDAO(newV);
        newDao.save();
        newV.setCodVideogame(newDao.getCodVideogame());
        videogames.add(newV);
        
    }

    @FXML
    public void removeVideogame() {
        Videogame selected = videogameTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            videogames.remove(selected);
            VideogameDAO cc = new VideogameDAO(selected);
            cc.remove();
        }
    }
    */
    
}
