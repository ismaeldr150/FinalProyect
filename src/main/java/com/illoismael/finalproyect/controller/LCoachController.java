package com.illoismael.finalproyect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LCoachController implements Initializable{
    
    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label title;
    @FXML
    private TableView table;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn age;
    @FXML
    private TableColumn phone;
    @FXML
    private TableColumn teamName;
    @FXML
    private TableColumn salary;
    @FXML
    private Button Cancel;
    @FXML
    private Button newBT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
