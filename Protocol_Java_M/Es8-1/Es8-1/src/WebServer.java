


import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args)  throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(8000);
        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            WebServerProtocolHandler request = new WebServerProtocolHandler(connectionSocket);
            request.handle();
        }

    }
}
