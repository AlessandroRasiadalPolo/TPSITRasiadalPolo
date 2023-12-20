import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    private static final String FILENAME = "src/products.txt";
    private static Map<Integer, Integer> productQuantities = new HashMap<>();



    public static void main(String[] args) {

        loadProductQuantitiesFromFile(FILENAME);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(6969)) {
                System.out.println("Server is listening on port " + 6969);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Handle each client in a separate thread
                executorService.execute(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProductQuantitiesFromFile(String filename) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int productCode = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);
                    productQuantities.put(productCode, quantity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String command;
            while ((command = reader.readLine()) != null) {
                String[] parts = command.split(" ");
                if (parts.length >= 2) {
                    int productCode = Integer.parseInt(parts[1]);

                    switch (parts[0]) {
                        case "GET":
                            int quantity = getProductQuantity(productCode);
                            writer.println("Quantity of product " + productCode + ": " + quantity);
                            break;
                        case "SET":
                            int newQuantity = Integer.parseInt(parts[2]);
                            setProductQuantity(productCode, newQuantity);
                            writer.println("Quantity of product " + productCode + " set to " + newQuantity);
                            break;
                        default:
                            writer.println("Invalid command");
                    }
                } else {
                    writer.println("Invalid command");
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int getProductQuantity(int productCode) {
        return productQuantities.getOrDefault(productCode, 0);
    }

    private static synchronized void setProductQuantity(int productCode, int newQuantity) {
        productQuantities.put(productCode, newQuantity);
    }

}
