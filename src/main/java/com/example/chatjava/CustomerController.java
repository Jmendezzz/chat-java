package com.example.chatjava;

import com.example.chatjava.model.Customer;
import javafx.application.Application;
import javafx.css.CssMetaData;
import javafx.css.SimpleSelector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private  TextArea chatText;
    @FXML
    private  TextField textInput;
    public Socket socket;
    public String userName;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;


    @FXML
    public void sendButtonHandler() throws IOException {
        if(!textInput.getText().equals("")){
            dataOutputStream.writeUTF(userName+": "+textInput.getText());
            textInput.setText("");
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Ejecutando customercontroller");


        Thread thread = new Thread(() -> {
            if(socket!=null){
                while (socket.isConnected()){

                    try{
                        String msj = dataInputStream.readUTF();
                        Text name =new Text( msj.split(":")[0]);
                        name.setFill(Color.DARKSEAGREEN);
                        Text msjText = new Text(msj.split(":")[1]);
                        chatText.appendText(name.getText() + ":"+msjText.getText() +"\n");

                    }catch (IOException err){
                        System.out.println(err.getMessage());
                    }

                }
            }
            });


        thread.start();

    }

    public void setUserData(Socket socket, String userName) {
        try {
            this.userName=userName;
            this.socket=socket;
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

