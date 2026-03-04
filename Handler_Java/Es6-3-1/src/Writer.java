/*
Angelo Saginario 863003188
05/11/24
*/

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Writer extends Thread {
    private Socket socket;

    public Writer(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        String str;

        Scanner inFromYou = new Scanner(System.in);
        PrintStream outToOther = null;
        try {
            outToOther = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            do {
                System.out.print("YOU-->");
                str = inFromYou.nextLine();
                outToOther.println(str);
            }while(!str.contains("."));
        } finally {
            //chiudiamo la socket in maniera parziale
            try{socket.shutdownOutput();} catch (IOException e) {e.printStackTrace();
                if (outToOther!=null) outToOther.close();
            }
        }
    }

}
