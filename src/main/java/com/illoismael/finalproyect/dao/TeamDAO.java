package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;

public class TeamDAO extends Team implements IDAO{
    
    private boolean persist;

    public TeamDAO(Team t) {
        super();
    }

    public TeamDAO(int codTeam, String name) {
        super(codTeam, name);
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
        
    }

    @Override
    public void save() {
        
    }
    
}
