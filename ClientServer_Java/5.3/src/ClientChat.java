/*
 * Exercise 5.3
 */

import java.io.*;
import java.net.*;
import java.util.*;

class ClientChat {

    public static final int YOU = 0;
    public static final int OTHER = 1;
    public static final int EXIT = 2;

    public static void main(String argv[]) throws Exception {
        int status = YOU;
        String str = "";

        Socket clientSocket = new Socket("127.0.0.1", 6789);

        Scanner inFromOther = new Scanner(clientSocket.getInputStream());
        Scanner inFromYou = new Scanner(System.in);
        PrintStream outToOther = new PrintStream(clientSocket.getOutputStream());
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

        clientSocket.close();
    }
}