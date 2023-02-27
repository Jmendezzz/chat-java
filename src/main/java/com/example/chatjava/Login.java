package com.example.chatjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 660, 646);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
