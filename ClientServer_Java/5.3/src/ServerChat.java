/*
* Exercise 5.3
*/

import java.io.*;
import java.net.*;
import java.util.*;

class ServerChat {

    public static final int YOU = 0;
    public static final int OTHER = 1;
    public static final int EXIT = 2;

    public static void main(String[] args)  throws Exception {
        int status = OTHER;
        String str = "";

        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();

        Scanner inFromOther = new Scanner(connectionSocket.getInputStream());
        Scanner inFromYou = new Scanner(System.in);
        PrintStream outToOther = new PrintStream(connectionSocket.getOutputStream());
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

        connectionSocket.close();
    }

}