package com.illoismael.finalproyect;

import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.model.Coach;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class PrimaryController implements Initializable{
    
    //LISTAS OBSERVABLES DE OBJETOS//
    public ObservableList<Player> players;
    public ObservableList<Coach> coachs;
    public ObservableList<Team> teams;
    public ObservableList<Videogame> videogames
            ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Cargando Controlador Primario");
    }

    
    
    
    @FXML
    public void addPlayer() {
        Player newP=new Player();
        PlayerDAO newDao=new PlayerDAO(newP);
        newDao.save();
        newP.setId(newDao.getId());
        players.add(newP);
    }
    
    
    void onLoad() {
        this.app.controller.title("FINAL PROYECT - D.A.M");
        this.app.controller.enableCon();
    }
    
    
}
