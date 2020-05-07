package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.model.Coach;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;

public class CoachDAO extends Coach implements DAO{

    public CoachDAO(int id, String name, int age, String phone, Videogame videogame, Team team, int idEmpleado) {
        super(id, name, age, phone, videogame, team, idEmpleado);
    }

    @Override
    public void persist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detach() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
