/*
Angelo Saginario 863003188
05/11/24
*/
import java.net.Socket;

class ClientChat {

    public static void main(String[] argv) throws Exception {
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        AppHandler ph = new AppHandler(clientSocket);
        ph.handle();
    }
}