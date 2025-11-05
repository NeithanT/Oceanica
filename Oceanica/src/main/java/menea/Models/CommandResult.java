package menea.Models;

import java.util.Objects;


public final class CommandResult {
    //existe para que la consola sepa qué mostrar y todas retornen lo mismo y así
    private final boolean ok;
    private final String message;

    public CommandResult(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public boolean ok() {
        return ok;
    }

    public String message() {
        return message;
    }

    public static CommandResult ok(String msg) {
        return new CommandResult(true, msg);
    }

    public static CommandResult fail(String msg) {
        return new CommandResult(false, msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResult)) return false;
        CommandResult that = (CommandResult) o;
        return ok == that.ok &&
               Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ok, message);
    }

    @Override
    public String toString() {
        return "CommandResult{ok=" + ok + ", message='" + message + "'}";
    }
}