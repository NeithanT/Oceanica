package menea.Models;

import menea.Client.Console.FrameConsole; 
import menea.Server.Client;
import menea.Tiles.Board;

public class CommandContext {
    //referencias útiles para cualquier comando 
    private final FrameConsole console;
    private final Client clientUI;
    private final Board board;

    public CommandContext(FrameConsole console, Client clientUI, Board board) {
        this.console = console;
        this.clientUI = clientUI;
        this.board = board;
    }
    public FrameConsole console(){ return console; }
    public Client client(){ return clientUI; }
    public Board board(){ return board; }
}