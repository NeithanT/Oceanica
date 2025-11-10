package menea.Models;

public class StartCommand extends Command {
    @Override public String name(){ return "START"; }
    @Override public String help(){ return "START -> asigna zonas iniciales y muestra resumen"; }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args){
        try {
            // TODO: Implement to complete the territory with every fighter :)
            //ctx.board().assignZone(FighterType.AQUAMAN,   30);
            //ctx.board().assignZone(FighterType.POSEIDON,  20);
            //ctx.board().assignZone(FighterType.BLACKMANTA,50);

            //registrar inicio de partida en la bitácora
            String resumen = ctx.board().resumen();
            Bitacora.getInstance().registrarEvento(EventType.INICIAR_PARTIDA,
                    ctx.player().getName(), "Territorio asignado - " + resumen);

            return CommandResult.ok("Territorio asignado. " + resumen);
        } catch (Exception ex) {
            return CommandResult.fail("Error en START: " + ex.getMessage());
        }
    }
}
