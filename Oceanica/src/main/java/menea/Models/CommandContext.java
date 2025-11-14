package menea.Models;

import java.io.Serializable;
import menea.Player.Player;
import menea.Server.Client;
import menea.Tiles.Board;

public class CommandContext {
    //Este no es serializable, pq tiene referencias a objetos cm Frame.. lo de los atributos
    //no lo serialicé, pero si voy a agregar un método para serrializar los datos
    //revisar si eso sí está bien o qué hacer si no lo está
    //referencias útiles para cualquier comando
    private final Client clientUI;
    private final Board board;
    private final Player player;
    private CommandManager commandManager;

    public CommandContext(Client clientUI, Board board, Player player) {
        this.clientUI = clientUI;
        this.board = board;
        this.player = player;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
    
    public class AttackMessage implements Serializable {
        private static final long serialVersionUID = 1L;

            private String attackType;  // CARDUMEN, SHARK y así
            private String[] parameters; // Parámetros adicionales
            private int playerID;

            public AttackMessage(String attackType, String[] parameters, int playerID) {
                this.attackType = attackType;
                this.parameters = parameters;
                this.playerID = playerID;
            }

            public String getAttackType() {
                return attackType;
            }

            public void setAttackType(String attackType) {
                this.attackType = attackType;
            }

            public String[] getParameters() {
                return parameters;
            }

            public void setParameters(String[] parameters) {
                this.parameters = parameters;
            }

            public int getPlayerID() {
                return playerID;
            }

            public void setPlayerID(int playerID) {
                this.playerID = playerID;
            }
      
        }
    public Client client(){ 
        return clientUI; 
    }
    public Board board(){ 
        return board; 
    }
    public Player player(){
        return player;
    }
}