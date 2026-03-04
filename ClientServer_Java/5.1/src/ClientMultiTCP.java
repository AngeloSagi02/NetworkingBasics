import java.io.*;
        import java.net.*;
        import java.util.*;
class ClientMultiTCP {
    public static void main(String argv[]) throws Exception {
        int x;
        int y;
        int result;
        Scanner inFromUser = new Scanner(System.in);
        Socket clientSocket = new Socket("127.0.0.1", 6789);

        PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());
        x = inFromUser.nextInt();
        y = inFromUser.nextInt();

        outToServer.println(x);
        outToServer.println(y);

        result = inFromServer.nextInt();
        System.out.println("FROM SERVER: " + result);
        clientSocket.close();
    }
}