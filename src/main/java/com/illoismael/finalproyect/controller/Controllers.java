package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;

public abstract class Controllers implements IControllers{
    public App app;
    @Override
    public void setMainApp(App app){
        this.app=app;
        this.onLoad();
    }
    
    @Override
    public void onLoad(){};
    //To be ovewritten
    @Override
    public void doOnCloseModal(Object response){}
    
    
}
