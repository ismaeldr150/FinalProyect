package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;

public class TeamDAO extends Team implements DAO{
    
    private boolean persist;

    public TeamDAO(Team t) {
        super();
    }

    public TeamDAO(int codTeam, Player[] players, String name, Videogame videogame, int codVideogame, int idEmpleado) {
        super(codTeam, players, name, videogame, codVideogame, idEmpleado);
    }
    
    

    @Override
    public void persist() {
        this.persist = true;
    }

    @Override
    public void detach() {
        this.persist = false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
