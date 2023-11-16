package com.example.labs5;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class AddController{
        @FXML
        private TextField text_client,text_number,text_type,text_status,text_dateopen,text_dateclose;
        private Stage dialog;
        private ObservableList<Agreement> agreements = FXCollections.observableArrayList();
        @FXML
        void add(ActionEvent event) {
                if(!text_client.getText().isEmpty() && !text_number.getText().isEmpty() && !text_dateclose.getText().isEmpty() && !text_dateopen.getText().isEmpty() && !text_type.getText().isEmpty() && !text_status.getText().isEmpty()){
                        agreements.add(new Agreement(text_number.getText(),text_client.getText(),text_type.getText(),text_status.getText(),text_dateopen.getText(),text_dateclose.getText()));
                        dialog.close();}
                else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.initOwner(dialog);
                        alert.setTitle("Пустое поле!");
                        alert.setHeaderText("Одно или несколько полей пусты!");
                        alert.showAndWait();
                }
        }
        @FXML
        void cancel(ActionEvent event) {dialog.close();}
        public void getdialog(Stage dialog)
        {this.dialog = dialog;}
        public void getagreements(ObservableList<Agreement> agreements) {this.agreements = agreements;}
}



