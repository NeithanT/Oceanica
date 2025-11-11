package menea.Client.GUI;

import menea.Fighters.GameManager;
import menea.Tiles.Board;
import menea.Vanity.ImageLoader;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import menea.Models.CommandManager;


public class ClientManager {

    private Board boardRef; 
    private ImageLoader imgLoader;
    private GameManager gameManager;
    private CommandManager commandManager;
    private JTextArea txaLog;
    private JTextField txfCommand;
    private JPanel plnMatriz;
    private PanelBoard panelBoard;
    
    public ClientManager() {
        imgLoader = new ImageLoader();
        gameManager = new GameManager();
        boardRef = gameManager.getBoard();
        gameManager.setClientManager(this);
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }
    
    public void processCommand() {
        gameManager.processCommand();
    }
    
    public void setLogArea(JTextArea txaLog) {
        this.txaLog = txaLog;
    }
    
    public void setCommandField(JTextField txfCommand) {
        this.txfCommand = txfCommand;
    }
    
    public void setBoardPanel(JPanel plnMatriz) {
        this.plnMatriz = plnMatriz;
    }
    
    public void setPanelBoard(PanelBoard panelBoard) {
        this.panelBoard = panelBoard;
    }
    
    public void addFighter() {
    
    }
    
    public void attachBoard(Board board) {
        this.boardRef = board;
        plnMatriz.repaint();       
    }

    public void refreshBoard() {
        if (panelBoard != null) {
            panelBoard.repaint();
        } else if (plnMatriz != null) {
            plnMatriz.repaint();
        }
    }
    
    
    public void log(String message) {
        txaLog.append(message + "\n"); //agregar una línea a la bitácora 
    }
    
}
