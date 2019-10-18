package com.sda;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {

    public static void main(String[] args) throws IOException {
        HelloServer helloServer = new HelloServer();
        helloServer.start(5555);
    }

    void start(int portNumber) throws IOException {

        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();

        handleClient(clientSocket);
    }

    private void handleClient(Socket clientSocket) throws IOException {
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        out.write("Tell me your name:\n");
        out.flush();

        String name = in.readLine();

        out.write("Hello " + name);
        out.flush();
    }
}
