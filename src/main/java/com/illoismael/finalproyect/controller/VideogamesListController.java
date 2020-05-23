package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Videogame;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VideogamesListController extends Controllers implements Initializable{

    Connection con;
    private ObservableList<Videogame> data;
    
    @FXML
    private TableColumn<Videogame, String> nameColumn;
    @FXML
    private TableColumn<Videogame, String> descriptionColumn;
    @FXML
    private TableColumn<Videogame, String> typeColumn;
    
    @FXML
    private TableView<Videogame> tblVideogames;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.data = FXCollections.observableArrayList();
        List<Videogame> videogames = VideogameDAO.selectAll();
        data.addAll(videogames);

        //Nomenclatura lambda
        nameColumn.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        descriptionColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });
        typeColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getType());
        });
        
        
         tblVideogames.setItems(data);
    }
    
        
    
}
