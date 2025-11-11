package menea.Server;

import Action.Action;
import Action.ActionType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerConnection extends Thread {

    private Server server;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    
    private int numOfPlayer;
    public ServerConnection(Socket s, int num, Server sv) {
        this.socket = s;
        this.numOfPlayer = num;
        server = sv;
    }
    
    @Override
    public void run() {
        
        try {
            System.out.println("Conectando usuario");
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            
            outputStream.writeInt(numOfPlayer);
            outputStream.flush();
            
            while (true) {
                try {
                    Action act = (Action) inputStream.readObject();
                    
                    if (act.getType() == ActionType.ATTACK) {
                        System.out.println("[Player " + numOfPlayer + "] Received attack action: " + act.toString());
                        server.attackOthers(act, this);
                    }
                    
                } catch (IOException ex) {
                    System.out.println("Error sending or receiving data in SERVER");
                    break;
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error deserializing Action object: " + ex.getMessage());
                }
            }
            
            inputStream.close();
            outputStream.close();
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Could not connect client: " + ex.getMessage());
        }
    }
    
    public void sendAttack(Action attackAction) {
        try {
            outputStream.writeObject(attackAction);
            outputStream.flush();
            
            System.out.println("Attack sent to player " + numOfPlayer + ": " + attackAction.toString());
        } catch (IOException ex) {
            System.out.println("Error sending attack: " + ex.getMessage());
        }
    }
    
    public void receiveAttack(Action attackAction) {
    
        try {
            outputStream.writeObject(attackAction);
            outputStream.flush();
        } catch(IOException ex) {
            System.out.println("Could not send attack");
        }
    }
}
