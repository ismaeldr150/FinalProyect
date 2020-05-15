package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;
import com.illoismael.finalproyect.PrimaryController;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.utils.MapEntry;
import com.illoismael.finalproyect.utils.PreferencesUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author srism
 */
public class AppController extends Controllers implements IAppController {

    //scene actual cargada
    public Scenes backScene;
    public Scenes currentScene;

    public static Connection currentConnection;

    @Override
    public void onLoad() {
        currentConnection = PreferencesUtil.getPreference();
    }

    /**
     * Recibe la url de una archivo FXML (de la carpeta resources) y devuelve su
     * contenedor y controlador
     *
     * @param fxml url del archivo
     * @return
     * @throws java.io.IOException @see(MapEntry) de un contenedor y su
     * controlador si lo tuviera
     */
    public static MapEntry<Parent, Controllers> loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent p = fxmlLoader.load();
        Controllers c = fxmlLoader.getController();
        MapEntry<Parent, Controllers> result = new MapEntry<>(p, c);
        return result;
    }

    /**
     * Carga en el Layout de la app principal la escena que se le pase en la
     * zona central. (requisito: el layout principal debe ser borderpane). Le
     * pasa automaticamente al controlador de la escena la referncia a la clase
     * principal para poder tener acceso a su controlador.
     *
     * @param scene La escena a cargar @see(Scenes)
     */
    @Override
    public void changeScene(Scenes scene) {
        try {
            MapEntry<Parent, Controllers> m = loadFXML(scene.getUrl());
            this.app.rootLayout.setCenter(m.getKey());
            if (m.getValue() != null) {
                m.getValue().setMainApp(this.app);
            }
            if (backScene != currentScene) {
                backScene = currentScene;
            }
            this.currentScene = scene;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Controllers openModal(Scenes scene, String title, Controllers parentController, Object params) {
        try {
            MapEntry<Parent, Controllers> m = loadFXML(scene.getUrl());
            Stage modalStage = new Stage();
            modalStage.setTitle(title);
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(this.app.mainStage);

            Scene modalScene = new Scene(m.getKey());
            modalStage.setScene(modalScene);

            if (m.getValue() != null) {
                m.getValue().setMainApp(this.app);
                ModalControllers mc = (ModalControllers) m.getValue();
                mc.setParentController(parentController);
                mc.setStage(modalStage);
                mc.setParams(params);
            }
            modalStage.showAndWait();
            return m.getValue();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @FXML
    private Button con;
    
    @FXML
    private Button list;

    //Boton de la pagina de about (diseño)
    @FXML
    
    @Override
public void AboutPage() {
    changeScene(Scenes.ABOUT);
}

    //Boton de crear y editar
    @FXML
    public void CreateEdit() {
        changeScene(Scenes.PRIMARY);
    }

    //Boton de listar (lista de jugadores)
    @FXML
    public void List() {
        changeScene(Scenes.GENERAL_LIST);
    }
 
    @FXML
    @Override
    public void closeApp() {
        System.exit(0);
    }

    @FXML
    @Override
    public void enableCon() {
        if (con != null) {
            con.setDisable(false);
        }
    }

    @FXML
    @Override
    public void disableCon() {
        if (con != null) {
            con.setDisable(true);
        }
    }

    @FXML
    @Override
    public void title(String txt) {
        this.app.mainStage.setTitle(txt + " ( " + (this.currentConnection == null ? "Ismael" : this.currentConnection.getName()) + " )");
    }

}
