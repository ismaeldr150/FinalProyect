package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.enums.VideogameType;
import com.illoismael.finalproyect.model.Videogame;

public class VideogameDAO extends Videogame implements IDAO {

    public static String SELECT_ALL = "SELECT * FROM videogame";
    public static String INSERT = "INSERT INTO videogame (codVideogame,name,description,type) VALUES (NULL,?,?,?)";
    public static String GETBYCODE = "SELECT * FROM videogame WHERE codVideogame=?";
    public static String FINDBYCODE = "SELECT * FROM videogame WHERE codVideogame IN";
    public static String FINDBYNAME = "SELECT * FROM videogame WHERE name LIKE ?";
    public static String UPDATE = "UPDATE videoagme SET name = ?, description = ?, type = ? = WHERE codVideogame = ?";
    public static String REMOVE = "DELETE FROM videogame WHERE codVideogame=?";

    public static String select2 = "WHERE name LIKE ?";

    java.sql.Connection con;
    private boolean persist;

    public VideogameDAO(Videogame v) {
        super();
    }

    public VideogameDAO(int codVideogame, String name, String desciption, VideogameType type) {
        super(codVideogame, name, desciption, type);
    }

    public VideogameDAO() {
    }

    @Override
    public void persist() {
        this.persist = true;
    }

    @Override
    public void detach() {
        this.persist = true;
    }

    @Override
    public void remove() {

    }

    @Override
    public void save() {

    }

}
