package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.model.Film;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KezdolapController {

    public void filmekButtonAction (ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/film.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Filmek");
            primaryStage.show();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void termekButtonAction (ActionEvent event){
        try {
            Parent root = FXMLLoader.load((getClass().getResource("/fxml/terem.fxml")));
            Scene scene2 = new Scene(root);
            Stage primaryStage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage2.setScene(scene2);
            primaryStage2.setTitle("Termek");
            primaryStage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void vetitesekButtonAction (ActionEvent event){
        try {
            Parent root = FXMLLoader.load((getClass().getResource("/fxml/vetites.fxml")));
            Scene scene2 = new Scene(root);
            Stage primaryStage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage2.setScene(scene2);
            primaryStage2.setTitle("Vetitesek");
            primaryStage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void foglalasokButtonAction (ActionEvent event){
        try {
            Parent root = FXMLLoader.load((getClass().getResource("/fxml/foglalas.fxml")));
            Scene scene2 = new Scene(root);
            Stage primaryStage2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage2.setScene(scene2);
            primaryStage2.setTitle("Foglalasok");
            primaryStage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
