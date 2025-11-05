package menea.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerConnection extends Thread {

    private Server server;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    
    private int numOfPlayer;
    public ServerConnection(Socket s, int num, Server sv) {
        this.socket = s;
        this.numOfPlayer = num;
        server = sv;
    }
    
    @Override
    public void run() {
        
        try {
            System.out.println("Problem here:");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            
            outputStream.writeInt(numOfPlayer);
            int dmg = 0;
            
            while (dmg > -1) {
                System.out.println("Created server Socked");
                try {
                    dmg = inputStream.readInt();
                    server.attackOthers(dmg, this);
                } catch (IOException ex) {
                    System.out.println("Error sending or input data in SERVER");
                }
            }
            
            
            inputStream.close();
            outputStream.close();
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Could not connect client");
        }
    }
    
    public void sendAttack(int dmg) {
    
    }
    
    public void receiveAttack(int dmg) {
    
        try {
            outputStream.writeInt(dmg);
        } catch(IOException ex) {
            System.out.println("Could not send attack");
        }
    }
}
