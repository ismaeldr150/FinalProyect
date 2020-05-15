package com.illoismael.finalproyect.controller;

public enum Scenes {
    
    ROOT("root"), 
    PRIMARY("primary"),
    ABOUT("about"),
    L_VIDEOGAMES("LVideogames"),
    L_TEAM("LTeam"),
    L_PLAYER("LPlayer"),
    C_VIDEOAGAME("CVideogame"),
    C_TEAM("CTeam"),
    C_PLAYER("Player"),
    GENERAL_LIST("GeneralList");

    private String url;
 
    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }
 
    public String getUrl() {
        return url;
    }
    
}
