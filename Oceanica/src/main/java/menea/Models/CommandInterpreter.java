package menea.Models;

public class CommandInterpreter {
    //Interpreta el texto que el usuario escribió en la consola y llama al comando correcto.
    private final CommandRegistry registry; //dónde buscar por nombre
    private final CommandContext ctx; //qué contexto se pasa cuando se ejecuta 

    public CommandInterpreter(CommandRegistry registry, CommandContext ctx) {
        this.registry = registry;
        this.ctx = ctx;
    }

    public void handle(String raw){
        if (raw == null || raw.isBlank()) return; //ignora entradas vacías 
        String[] parts = raw.trim().split("\\s+"); //separa por espacios 
        String name = parts[0]; //comando
        String[] args = new String[Math.max(0, parts.length-1)]; //el resto son args 
        if (args.length > 0) System.arraycopy(parts, 1, args, 0, args.length);

        Command cmd = registry.get(name);
        if (cmd == null) {
            ctx.console().log("Comando no reconocido: " + name);
            return;
        }
        CommandResult res = cmd.execute(ctx, args);
        ctx.console().log((res.ok() ? "" : "[ERROR] ") + res.message());
    }
}