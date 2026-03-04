/*
Angelo Saginario 863003188
05/11/24
*/

import java.io.IOException;
import java.net.Socket;

public class ServerHandlerThread extends ServerHandler implements Runnable {

    private Socket socket;
    private Thread tr;

    public ServerHandlerThread(Socket socket) {
        super(socket);
        tr = new Thread(this);
    }
    public void run() {
        try {
            super.handle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handle(){
        System.out.println("Server handler thread started");
        tr.start();
    }
}
