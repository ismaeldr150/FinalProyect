
package com.illoismael.finalproyect.controller;

import static com.illoismael.finalproyect.controller.AppController.loadFXML;
import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.MapEntry;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LVideogameController extends Controllers implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label title;
    @FXML
    private TableView<Videogame> tblVideogames;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn description;
    @FXML
    private TableColumn type;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    
    private ObservableList<Videogame> videogames;
    private Videogame videogame;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    /**
     * Método que sirve para abrir una ventana modal (CVideogame) 
     * para rellenar los datos del videojuego
     */
    public void newVideogame() throws IOException {
       Stage stage = new Stage();
       MapEntry<Parent, Controllers> m = loadFXML(Scenes.C_VIDEOAGAME.getUrl());
       Parent modal = m.getKey();

       Scene modalScene = new Scene(modal);
       
       stage.setTitle("Create player...");
       stage.initModality(Modality.WINDOW_MODAL);
       stage.initOwner(this.app.mainStage);
       stage.setScene(modalScene);
       stage.showAndWait();

       //ARREGLAR PARA QUE ACTUALICE LA TABLA
       CvideogameController controlador = null;
       controlador.iniAttributte(videogames);
       if(videogame != null){
                this.videogames.add(videogame);
                this.tblVideogames.refresh();
                
            }
    }
    @FXML
    public void removeVideogame() {
        Videogame selected = tblVideogames.getSelectionModel().getSelectedItem();
        if (selected != null) {
            videogames.remove(selected);
            VideogameDAO cc = new VideogameDAO(selected);
            cc.remove();
        }
    }
    
    
    @FXML
    public void cancel(ActionEvent event){
        this.videogame = null;
        Stage stage = (Stage) this.btnNew.getScene().getWindow();
        stage.close();
    }
    
}
