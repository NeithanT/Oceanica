package menea.Models;

import menea.Client.Console.FrameConsole; 
import menea.Player.Player;
import menea.Server.Client;
import menea.Tiles.Board;

public class CommandContext {
    //referencias útiles para cualquier comando 
    private final FrameConsole console;
    private final Client clientUI;
    private final Board board;
    private final Player player;

    public CommandContext(FrameConsole console, Client clientUI, Board board, Player player) {
        this.console = console;
        this.clientUI = clientUI;
        this.board = board;
        this.player = player;
    }
    public FrameConsole console(){ 
        return console; 
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