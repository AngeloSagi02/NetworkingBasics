/*
Exercise 5.2
Write the same application of exercise 5.1 by using the datagram-oriented communication model (UDP). Exploit
Wireshark to capture the data exchanged between client and server.
* */

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.ByteBuffer;

class DatagramClient {
    public static void main(String args[]) throws Exception {
        Scanner inFromUser = new Scanner(System.in);
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        byte[] sendData;
        byte[] receiveData = new byte[1024];

        int x = inFromUser.nextInt();
        int y = inFromUser.nextInt();
        long result;

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(x);
        buffer.putInt(y);

        sendData = buffer.array();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1200);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        buffer = ByteBuffer.wrap(receivePacket.getData());
        result = buffer.getLong();

        System.out.println("FROM SERVER:" + result);
    }
}