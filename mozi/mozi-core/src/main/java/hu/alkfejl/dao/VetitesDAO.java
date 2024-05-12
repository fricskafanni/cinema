package hu.alkfejl.dao;

import hu.alkfejl.model.Foglalas;
import hu.alkfejl.model.Vetites;

import java.util.List;

public interface VetitesDAO {
    List<Vetites> findAllVetites();
    void delete(Vetites vetites);
    Vetites mentes(Vetites vetites);
    List<Vetites> findVetites(Foglalas foglalas);
    List<Vetites> findVetites(int foglalasId);

}
