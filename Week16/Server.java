import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        Scanner inputs = new Scanner(System.in);

        try {
            // Create socket and bind to port
            DatagramSocket socket = new DatagramSocket(6780);
            System.out.println("Server open on port: 6780\nWaiting for client...\n");

            while (true) {
                // Create byte-array, prepare packet, receive
                byte[] input = new byte[256];
                DatagramPacket packet = new DatagramPacket(input, input.length);
                socket.receive(packet);

                // Convert byte array to string and print the message
                String receivedMessage = new String(input);
                System.out.println("C:> " + receivedMessage);

                // Doesnt work, idea is to quit when client does
                if (receivedMessage.equalsIgnoreCase("quit")) {
                    System.out.println("Client disconnected");
                    break;
                }

                // Create byte-array to fill with a string from user input
                byte[] output = new byte[256];
                System.out.print("S:> ");
                String response = inputs.nextLine();
                output = response.getBytes();

                // Get address and port from the packet received earlier, send our message
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(output, output.length, address, port);
                socket.send(packet);
            }

            // If the If statement worked it would go here.
            System.out.println("Closing server.");
            socket.close();
            System.exit(0);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}