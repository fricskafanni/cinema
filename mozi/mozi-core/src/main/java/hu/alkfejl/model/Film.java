package hu.alkfejl.model;

import javafx.beans.property.*;

public class Film {

    private IntegerProperty film_id = new SimpleIntegerProperty(this, "film_id");
    private StringProperty cim = new SimpleStringProperty(this, "cim");
    private IntegerProperty hossz = new SimpleIntegerProperty(this, "hossz");  //percben van megadva
    private IntegerProperty korhatar = new SimpleIntegerProperty(this, "korhatar");
    private StringProperty rendezo = new SimpleStringProperty(this, "rendezo");
    private StringProperty szereplok = new SimpleStringProperty(this, "szereplok");
    private StringProperty leiras = new SimpleStringProperty(this, "leiras");
    private StringProperty boritokep = new SimpleStringProperty(this, "boritokep");
    private IntegerProperty vetites_id = new SimpleIntegerProperty(this, "vetites_id");

  /*  public Film(int film_id, String cim, int hossz, int korhatar, String rendezo, String szereplok, String leiras, String boritokep) {

        film_id = film_id;
        cim = cim;
        hossz = hossz;
        korhatar = korhatar;
        rendezo = rendezo;
        szereplok = szereplok;
        leiras = leiras;
        boritokep = boritokep;

    }
    public Film() {

    }*/

    //Getter setter


    public int getVetites_id() {
        return vetites_id.get();
    }

    public IntegerProperty vetites_idProperty() {
        return vetites_id;
    }

    public void setVetites_id(int vetites_id) {
        this.vetites_id.set(vetites_id);
    }

    @Override
    public String toString() {
        return cim.getValue();
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

    public String getCim() {
        return cim.get();
    }

    public StringProperty cimProperty() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim.set(cim);
    }

    public int getHossz() {
        return hossz.get();
    }

    public IntegerProperty hosszProperty() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz.set(hossz);
    }

    public int getKorhatar() {
        return korhatar.get();
    }

    public IntegerProperty korhatarProperty() {
        return korhatar;
    }

    public void setKorhatar(int korhatar) {
        this.korhatar.set(korhatar);
    }

    public String getRendezo() {
        return rendezo.get();
    }

    public StringProperty rendezoProperty() {
        return rendezo;
    }

    public void setRendezo(String rendezo) {
        this.rendezo.set(rendezo);
    }

    public String getSzereplok() {
        return szereplok.get();
    }

    public StringProperty szereplokProperty() {
        return szereplok;
    }

    public void setSzereplok(String szereplok) {
        this.szereplok.set(szereplok);
    }

    public String getLeiras() {
        return leiras.get();
    }

    public StringProperty leirasProperty() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras.set(leiras);
    }

    public String getBoritokep() {
        return boritokep.get();
    }

    public StringProperty boritokepProperty() {
        return boritokep;
    }

    public void setBoritokep(String boritokep) {
        this.boritokep.set(boritokep);
    }


}