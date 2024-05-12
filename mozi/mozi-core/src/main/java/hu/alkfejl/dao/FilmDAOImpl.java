package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Vetites;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO{

    private static final String SELECT_ALL_FILM = "SELECT * FROM FILM";
    private static final String INSERT_FILM = "INSERT INTO FILM(cim, hossz, korhatar, rendezo, szereplok, leiras, boritokep) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_FILM = "UPDATE FILM SET cim=?, hossz=?, korhatar=?, rendezo=?, szereplok=?, leiras=?, boritokep=? WHERE film_id=?";
    private static final String DELETE_FILM = "DELETE FROM FILM WHERE film_id = ?";
    private static final String SELECT_CIM_BY_VETITES_ID = "SELECT cim FROM FILM WHERE film_id =?";

    private String connectionURL;


    public FilmDAOImpl() {

        connectionURL = FilmConfig.getValue("db.url"); // obtaining DB URL

    }
    private static FilmDAO instance;

    public static FilmDAO getInstance() {
        if (instance == null) {
            instance = new FilmDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Film> findAll() {

        List<Film> films = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FILM)
        ){

            while (rs.next()) {
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setCim(rs.getString("cim"));
                film.setHossz(rs.getInt("hossz"));
                film.setKorhatar(rs.getInt("korhatar"));
                film.setRendezo(rs.getString("rendezo"));
                film.setSzereplok(rs.getString("szereplok"));
                film.setLeiras(rs.getString("leiras"));
                film.setBoritokep(rs.getString("boritokep"));

                films.add(film);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return films;
    }


    @Override
    public void delete(Film film) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_FILM);
        ) {
            stmt.setInt(1, film.getFilm_id());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Film> findCimByVetitesId(Vetites vetites) {

        return findCimByVetitesId(vetites.getVetites_id());
    }

     @Override
     public List<Film> findCimByVetitesId(int vetitesId) {
        List<Film> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement statement = c.prepareStatement(SELECT_CIM_BY_VETITES_ID)) {

            statement.setInt(1, vetitesId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Film film = new Film();
                film.setCim(rs.getString("cim"));

                result.add(film);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public Film mentes(Film film){
        System.out.println("elsosor");
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = film.getFilm_id() <= 0 ? c.prepareStatement(INSERT_FILM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_FILM)
        ){
            if (film.getFilm_id() > 0){ //UPDATE
                stmt.setInt(8, film.getFilm_id());
                System.out.println("id");
            }

            //cim=?, hossz=?, korhatar=?, rendezo=?, szereplok=?, leiras=?, boritokep=?
            stmt.setString(1, film.getCim());
            stmt.setInt(2, film.getHossz());
            stmt.setInt(3, film.getKorhatar());
            stmt.setString(4, film.getRendezo());
            stmt.setString(5, film.getSzereplok());
            stmt.setString(6, film.getLeiras());
            stmt.setString(7, film.getBoritokep());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if (film.getFilm_id() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    film.setFilm_id(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return film;
    }



}


