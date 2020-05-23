package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.enums.VideogameType;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.Dialog;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CvideogameController extends Controllers implements Initializable {

    //ELEMENTOS DE LA ESCENA <--
    @FXML
    private SplitPane sp;
    @FXML
    private GridPane gp;

    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label type;

    @FXML
    private TextField tname;
    @FXML
    private TextField tdescription;
    

    @FXML
    private ComboBox<String> options;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;

    public ObservableList<Videogame> videogames;

    private Videogame videogame;

    private LVideogameController parent;
    private Object params;
    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb();
    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(LVideogameController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }

    public void cb() {
        for (VideogameType cbp : VideogameType.values()) {
            options.getItems().addAll(cbp.getCombo());
        }
    }


    /**
     * Método para darle funcionalidad al botón "Create" de la pantalla
     * CVideogame.fxml
     *
     * @param event
     */
    @FXML
    public void create(ActionEvent event) {
        String Nname = this.name.getText();
        String Ndescription = this.description.getText();
        String Ntype = this.type.getText();

        if (Nname.trim().length() > 0 && Ndescription.trim().length() > 0 && Ntype.trim().length() > 0) {

            Videogame v = new Videogame(-1, Nname, Ndescription, Ntype);
            if (parent != null) {
                parent.doOnCloseModal(v);
            }

            if (this.myStage != null) {
                this.myStage.close();
            }
        } else {
            if (parent != null) {
                Dialog.showWarning("Validation error", "Fix errors", "Name, Description and Type cant be empty");
            }
        }

    }

    @FXML
    public void cancel(ActionEvent event) {

        if (parent != null) {
            parent.doOnModalClosed(null);
        }

        this.videogame = null;
        Stage stage = (Stage) this.btnCreate.getScene().getWindow();
        stage.close();
    }

    public Videogame getVideogame() {
        return videogame;
    }

}
