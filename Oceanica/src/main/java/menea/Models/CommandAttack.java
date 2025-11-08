package menea.Models;

import menea.Tiles.Board;
import java.util.Random;
import menea.Tiles.HitResult;


public class CommandAttack extends Command {
    
    public CommandAttack() { }

    @Override public String name(){ return "ATTACK"; }
    @Override public String help(){ //TODO: agregar los ataques de Alina 
        return "ATTACK <método> [parámetros] -> Ejecuta un ataque específico\n" +  //los parámetros son la info extra que el usuario debe ingresar estos varían dependiendo del ataque y así 
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
        
    }
    
}