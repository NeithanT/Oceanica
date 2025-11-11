package menea.Models;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import menea.Fighters.GameManager;

public class CommandManager {
    
    private GameManager gameManager;
    private JTextArea txaLogCommands;
    private JTextArea txaLogAttacks;
    private JTextArea txaLogBitacora;
    private JTextField txfCommand;
    private final CommandRegistry registry; //dónde buscar por nombre
    
    public CommandManager(GameManager gameManager) {
        
        this.gameManager = gameManager;
        registry = new CommandRegistry(gameManager);
        
    }

    public void handle(String str) {
        if (str == null || str.isBlank()) return; // que no explote
        String[] parts = str.split(" "); //separa por espacios 
        
        String command = parts[0]; //comando
        String[] args = new String[Math.max(0, parts.length-1)]; //el resto son args
        
        for (int i = 0; i < args.length; i++) {
            args[i] = parts[i + 1];
        }
        
        if (!registry.isValidCommand(command)) {
            logError("Comando invalido.");
        } else {
            log(registry.execute(command, args));
        }
    }
    
    public void log(String message) {
        txaLogCommands.append(">" + message + "\n");
    }
    
    public void logError(String message) {
        txaLogCommands.append("[ERROR]." + message + "\n");
    }
    
    public void logAttack(String message) {
        txaLogAttacks.append(message + "\n");
    }
    
    public void logBitacora(String message) {
        txaLogBitacora.append(message + "\n");
    }
    
    public void processCommand() {
        String comando = txfCommand.getText();
        if (comando.trim().isEmpty()) return; // ver si esta vacio, quitandole los espacios

        log(comando);
        handle(comando);
        
        txfCommand.setText("");
    }         
    
    public void logAttack(int dmg, int attacker, int target) {
        log("Jugador " + attacker + " atacó al jugador " + target + " con daño de " + dmg);
    }

    public void setCommandLine(JTextField txfCommand) {
        this.txfCommand = txfCommand;
    }

    public void setLogCommands(JTextArea txaLog) {
        this.txaLogCommands = txaLog;
    }
    
    public void setLogAttacks(JTextArea txaLog) {
        this.txaLogAttacks = txaLog;
    }
    
    public void setLogBitacora(JTextArea txaLog) {
        this.txaLogBitacora = txaLog;
    }
}