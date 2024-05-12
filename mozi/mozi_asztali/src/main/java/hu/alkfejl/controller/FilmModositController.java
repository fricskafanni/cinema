package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Terem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FilmModositController implements Initializable {

    FilmDAO filmDAO = new FilmDAOImpl();
    private Film film;


    @FXML
    private TextField cim;

    @FXML
    private TextField hossz;

    @FXML
    private TextField korhatar;

    @FXML
    private TextField rendezo;

    @FXML
    private TextField szereplok;

    @FXML
    private TextField leiras;

    @FXML
    private TextField boritokep;



    public void setFilm(Film film) {
        this.film = film;

        cim.textProperty().bindBidirectional(film.cimProperty());
        hossz.textProperty().bindBidirectional(film.hosszProperty(), new NumberStringConverter());
        korhatar.textProperty().bindBidirectional(film.korhatarProperty(), new NumberStringConverter());
        rendezo.textProperty().bindBidirectional(film.rendezoProperty());
        szereplok.textProperty().bindBidirectional(film.szereplokProperty());
        leiras.textProperty().bindBidirectional(film.leirasProperty());
        boritokep.textProperty().bindBidirectional(film.boritokepProperty());
    }
    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/film.fxml");
    }

    @FXML
    public void onSave(){
        film = filmDAO.mentes(film);
        App.loadFXML("/fxml/film.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
