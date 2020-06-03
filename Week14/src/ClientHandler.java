import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    // Arraylist with all current users
    private ArrayList<UserInformation> users = new ArrayList<>();

    // Method that returns a Runnable we can put into a thread
    public Runnable handleClient(Socket socket) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    // Transfer the socket we pass to this method, into another method that creates a new user
                    UserInformation currentUser = newUser(socket);
                    while (true) {

                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            String message = in.readLine() + "\n";
                            System.out.println(message);

                            for (UserInformation user : users) {
                                if (!(currentUser.getUserName().equalsIgnoreCase(user.getUserName()))) {
                                    Socket socket = user.getSocket();
                                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                                    out.write(currentUser.getUserName() + ":> " + message);
                                    out.flush();
                                }
                            }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
    }
    // Method to create a new user in our Arraylist of users from a given socket
    public UserInformation newUser(Socket socket) throws IOException {
        // From socket get Inetaddress, from Inetaddress get hostAddress put into a String, to pass to UserInformation Constructor
        InetAddress inetAddress = socket.getInetAddress();
        String hostAddress = inetAddress.getHostAddress();
        // In and output streams controlled by buffered reader and writer
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        // String that requests a username and sends it to the socket trying to connect
        String requestUsername = "Please select a username to join: " + "\n";

        out.write(requestUsername);
        out.flush();
        // Infinite loop to create a username and pass username + ip address into the constructor for UserInformation, adding it to the arraylist
        // keeps asking until a username that isnt taken gets found
        while (true) {
            // Acknowledged String and Username Taken message
            String acknowledged = "Username free, Welcome!" + "\n";
            String usernameTaken = "Username already taken, please try another: " + "\n";
            // Read the username coming in
            String username = in.readLine();
            // Call method to check username against connected users.
            if (checkUserName(username)) {
                UserInformation newUser = new UserInformation(username, hostAddress, socket);
                users.add(newUser);
                out.write(acknowledged);
                out.flush();
                return newUser;

            } else {
                out.write(usernameTaken);
                out.flush();
            }
        }

    }
    // Method to check new clients username against connected users.
    public boolean checkUserName(String username) {
        // For each loop going through each user to get the username, and checking if the new user matches any.
        for (UserInformation user : users) {
            String name = user.getUserName();
            // Return false if there is a match
            if (name.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

}
