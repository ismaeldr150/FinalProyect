
package com.illoismael.finalproyect.enums;

public enum ComboBoxPrimary {
    
    PLAYER("PLAYER"),
    TEAM("TEAM"),
    VIDEOGAME("VIDEOGAME");
    
    private String url;
    
    private ComboBoxPrimary(String u){
        this.url = u;
    }
    
    public String getCombo(){
        return url;
    }
    
}
