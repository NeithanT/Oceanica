package menea.Models;

import menea.Tiles.Board;
import java.util.Random;
import menea.Tiles.HitResult;


public class CommandAttack extends Command {
 
    private final Random ramdom = new Random();
    
    public CommandAttack() { }

    @Override public String name(){ return "ATTACK"; }
    @Override public String help(){ return "ATTACK -> envía un ataque aleatorio (10-20)"; }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args){
            if (args.length < 2) {
                return CommandResult.fail("Uso: attack <fila> <columna>");
            }
            int fila, col;
        try {
            fila = Integer.parseInt(args[0]);
            col  = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return CommandResult.fail("Fila y columna deben ser números enteros.");
        }

        
        
        int dmg = 10 + ramdom.nextInt(11);
        // convertir a 0 based
        int r = fila - 1;
        int c = col  - 1;

        Board board = ctx.board();
        if (!board.inBounds(r, c)) {
            return CommandResult.fail("Fuera de rango. Fila: 1.." + board.getRows() + ", Columna: 1.." + board.getColumns());
        }

        HitResult res = board.attackAt(r, c);
        switch (res) {
            case HIT:
                ctx.console().log("Impacto en (" + fila + "," + col + ").");
                break;
            case DESTROYED:
                ctx.console().log("Celda (" + fila + "," + col + ") destruida.");
                break;
            case MISS:
                ctx.console().log("Sin efecto en (" + fila + "," + col + ").");
                break;
        }

       
        ctx.console().refreshBoard();

        return CommandResult.ok(
            "Ataque a (" + fila + "," + col + ") con " + dmg + " de daño. " + board.resumen()
        );
    }
}