import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){

        try{
            ServerSocket serverSocket = new ServerSocket(6969);
            System.out.println("Waiting...");
            Socket socket = serverSocket.accept();
            System.out.println("Connected");

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            JLabel label = new JLabel();

            JFrame frame = new JFrame();
            frame.setSize(640,360);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            label.setSize(640,360);
            label.setVisible(true);


            frame.add(label);
            frame.setVisible(true);


            while(true){
                label.setIcon((ImageIcon)in.readObject());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
