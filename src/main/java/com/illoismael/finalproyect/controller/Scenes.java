package com.illoismael.finalproyect.controller;

public enum Scenes {
    
    ROOT("root"), 
    PRIMARY("primary"),
    ABOUT("about"),
    CREATE("create"),
    L_VIDEOGAMES("LVideogames"),
    L_TEAM("LTeam"),
    L_PLAYER("LPlayer"),
    L_COACH("LCoach"),
    C_VIDEOAGAME("CVideogame"),
    C_TEAM("CTeam"),
    C_PLAYER("Player"),
    C_COACH("CCoach");

    private String url;
 
    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }
 
    public String getUrl() {
        return url;
    }
    
}
