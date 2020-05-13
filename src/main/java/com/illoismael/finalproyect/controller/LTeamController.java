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

public class LTeamController implements Initializable{
    
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label label;
    @FXML
    private TableView table;
    @FXML
    private TableColumn name;
    @FXML
    private Button cancel;
    @FXML
    private Button BTnew;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
