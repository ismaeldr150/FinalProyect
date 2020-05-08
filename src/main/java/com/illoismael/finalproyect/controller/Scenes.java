package com.illoismael.finalproyect.controller;

public enum Scenes {
    
    ROOT("view/root"), 
    LIST("view/list"),
    PRIMARY("view/primary"),
    CREATE("view/create"),
    ABOUT("view/about"),
    EDIT("view/editConnection");

    private String url;
 
    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }
 
    public String getUrl() {
        return url;
    }
    
}
