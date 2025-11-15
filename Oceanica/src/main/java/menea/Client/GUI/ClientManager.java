package menea.Client.GUI;

import javax.swing.ImageIcon;
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
    private JTextArea txaLogCommands;
    private JTextArea txaLogAttacks;
    private JTextArea txaLogBitacora;
    
    public ClientManager() {
        imgLoader = new ImageLoader();
        gameManager = new GameManager();
        boardRef = gameManager.getBoard();
        gameManager.setClientManager(this);
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

    public Board getBoardRef() {
        return boardRef;
    }

    public void setBoardRef(Board boardRef) {
        this.boardRef = boardRef;
    }

    public ImageLoader getImgLoader() {
        return imgLoader;
    }

    public void setImgLoader(ImageLoader imgLoader) {
        this.imgLoader = imgLoader;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public JTextArea getTxaLog() {
        return txaLog;
    }

    public void setTxaLog(JTextArea txaLog) {
        this.txaLog = txaLog;
    }

    public JTextField getTxfCommand() {
        return txfCommand;
    }

    public void setTxfCommand(JTextField txfCommand) {
        this.txfCommand = txfCommand;
    }

    public JPanel getPlnMatriz() {
        return plnMatriz;
    }

    public void setPlnMatriz(JPanel plnMatriz) {
        this.plnMatriz = plnMatriz;
    }

    public JTextArea getTxaLogCommands() {
        return txaLogCommands;
    }

    public void setTxaLogCommands(JTextArea txaLogCommands) {
        this.txaLogCommands = txaLogCommands;
    }

    public JTextArea getTxaLogAttacks() {
        return txaLogAttacks;
    }

    public void setTxaLogAttacks(JTextArea txaLogAttacks) {
        this.txaLogAttacks = txaLogAttacks;
    }

    public JTextArea getTxaLogBitacora() {
        return txaLogBitacora;
    }

    public void setTxaLogBitacora(JTextArea txaLogBitacora) {
        this.txaLogBitacora = txaLogBitacora;
    }
    
    

    public GameManager getGameManager() {
        return gameManager;
    }
    
    public void processCommand() {
        commandManager.processCommand();
    }
    
    
    public void setPanelBoard(PanelBoard panelBoard) {
        this.panelBoard = panelBoard;
    }
    
    public void setLogCommands(JTextArea txaLogCommands) {
        this.txaLogCommands = txaLogCommands;
        commandManager.setLogCommands(txaLogCommands);
    }
    
    public void setLogAttacks(JTextArea txaLogAttacks) {
        this.txaLogAttacks = txaLogAttacks;
        commandManager.setLogAttacks(txaLogAttacks);
    }
    
    public void setLogBitacora(JTextArea txaLogBitacora) {
        this.txaLogBitacora = txaLogBitacora;
        commandManager.setLogBitacora(txaLogBitacora);
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
    
    public ImageIcon loadImageIcon(String id) {
        return imgLoader.loadImageIcon(id);
    }
    
    public ImageIcon loadImageIcon(String id, int width, int height) {
        return imgLoader.loadImageIcon(id, width, height);
    }
    
    public void log(String message) {
        if (txaLogBitacora != null) {
            txaLogBitacora.append(message + "\n"); //agregar una línea a la bitácora
        }
    }

    public void logError(String message) {
        if (commandManager != null) {
            commandManager.logError(message);
        }
    }
    
}
