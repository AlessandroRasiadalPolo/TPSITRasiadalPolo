import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {


    private Socket clientSocket;

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6969;

    public static void main(String[] args) {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Client connected to server. Type commands (e.g., GET 1001, SET 1002 10)");

            String userInput;
            while ((userInput = userInputReader.readLine()) != null) {
                writer.println(userInput);

                // Receive and print server response
                String serverResponse = reader.readLine();
                System.out.println("Server response: " + serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
