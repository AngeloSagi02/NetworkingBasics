/*
Angelo Saginario 863003188
05/11/24
*/
import java.net.*;

public class ServerFact {
    public static void main(String[] args)  throws Exception {

            ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            /*
            ProtocolHandler ph = new ServerHandler(connectionSocket);
            */
            ProtocolHandler ph = new ServerHandlerThread(connectionSocket);
            ph.handle();
        }

    }


}