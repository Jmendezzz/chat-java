package com.example.chatjava;


import com.example.chatjava.model.Customer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static ServerSocket server;


    public static void main(String[] args) {


            try{
                server = new ServerSocket(5050);

                while  (true){
                    System.out.println("Esperando conexion");
                    Socket socket = server.accept();
                    System.out.println("Usuario conectado: "+socket.getLocalAddress());
                    DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
                    DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                    String name = flujoEntrada.readUTF();
                    String message = name + " Se ha unido al chat";
                    System.out.println(message);

                    Customer customer = new Customer(name,socket,flujoSalida,flujoEntrada);
                    Thread thread = new Thread(customer::waitingForMessages);
                    thread.start(); //Ejecuto el hilo por cada customer que se va agregando para que este a la espera de nuevos mensajes.
                }

            }catch (IOException e){
                System.out.println("No se pudo crear el server");
            }

    }

}