package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.*;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Foglalas;
import hu.alkfejl.model.Vetites;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FoglalasModositController implements Initializable {
    FoglalasDAO foglalasDAO = new FoglalasDAOImpl();
    VetitesDAO vetitesDAO = new VetitesDAOImpl();
    private Foglalas foglalas;

    @FXML
    private TextField foglaloNeve;

    @FXML
    private ComboBox vetitesek;

    @FXML
    private TextField foglaltSor;

    @FXML
    private TextField foglaltOszlop;

    public void setFoglalas(Foglalas foglalas){
        this.foglalas = foglalas;

        foglaloNeve.textProperty().bindBidirectional(foglalas.foglalo_neveProperty());
        foglaltSor.textProperty().bindBidirectional(foglalas.foglalt_sorProperty(), new NumberStringConverter());
        foglaltOszlop.textProperty().bindBidirectional(foglalas.foglalt_oszlopProperty(), new NumberStringConverter());

        List<Vetites> vetitesList = new VetitesDAOImpl().findVetites(foglalas.getFoglalas_id());
        foglalas.setVetitesek(FXCollections.observableArrayList(vetitesList));

        vetitesek.itemsProperty().bindBidirectional(foglalas.vetitesekProperty());
    }
    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/foglalas.fxml");
    }

    @FXML
    public void onSave(){
        foglalas = foglalasDAO.mentes(foglalas);
        App.loadFXML("/fxml/foglalas.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
