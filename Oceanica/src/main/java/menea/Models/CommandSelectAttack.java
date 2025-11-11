/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menea.Models;

import menea.Fighters.Attack;
import menea.Fighters.FishTelepathy;
import menea.Fighters.ReleaseTheKraken;
import menea.Fighters.TheTrident;
import menea.Fighters.ThundersUnderTheSea;
import menea.Fighters.UnderseaVolcanoes;
import menea.Fighters.WavesControl;

/**
 *
 * @author melissa
 */
public class CommandSelectAttack extends Command{
   @Override 
    public String name(){ return "SELECT"; }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args){
        if (args.length < 1) {
            return CommandResult.fail("Uso: SELECT <tipo>\nTipos disponibles: 1. FISH_TELEPHATY\n" + " 2. THUNDERS_UNDER_THE_SEA\n" + "2. RELEASE_THE_KRAKEN\n" + " THE_TRIDENT\n" + "WAVES_CONTROL\n" + "UNDERSEA_VOLCANOES \n");
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
            case "THE_TRIDENT":
            case "TRIDENT":
            case "4":
                ataque = new TheTrident();
                break;
            case "WAVES_CONTROL":
            case "WAVES":
            case "5":
                ataque = new WavesControl();
                break;
            case "UNDERSEA_VOLCANOES":
            case "VOLCANOES":
            case "UNDERSEA":
            case "6":
                ataque = new UnderseaVolcanoes();
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
