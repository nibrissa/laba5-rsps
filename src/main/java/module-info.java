module com.example.labs5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labs5 to javafx.fxml;
    exports com.example.labs5;
}