package hu.alkfejl.dao;

import hu.alkfejl.model.Film;
import hu.alkfejl.model.Vetites;

import java.util.List;

public interface FilmDAO {

    List<Film> findAll();
    Film mentes(Film film);
    void delete(Film film);
    List<Film> findCimByVetitesId(int vetitesId);
    List<Film> findCimByVetitesId(Vetites vetites);

}
