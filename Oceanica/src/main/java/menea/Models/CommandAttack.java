package menea.Models;

import java.io.Serializable;
import menea.Tiles.Board;
import menea.Fighters.Attack;
import menea.Fighters.FishTelepathy;
import menea.Fighters.ReleaseTheKraken;
import menea.Fighters.TheTrident;
import menea.Fighters.ThundersUnderTheSea;
import menea.Fighters.UnderseaVolcanoes;
import menea.Fighters.WavesControl;


public class CommandAttack extends Command implements Serializable {
    private static final long serialVersionUID = 1L;//Es un número de versión de la clase Java lo usa para verificar que la clase que se envía y la que recibe son compatibles
    
    public CommandAttack() { }

    @Override public String name(){ 
        return "ATTACK";
    }
    @Override public String help(){
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
               "  - ATTACK KRAKEN\n\n" +
               "WAVES_CONTROL:\n" +
               "  - ATTACK SWIRL\n" +
               "  - ATTACK GARBAGE\n" +
               "  - ATTACK RADIOACTIVE\n\n" +
               "UNDERSEA_VOLCANOES:\n" +
               "  - ATTACK VOLCANO\n" +
               "  - ATTACK EXPLOSION\n" +
               "  - ATTACK TERMAL\n\n" +
               "THE_TRIDENT:\n" +
               "  - ATTACK THREELINES <x1> <y1> <x2> <y2> <x3> <y3>\n" +
               "  - ATTACK THREENUMBERS <n1> <n2> <n3> <en1> <en2> <en3>";
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
        } else if (ataque instanceof WavesControl) {
            return ejecutarWavesControl((WavesControl) ataque, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof UnderseaVolcanoes) {
            return ejecutarUnderseaVolcanoes((UnderseaVolcanoes) ataque, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof TheTrident) {
            return ejecutarTheTrident((TheTrident) ataque, metodo, args, tableroEnemigo, ctx);
        }

        return CommandResult.fail("Tipo de ataque no reconocido.");
    }
  
    private Attack obtenerAtaqueDelJugador(CommandContext ctx) {
        // Aquí se obtiene el Attack del Fighter del jugador
        return null; // Placeholder
    }

    private CommandResult ejecutarFishTelepathy(FishTelepathy ataque, String metodo, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "CARDUMEN":
                ataque.cardumen(tablero);
                detalles = "Cardumen de peces lanzado";
                ctx.console().log(detalles);
                break;
            case "SHARK":
                ataque.sharkAttack(tablero);
                detalles = "Ataque de tiburones desde las esquinas";
                ctx.console().log(detalles);
                break;
            case "PULP":
                ataque.pulp(tablero);
                detalles = "Pulpos con tentáculos lanzados";
                ctx.console().log(detalles);
                break;
            default:
                return CommandResult.fail("Método no válido para Fish Telepathy. Usa: CARDUMEN, SHARK, o PULP");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "FISH_TELEPATHY - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarThunders(ThundersUnderTheSea ataque, String metodo, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "THUNDERRAIN":
            case "RAIN":
                ataque.thunderRain(tablero);
                detalles = "Lluvia de truenos cayendo";
                ctx.console().log(detalles);
                break;
            case "POSEIDON":
                ataque.poseidonThunders(tablero);
                detalles = "Truenos de Poseidón cayendo";
                ctx.console().log(detalles);
                break;
            case "ELATTACK":
                ataque.elAttack(tablero);
                detalles = "Anguilas eléctricas atacan";
                ctx.console().log(detalles);
                break;
            default:
                return CommandResult.fail("Método no válido para Thunders. Usa: THUNDERRAIN, POSEIDON, o ELATTACK");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "THUNDERS_UNDER_THE_SEA - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarKraken(ReleaseTheKraken ataque, String metodo, String[] args, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "TENTACLES":
            case "TENTACULOS":
                ataque.tentaculos(tablero);
                detalles = "¡Tentáculos del Kraken emergen!";
                ctx.console().log(detalles);
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
                    detalles = "¡Aliento del Kraken lanzado hacia " + direccion + "!";
                    ctx.console().log(detalles);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Fila y columna deben ser números.");
                }
                break;
            case "KRAKEN":
            case "RELEASE":
                ataque.releaseTheKraken(tablero);
                detalles = "¡Release the Kraken! Destrucción masiva!";
                ctx.console().log(detalles);
                break;
            default:
                return CommandResult.fail("Método no válido para Kraken. Usa: TENTACLES, BREATH, o KRAKEN");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "RELEASE_THE_KRAKEN - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarWavesControl(WavesControl ataque, String metodo, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "SWIRL":
            case "SWIRLRAISING":
                ataque.swirlRaising(tablero);
                detalles = "Remolino creado y destruyendo casillas";
                ctx.console().log(detalles);
                break;
            case "GARBAGE":
            case "SENDGARBAGE":
                ataque.sendHumanGarbage(tablero);
                detalles = "Basura humana enviada desde remolino";
                ctx.console().log(detalles);
                break;
            case "RADIOACTIVE":
            case "RADIOACTIVERUSH":
                ataque.radioactiveRush(tablero);
                detalles = "Ataque radioactivo activado";
                ctx.console().log(detalles);
                break;
            default:
                return CommandResult.fail("Método no válido para Waves Control. Usa: SWIRL, GARBAGE, o RADIOACTIVE");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "WAVES_CONTROL - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarUnderseaVolcanoes(UnderseaVolcanoes ataque, String metodo, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "VOLCANO":
            case "VOLCANORAISING":
                ataque.volcanoRaising(tablero);
                detalles = "Volcán submarino creado y destruyendo casillas";
                ctx.console().log(detalles);
                break;
            case "EXPLOSION":
            case "VOLCANOEXPLOSION":
                ataque.volcanoExplosion(tablero);
                detalles = "¡Volcán explotó lanzando piedras!";
                ctx.console().log(detalles);
                break;
            case "TERMAL":
            case "TERMALRUSH":
                ataque.termalRush(tablero);
                detalles = "Sobrecalentamiento termal activado";
                ctx.console().log(detalles);
                break;
            default:
                return CommandResult.fail("Método no válido para Undersea Volcanoes. Usa: VOLCANO, EXPLOSION, o TERMAL");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "UNDERSEA_VOLCANOES - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarTheTrident(TheTrident ataque, String metodo, String[] args, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "THREELINES":
            case "3LINES":
                if (args.length < 7) {
                    return CommandResult.fail("Uso: ATTACK THREELINES <x1> <y1> <x2> <y2> <x3> <y3>\nDebe proporcionar 3 puntos (6 coordenadas)");
                }
                try {
                    int[][] puntos = {
                        {Integer.parseInt(args[1]), Integer.parseInt(args[2])},
                        {Integer.parseInt(args[3]), Integer.parseInt(args[4])},
                        {Integer.parseInt(args[5]), Integer.parseInt(args[6])}
                    };
                    ataque.threeLines(tablero, puntos);
                    detalles = "¡Tres líneas de ataque lanzadas!";
                    ctx.console().log(detalles);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Las coordenadas deben ser números.");
                }
                break;
            case "THREENUMBERS":
            case "3NUMBERS":
                if (args.length < 7) {
                    return CommandResult.fail("Uso: ATTACK THREENUMBERS <n1> <n2> <n3> <en1> <en2> <en3>\n" +
                            "n1-n3: tus números (0-9)\nen1-en3: números del enemigo (0-9)");
                }
                try {
                    int[] numerosTrident = {
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                    };
                    int[] numerosAtacado = {
                        Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6])
                    };
                    ataque.threeNumbers(tablero, numerosTrident, numerosAtacado);
                    detalles = "Juego de números ejecutado";
                    ctx.console().log(detalles);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Los números deben ser válidos (0-9).");
                }
                break;
            default:
                return CommandResult.fail("Método no válido para The Trident. Usa: THREELINES o THREENUMBERS");
        }

        //registrar en la bitácora
        Bitacora.getInstance().registrarEvento(EventType.ATAQUE, ctx.player().getName(),
                "Jugador enemigo", "THE_TRIDENT - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }




}