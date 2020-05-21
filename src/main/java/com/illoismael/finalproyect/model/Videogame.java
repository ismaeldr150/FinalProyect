package com.illoismael.finalproyect.model;

import com.illoismael.finalproyect.enums.VideogameType;

public class Videogame implements IVideogame{
    protected int codVideogame;
    protected String name;
    protected String description;
    //private VideogameType type;
    protected String type;

    public Videogame() {
        this.codVideogame = -1;
        this.name = "";
        this.description = "";
        this.type = null;
    }

    public Videogame(int codVideogame, String name, String desciption, String type) {
        this.codVideogame = codVideogame;
        this.name = name;
        this.description = desciption;
        this.type = type;
    }

    public Videogame(String name, String desciption, String type) {
        this.name = name;
        this.description = desciption;
        this.type = type;
    }
    
    

    public int getCodVideogame() {
        return codVideogame;
    }

    public void setCodVideogame(int codVideogame) {
        this.codVideogame = codVideogame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Videogame{" + "codVideogame=" + codVideogame + ", name=" + name + ", desciption=" + description + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Videogame other = (Videogame) o;
        if (this.codVideogame != other.codVideogame) {
            return false;
        }
        return true;
    }
    
    
}
