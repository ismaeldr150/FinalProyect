
package com.illoismael.finalproyect.enums;

public enum ComboBoxPrimary {
    
    PLAYER("Player"),
    COACH("Coach"),
    TEAM("Team"),
    VIDEOGAME("Videogame");
    
    private String url;
    
    private ComboBoxPrimary(String u){
        this.url = u;
    }
    
    public String getCombo(){
        return url;
    }
    
}
