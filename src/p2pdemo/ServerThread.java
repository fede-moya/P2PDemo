package p2pdemo;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This thread is responsible to handle client connection.
 *
 * @author www.codejava.net
 */
public class ServerThread extends Thread {
    private Socket clientSocket;
    private chat view;
    
    public ServerThread(Socket socket, chat view) {
        this.clientSocket = socket;
        this.view = view;
    }
 
    public void run() {
        try {
            
            MessaggePacket recibed;
            ObjectInputStream inputData = new ObjectInputStream(clientSocket.getInputStream());
            recibed = (MessaggePacket) inputData.readObject();
            this.view.getChatField().append("\n Somebody: " + recibed.getMessage());
            clientSocket.close();
            
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}