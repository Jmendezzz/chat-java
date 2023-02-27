package com.example.chatjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;


public class LoginController {

    @FXML
    private TextField usernameField;

    private  String userName="";


    @FXML
    public void joinButtonHandler(ActionEvent event){
        try {
            validateUsername();
            Socket socket = new Socket("localhost", 5050);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(userName);
            System.out.println("Cambiando de ventana desde login controller......");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/cliente.fxml"));
            Parent root = loader.load();
            CustomerController customerController = loader.getController();
            customerController.setUserData(socket,userName);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1400, 710);
            stage.setScene(scene);
            stage.show();

        }
        catch(RuntimeException | IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();

        }
    }
    public void validateUsername () throws RuntimeException{
        if (usernameField.getText().equals("")) {
            throw new RuntimeException("Debe completar el campo de nombre para poder enviar mensajes");
        } else{
            userName= usernameField.getText();
            usernameField.setText(userName);

        }
    }
}
