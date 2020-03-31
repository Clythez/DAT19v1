import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

//  String welcome = "Welcome to Walkie Talkie";

    try {
        ServerSocket serverSocket = new ServerSocket(6780);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Socket socket = serverSocket.accept();
        System.out.println("Connection Established");
        System.out.println("Waiting for input... \n");

        while (true) {
            BufferedReader receivedFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream pw = new DataOutputStream(socket.getOutputStream());

            String modtagetString = receivedFromClient.readLine();

            if (modtagetString.equalsIgnoreCase("quit")) {
                System.out.println("Client terminated the connection");
                break;
            }

            System.out.println("Client" + ":> " + modtagetString);

            System.out.print("You: ");
            String message = bufferedReader.readLine();
            pw.writeBytes(message + "\n");
        }
        socket.close();
    } catch (Exception e) {
        System.out.println("Error " + e.getMessage());
    }
}
}
