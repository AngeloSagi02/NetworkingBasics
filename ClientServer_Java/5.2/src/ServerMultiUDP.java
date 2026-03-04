
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

class DatagramServer {
        public static void main(String args[]) throws Exception {
            DatagramSocket serverSocket = new DatagramSocket(1200);

            int a;
            int b;
            long result;

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                ByteBuffer buffer = ByteBuffer.wrap(receivePacket.getData());

                a = buffer.getInt();
                b = buffer.getInt();
                result = a*(long)b;

                buffer = ByteBuffer.allocate(8);
                buffer.putLong(result);

                sendData=buffer.array();

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }
    }

