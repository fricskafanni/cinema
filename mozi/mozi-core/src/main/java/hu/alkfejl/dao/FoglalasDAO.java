package hu.alkfejl.dao;

import hu.alkfejl.model.Foglalas;

import java.util.List;

public interface FoglalasDAO {
    public List<Foglalas> findAll();
    public void delete(Foglalas foglalas);
    public Foglalas mentes(Foglalas foglalas);
}
