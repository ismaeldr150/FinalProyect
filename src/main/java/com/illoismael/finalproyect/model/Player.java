package com.illoismael.finalproyect.model;

public class Player extends Person implements IPlayer{
    
    private int idEmpleado;

    public Player() {
    }
 

    public Player(int id, String name, int age, String phone, Videogame videogame, Team team, int idEmpleado) {
        super(id, name, age, phone, videogame, team);
    }
    
    public Player(int id, String name, int age, String phone, Videogame videogame, Team team) {
        super(id, name, age, phone, videogame, team);
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Player{" + "idEmpleado=" + idEmpleado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.idEmpleado != other.idEmpleado) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
