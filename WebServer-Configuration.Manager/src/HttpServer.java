import configuration.Configuration;
import configuration.ConfigurationManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Starting...");


        ConfigurationManager.getInstance().loadConfigurationFile("src/configuration/conf.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());

            while(true){
                Socket client = serverSocket.accept();
                Thread thread = new Thread( () -> handleClient(client));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void handleClient(Socket socket){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println(br.read(new char[] {'c', 'i', 'a', 'o'}));

            br.close();
            bw.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
