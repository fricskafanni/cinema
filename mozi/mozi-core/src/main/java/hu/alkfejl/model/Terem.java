package hu.alkfejl.model;

import javafx.beans.property.*;

public class Terem {
    private IntegerProperty azonosito = new SimpleIntegerProperty(this, "azonosito");
    private IntegerProperty sorok_szama = new SimpleIntegerProperty(this, "sorok_szama");
    private IntegerProperty oszlopok_szama = new SimpleIntegerProperty(this, "oszlopok_szama");

    //getter setter


    public int getAzonosito() {
        return azonosito.get();
    }

    public IntegerProperty azonositoProperty() {
        return azonosito;
    }

    public void setAzonosito(int azonosito) {
        this.azonosito.set(azonosito);
    }

    public int getSorok_szama() {
        return sorok_szama.get();
    }

    public IntegerProperty sorok_szamaProperty() {
        return sorok_szama;
    }

    public void setSorok_szama(int sorok_szama) {
        this.sorok_szama.set(sorok_szama);
    }

    public int getOszlopok_szama() {
        return oszlopok_szama.get();
    }

    public IntegerProperty oszlopok_szamaProperty() {
        return oszlopok_szama;
    }

    public void setOszlopok_szama(int oszlopok_szama) {
        this.oszlopok_szama.set(oszlopok_szama);
    }
}
