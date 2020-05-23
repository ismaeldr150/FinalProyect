package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;
import com.illoismael.finalproyect.dao.VideogameDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LVideogameController extends Controllers implements Initializable {

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label title;
    @FXML
    private TableView<Videogame> tblVideogames;

    @FXML
    private TableColumn<Videogame, String> nameColumn;
    @FXML
    private TableColumn<Videogame, String> descriptionColumn;
    @FXML
    private TableColumn<Videogame, Integer> codVideogame;
    @FXML
    private TableColumn<Videogame, String> typeColumn;

    @FXML
    private Button btnNew;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;

    private ObservableList<Videogame> Ob_videogames;
    private Videogame videogame;
    Connection con;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Ob_videogames = FXCollections.observableArrayList();
        List<Videogame> videogames = VideogameDAO.selectAll();
        Ob_videogames.addAll(videogames);

        //Nomenclatura lambda
        nameColumn.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        descriptionColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getDescription());
        });
        typeColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getType());
        });

        //Editables
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Videogame, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Videogame, String> t) {
                Videogame selected = (Videogame) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setName(t.getNewValue());

                VideogameDAO vd = new VideogameDAO(selected);
                vd.save();
            }
        }
        );
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Videogame, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Videogame, String> t) {
                Videogame selected = (Videogame) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setDescription(t.getNewValue());

                VideogameDAO vd = new VideogameDAO(selected);
                vd.save();
            }
        }
        );
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Videogame, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Videogame, String> t) {
                Videogame selected = (Videogame) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setType(t.getNewValue());

                VideogameDAO vd = new VideogameDAO(selected);
                vd.save();
            }
        }
        );
        tblVideogames.setEditable(true);

        //Indicamos la información que muestra la tabla
        tblVideogames.setItems(Ob_videogames);

    }

    /**
     * Método que sirve para abrir una ventana modal (CVideogame) para rellenar
     * los datos del videojuego
     */
    public void newVideogame() {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CVideogame.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();
        
        Stage modalStage = new Stage();
        modalStage.setTitle("New Videogame");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(App.mainStage);

        Scene modalScene = new Scene(modal);
        modalStage.setScene(modalScene);
        
        CvideogameController modalController = fxmlLoader.getController();
        if(modalController != null){
            //AUTOCERRAR
            modalController.setStage(modalStage);
            //HIJO --> PADRE
            modalController.setParent(this);
            //PADRE --> HIJO
            modalController.setParams(null);
        }
        
        modalStage.showAndWait();
        
        } catch (IOException ex) {
            Logger.getLogger(LVideogameController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void doOnModalClosed(Object response){
        if(response != null){
            Videogame v = (Videogame) response;
            Ob_videogames.add(v);
            VideogameDAO vd = new VideogameDAO(v);
            int newCode=vd.save();
            v.setCodVideogame(newCode);
        }
    }

    @FXML
    public void removeVideogame() {
        Videogame selected = tblVideogames.getSelectionModel().getSelectedItem();
        if (selected != null) {

            if (!Dialog.showConfirm2(selected.getName())) {
                return;
            }

            Ob_videogames.remove(selected);

            //Eliminar de BBDD (No funciona)
            VideogameDAO vd = new VideogameDAO(selected);
            vd.remove();

        } else {
            Dialog.showWarning("WAIT!", "No videogame selected!", "You must select a videogame");
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        this.videogame = null;
        Stage stage = (Stage) this.btnNew.getScene().getWindow();
        stage.close();
    }

}
