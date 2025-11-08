package menea.Models;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author melissa
 */
public class CommandRegistry {
    //Diccionario que guarda todos los comandos disponibles 
    //TODO: Registrar los comandos porque no están , se puede hacer en FrameConsole 
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command cmd){
        commands.put(cmd.name().toUpperCase(), cmd);
    }

    public Command get(String name){
        if (name == null) return null;
        return commands.get(name.toUpperCase());
    }

    public String helpIndex(){
        StringBuilder sb = new StringBuilder("Comandos disponibles: ");
        for (String k : commands.keySet()) {
            sb.append(k).append("  ");
        }
        return sb.toString().trim();
    }
}
