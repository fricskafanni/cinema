package hu.alkfejl.controller;


import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.dao.VetitesDAO;
import hu.alkfejl.dao.VetitesDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Vetites;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class VetitesModositController implements Initializable {

    private Vetites vetites;

    private VetitesDAO vetitesDAO = new VetitesDAOImpl();
    private FilmDAO filmDAO = new FilmDAOImpl();


    @FXML
    private ComboBox filmek;

    @FXML
    private DatePicker datum;

    @FXML
    private TextField idopont;

    @FXML
    private TextField terem_id;

    @FXML
    private Label timeError;

    @FXML
    private Button saveButton;

    public void setVetites(Vetites v) {
        this.vetites = v;

        List<Film> filmList = new FilmDAOImpl().findCimByVetitesId(v.getVetites_id());
        vetites.setFilmek(FXCollections.observableArrayList(filmList));

        filmek.itemsProperty().bindBidirectional(vetites.filmekProperty());

        datum.valueProperty().bindBidirectional(vetites.datumProperty());
        idopont.textProperty().bindBidirectional(vetites.idopontProperty(), new LocalTimeStringConverter());
        terem_id.textProperty().bindBidirectional(vetites.terem_idProperty(), new NumberStringConverter());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idopont.textProperty().addListener((observable, oldValue, newValue) -> {
            String regex = "(([0-5][0-9]):([0-5][0-9]))";
            if (newValue != null) {
                if (newValue.matches(regex)) {
                    timeError.setText("");
                } else {
                    timeError.setText("Rosszul adta meg az időt!");
                }
            }
        });

        saveButton.disableProperty().bind(datum.valueProperty().isNull().or(idopont.textProperty().isEmpty()));
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/vetites.fxml");
    }

    @FXML
    public void onSave(){
        vetites = vetitesDAO.mentes(vetites);
        App.loadFXML("/fxml/vetites.fxml");
    }
}
