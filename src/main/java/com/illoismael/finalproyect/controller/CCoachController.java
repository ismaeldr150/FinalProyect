
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.dao.CoachDAO;
import com.illoismael.finalproyect.model.Coach;
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

public class CCoachController implements Initializable{  
    
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
    private Label teamName;
    @FXML
    private Label salary;
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tage;
    @FXML
    private TextField Tphone;
    @FXML
    private TextField TteamName;
    @FXML
    private TextField Tsalary;
    @FXML
    private Button cancel;
    @FXML
    private Button create;
    
    private TableView<Coach> coachTable;
    public ObservableList<Coach> coachs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @FXML
    public void addCoach() {
        Coach newC=new Coach();
        CoachDAO newDao=new CoachDAO(newC);
        newDao.save();
        newC.setId(newDao.getId());
        coachs.add(newC);
        
    }
    
    
    @FXML
    public void removeCoach() {
        Coach selected = coachTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            coachs.remove(selected);
            CoachDAO cc = new CoachDAO(selected);
            cc.remove();
        }
    }
    
    
}
