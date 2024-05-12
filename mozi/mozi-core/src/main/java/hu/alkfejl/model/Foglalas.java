package hu.alkfejl.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Foglalas {

    private IntegerProperty foglalas_id = new SimpleIntegerProperty(this, "foglalas_id");
    private IntegerProperty vetites_id = new SimpleIntegerProperty(this, "vetites_id");
    private StringProperty foglalo_neve = new SimpleStringProperty(this, "foglalo_neve");
    private ObjectProperty<ObservableList<Vetites>> vetitesek = new SimpleObjectProperty<>(this, "vetitesek");
    private IntegerProperty foglalt_sor = new SimpleIntegerProperty(this, "foglalt_sor");
    private IntegerProperty foglalt_oszlop = new SimpleIntegerProperty(this, "foglalt_oszlop");


    public int getVetites_id() {
        return vetites_id.get();
    }

    public IntegerProperty vetites_idProperty() {
        return vetites_id;
    }

    public void setVetites_id(int vetites_id) {
        this.vetites_id.set(vetites_id);
    }

    public ObservableList<Vetites> getVetitesek() {
        return vetitesek.get();
    }

    public ObjectProperty<ObservableList<Vetites>> vetitesekProperty() {
        return vetitesek;
    }

    public void setVetitesek(ObservableList<Vetites> vetitesek) {
        this.vetitesek.set(vetitesek);
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

    public String getFoglalo_neve() {
        return foglalo_neve.get();
    }

    public StringProperty foglalo_neveProperty() {
        return foglalo_neve;
    }

    public void setFoglalo_neve(String foglalo_neve) {
        this.foglalo_neve.set(foglalo_neve);
    }


    public int getFoglalt_sor() {
        return foglalt_sor.get();
    }

    public IntegerProperty foglalt_sorProperty() {
        return foglalt_sor;
    }

    public void setFoglalt_sor(int foglalt_sor) {
        this.foglalt_sor.set(foglalt_sor);
    }

    public int getFoglalt_oszlop() {
        return foglalt_oszlop.get();
    }

    public IntegerProperty foglalt_oszlopProperty() {
        return foglalt_oszlop;
    }

    public void setFoglalt_oszlop(int foglalt_oszlop) {
        this.foglalt_oszlop.set(foglalt_oszlop);
    }
}
