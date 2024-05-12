package hu.alkfejl.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;


import java.time.LocalDate;
import java.time.LocalTime;

public class Vetites {

    private IntegerProperty vetites_id = new SimpleIntegerProperty(this, "vetites_id");
    private IntegerProperty film_id = new SimpleIntegerProperty(this, "film_id");
    private ObjectProperty<ObservableList<Film>> filmek = new SimpleObjectProperty<>(this, "filmek");
    private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<>(this, "datum");
    private ObjectProperty<LocalTime> idopont = new SimpleObjectProperty(this, "idopont");
    private IntegerProperty terem_id = new SimpleIntegerProperty(this, "terem_id");
    private IntegerProperty foglalas_id = new SimpleIntegerProperty(this, "foglalas_id");
    private IntegerProperty jegy_ar = new SimpleIntegerProperty(this, "jegy_ar");


//getter setter
    @Override
    public String toString() {
        return jegy_ar.getValue().toString();
    }

    public int getFoglalas_id() {
        return foglalas_id.get();
    }

    public IntegerProperty foglalas_idProperty() {
        return foglalas_id;
    }

    public void setFoglalas_id(int foglalas_id) {
        this.foglalas_id.set(foglalas_id);
    }

    public ObservableList<Film> getFilmek() {
        return filmek.get();
    }

    public ObjectProperty<ObservableList<Film>> filmekProperty() {
        return filmek;
    }

    public void setFilmek(ObservableList<Film> filmek) {
        this.filmek.set(filmek);
    }

    public int getVetites_id() {
        return vetites_id.get();
    }

    public IntegerProperty vetites_idProperty() {
        return vetites_id;
    }

    public void setVetites_id(int vetites_id) {
        this.vetites_id.set(vetites_id);
    }

    public int getFilm_id() {
        return film_id.get();
    }

    public IntegerProperty film_idProperty() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id.set(film_id);
    }

    public LocalDate getDatum() {
        return datum.get();
    }

    public ObjectProperty<LocalDate> datumProperty() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum.set(datum);
    }

    public LocalTime getIdopont() {
        return idopont.get();
    }

    public ObjectProperty<LocalTime> idopontProperty() {
        return idopont;
    }

    public void setIdopont(LocalTime idopont) {
        this.idopont.set(idopont);
    }

    public int getTerem_id() {
        return terem_id.get();
    }

    public IntegerProperty terem_idProperty() {
        return terem_id;
    }

    public void setTerem_id(int terem_id) {
        this.terem_id.set(terem_id);
    }

    public int getJegy_ar() {
        return jegy_ar.get();
    }

    public IntegerProperty jegy_arProperty() {
        return jegy_ar;
    }

    public void setJegy_ar(int jegy_ar) {
        this.jegy_ar.set(jegy_ar);
    }
}
