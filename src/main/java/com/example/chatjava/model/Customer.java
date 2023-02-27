package com.example.chatjava.model;

import com.example.chatjava.CustomerController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Customer {
    public static ArrayList<Customer> customers = new ArrayList<>();

    private String name;
    private Socket socket;
    private DataOutputStream flujoSalida;
    private DataInputStream flujoEntrada;
    private  CustomerController customerController;
    public Customer(String name, Socket socket, DataOutputStream flujoSalida, DataInputStream flujoEntrada) {
        this.name = name;
        this.socket = socket;
        this.flujoSalida = flujoSalida;
        this.flujoEntrada = flujoEntrada;
        customers.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getFlujoSalida() {
        return flujoSalida;
    }

    public void setFlujoSalida(DataOutputStream flujoSalida) {
        this.flujoSalida = flujoSalida;
    }

    public DataInputStream getFlujoEntrada() {
        return flujoEntrada;
    }

    public void setFlujoEntrada(DataInputStream flujoEntrada) {
        this.flujoEntrada = flujoEntrada;
    }
    public static void shareMessages(String message){
        System.out.println("Sharing messages ");
        for (Customer customer : customers){
            try {

                customer.getFlujoSalida().writeUTF(message);

            }catch (IOException er){
                System.out.println("****" + er.getMessage());
                er.printStackTrace();
            }
        }

    }
    public void waitingForMessages(){
        String msj="";
        while (socket.isConnected()){
            System.out.println("Esperando mensajes");
            try{
                msj=flujoEntrada.readUTF();
                shareMessages(msj);
            }catch (IOException err){
                close();
                System.out.println("No se pudo recibir el mensaje " + err.getMessage());
                break;
            }


        }
    }

    public void close(){

        try{
            socket.close();
            flujoEntrada.close();
            flujoSalida.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

}
