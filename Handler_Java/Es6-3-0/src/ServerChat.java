/*
Angelo Saginario 863003188
05/11/24
*/
import java.net.*;

public class ServerChat {

    public static final byte OTHER = 1;

    public static void main(String[] args)  throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();

        ProtocolHandler ph = new AppHandler(connectionSocket, OTHER);
        ph.handle();

    }


}