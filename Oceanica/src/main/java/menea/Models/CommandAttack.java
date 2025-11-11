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


public class CommandAttack extends Command {
    
    public CommandAttack() { }

    @Override
    public String name() { 
        return "ATTACK";
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
        } else if (ataque instanceof TheTrident) {
            return ejecutarTrident((TheTrident) ataque, metodo, args, tableroEnemigo, ctx);
        } else if (ataque instanceof WavesControl) {
            return ejecutarWavesControl((WavesControl) ataque, metodo, tableroEnemigo, ctx);
        } else if (ataque instanceof UnderseaVolcanoes) {
            return ejecutarUnderseaVolcanoes((UnderseaVolcanoes) ataque, metodo, tableroEnemigo, ctx);
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

                
                break;
            case "SHARK":
                ataque.sharkAttack(tablero);
                break;
            case "PULP":
                ataque.pulp(tablero);
                break;
            default:
                return CommandResult.fail("Método no válido para Fish Telepathy. Usa: CARDUMEN, SHARK, o PULP");
        }
        
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarThunders(ThundersUnderTheSea ataque, String metodo, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "THUNDERRAIN":
            case "RAIN":
                ataque.thunderRain(tablero);
                break;
            case "POSEIDON":
                ataque.poseidonThunders(tablero);
                break;
            case "ELATTACK":
                ataque.elAttack(tablero);
                break;
            default:
                return CommandResult.fail("Método no válido para Thunders. Usa: THUNDERRAIN, POSEIDON, o ELATTACK");
        }
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarKraken(ReleaseTheKraken ataque, String metodo, String[] args, Board tablero, CommandContext ctx) {
        switch (metodo) {
           case "TENTACLES":
           case "TENTACULOS":
               if (args.length < 7) {
                   return CommandResult.fail("Uso: ATTACK TENTACLES <fila1> <col1> <fila2> <col2> <fila3> <col3>");
               }
               try {
                   int fila1 = Integer.parseInt(args[1]) - 1;
                   int col1 = Integer.parseInt(args[2]) - 1;
                   int fila2 = Integer.parseInt(args[3]) - 1;
                   int col2 = Integer.parseInt(args[4]) - 1;
                   int fila3 = Integer.parseInt(args[5]) - 1;
                   int col3 = Integer.parseInt(args[6]) - 1;
                   
                   ataque.tentaculos(tablero, fila1, col1, fila2, col2, fila3, col3);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Todas las coordenadas deben ser números.");
                    }
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
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Fila y cola deben ser números.");
                }
                break;
            case "KRAKEN":
            case "RELEASE":
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
        
        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }
    private CommandResult ejecutarTrident(TheTrident ataque, String metodo, String[] args, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "THREELINES":
            case "LINES":
                if (args.length < 7) {
                    return CommandResult.fail("Uso: ATTACK THREELINES <f1> <c1> <f2> <c2> <f3> <c3>");
                }
                try {
                    int[][] puntos = new int[3][2];
                    puntos[0][0] = Integer.parseInt(args[1]) - 1;
                    puntos[0][1] = Integer.parseInt(args[2]) - 1;
                    puntos[1][0] = Integer.parseInt(args[3]) - 1;
                    puntos[1][1] = Integer.parseInt(args[4]) - 1;
                    puntos[2][0] = Integer.parseInt(args[5]) - 1;
                    puntos[2][1] = Integer.parseInt(args[6]) - 1;

                    ataque.threeLines(tablero, puntos);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Todas las coordenadas deben ser números.");
                }
                break;

            case "THREENUMBERS":
            case "NUMBERS":
                if (args.length < 7) {
                    return CommandResult.fail("Uso: ATTACK THREENUMBERS <n1> <n2> <n3> <enemigo1> <enemigo2> <enemigo3>");
                }
                try {
                    int[] numerosTrident = new int[3];
                    int[] numerosEnemigo = new int[3];

                    numerosTrident[0] = Integer.parseInt(args[1]);
                    numerosTrident[1] = Integer.parseInt(args[2]);
                    numerosTrident[2] = Integer.parseInt(args[3]);
                    numerosEnemigo[0] = Integer.parseInt(args[4]);
                    numerosEnemigo[1] = Integer.parseInt(args[5]);
                    numerosEnemigo[2] = Integer.parseInt(args[6]);

                    ataque.threeNumbers(tablero, numerosTrident, numerosEnemigo);
                } catch (NumberFormatException e) {
                    return CommandResult.fail("Todos los números deben ser enteros entre 0-9.");
                }
                break;

            case "CONTROLKRAKEN":
            case "CONTROL":
                // TODO: Obtener la instancia de ReleaseTheKraken del enemigo
                break;

            default:
                return CommandResult.fail("Método no válido para Trident. Usa: THREELINES, THREENUMBERS, o CONTROLKRAKEN");
        }

        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarWavesControl(WavesControl ataque, String metodo, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "SWIRLRAISING":
            case "SWIRL":
                ataque.swirlRaising(tablero);
                break;

            case "SENDHUMANGARDBAGE":
            case "GARBAGE":
                ataque.sendHumanGarbage(tablero);
                break;

            case "RADIACTIVERUSH":
            case "RADIOACTIVE":
                ataque.radioactiveRush(tablero);
                break;

            default:
                return CommandResult.fail("Método no válido para Waves Control. Usa: SWIRLRAISING, SENDHUMANGARDBAGE, o RADIACTIVERUSH");
        }

            return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    }

    private CommandResult ejecutarUnderseaVolcanoes(UnderseaVolcanoes ataque, String metodo, Board tablero, CommandContext ctx) {
        switch (metodo) {
            case "VOLCANORAISING":
            case "RAISING":
                ataque.volcanoRaising(tablero);
                break;

            case "VOLCANOEXPLOSION":
            case "EXPLOSION":
                ataque.volcanoExplosion(tablero);
                break;

            case "TERMALRUSH":
            case "TERMAL":
                ataque.termalRush(tablero);
                break;

            default:
                return CommandResult.fail("Método no válido para Undersea Volcanoes. Usa: VOLCANORAISING, VOLCANOEXPLOSION, o TERMALRUSH");
        }

        return CommandResult.ok("Ataque " + metodo + " ejecutado exitosamente.");
    } 
    // Método auxiliar para registrar

    

       
    
    
}