package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Terem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeremDAOImpl implements TeremDAO{

    private static final String SELECT_ALL_TEREM = "SELECT * FROM TEREM";
    private static final String DELETE_TEREM = "DELETE FROM TEREM WHERE azonosito = ?";
    private static final String INSERT_TEREM = "INSERT INTO TEREM(sorok_szama, oszlopok_szama) VALUES (?,?)";
    private static final String UPDATE_TEREM = "UPDATE TEREM SET sorok_szama = ?, oszlopok_szama = ? WHERE azonosito=?";
    private String connectionURL;

    public TeremDAOImpl() {

        connectionURL = FilmConfig.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Terem> findAllTerem() {
        List<Terem> termek = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_TEREM)
        ){
            while (rs.next()){
                Terem terem = new Terem();
                terem.setAzonosito(rs.getInt("azonosito"));
                terem.setSorok_szama(rs.getInt("sorok_szama"));
                terem.setOszlopok_szama(rs.getInt("oszlopok_szama"));

                termek.add(terem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return termek;
    }

    @Override
    public void delete(Terem terem) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_TEREM);
        ) {
            stmt.setInt(1, terem.getAzonosito());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Terem mentes(Terem terem) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = terem.getAzonosito() <= 0 ? c.prepareStatement(INSERT_TEREM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_TEREM)
        ){
            if (terem.getAzonosito() > 0){ //UPDATE
                stmt.setInt(3, terem.getAzonosito());

            }

            //sorok_szama=?, oszlopok_szama=?

            stmt.setInt(1, terem.getSorok_szama());
            stmt.setInt(2, terem.getOszlopok_szama());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if (terem.getAzonosito() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    terem.setAzonosito(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return terem;
    }
}
