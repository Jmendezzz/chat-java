package com.example.chatjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Cliente.class.getResource("servidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        System.out.println("Esperando clientes!");
        try {
            ServerSocket server = new ServerSocket(9806);
            Socket socket = server.accept();//bloqueante
            System.out.println("Nos conectamos!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(message);
        } catch (IOException err) {
            System.out.println(err.getMessage());
            throw new RuntimeException();
        }


    }
}
