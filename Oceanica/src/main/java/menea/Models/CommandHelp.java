package menea.Models;

public class CommandHelp extends Command {
    private final CommandRegistry registry;
    public CommandHelp(CommandRegistry registry){ this.registry = registry; }

    @Override public String name(){ return "HELP"; }
    @Override public String help(){ return "HELP -> lista comandos disponibles"; }

    @Override
    public CommandResult execute(CommandContext ctx, String[] args){
        return CommandResult.ok(registry.helpIndex());// genera "Comandos: HELP ATTACK START LOG y esos 
    }    
}

