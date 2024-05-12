package hu.alkfejl.controller;


import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.dao.VetitesDAO;
import hu.alkfejl.dao.VetitesDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Vetites;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class VetitesController implements Initializable {

    VetitesDAO dao = new VetitesDAOImpl();

    @FXML
    private TableView<Vetites> vetitesTable;

    @FXML
    private TableColumn<Vetites, Integer> vetitesIdColumn;
    @FXML
    private TableColumn<Vetites, Integer> filmIdColumn;
    @FXML
    private TableColumn<Vetites, LocalDate> datumColumn;
    @FXML
    private TableColumn<Vetites, LocalTime> idopontColumn;
    @FXML
    private TableColumn<Vetites, String> teremIdColumn;

    @FXML
    private TableColumn<Vetites, Integer> jegyArColumn;

    @FXML
    private TableColumn<Vetites, Void> actionColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        vetitesTable.getItems().setAll(dao.findAllVetites());

        vetitesIdColumn.setCellValueFactory(new PropertyValueFactory<>("vetites_id"));
        filmIdColumn.setCellValueFactory(new PropertyValueFactory<>("film_id"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        idopontColumn.setCellValueFactory(new PropertyValueFactory<>("idopont"));
        teremIdColumn.setCellValueFactory(new PropertyValueFactory<>("terem_id"));
        jegyArColumn.setCellValueFactory(new PropertyValueFactory<>("jegy_ar"));


        actionColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Töröl");
            private final Button editBtn = new Button("Módosít");

            {
                deleteBtn.setOnAction(event -> {
                    Vetites vetites = getTableRow().getItem();
                    deleteVetites(vetites);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Vetites vetites = getTableRow().getItem();
                    editVetites(vetites);
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

    private void deleteVetites(Vetites vetites){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos benne, hogy törli a vetítést?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                dao.delete(vetites);
            }
        });
    }

    private void editVetites(Vetites vetites){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/vetitesModosit.fxml");
        VetitesModositController controller = fxmlLoader.getController();
        controller.setVetites(vetites);
    }

    private void refreshTable() {
        vetitesTable.getItems().setAll(dao.findAllVetites());
    }

    @FXML
    public void onAddNewVetites(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/vetitesModosit.fxml"));
        VetitesModositController controller = fxmlLoader.getController();
        controller.setVetites(new Vetites());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/kezdolap.fxml");
    }
}
