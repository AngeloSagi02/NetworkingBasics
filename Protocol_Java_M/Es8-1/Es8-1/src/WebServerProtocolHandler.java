
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class WebServerProtocolHandler implements ProtocolHandler{
    Socket socket;

    public WebServerProtocolHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() throws IOException {
        InputStream input = null;
        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(new InputStreamReader(input));

        DataOutputStream os = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        System.out.println(new Date());
        String request = sc.nextLine();

        if (sc.hasNextLine()) {
            request = sc.nextLine();
            System.out.println("\n" + request);
        }

        String line = "";
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            System.out.println(line);
            if (line.isEmpty()) { break; }
        }

        os.writeBytes("HTTP/1.1 200 OK\r\n");
        os.writeBytes("Content-type: text/html\r\n");
        os.writeBytes("\r\n");
        os.writeBytes("<HTML><HEAD></HEAD><BODY><H2>Hello<H2></BODY></HTML>");
        os.flush();
        os.close();

        System.out.println("Processed");
    }
}
