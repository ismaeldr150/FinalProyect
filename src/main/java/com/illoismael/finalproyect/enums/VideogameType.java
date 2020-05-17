package com.illoismael.finalproyect.enums;

public enum VideogameType {
    
    
    ACCION("ACCION"),
    SPORT("SPORT"),
    ADVENTURE("ADVENTURE");
    
    private String url;
    
    private VideogameType(String u){
        this.url = u;
    }
    
    public String getCombo(){
        return url;
    }
  
    
}
