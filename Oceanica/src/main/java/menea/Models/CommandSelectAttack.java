/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menea.Models;

import menea.Fighters.Attack;
import menea.Fighters.FishTelepathy;
import menea.Fighters.ReleaseTheKraken;
import menea.Fighters.ThundersUnderTheSea;

/**
 *
 * @author melissa
 */
public class CommandSelectAttack extends Command{
   @Override 
    public String name(){ return "SELECT"; }
    
    @Override 
    public String help(){ 
        return "SELECT <tipo>: Selecciona tu tipo de ataque:\n" +
               "  1. FISH_TELEPHATY\n" +
               "  2. THUNDERS_UNDER_THE_SEA\n" +
               "  3. RELEASE_THE_KRAKEN";
    }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args){
        if (args.length < 1) {
            return CommandResult.fail("Uso: SELECT <tipo>\nTipos disponibles: FISH_TELEPHATY, THUNDERS_UNDER_THE_SEA, RELEASE_THE_KRAKEN");
        }
    String tipo = args[0].toUpperCase();
        Attack ataque = null;

        switch (tipo) {
            case "FISH_TELEPHATY":
            case "FISH":
            case "1":
                ataque = new FishTelepathy();
                break;
            case "THUNDERS_UNDER_THE_SEA":
            case "THUNDERS":
            case "2":
                ataque = new ThundersUnderTheSea();
                break;
            case "RELEASE_THE_KRAKEN":
            case "KRAKEN":
            case "3":
                ataque = new ReleaseTheKraken();
                break;
            default:
                return CommandResult.fail("Tipo de ataque no reconocido: " + tipo);
        }

        // Guardar el ataque en el primer Fighter del jugador
        if (ctx.player().getFighters().isEmpty()) {
            return CommandResult.fail("No tienes fighters asignados aún.");
        }
        
        // TODO: agregar un setter en Fighter
        // como ctx.player().getFighters().get(0).setAttack(ataque);
        
        return CommandResult.ok("Ataque " + tipo + " seleccionado correctamente.");
    }
    
}
