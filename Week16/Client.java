import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner inputs = new Scanner(System.in);
        // Match the port of the server, declare an address and a packet.
        int port = 6780;
        InetAddress address;
        DatagramPacket packet;

        System.out.println("Welcome to Walkie Talkie Chatting: \n");

        try {
            // Create socket, get address via localhost
            DatagramSocket socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");

            while (true) {
                // Create byte-array, put it into a packet, send to server
                System.out.print("C:> ");
                String messageToSend = inputs.nextLine();
                byte[] output = messageToSend.getBytes();
                packet = new DatagramPacket(output, output.length, address, port);
                socket.send(packet);

                // If quit is entered loop is broken
                if (messageToSend.equalsIgnoreCase("quit")) {
                    break;
                }

                // Create byte-array for the response from server, receive it via a datagram packet, decode and print
                byte[] input = new byte[256];
                packet = new DatagramPacket(input, input.length);
                socket.receive(packet);
                String received = new String(input);
                System.out.println("S:> " + received);
            }
            // Goes here to quit if quit is entered
            System.out.println("Closing Program.");
            socket.close();
            System.exit(0);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}