package menea.Models;

import java.io.Serializable;

public abstract class Command implements Serializable {
  
    
    public abstract String name(); //nombre del comando
    public abstract CommandResult execute(CommandContext ctx, String[] args); //lógica del comanto 
    //no es serializable, nada viaja por red aún, todo se ejecuta localmente 
}
