/*
Angelo Saginario 863003188
05/11/24
*/

import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {

    public static void main(String[] args)  throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();

        AppHandler ph = new AppHandler(connectionSocket);
        ph.handle();

    }


}