package menea.Models;

import java.io.Serializable;

public abstract class Command implements Serializable {
                                                        //long + grande
    public abstract String name(); //nombre del comando
    public abstract String help(); // mensaje corto de ayuda
    
    public abstract CommandResult execute(CommandContext ctx, String[] args); //lógica del comanto 
    //no es serializable, nada viaja por red aún, todo se ejecuta localmente 
}
