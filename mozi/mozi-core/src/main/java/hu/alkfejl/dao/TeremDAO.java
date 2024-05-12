package hu.alkfejl.dao;

import hu.alkfejl.model.Terem;

import java.util.List;

public interface TeremDAO {
    List<Terem> findAllTerem();

    void delete(Terem terem);

    Terem mentes(Terem terem);
}
