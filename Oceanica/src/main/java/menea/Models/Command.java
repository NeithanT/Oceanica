package menea.Models;

import java.io.Serializable;

public abstract class Command implements Serializable {
    
    private static final long serialVersionUID = 1L;//Es un número de versión de la clase Java lo usa para verificar que la clase que se envía y la que recibe son compatibles
                                                    //long + grande
    public abstract String name(); //nombre del comando
    public abstract String help(); // mensaje corto de ayuda
    
    public abstract CommandResult execute(CommandContext ctx, String[] args); //lógica del comanto 
    //no es serializable, nada viaja por red aún, todo se ejecuta localmente 
}
