package com.illoismael.finalproyect.model;

import com.illoismael.finalproyect.enums.VideogameType;

public class Videogame implements IVideogame{
    private int codVideogame;
    private String name;
    private String desciption;
    private VideogameType type;

    public Videogame() {
    }

    public Videogame(int codVideogame, String name, String desciption, VideogameType type) {
        this.codVideogame = codVideogame;
        this.name = name;
        this.desciption = desciption;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public VideogameType getType() {
        return type;
    }

    public void setType(VideogameType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Videogame{" + "codVideogame=" + codVideogame + ", name=" + name + ", desciption=" + desciption + ", type=" + type + '}';
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
