import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 6780);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Har oprettet forbindelse til server");
            System.out.println();

            while (true) {
                DataOutputStream sendToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.print("You: ");
                String message = inFromUser.readLine();
                sendToServer.writeBytes(message + "\n");

                String sentenceFromServer = inFromServer.readLine();
                System.out.println("Server:> " + sentenceFromServer);

                if (message.equalsIgnoreCase("quit")) {
                    System.out.println("Du har afsluttet forbindelsen");
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}