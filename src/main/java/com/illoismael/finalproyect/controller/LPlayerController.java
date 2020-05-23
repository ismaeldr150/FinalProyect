
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;
import com.illoismael.finalproyect.dao.PlayerDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Player;
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

public class LPlayerController extends Controllers implements Initializable{
    
     //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private TableView<Player> tblPlayers;
    @FXML
    private TableColumn<Player,String> name;
    @FXML
    private TableColumn<Player,Integer> age;
    @FXML
    private TableColumn<Player,Integer> salary;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnDelete;
    @FXML
    private Label title;

    
    private ObservableList<Player> players;
    private Player player;
    Connection con;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.players = FXCollections.observableArrayList();
        List<Player> player = PlayerDAO.selectAll();
        players.addAll(player);

        
        //Nomenclatura lambda
        name.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });
        age.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getAge());
        });
        salary.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getSalary());
        });
        

        //Editables
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, String> t) {
                Player selected = (Player) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setName(t.getNewValue());

                PlayerDAO vd = new PlayerDAO(selected);
                vd.save();
            }
        }
        );
        //age.setCellFactory(TextFieldTableCell.forTableColumn());
        age.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, Integer> t) {
                Player selected = (Player) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setAge(t.getNewValue());

                PlayerDAO vd = new PlayerDAO(selected);
                vd.save();
            }
        }
        );
       // salary.setCellFactory(TextFieldTableCell.forTableColumn());
        salary.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Player, Integer> t) {
                Player selected = (Player) t.getTableView().getItems().get(t.getTablePosition().getRow());

               selected.setSalary(t.getNewValue());

                PlayerDAO vd = new PlayerDAO(selected);
                vd.save();
            }
        }
        );
        tblPlayers.setEditable(true);

        //Indicamos la información que muestra la tabla
        tblPlayers.setItems(players);
    }

    /**
     * Método que sirve para abrir una ventana modal (CPlayer) 
     * para rellenar los datos del jugador
     */
    public void newPlayer(ActionEvent event) throws IOException {
        
       FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CPlayer.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();
        
        Stage modalStage = new Stage();
        modalStage.setTitle("New Player");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(App.mainStage);

        Scene modalScene = new Scene(modal);
        modalStage.setScene(modalScene);
        
        CPlayerController modalController = fxmlLoader.getController();
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
            Player p = (Player) response;
            players.add(p);
            PlayerDAO vd = new PlayerDAO(p);
            int newId=vd.save();
            p.setId(newId);
        }
    }
    
    
    @FXML
    public void removePlayer() {
        Player selected = tblPlayers.getSelectionModel().getSelectedItem();
        if (selected != null) {

            if (!Dialog.showConfirm2(selected.getName())) {
                return;
            }

            players.remove(selected);

            //Eliminar de BBDD (No funciona)
            PlayerDAO pd = new PlayerDAO(selected);
            pd.remove();

        } else {
            Dialog.showWarning("WAIT!", "No player selected!", "You must select a player");
        }
    }

    
    
    @FXML
    public void cancel(ActionEvent event){
        this.player = null;
        Stage stage = (Stage) this.btnNew.getScene().getWindow();
        stage.close();
    }
    
   
    
    
    
}
