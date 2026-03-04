/*
Angelo Saginario 863003188
05/11/24
*/
import java.net.*;

class ClientFact {
    public static void main(String[] argv) throws Exception {
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        ProtocolHandler ph = new ClientHandler(clientSocket);
        ph.handle();
    }
}