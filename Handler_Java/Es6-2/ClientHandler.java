/*
Angelo Saginario 863003188
05/11/24
*/

import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler implements ProtocolHandler{
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void handle() throws IOException{
        Scanner fromUser = new Scanner(System.in);
        PrintStream toServer = new PrintStream(socket.getOutputStream());
        Scanner fromServer = new Scanner(socket.getInputStream());

        int n;
        int f;

        do {
            System.out.println("Scrivi un intero di cui vuoi il fattoriale: ");
            n = fromUser.nextInt();
            toServer.println(n);
            f = fromServer.nextInt();
            if(n>0) System.out.println(f + " è il risultato");
        }while(n>=0);

        socket.close();
    }
}
