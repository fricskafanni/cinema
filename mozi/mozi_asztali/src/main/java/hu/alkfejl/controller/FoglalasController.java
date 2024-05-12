package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.dao.FoglalasDAO;
import hu.alkfejl.dao.FoglalasDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Foglalas;
import hu.alkfejl.model.Vetites;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FoglalasController implements Initializable {

    FoglalasDAO dao = new FoglalasDAOImpl();
    private List<Foglalas> all;

    @FXML
    private TableView<Foglalas> foglalasTable;

    @FXML
    private TableColumn<Foglalas, Integer> foglalasId;

    @FXML
    private TableColumn<Foglalas, String> foglaloNeve;

    @FXML
    private TableColumn<Foglalas, Integer> vetitesId;

    @FXML
    private TableColumn<Foglalas, Integer> foglaltSor;

    @FXML
    private TableColumn<Foglalas, Integer> getFoglaltOszlop;

    @FXML
    private TableColumn<Foglalas, Void> actionColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        foglalasTable.getItems().setAll(dao.findAll());

        foglalasId.setCellValueFactory(new PropertyValueFactory<>("foglalas_id"));
        foglaloNeve.setCellValueFactory(new PropertyValueFactory<>("foglalo_neve"));
        vetitesId.setCellValueFactory(new PropertyValueFactory<>("vetites_id"));
        foglaltSor.setCellValueFactory(new PropertyValueFactory<>("foglalt_sor"));
        getFoglaltOszlop.setCellValueFactory(new PropertyValueFactory<>("foglalt_oszlop"));

        actionColumn.setCellFactory(param -> new TableCell<>(){
            private final Button deleteBtn = new Button("Töröl");
            private final Button editBtn = new Button("Módosít");

            {
                deleteBtn.setOnAction(event -> {
                    Foglalas foglalas = getTableRow().getItem();
                    deleteFoglalas(foglalas);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Foglalas foglalas = getTableRow().getItem();
                    editFoglalas(foglalas);
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

    private void deleteFoglalas(Foglalas foglalas){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos benne, hogy törli a " + foglalas.getFoglalas_id() + " foglalást?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                dao.delete(foglalas);
            }
        });
    }

    private void refreshTable() {
        all = dao.findAll();
        foglalasTable.getItems().setAll(all);
    }

    private void editFoglalas(Foglalas foglalas){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/foglalasModosit.fxml");
        FoglalasModositController controller = fxmlLoader.getController();
        controller.setFoglalas(foglalas);
    }

    @FXML
    public void onAddNewFoglalas(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/foglalasModosit.fxml"));
        FoglalasModositController controller = fxmlLoader.getController();
        controller.setFoglalas(new Foglalas());
    }

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/kezdolap.fxml");
    }
}
