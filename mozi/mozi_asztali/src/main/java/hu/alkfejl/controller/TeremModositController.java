package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.dao.TeremDAO;
import hu.alkfejl.dao.TeremDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Terem;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class TeremModositController {

    TeremDAO teremDAO = new TeremDAOImpl();
    private Terem terem;

    @FXML
    private TextField sorok_szama;

    @FXML
    private TextField oszlopok_szama;


    public void setTerem(Terem terem) {
        this.terem = terem;

        sorok_szama.textProperty().bindBidirectional(terem.sorok_szamaProperty(), new NumberStringConverter());
        oszlopok_szama.textProperty().bindBidirectional(terem.oszlopok_szamaProperty(), new NumberStringConverter());

    }

    @FXML
    public void onSave(){
        terem = teremDAO.mentes(terem);
        App.loadFXML("/fxml/terem.fxml");
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/terem.fxml");
    }
}
