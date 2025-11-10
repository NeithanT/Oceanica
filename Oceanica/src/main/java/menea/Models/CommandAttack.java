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
    
    public CommandAttack() { }

    @Override
    public String name() { 
        return "ATTACK";
    }
    
    @Override public String help(){
        return "ATTACK <NombreJugador> <metodo> [parámetros] -> Ejecuta un ataque específico\n" +
               "Métodos disponibles dependen de tu tipo de ataque seleccionado:\n\n" +
               "FISH_TELEPHATY:\n" +
               "  - ATTACK NombreJugador CARDUMEN\n" +
               "  - ATTACK NombreJugador SHARK\n" +
               "  - ATTACK NombreJugador PULP\n\n" +
               "THUNDERS_UNDER_THE_SEA:\n" +
               "  - ATTACK NombreJugador THUNDERRAIN\n" +
               "  - ATTACK NombreJugador POSEIDON\n" +
               "  - ATTACK NombreJugador EEL\n\n" +
               "RELEASE_THE_KRAKEN:\n" +
               "  - ATTACK NombreJugador TENTACLES\n" +
               "  - ATTACK NombreJugador BREATH <fila> <col> <direccion>\n" +
               "  - ATTACK NombreJugador KRAKEN\n\n" +
               "WAVES_CONTROL:\n" +
               "  - ATTACK NombreJugador SWIRL\n" +
               "  - ATTACK NombreJugador GARBAGE\n" +
               "  - ATTACK NombreJugador RADIOACTIVE\n\n" +
               "UNDERSEA_VOLCANOES:\n" +
               "  - ATTACK NombreJugador VOLCANO\n" +
               "  - ATTACK NombreJugador EXPLOSION\n" +
               "  - ATTACK NombreJugador TERMAL\n\n" +
               "THE_TRIDENT:\n" +
               "  - ATTACK NombreJugador THREELINES <x1> <y1> <x2> <y2> <x3> <y3>\n" +
               "  - ATTACK NombreJugador THREENUMBERS <n1> <n2> <n3> <en1> <en2> <en3>";
    }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args) {
       if (args.length < 2) {
            return CommandResult.fail("Uso: ATTACK <NombreJugador> <metodo> [parámetros]\nEscribe 'HELP ATTACK' para ver métodos disponibles.");
        }
       if (ctx.player().getFighters().isEmpty()) { //obtiene el ataque del jugador actual
            return CommandResult.fail("No tienes fighters asignados.");
        }
       //TODO: agregar getter en figther
       //como Attack ataque = ctx.player().getFighters().get(0).getAttack();

       //ACÁ se SIMULA que ys está seleccionado, pero se tiene que verificar si sí

       String jugadorObjetivo = args[0]; // Primer parámetro: nombre del jugador a atacar
       String metodo = args[1].toUpperCase(); // Segundo parámetro: método de ataque

       Board tableroEnemigo = ctx.board(); //se debe de asegurar que este tablero sí sea el del enemigo

       try {
            return ejecutarAtaque(jugadorObjetivo, metodo, args, tableroEnemigo, ctx);
        } catch (Exception e) {
            return CommandResult.fail("Error al ejecutar ataque: " + e.getMessage());
        }
    }
    private CommandResult ejecutarAtaque(String jugadorObjetivo, String metodo, String[] args, Board tableroEnemigo, CommandContext ctx) {
        // Aquí se necesita obtener el tipo de ataque del jugador
        // De la manera en q lo programé está genérico

        Attack ataque = obtenerAtaqueDelJugador(ctx);

        if (ataque == null) {
            return CommandResult.fail("Primero debes seleccionar un tipo de ataque con: SELECT <tipo>");
        }

        // Ejecutar según el tipo de ataque
        if (ataque instanceof FishTelepathy) {
            return ejecutarFishTelepathy((FishTelepathy) ataque, jugadorObjetivo, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof ThundersUnderTheSea) {
            return ejecutarThunders((ThundersUnderTheSea) ataque, jugadorObjetivo, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof ReleaseTheKraken) {
            return ejecutarKraken((ReleaseTheKraken) ataque, jugadorObjetivo, metodo, args, tableroEnemigo, ctx);
        } else if (ataque instanceof WavesControl) {
            return ejecutarWavesControl((WavesControl) ataque, jugadorObjetivo, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof UnderseaVolcanoes) {
            return ejecutarUnderseaVolcanoes((UnderseaVolcanoes) ataque, jugadorObjetivo, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof TheTrident) {
            return ejecutarTheTrident((TheTrident) ataque, jugadorObjetivo, metodo, args, tableroEnemigo, ctx);
        }

        return CommandResult.fail("Tipo de ataque no reconocido.");
    }
  
    private Attack obtenerAtaqueDelJugador(CommandContext ctx) {
        // Aquí se obtiene el Attack del Fighter del jugador
        return null; // Placeholder
    }

    private CommandResult ejecutarFishTelepathy(FishTelepathy ataque, String jugadorObjetivo, String metodo, Board tablero, CommandContext ctx) {
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
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "FISH_TELEPATHY - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarThunders(ThundersUnderTheSea ataque, String jugadorObjetivo, String metodo, Board tablero, CommandContext ctx) {
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
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "THUNDERS_UNDER_THE_SEA - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarKraken(ReleaseTheKraken ataque, String jugadorObjetivo, String metodo, String[] args, Board tablero, CommandContext ctx) {
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
                if (args.length < 5) {
                    return CommandResult.fail("Uso: ATTACK <NombreJugador> BREATH <fila> <col> <direccion>\nDirecciones: arriba, abajo, izquierda, derecha");
                }
                try {
                    int fila = Integer.parseInt(args[2]) - 1; // Convertir a 0-based
                    int col = Integer.parseInt(args[3]) - 1;
                    String direccion = args[4];
                    ataque.krakenBreath(tablero, fila, col, direccion);
                    detalles = "¡Aliento del Kraken lanzado hacia " + direccion + "!";
                    ctx.console().log(detalles);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Fila y cola deben ser números.");
                }
                break;
            case "KRAKEN":
            case "RELEASE":
                ataque.releaseTheKraken(tablero);
                detalles = "¡Release the Kraken! Destrucción masiva!";
                ctx.console().log(detalles);
                if (args.length >= 3) {
                    // El jugador colocó coordenadas 
                    try {
                        int fila = Integer.parseInt(args[1]) - 1;
                        int col = Integer.parseInt(args[2]) - 1;
                        ataque.releaseTheKraken(tablero, fila, col);
                    } catch (NumberFormatException e) {
                        return CommandResult.fail("Fila y cola deben ser números.");
                    }
                } else {
                    // si no coloca coordenada q genera una posición aleatoria
                    int[] pos = ataque.getRandomTile(tablero);
                    ataque.releaseTheKraken(tablero, pos[0], pos[1]);
                }
                break;
            default:
                return CommandResult.fail("Método no válido para Kraken. Usa: TENTACLES, BREATH, o KRAKEN");
        }

        //registrar en la bitácora
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "RELEASE_THE_KRAKEN - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarWavesControl(WavesControl ataque, String jugadorObjetivo, String metodo, Board tablero, CommandContext ctx) {
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
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "WAVES_CONTROL - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarUnderseaVolcanoes(UnderseaVolcanoes ataque, String jugadorObjetivo, String metodo, Board tablero, CommandContext ctx) {
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
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "UNDERSEA_VOLCANOES - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarTheTrident(TheTrident ataque, String jugadorObjetivo, String metodo, String[] args, Board tablero, CommandContext ctx) {
        String detalles = "";
        switch (metodo) {
            case "THREELINES":
            case "3LINES":
                if (args.length < 8) {
                    return CommandResult.fail("Uso: ATTACK <NombreJugador> THREELINES <x1> <y1> <x2> <y2> <x3> <y3>\nDebe proporcionar 3 puntos (6 coordenadas)");
                }
                try {
                    int[][] puntos = {
                        {Integer.parseInt(args[2]), Integer.parseInt(args[3])},
                        {Integer.parseInt(args[4]), Integer.parseInt(args[5])},
                        {Integer.parseInt(args[6]), Integer.parseInt(args[7])}
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
                if (args.length < 8) {
                    return CommandResult.fail("Uso: ATTACK <NombreJugador> THREENUMBERS <n1> <n2> <n3> <en1> <en2> <en3>\n" +
                            "n1-n3: tus números (0-9)\nen1-en3: números del enemigo (0-9)");
                }
                try {
                    int[] numerosTrident = {
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]),
                        Integer.parseInt(args[4])
                    };
                    int[] numerosAtacado = {
                        Integer.parseInt(args[5]),
                        Integer.parseInt(args[6]),
                        Integer.parseInt(args[7])
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
        Bitacora.registrarEvento(EventType.ATAQUE, ctx.player().getName(), jugadorObjetivo, "THE_TRIDENT - " + detalles);

        ctx.console().refreshBoard();
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }




}