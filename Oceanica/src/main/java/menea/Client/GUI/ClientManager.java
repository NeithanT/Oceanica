package menea.Client.GUI;

import menea.Fighters.GameManager;
import menea.Tiles.Board;
import menea.Vanity.ImageLoader;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import menea.Models.CommandManager;


public class ClientManager {

    private GameManager gameManager;
    private CommandManager commandManager;
    private Board boardRef; 
    private ImageLoader imgLoader;
    private JPanel pnlBoard;
    
    public ClientManager() {
        imgLoader = new ImageLoader();
        gameManager = new GameManager();
        boardRef = gameManager.getBoard();
        
        commandManager = new CommandManager(gameManager);
    }
    
    public void setCommandField(JTextField txfCommand) {
        commandManager.setCommandLine(txfCommand);
    }
     
    public void setLogArea(JTextArea txaLog) {
        commandManager.setLogCommands(txaLog);
    }
    
    public void setAttackLog(JTextArea txaLog) {
        commandManager.setLogAttacks(txaLog);
    }
    
    public void setBitacora(JTextArea txaLog) {
        commandManager.setLogBitacora(txaLog);
    }
    
    public void setBoardPanel(JPanel plnMatriz) {
        this.pnlBoard = plnMatriz;
    }
    
    public void processCommand() {
        commandManager.processCommand();
    }
    
}
