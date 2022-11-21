module com.example.mytasks {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mytasks to javafx.fxml;
    exports com.example.mytasks;
}