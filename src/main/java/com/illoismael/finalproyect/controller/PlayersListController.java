package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Player;
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

public class PlayersListController extends Controllers implements Initializable {

    Connection con;
    private ObservableList<Player> data;
    
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, String> ageColumn;
    @FXML
    private TableColumn<Player, String> salaryColumn;
    
    @FXML
    private TableView<Player> tblPlayers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        List<Player> player = PlayerDAO.selectAll();
        data.addAll(player);

        //Nomenclatura lambda
        nameColumn.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        
        //Error nomenclatura lambda con tipos INT
        /*
         ageColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getAge());
        });
        salaryColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSalary());
        });
        */
        
         tblPlayers.setItems(data);
    }

}
