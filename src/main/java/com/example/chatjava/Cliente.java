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

public class Cliente extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Cliente.class.getResource("cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        System.out.println("Soy un cliente!");
        try {
            Socket socket = new Socket("localhost",9806);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String message = input.readLine();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) throws IOException {
        launch();

    }
}