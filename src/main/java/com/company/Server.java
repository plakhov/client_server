package com.company;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            System.out.println("Создали серверный сокет");
            System.out.println("Ожидаем подключение клиента");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String inputString;
            Message message;
            do {
                inputString = dataInputStream.readUTF();
                message = new Gson().fromJson(inputString, Message.class);
                System.out.println("Echo clientId="+message.getClientId()+": ");
                System.out.println(message.getMessage());
            } while (!"\\finish".equals(message.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
