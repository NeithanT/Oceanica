package menea.Models;

import java.io.Serializable;
import menea.Tiles.Board;
import menea.Fighters.Attack;
import menea.Fighters.FishTelepathy;
import menea.Fighters.ReleaseTheKraken;
import menea.Fighters.ThundersUnderTheSea;


public class CommandAttack extends Command implements Serializable {
    private static final long serialVersionUID = 1L;//Es un número de versión de la clase Java lo usa para verificar que la clase que se envía y la que recibe son compatibles
    
    public CommandAttack() { }

    @Override public String name(){ 
        return "ATTACK";
    }
    @Override public String help(){ //TODO: agregar los ataques de Alina 
        return "ATTACK <metodo> [parámetros] -> Ejecuta un ataque específico\n" +  //los parámetros son la info extra que el usuario debe ingresar estos varían dependiendo del ataque y así 
               "Métodos disponibles dependen de tu tipo de ataque seleccionado:\n\n" +
               "FISH_TELEPHATY:\n" +
               "  - ATTACK CARDUMEN\n" +
               "  - ATTACK SHARK\n" +
               "  - ATTACK PULP\n\n" +
               "THUNDERS_UNDER_THE_SEA:\n" +
               "  - ATTACK THUNDERRAIN\n" +
               "  - ATTACK POSEIDON\n" +
               "  - ATTACK EEL\n\n" +
               "RELEASE_THE_KRAKEN:\n" +
               "  - ATTACK TENTACLES\n" +
               "  - ATTACK BREATH <fila> <col> <direccion>\n" +
               "  - ATTACK KRAKEN"; 
    }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args) {
       if (args.length < 1) {
            return CommandResult.fail("Uso: ATTACK <metodo> [parámetros]\nEscribe 'HELP ATTACK' para ver métodos disponibles.");
        }
       if (ctx.player().getFighters().isEmpty()) { //obtiene el ataque del jugador actual
            return CommandResult.fail("No tienes fighters asignados.");
        }
       //TODO: agregar getter en figther
       //como Attack ataque = ctx.player().getFighters().get(0).getAttack();
       
       //ACÁ se SIMULA que ys está seleccionado, pero se tiene que verificar si sí 
       
       String metodo = args[0].toUpperCase();
       
       Board tableroEnemigo = ctx.board(); //se debe de asegurar que este tablero sí sea el del enemigo
       
       try {
            return ejecutarAtaque(metodo, args, tableroEnemigo, ctx);
        } catch (Exception e) {
            return CommandResult.fail("Error al ejecutar ataque: " + e.getMessage());
        }
    }
    private CommandResult ejecutarAtaque(String metodo, String[] args, Board tableroEnemigo, CommandContext ctx) {
        // Aquí se necesita obtener el tipo de ataque del jugador
        // De la manera en q lo programé está genérico
        
        Attack ataque = obtenerAtaqueDelJugador(ctx);
        
        if (ataque == null) {
            return CommandResult.fail("Primero debes seleccionar un tipo de ataque con: SELECT <tipo>");
        }

        // Ejecutar según el tipo de ataque
        if (ataque instanceof FishTelepathy) {
            return ejecutarFishTelepathy((FishTelepathy) ataque, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof ThundersUnderTheSea) {
            return ejecutarThunders((ThundersUnderTheSea) ataque, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof ReleaseTheKraken) {
            return ejecutarKraken((ReleaseTheKraken) ataque, metodo, args, tableroEnemigo, ctx);
        }
        
        return CommandResult.fail("Tipo de ataque no reconocido.");
    }
  
    private Attack obtenerAtaqueDelJugador(CommandContext ctx) {
        // Aquí se obtiene el Attack del Fighter del jugador
        return null; // Placeholder
    }

    private CommandResult ejecutarFishTelepathy(FishTelepathy ataque, String metodo, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "CARDUMEN":
                ataque.cardumen(tablero);
                ctx.console().log("Cardumen de peces lanzado");
                break;
            case "SHARK":
                ataque.sharkAttack(tablero);
                ctx.console().log("Ataque de tiburones desde las esquinas");
                break;
            case "PULP":
                ataque.pulp(tablero);
                ctx.console().log("Pulpos con tentáculos lanzados");
                break;
            default:
                return CommandResult.fail("Método no válido para Fish Telepathy. Usa: CARDUMEN, SHARK, o PULP");
        }
        
        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarThunders(ThundersUnderTheSea ataque, String metodo, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "THUNDERRAIN":
            case "RAIN":
                ataque.thunderRain(tablero);
                ctx.console().log("Lluvia de truenos cayendo");
                break;
            case "POSEIDON":
                ataque.poseidonThunders(tablero);
                ctx.console().log("Truenos de Poseidón cayendo");
                break;
            case "ELATTACK":
                ataque.elAttack(tablero);
                ctx.console().log("Anguilas eléctricas atacan");
                break;
            default:
                return CommandResult.fail("Método no válido para Thunders. Usa: THUNDERRAIN, POSEIDON, o ELATTACK");
        }
        
        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarKraken(ReleaseTheKraken ataque, String metodo, String[] args, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "TENTACLES":
            case "TENTACULOS":
                ataque.tentaculos(tablero);
                ctx.console().log("¡Tentáculos del Kraken emergen!");
                break;
            case "BREATH":
            case "ALIENTO":
                if (args.length < 4) {
                    return CommandResult.fail("Uso: ATTACK BREATH <fila> <col> <direccion>\nDirecciones: arriba, abajo, izquierda, derecha");
                }
                try {
                    int fila = Integer.parseInt(args[1]) - 1; // Convertir a 0-based
                    int col = Integer.parseInt(args[2]) - 1;
                    String direccion = args[3];
                    ataque.krakenBreath(tablero, fila, col, direccion);
                    ctx.console().log("¡Aliento del Kraken lanzado hacia " + direccion + "!");
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Fila y columna deben ser números.");
                }
                break;
            case "KRAKEN":
            case "RELEASE":
                ataque.releaseTheKraken(tablero);
                ctx.console().log("¡Release the Kraken! Destrucción masiva!");
                break;
            default:
                return CommandResult.fail("Método no válido para Kraken. Usa: TENTACLES, BREATH, o KRAKEN");
        }
        
        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }
       
       
    
    
}