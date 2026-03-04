/*
Angelo Saginario 863003188
05/11/24
*/
import java.io.*;
import java.net.*;
import java.util.*;

class ServerHandler implements ProtocolHandler{
    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    public void handle() throws IOException{

        PrintStream toUser = new PrintStream(socket.getOutputStream());
        Scanner fromUser = new Scanner(socket.getInputStream());
        int n;
        int f;

        do {
            n = fromUser.nextInt();
            f = fact(n);
            toUser.println(f);
        }while(n>=0);

        socket.close();
    }

    int fact(int n){
        if (n<=1) return 1;
        else return fact(n-1)*n;
    }
}
