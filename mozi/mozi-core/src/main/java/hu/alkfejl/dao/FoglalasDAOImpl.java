package hu.alkfejl.dao;

import hu.alkfejl.config.FilmConfig;
import hu.alkfejl.model.Foglalas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoglalasDAOImpl implements FoglalasDAO{

    private static final String SELECT_ALL_FOGLALAS = "SELECT * FROM FOGLALAS";
    private static final String DELETE_FOGLALAS = "DELETE FROM FOGLALAS WHERE foglalas_id = ?";
    private static final String INSERT_FOGLALAS = "INSERT INTO FOGLALAS(foglalo_neve, vetites_id, foglalt_sor, foglalt_oszlop) VALUES (?,?,?,?)";
    private static final String UPDATE_FOGLALAS = "UPDATE FOGLALAS SET foglalo_neve=?, vetites_id=?, foglalt_sor=?, foglalt_oszlop=? WHERE foglalas_id=?";
    private String connectionURL;
    
    public FoglalasDAOImpl() {

        connectionURL = FilmConfig.getValue("db.url"); // obtaining DB URL
    }

    @Override
    public List<Foglalas> findAll() {

        List<Foglalas> foglalasok = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FOGLALAS)
        ){

            while (rs.next()) {
                Foglalas foglalas = new Foglalas();
                foglalas.setFoglalas_id(rs.getInt("foglalas_id"));
                foglalas.setFoglalo_neve(rs.getString("foglalo_neve"));
                foglalas.setVetites_id(rs.getInt("vetites_id"));
                foglalas.setFoglalt_sor(rs.getInt("foglalt_sor"));
                foglalas.setFoglalt_oszlop(rs.getInt("foglalt_oszlop"));

                foglalasok.add(foglalas);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return foglalasok;
    }

    @Override
    public void delete(Foglalas foglalas) {
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_FOGLALAS);
        ) {
            stmt.setInt(1, foglalas.getFoglalas_id());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Foglalas mentes(Foglalas foglalas){
        System.out.println("elsosor");
        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = foglalas.getFoglalas_id() <= 0 ? c.prepareStatement(INSERT_FOGLALAS, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_FOGLALAS)
        ){
            if (foglalas.getFoglalas_id() > 0){ //UPDATE
                stmt.setInt(5, foglalas.getFoglalas_id());
                System.out.println("id");
            }


            stmt.setString(1, foglalas.getFoglalo_neve());
            stmt.setInt(2, foglalas.getVetites_id());
            stmt.setInt(3, foglalas.getFoglalt_sor());
            stmt.setInt(4, foglalas.getFoglalt_oszlop());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if (foglalas.getFoglalas_id() <= 0){ //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()){
                    foglalas.setFoglalas_id(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return foglalas;
    }


}
