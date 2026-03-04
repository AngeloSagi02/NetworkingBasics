
/*
Angelo Saginario 863003188
05/11/24
*/
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Reader extends Thread {

    private Socket socket;

    public Reader(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        String str;
        Scanner inFromOther = null;
        try {
            inFromOther = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintStream outToYou = System.out;

        try{
            do {
                System.out.print("PEER-->");
                str = inFromOther.nextLine();
                outToYou.println(str);
            }while(!str.contains("."));
        } finally {
            //chiudiamo la socket in maniera parziale
            try{socket.shutdownInput();} catch (IOException e) {e.printStackTrace();
            if (inFromOther!=null) inFromOther.close();
            }
        }
    }
}
