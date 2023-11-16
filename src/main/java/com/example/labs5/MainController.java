package com.example.labs5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TableColumn<Agreement, String> column_client,column_dateclose,column_dateopen,column_number,column_status,column_type;
    @FXML
    private TableView<Agreement> table_view;
    @FXML
    private Label l_client,l_dateclose,l_dateopen,l_number,l_status,l_type;
    ObservableList<Agreement> agreements = FXCollections.observableArrayList(
            new Agreement("E1232FG","ПАО \"Солидное счастье\"","Предварительный ","Завершен","02.10.2023","09.10.2023"),
            new Agreement("Р5123","ООО \"ТЕХНИК\"","Безвозмездный","Завершен","01.10.2023","03.10.2023"),
            new Agreement("Е3234А3","ООО \"Зрение\"","Двухсторонний","В работе","09.10.2023","25.12.2023"),
            new Agreement("B23111","ООО \"Кодекс\"","Публичный","Подготовка","27.11.2023","14.01.2024"));


    @FXML
    void add(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("addview.fxml"));
        dialog.setScene(new Scene(loader.load(),600, 400));
        AddController controller = loader.getController();
        controller.getdialog(dialog);
        controller.getagreements(agreements);
        dialog.showAndWait();
        table_view.setItems(FXCollections.observableList(agreements));
    }

    @FXML
    void delete(ActionEvent event) {
        if (table_view.getSelectionModel().getSelectedItem()!=null) {
            agreements.remove(table_view.getSelectionModel().getSelectedItem());
            table_view.setItems(FXCollections.observableList(agreements));
            l_number.setText("Договор №");
            l_client.setText("Клиент: ");
            l_type.setText("Тип: ");
            l_status.setText("Статус договора: ");
            l_dateopen.setText("Дата заключения договора: ");
            l_dateclose.setText("Дата закрытия договора: ");
        }
    }

    @FXML
    void edit(ActionEvent event) throws IOException {
        if (table_view.getSelectionModel().getSelectedItem()!=null) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("editview.fxml"));
            stage.setScene(new Scene(loader.load(),600, 400));
            EditController controller = loader.getController();
            controller.getdialog(stage);
            int id = agreements.indexOf(table_view.getSelectionModel().getSelectedItem());
            controller.getagreeemet(agreements.get(id));
            stage.showAndWait();
            table_view.setItems(agreements);
            table_view.getSelectionModel().clearSelection();
            table_view.getSelectionModel().select(id);
            table_view.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column_number.setCellValueFactory(new PropertyValueFactory<Agreement,String>("number"));
        column_client.setCellValueFactory(new PropertyValueFactory<Agreement,String>("client"));
        column_type.setCellValueFactory(new PropertyValueFactory<Agreement,String>("type"));
        column_status.setCellValueFactory(new PropertyValueFactory<Agreement,String>("status"));
        column_dateopen.setCellValueFactory(new PropertyValueFactory<Agreement,String>("dateopen"));
        column_dateclose.setCellValueFactory(new PropertyValueFactory<Agreement,String>("dateclose"));
        table_view.setItems(agreements);

        table_view.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Agreement agreement = table_view.getSelectionModel().getSelectedItem();
                l_number.setText("Договор №"+agreement.getNumber());
                l_client.setText("Клиент: "+agreement.getClient());
                l_type.setText("Тип: "+agreement.getType());
                l_status.setText("Статус договора: "+agreement.getStatus());
                l_dateopen.setText("Дата заключения договора: "+agreement.getDateopen());
                l_dateclose.setText("Дата закрытия договора: "+agreement.getDateclose());
            }
        });
    }
}
