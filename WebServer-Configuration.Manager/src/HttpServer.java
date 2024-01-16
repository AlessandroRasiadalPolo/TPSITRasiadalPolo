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
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void handleClient(Socket socket){

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Leggi la richiesta HTTP
            StringBuilder request = new StringBuilder();
            String line;
            while (!(line = br.readLine()).isEmpty()) {
                request.append(line).append("\r\n");
            }

            // Contenuto della pagina HTML di esempio
            String htmlContent = "<html><head><title>Simple HTTP Server</title></head><body><h1>Hello, World!</h1></body></html>";

            // Risposta HTTP con il contenuto della pagina HTML
            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Length: " + htmlContent.length() + "\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n" +
                    htmlContent;

            // Invia la risposta al client
            bw.write(response);
            bw.flush();

            // Chiudi flussi e socket
            br.close();
            bw.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
