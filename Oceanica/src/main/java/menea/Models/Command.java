package menea.Models;

import java.io.Serializable;
import java.util.Arrays;

public abstract class Command {
    public abstract String name(); //nombre del comando
    public abstract String help(); // mensaje corto de ayuda
    
    public abstract CommandResult execute(CommandContext ctx, String[] args); //lógica del comanto 
    //no es serializable, nada viaja por red aún, todo se ejecuta localmente 
}
