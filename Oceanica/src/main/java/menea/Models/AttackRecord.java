package menea.Models;

import java.io.Serializable;

public class AttackRecord implements Serializable {

    private String nombreJugador;
    private String tipoAtaque;
    private boolean exitoso;

    public AttackRecord(String nombreJugador, String tipoAtaque, boolean exitoso) {
        this.nombreJugador = nombreJugador;
        this.tipoAtaque = tipoAtaque;
        this.exitoso = exitoso;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getTipoAtaque() {
        return tipoAtaque;
    }

    public boolean isExitoso() {
        return exitoso;
    }
}
