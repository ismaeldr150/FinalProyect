package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.TeamDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Team;
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

public class TeamsListController extends Controllers implements Initializable {

    Connection con;
    private ObservableList<Team> data;
    @FXML
    private TableColumn<Team, String> nameColumn;
    @FXML
    private TableView<Team> tblTeams;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        List<Team> team = TeamDAO.selectAll();
        data.addAll(team);

        //Nomenclatura lambda
        nameColumn.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });

         tblTeams.setItems(data);
    }
}
