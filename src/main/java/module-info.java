module com.example.chatjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatjava to javafx.fxml;
    exports com.example.chatjava;
}