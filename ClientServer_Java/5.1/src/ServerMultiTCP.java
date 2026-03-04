/*
* Exercise 5.1
Write a client/server application in Java for remote multiplication of two integers. Use the stream-oriented
communication model (TCP). Exploit Wireshark to capture the data exchanged between client and server.
* */

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMultiTCP {
    public static void main(String[] args)  throws Exception {
        int x;
        int y;
        int result;

        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            Scanner inFromClient = new Scanner(connectionSocket.getInputStream());

            PrintStream outToClient = new PrintStream(connectionSocket.getOutputStream());
            x = inFromClient.nextInt();
            y = inFromClient.nextInt();
            result = x*y;
            outToClient.println(result);
            connectionSocket.close();
        }
    }

}