package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Foglalas;
import hu.alkfejl.model.Vetites;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VetitesDAOImpl implements VetitesDAO {
    private static final String SELECT_ALL_VETITESEK = "SELECT * FROM VETITES";
    private static final String DELETE_VETITES = "DELETE FROM VETITES WHERE vetites_id = ?";
    private static final String INSERT_VETITES = "INSERT INTO VETITES (film_id, datum, idopont, terem_id, jegy_ar) VALUES (?,?,?,?,?)";
    private static final String UPDATE_VETITES = "UPDATE VETITES SET film_id = ?, datum = ?, idopont = ?, terem_id=? WHERE vetites_id = ?";
    private static final String SELECT_VETITES = "SELECT vetites_id FROM VETITES WHERE foglalas_id =?";
    private String connectionURL;

    public VetitesDAOImpl() {

        connectionURL = FilmConfig.getValue("db.url"); // obtaining DB URL
    }



    @Override
    public List<Vetites> findAllVetites() {
        List<Vetites> vetitesek = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_VETITESEK)
        ){
            while (rs.next()){
                Vetites vetites = new Vetites();
                vetites.setVetites_id(rs.getInt("vetites_id"));
                vetites.setFilm_id(rs.getInt("film_id"));
                LocalDate localDate = LocalDate.parse(rs.getString("datum"));
                vetites.setDatum(localDate == null ? LocalDate.now() : localDate);
                LocalTime localTime = LocalTime.parse(rs.getString("idopont"));
                vetites.setIdopont(localTime == null ? LocalTime.now() : localTime);
                vetites.setTerem_id(rs.getInt("terem_id"));
                vetites.setJegy_ar(rs.getInt("jegy_ar"));

                vetitesek.add(vetites);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vetitesek;
    }

    @Override
    public void delete(Vetites vetites) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_VETITES);
        ) {
            stmt.setInt(1, vetites.getVetites_id());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Vetites mentes(Vetites vetites) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = vetites.getVetites_id() <= 0 ? c.prepareStatement(INSERT_VETITES, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_VETITES)
        ){
            if (vetites.getVetites_id() > 0){ //UPDATE
                stmt.setInt(5, vetites.getVetites_id());
                System.out.println("id");
            }

            stmt.setInt(1, vetites.getFilm_id());
            stmt.setString(2, vetites.getDatum().toString());
            stmt.setString(3, vetites.getIdopont().toString());
            stmt.setInt(4, vetites.getTerem_id());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if (vetites.getVetites_id() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    vetites.setVetites_id(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return vetites;
    }

    @Override
    public List<Vetites> findVetites(Foglalas foglalas) {

        return findVetites(foglalas.getFoglalas_id());
    }

     @Override
     public List<Vetites> findVetites(int foglalasId) {
        List<Vetites> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = c.prepareStatement(SELECT_VETITES)) {

            statement.setInt(1, foglalasId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Vetites vetites = new Vetites();
                vetites.setVetites_id(rs.getInt("vetites_id"));

                result.add(vetites);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
