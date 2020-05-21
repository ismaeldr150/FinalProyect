package com.illoismael.finalproyect.model;

public class Team implements ITeam {

    protected int codTeam;
    protected String name;

    public Team() {
        this.codTeam = -1;
        this.name = "";
    }

    public Team(int codTeam, String name) {
        this.codTeam = codTeam;
        this.name = name;

    }
    public Team(String name){
        this.name="";
    }
    

    public int getCodTeam() {
        return codTeam;
    }

    public void setCodTeam(int codTeam) {
        this.codTeam = codTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" + "codTeam=" + codTeam + ", name=" + name + '}';
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
        final Team other = (Team) obj;
        if (this.codTeam != other.codTeam) {
            return false;
        }
        return true;
    }

}
