package com.illoismael.finalproyect.controller;

import javafx.stage.Stage;

public abstract class ModalControllers {
    Controllers parentController;
    Stage stage;
    
    public Controllers getParentController() {
        return parentController;
    }

    public void setParentController(Controllers parentController) {
        this.parentController = parentController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    abstract public void setParams(Object p);
}
