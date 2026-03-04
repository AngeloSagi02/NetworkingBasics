/*
Angelo Saginario 863003188
05/11/24
*/
import java.net.Socket;

class AppHandler  {

    Socket socket;
    public AppHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle(){
        Writer writer = new Writer(socket);
        Reader reader = new Reader(socket);

        writer.start();
        reader.start();
    }
}
