
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CvideogameController implements Initializable{
    
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
    private ComboBox types;
    @FXML
    private Button cancel;
    @FXML
    private Button create;
    
    
    private TableView<Videogame> videogameTable;
    public ObservableList<Videogame> videogames;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
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
    
}
