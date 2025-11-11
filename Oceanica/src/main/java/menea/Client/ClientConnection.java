package menea.Client;

import Action.Action;
import Action.ActionType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import menea.Fighters.GameManager;
import menea.Server.Client;
import menea.Server.Server;


public class ClientConnection extends Thread {

    private Socket socket;
    private ObjectInputStream dataInput;
    private ObjectOutputStream dataOutput;
    private GameManager gameManager;
    
    public ClientConnection(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    
    @Override
    public void run() {
        try {
            // we have to have a specific thread for this ...
        
            //localhost is the equivalent to 127.0.0.1
            System.out.println("Connecting to server");
            socket = new Socket("localhost", Server.PORT);
            dataOutput = new ObjectOutputStream(socket.getOutputStream());
            dataOutput.flush();
            dataInput = new ObjectInputStream(socket.getInputStream());
            
            
            while (true) {
                try {
                    Action act = (Action) dataInput.readObject();
                    

                    gameManager.identifyAction(act);

                }
                catch (IOException ex) {
                    System.out.println("Error at input or output");
                    break;
                }
                catch (ClassNotFoundException e) {
                    System.out.println("Error en pasando objetos: " + e.getMessage());
                }
            }
            
        } catch(IOException ex) {
            System.out.println("Error conectando a server");
        
        } finally {
            try {
                // cerrar sockets asi, por que si no dan 17 errores raros
                if (dataInput != null) dataInput.close();
                if (dataOutput != null) dataOutput.close();
                if (socket != null) socket.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando connection");
            }
        }
    }
    
    public void attack(String attackMethod) {
        try {
            Action attackAction = new Action(ActionType.ATTACK, attackMethod);
            
            dataOutput.writeObject(attackAction);
            dataOutput.flush();
            
            System.out.println("Ataque mandado : " + attackAction.toString());
        } catch (IOException ex) {
            System.out.println("Error mandando ataque: " + ex.getMessage());
        }
    }
    

}
