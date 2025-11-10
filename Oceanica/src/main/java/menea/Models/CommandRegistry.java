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

    private void validateAttack(String method, String[] args) {
        
        if (method == null) throw new IllegalArgumentException("Attack method cannot be null");
        String m = method.toUpperCase();
        switch (m) {
            // FISH_TELEPATHY
            case "CARDUMEN":
            case "SHARK":
            case "PULP":
                if (args.length != 0) throw new IllegalArgumentException(m + " takes no arguments");
                break;

            // THUNDERS_UNDER_THE_SEA
            case "THUNDERRAIN":
            case "POSEIDON":
            case "EEL":
                if (args.length != 0) throw new IllegalArgumentException(m + " takes no arguments");
                break;

            // RELEASE_THE_KRAKEN
            case "TENTACLES":
                if (args.length != 6) throw new IllegalArgumentException("TENTACLES requires 6 integer arguments: fila1 col1 fila2 col2 fila3 col3");
                for (String arg : args) {
                    try {
                        Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("TENTACLES arguments must be integers");
                    }
                }
                break;
            case "BREATH":
                if (args.length != 3) throw new IllegalArgumentException("BREATH requires 3 arguments: fila col direccion");
                try {
                    Integer.parseInt(args[0]);
                    Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("BREATH fila and col must be integers");
                }
                // direccion is a string, no check
                break;
            case "KRAKEN":
                if (args.length != 0) throw new IllegalArgumentException("KRAKEN takes no arguments");
                break;

            // THE_TRIDENT
            case "THREELINES":
                if (args.length != 6) throw new IllegalArgumentException("THREELINES requires 6 integer arguments: fila1 col1 fila2 col2 fila3 col3");
                for (String arg : args) {
                    try {
                        Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("THREELINES arguments must be integers");
                    }
                }
                break;
            case "THREENUMBERS":
                if (args.length != 3) throw new IllegalArgumentException("THREENUMBERS requires 3 integer arguments: num1 num2 num3");
                for (String arg : args) {
                    try {
                        Integer.parseInt(arg);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("THREENUMBERS arguments must be integers");
                    }
                }
                break;
            case "CONTROLKRAKEN":
                if (args.length != 0) throw new IllegalArgumentException("CONTROLKRAKEN takes no arguments");
                break;

            // UNDERSEA_VOLCANOES
            case "VOLCANORAISING":
            case "VOLCANOEXPLOSION":
            case "TERMALRUSH":
                if (args.length != 0) throw new IllegalArgumentException(m + " takes no arguments");
                break;

            // WAVES_CONTROL
            case "SWIRLRAISING":
                if (args.length != 2) throw new IllegalArgumentException("SWIRLRAISING requires 2 integer arguments: fila1 col1");
                try {
                    Integer.parseInt(args[0]);
                    Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("SWIRLRAISING arguments must be integers");
                }
                break;
            case "SENDHUMANGARDBAGE":
            case "RADIACTIVERUSH":
                if (args.length != 0) throw new IllegalArgumentException(m + " takes no arguments");
                break;

            default:
                throw new IllegalArgumentException("Unknown attack method: " + method);
        }
        
    }

    public String execute(String command, String[] args) {

        if (command == null) throw new IllegalArgumentException("command cannot be null");

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
                if (args != null && args.length > 0) throw new IllegalArgumentException("ready takes no arguments");
                System.out.println("Player ready");
                break;

            case "attack":
                // attack <method> [params...]
                if (args == null || args.length < 1) throw new IllegalArgumentException("attack requires at least the attack method name as argument");
                validateAttack(args[0], Arrays.copyOfRange(args, 1, args.length));
                System.out.println("Attack requested: " + String.join(" ", args));
                break;

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
