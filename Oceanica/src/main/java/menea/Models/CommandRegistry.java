package menea.Models;

import java.util.ArrayList;
import java.util.Arrays;
import menea.Fighters.GameManager;

public class CommandRegistry {
    // Diccionario que guarda todos los comandos disponibles
    private ArrayList<String> commands;
    private GameManager gameManager;
    
    public CommandRegistry(GameManager gmMag) {
        
        commands = new ArrayList<>();
        commands.add("help");
        commands.add("create");
        commands.add("ready");
        commands.add("attack");
        commands.add("skip");
        commands.add("surrender");
        commands.add("cell");
        commands.add("log");
        commands.add("logattacks");
        commands.add("logenemy");
        commands.add("logdamage");
        commands.add("logcells");
        commands.add("logalive");  
        
        this.gameManager = gmMag;
        
    }
    
    public boolean isValidCommand(String command) {
        if (command == null) return false;
        return commands.contains(command.toLowerCase());
    }


    private boolean validateAttack(String[] args) {
        
        if (args == null || args.length == 0) return false;
        
        String method = args[0];
        
        switch (method.toUpperCase()) {
            // FISH_TELEPATHY
            case "CARDUMEN":
            case "SHARK":
            case "PULP":
                if (args.length != 1) return false;
                break;

            // THUNDERS_UNDER_THE_SEA
            case "THUNDERRAIN":
            case "POSEIDON":
            case "EEL":
                if (args.length != 1) return false;
                break;

            // RELEASE_THE_KRAKEN
            case "TENTACLES":
                if (args.length != 7) return false;
                for (int i = 1; i < args.length; i++) {
                    try {
                        Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                break;
            case "BREATH":
                if (args.length != 4) return false;
                try {
                    Integer.parseInt(args[1]);
                    Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    return false;
                }
                // direccion is a string, no check
                break;
            case "KRAKEN":
                if (args.length != 1) return false;
                break;

            // THE_TRIDENT
            case "THREELINES":
                if (args.length != 7) return false;
                for (int i = 1; i < args.length; i++) {
                    try {
                        Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                break;
            case "THREENUMBERS":
                if (args.length != 4) return false;
                for (int i = 1; i < args.length; i++) {
                    try {
                        Integer.parseInt(args[i]);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                break;
            case "CONTROLKRAKEN":
                if (args.length != 1) return false;
                break;

            // UNDERSEA_VOLCANOES
            case "VOLCANORAISING":
            case "VOLCANOEXPLOSION":
            case "TERMALRUSH":
                if (args.length != 1) return false;
                break;

            // WAVES_CONTROL
            case "SWIRLRAISING":
                if (args.length != 3) return false;
                try {
                    Integer.parseInt(args[1]);
                    Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    return false;
                }
                break;
            case "SENDHUMANGARDBAGE":
            case "RADIACTIVERUSH":
                if (args.length != 1) return false;
                break;

            default:
                return false;
        }
        
        return true;
    }

    public String execute(String command, String[] args) {

        if (command == null) return "Comando invalido";

        String cmd = command.toLowerCase();
        switch (cmd) {
            case "help":
                if (args == null || args.length == 0) {
                    return CommandHelp.help();
                } else if (args.length == 1 && args[0].toLowerCase().equals("attack")) {
                    return CommandHelp.helpAttack();
                } else {
                    return "Argumentos Invalidos";
                }

            case "create":
                if (args == null || args.length == 0) {
                    // TODO: Create PANEL
                } else if (args.length == 7) {
                    // name, image, attackType, control(int), fuerza(int), resistencia(int), sanidad(int)
                    if (gameManager.createFighter(args)) {
                        return "Se creo con exito";
                    } else {
                        return "Argumentos Invalidos";
                    }
                    
                } else {
                    return "Argumentos Invalidos";
                }

            case "ready":
                if (args != null && args.length > 0) return "Ready no toma Argumentos";
                if (gameManager.checkReady()) {
                    return "Conectandose al Juego";
                }
                return "No hay 3 luchadores";

            case "attack":
                // TODO : VALIDAR >>> que si tenga el turno/ ataque el jugador o lo que sea
                if (args == null || args.length < 1) return "Argumentos Invalidos para attack";

                if (!validateAttack(args)) {
                    return "Argumentos Invalido para ataque";
                }

                gameManager.sendAttack(String.join(" ", args)); // el join es para restaurar la estructura

                return "Attack " + args[0] + " sent successfully";

            case "skip":
                if (args != null && args.length > 0) throw new IllegalArgumentException("skip takes no arguments");
                System.out.println("Skipping turn");
                break;

            case "surrender":
                if (args != null && args.length > 0) throw new IllegalArgumentException("surrender takes no arguments");
                System.out.println("Surrendering game");
                break;

            case "cell":
                // cell x y
                if (args == null || args.length != 2) throw new IllegalArgumentException("cell requires 2 integer arguments: x y");
                try {
                    int x = Integer.parseInt(args[0]);
                    int y = Integer.parseInt(args[1]);
                    System.out.println("Cell requested: (" + x + "," + y + ")");
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("cell coordinates must be integers");
                }
                break;

            case "log":
            case "logattacks":
            case "logenemy":
            case "logdamage":
            case "logcells":
            case "logalive":
                if (args != null && args.length > 0) throw new IllegalArgumentException(cmd + " takes no arguments");
                System.out.println("Executing log command: " + cmd);
                break;

            default:
                throw new AssertionError("Unknown command: " + command);
        }
        return "Invalid Command";
    }
    
}
