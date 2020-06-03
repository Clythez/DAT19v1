import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {
            // Create and instantiate Socket on localhost, port 5959
            Socket socket = new Socket("localhost", 5959);
            // Make a buffered reader for user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            // In and output streams controlled by buffered reader and writer
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // Username request comes in when you connect
            String instruction = in.readLine();
            System.out.println(instruction);

            // Infinite Loop to select a username
            while (true) {
                // Input a username (Capitalization ignored)
                String userName = userInput.readLine() + "\n";
                // Use the writer to send
                out.write(userName);
                out.flush();
                // Read the response
                String response = in.readLine();
                System.out.println(response);
                // Break out of the loop when the acknowledgement is send
                if (response.startsWith("Username free")) {
                    break;
                }

            }
            // Start the receiver thread which reads all messages coming in
            Thread thread = new Thread(receive(in));
            thread.start();
            // Infinite loop so send messages, since its a buffered writer we need \n
            while (true) {
                System.out.println("Enter your message: ");
                String newMessage = userInput.readLine() + "\n";
                out.write(newMessage);
                out.flush();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to return a Runnable that we can put into a thread. It reads messages coming in and prints them to the console
    public static Runnable receive(BufferedReader in) {
        return new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = in.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }


}
