package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            final String input = in.readLine();
            Gson gson = new Gson();
            var operation = gson.fromJson(input,Operation.class);
            if(operation.type.equals("ADD")){
                todos.addTask(operation.task);
            } else if(operation.type.equals("REMOVE")){
                todos.removeTask(operation.task);
            }
            out.println(todos.getAllTasks());
        }
    }

    public class Operation {
        String type;
        String task;
    }
}
