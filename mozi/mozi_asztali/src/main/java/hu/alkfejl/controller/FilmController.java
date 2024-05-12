package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.model.Film;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FilmController implements Initializable {


    FilmDAO dao = new FilmDAOImpl();
    private List<Film> all;

    @FXML
    private TableView<Film> filmTable;

    @FXML
    private TableColumn<Film, String> cimColumn;

    @FXML
    private TableColumn<Film, Integer> hosszColumn;

    @FXML
    private TableColumn<Film, Void> actionColumn;

    @FXML
    private TextField cimSearch;

    @FXML
    private TextField szereplokSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        filmTable.getItems().setAll(dao.findAll());

        cimColumn.setCellValueFactory(new PropertyValueFactory<>("cim"));
        hosszColumn.setCellValueFactory(new PropertyValueFactory<>("hossz"));

        actionColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Töröl");
            private final Button editBtn = new Button("Módosít");

            {
                deleteBtn.setOnAction(event -> {
                    Film film = getTableRow().getItem();
                    deleteFilm(film);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Film film = getTableRow().getItem();
                    editFilm(film);
                    System.out.println("edit");
                    refreshTable();
                });
            }

                @Override
                protected void updateItem(Void item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                    }else {
                        HBox container = new HBox();
                        container.getChildren().addAll(editBtn, deleteBtn);
                        container.setSpacing(10.0);
                        setGraphic(container);
                    }
                }


            });
    }


    @FXML
    public void onExit(){
        Platform.exit();
    }

    private void deleteFilm(Film film){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos benne, hogy törli a " + film.getCim() + " filmet?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                dao.delete(film);
            }
        });
    }

    private void refreshTable() {
        all = dao.findAll();
        filmTable.getItems().setAll(all);
    }

    private void editFilm(Film film){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/filmModosit.fxml");
        FilmModositController controller = fxmlLoader.getController();
        controller.setFilm(film);
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/kezdolap.fxml");
    }

    @FXML
    public void onAddNewContact(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/filmModosit.fxml"));
        FilmModositController controller = fxmlLoader.getController();
        controller.setFilm(new Film());
    }




    @FXML
    public void onSearch(){
        List<Film> filtered = all.stream().filter(film -> film.getCim()
                .contains(cimSearch.getText()) && film
                .getSzereplok()
                .contains(szereplokSearch.getText())).collect(Collectors.toList());
        filmTable.getItems().setAll(filtered);
    }


}
