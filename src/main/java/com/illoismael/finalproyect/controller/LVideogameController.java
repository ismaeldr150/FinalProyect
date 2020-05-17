
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.model.Videogame;
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

public class LVideogameController implements Initializable{
    
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
    
    private ObservableList<Videogame> videogames;
    private Videogame videogame;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Método que sirve para abrir una ventana modal (CVideogame) 
     * para rellenar los datos del videojuego
     */
    public void newVideogame() {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CVideogame"));
            
            Parent root = loader.load();
            
            CvideogameController controlador = loader.getController();
            controlador.iniAttributte(videogames);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Videogame v = controlador.getVideogame();
            if(v != null){
                this.videogames.add(v);
                this.tblVideogames.refresh();
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cancel(ActionEvent event){
        this.videogame = null;
        Stage stage = (Stage) this.btnNew.getScene().getWindow();
        stage.close();
    }
    
}
