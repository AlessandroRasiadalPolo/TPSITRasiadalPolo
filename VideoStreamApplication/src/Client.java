import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static Socket socket;

    public static void main(String[] args){

        try{
            Webcam webcam = Webcam.getDefault();
            webcam.open();

            socket = new Socket("localhost", 6969);

            BufferedImage bi = webcam.getImage();


            ObjectOutputStream oust = new ObjectOutputStream(socket.getOutputStream());

            ImageIcon im = new ImageIcon(bi);

            JFrame frame = new JFrame(socket.getInetAddress().getHostAddress());

            frame.setSize(640,360);
            JLabel l = new JLabel();
            l.setVisible(true);

            frame.add(l);
            frame.setVisible(true);

            while(true)
            {
                bi = webcam.getImage();
                im = new ImageIcon(bi);
                oust.writeObject(im);
                l.setIcon(im);
                oust.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
