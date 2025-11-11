package menea.Server;

import Action.Action;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static final int PORT = 44444;
    private final int MAX_CONNECTIONS = 4;
    private ServerConnection[] players;
    
    private int amtOfPlayers;
    
    private ServerSocket serverSocket;
    private Socket temptSocket;
    
    public Server() {
        System.out.println("Starting...");
        players = new ServerConnection[4];
        init();
    }
    
    private void init() {
        try {
            serverSocket = new ServerSocket(PORT);
            waitConnection();
        } catch (IOException ex) {
            System.out.println("The port is not avalaible");
        }
    }
    
    public void waitConnection() {
    
        try {
            while (amtOfPlayers < MAX_CONNECTIONS) {
                for (int i = 0; i < MAX_CONNECTIONS; i++) {
                    if (players[i] == null) {
                        System.out.println("Waiting for player "
                            + (i + 1));
                        break;
                    }
                }
                
                temptSocket = serverSocket.accept();
                
                for (int i = 0; i < MAX_CONNECTIONS; i++) {
                    if (players[i] == null) {
                        players[i] = new ServerConnection(temptSocket, i + 1, this);
                        players[i].start();
                        notifyOthers(i);
                        break;
                    }
                }
                temptSocket = null;
            }
        } catch (IOException ex) {
            System.out.println("Error handling connections");
        }
    }
    

    public void notifyOthers(int index) {
    
        // TODO, in future
    }
    
    public void attackOthers(Action attackAction, ServerConnection sc) {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            if (players[i] != null && !players[i].equals(sc)) {
                players[i].receiveAttack(attackAction);
            }
        }
    }
    
    
    public static void main(String[] args) {
        Server sev = new Server();
    }
    
}
