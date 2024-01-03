import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String messaggio;
        String messaggioModificato;

        // Leggi il messaggio da inviare al server dall'utente
        System.out.print("Inserisci il messaggio da inviare al server: ");
        BufferedReader inputUtente = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Sostituisci "indirizzo_del_tuo_server" con l'indirizzo IP o il nome host del tuo server
            Socket clientSocket = new Socket("192.168.133.136", 6969);

            DataOutputStream outputServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inputServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            messaggio = inputUtente.readLine();

            outputServer.writeBytes(messaggio + "\n");

            messaggioModificato = inputServer.readLine();

            System.out.println("Messaggio dal server: " + messaggioModificato);

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
