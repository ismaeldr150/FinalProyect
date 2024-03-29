package com.illoismael.finalproyect;

import com.illoismael.finalproyect.controller.AppController;
import com.illoismael.finalproyect.controller.Controllers;
import com.illoismael.finalproyect.controller.Scenes;
import com.illoismael.finalproyect.utils.MapEntry;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;;
import javafx.scene.layout.BorderPane;

/**
 * JavaFX App
 */
public class App extends Application {
    
    public Scene scene;
    public static Stage mainStage;
    public BorderPane rootLayout;

    public AppController controller;

    @Override
    public void start(Stage stage) throws IOException {
        
        MapEntry<Parent, Controllers> m = AppController.loadFXML(Scenes.ROOT.getUrl());
        
        mainStage=stage;
        rootLayout=(BorderPane)m.getKey();
        scene = new Scene(rootLayout, 640, 480);
        stage.setScene(scene);
        
        controller=(AppController)m.getValue();
        controller.setMainApp(this);
        controller.changeScene(Scenes.PRIMARY);
        stage.show();   
    }

/*
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
*/
    public static void main(String[] args) {
        launch();
    }

}