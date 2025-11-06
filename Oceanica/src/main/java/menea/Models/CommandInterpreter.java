package menea.Models;

public class CommandInterpreter {
    //Interpreta el texto que el usuario escribió en la consola y llama al comando correcto.
    private final CommandRegistry registry;
    private final CommandContext ctx;

    public CommandInterpreter(CommandRegistry registry, CommandContext ctx) {
        this.registry = registry;
        this.ctx = ctx;
    }

    public void handle(String rawString){
        if (rawString == null || rawString.isBlank()) return;
        String[] parts = rawString.trim().split("\\s+");
        String name = parts[0];
        String[] args = new String[Math.max(0, parts.length-1)];
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
