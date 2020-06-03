import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        // Instantiate a ClientHandler
        ClientHandler handler = new ClientHandler();

        try {
            // Create a Server Socket on port 5959
            ServerSocket serverSocket = new ServerSocket(5959);

            while (true) {

                // Serversockets accepts a socket
                Socket socket = serverSocket.accept();

                // From socket get Inetaddress, from Inetaddress get hostAddress
                InetAddress inetAddress = socket.getInetAddress();
                String hostAddress = inetAddress.getHostAddress();

                // Prints the ip address of whomever connects to the server
                System.out.println(hostAddress + " : Connected to server");

                // Create and start Thread with the handler on the connected socket
                Thread thread = new Thread(handler.handleClient(socket));

                thread.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
