package com.example.chatjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene {
    private static Stage stage;
    private  static Scene scene;
    private static Parent root;

    public static void switchScene(ActionEvent e, String resource) throws IOException {
        System.out.println("Cambiando de ventana desde switch controller......");

        root = FXMLLoader.load(SwitchScene.class.getResource(resource));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1400, 710);
        stage.setScene(scene);
        stage.show();
    }
    public static  void switchToApp(ActionEvent e) throws IOException {
        switchScene(e, "fxml/cliente.fxml");
    }
}
