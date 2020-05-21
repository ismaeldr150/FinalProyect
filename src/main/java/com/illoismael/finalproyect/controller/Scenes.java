package com.illoismael.finalproyect.controller;

public enum Scenes {
    
    ROOT("root"), 
    PRIMARY("primary"),
    ABOUT("about"),
    
    L_VIDEOGAMES("LVideogames"),
    L_TEAM("LTeam"),
    L_PLAYER("LPlayer"),
    
    C_PLAYER("CPlayer"),
    C_VIDEOAGAME("CVideogame"),
    C_TEAM("CTeam"),
    
    GENERAL_LIST("GeneralList"),
    PLAYERS_LIST("PlayersList"),
    TEAMS_LIST("TeamsList"),
    VIDEOGAMES_LIST("VideogamesList");

    private String url;
 
    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }
 
    public String getUrl() {
        return url;
    }
    
}
