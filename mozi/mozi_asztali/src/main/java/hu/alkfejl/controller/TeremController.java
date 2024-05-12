package hu.alkfejl.controller;

import hu.alkfejl.App;
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.dao.TeremDAO;
import hu.alkfejl.dao.TeremDAOImpl;
import hu.alkfejl.model.Film;
import hu.alkfejl.model.Terem;
import hu.alkfejl.model.Vetites;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TeremController implements Initializable {

    TeremDAO dao = new TeremDAOImpl();

    @FXML
    private TableView<Terem> teremTable;
    @FXML
    private TableColumn<Terem, Integer> columnAzonosito;
    @FXML
    private TableColumn<Terem, String> columnSorokSzama;
    @FXML
    private TableColumn<Terem, Integer> columnOszlopokSzama;

    @FXML
    private TableColumn<Terem, Void> actionColumn;

    @FXML
    public void onCancel(){
        App.loadFXML("/fxml/kezdolap.fxml");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();

        teremTable.getItems().setAll(dao.findAllTerem());

        columnAzonosito.setCellValueFactory(new PropertyValueFactory<>("azonosito"));
        columnSorokSzama.setCellValueFactory(new PropertyValueFactory<>("sorok_szama"));
        columnOszlopokSzama.setCellValueFactory(new PropertyValueFactory<>("oszlopok_szama"));

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Töröl");
            private final Button editBtn = new Button("Módosít");

            {
                deleteBtn.setOnAction(event -> {
                    Terem terem = getTableRow().getItem();
                    deleteTerem(terem);
                    refreshTable();
                });

                editBtn.setOnAction(event -> {
                    Terem terem = getTableRow().getItem();
                    editTerem(terem);
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

    private void deleteTerem(Terem terem){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Biztos benne, hogy törli a " + terem.getAzonosito() + " termet?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                dao.delete(terem);
            }
        });
    }

    private void editTerem(Terem terem){
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/teremModosit.fxml");
        TeremModositController controller = fxmlLoader.getController();
        controller.setTerem(terem);
    }

    private void refreshTable() {
        teremTable.getItems().setAll(dao.findAllTerem());
    }

    @FXML
    public void onAddNewTerem(){
        FXMLLoader fxmlLoader = App.loadFXML(("/fxml/teremModosit.fxml"));
        TeremModositController controller = fxmlLoader.getController();
        controller.setTerem(new Terem());
    }

}
