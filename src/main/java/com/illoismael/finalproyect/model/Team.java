package com.illoismael.finalproyect.model;

public class Team implements ITeam{
    private int codTeam;
    private Player[] players;
    private String name;
    private Videogame videogame;
    private int codVideogame;
    private int idEmpleado;

    public Team() {
    }

    public Team(int codTeam, Player[] players, String name, Videogame videogame, int codVideogame, int idEmpleado) {
        this.codTeam = codTeam;
        this.players = players;
        this.name = name;
        this.videogame = videogame;
        this.codVideogame = codVideogame;
        this.idEmpleado = idEmpleado;
    }

    public int getCodTeam() {
        return codTeam;
    }

    public void setCodTeam(int codTeam) {
        this.codTeam = codTeam;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    public int getCodVideogame() {
        return codVideogame;
    }

    public void setCodVideogame(int codVideogame) {
        this.codVideogame = codVideogame;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Team{" + "codTeam=" + codTeam + ", players=" + players + ", name=" + name + ", videogame=" + videogame + ", codVideogame=" + codVideogame + ", idEmpleado=" + idEmpleado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Team other = (Team) o;
        if (this.codTeam != other.codTeam) {
            return false;
        }
        return true;
    }
    
    
    
}
