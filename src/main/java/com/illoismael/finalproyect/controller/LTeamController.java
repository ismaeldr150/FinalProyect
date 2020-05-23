package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;
import com.illoismael.finalproyect.dao.TeamDAO;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Team;
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

public class LTeamController extends Controllers implements Initializable {

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private Label label;
    @FXML
    private TableView<Team> tblTeams;
    @FXML
    private TableColumn<Team, String> C_name;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNewTeam;
    @FXML
    private Button btnDelete;

    private ObservableList<Team> teams;
    private Team team;
    Connection con;

    private LTeamController parent;
    private Object params;
    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.teams = FXCollections.observableArrayList();
        List<Team> t = TeamDAO.selectAll();
        teams.addAll(t);

        //Nomenclatura lambda
        C_name.setCellValueFactory(eachRowData -> {
            //Convertimos un String a ObservableString
            return new SimpleObjectProperty<>(eachRowData.getValue().getName());
        });

        //Editables
        C_name.setCellFactory(TextFieldTableCell.forTableColumn());
        C_name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Team, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Team, String> t) {
                Team selected = (Team) t.getTableView().getItems().get(t.getTablePosition().getRow());

                selected.setName(t.getNewValue());

                TeamDAO vd = new TeamDAO(selected);
                vd.save();
            }
        }
        );

        tblTeams.setEditable(true);

        //Indicamos la información que muestra la tabla
        tblTeams.setItems(teams);
    }

    /**
     * Método que sirve para abrir una ventana modal (CTeam) para rellenar los
     * datos del equipo
     */
    public void newTeam() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("CTeam.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("New Team");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.mainStage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            CTeamController modalController = fxmlLoader.getController();
            if (modalController != null) {
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

    public void doOnModalClosed(Object response) {
        if (response != null) {
            Team t = (Team) response;
            teams.add(t);
            TeamDAO td = new TeamDAO(t);
            int newCode = td.save();
            t.setCodTeam(newCode);
        }
    }

    @FXML
    public void deleteTeam() {
        Team selected = tblTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {

            if (!Dialog.showConfirm2(selected.getName())) {
                return;
            }

            teams.remove(selected);

            //Eliminar de BBDD (No funciona)
            TeamDAO vd = new TeamDAO(selected);
            vd.remove();

        } else {
            Dialog.showWarning("WAIT!", "No team selected!", "You must select a team");
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        this.team = null;
        Stage stage = (Stage) this.btnNewTeam.getScene().getWindow();
        stage.close();
    }

}
