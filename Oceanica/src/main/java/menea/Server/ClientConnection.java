package menea.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientConnection extends Thread {

    private Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private int currentPlayer;
    private Client clientFrame;
    
    public ClientConnection(Client cli) {
        clientFrame = cli;
    }
    
    @Override
    public void run() {
        try {
            // we have to have a specific thread for this ...
        
            //localhost is the equivalent to 127.0.0.1
            System.out.println("Connecting to server");
            socket = new Socket("localhost", Server.PORT);
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            
            currentPlayer = dataInput.readInt();
            int dmg = 0;
            
            while (dmg > -1) {
                try {
                    dmg = dataInput.readInt();
                    receiveAttack(dmg);
                    //dataOutput.writeUTF("Hello back!");
                }
                catch (IOException e) {
                    System.out.println("Error at input or output");
                }
            }
            
            
            dataInput.close();
            dataOutput.close();
            socket.close();
        
        } catch(IOException ex) {
            System.out.println("Error connecting to server");
        
        }
    }
    
    public int getPlayer() {
        return currentPlayer;
    }
    
    public void attack(int dmg) {
        try {
            dataOutput.writeInt(dmg);
        } catch (IOException ex) {
            System.out.println("No funciono atacar");
        }
    }
    
    public void receiveAttack(int damage) {
        clientFrame.receiveAttack(damage);
    }

}
