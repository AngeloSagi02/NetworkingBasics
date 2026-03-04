/*
Angelo Saginario 863003188
05/11/24
*/
import java.io.*;
import java.net.*;
import java.util.*;

class AppHandler implements ProtocolHandler{
    private Socket socket;
    private byte status;
    private String str;

    public static final byte YOU = 0;
    public static final byte OTHER = 1;
    public static final byte EXIT = 2;


    public AppHandler(Socket socket, byte status){
        this.socket = socket;
        this.status = status;
    }

    public void handle() throws IOException{

        Scanner inFromOther = new Scanner(socket.getInputStream());
        Scanner inFromYou = new Scanner(System.in);
        PrintStream outToOther = new PrintStream(socket.getOutputStream());
        PrintStream outToYou = System.out;

        do {
            switch (status) {
                case YOU:
                    System.out.print("YOU-->");
                    str = inFromYou.nextLine();
                    outToOther.println(str);
                    if(str.contains ("-")) status = OTHER;
                    break;
                case OTHER:
                    System.out.print("PEER-->");
                    str = inFromOther.nextLine();
                    outToYou.println(str);
                    if(str.contains ("-")) status = YOU;
                    break;
            }
            if(str.contains(".")) status = EXIT;

        }while(status != EXIT);

        socket.close();
    }
}
