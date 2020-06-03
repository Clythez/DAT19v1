import java.net.Socket;

public class UserInformation {
    // Each user has a user name, ipAddress and a socket.
    private String userName;
    private String ipAddress;
    private Socket socket;
    // Constructor consisting of all fields.
    public UserInformation(String userName, String ipAddress, Socket socket) {
        this.userName = userName;
        this.ipAddress = ipAddress;
        this.socket = socket;
    }
    // Getters and setters.
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
