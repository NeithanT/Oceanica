package menea.Models;

import java.io.Serializable;

public class PlayerStats implements Serializable {

    private int totalAtaques;
    private int exitosos;
    private int fallidos;

    public PlayerStats() {
        this.totalAtaques = 0;
        this.exitosos = 0;
        this.fallidos = 0;
    }

    public int getTotalAtaques() {
        return totalAtaques;
    }

    public void setTotalAtaques(int totalAtaques) {
        this.totalAtaques = totalAtaques;
    }

    public int getExitosos() {
        return exitosos;
    }

    public void setExitosos(int exitosos) {
        this.exitosos = exitosos;
    }

    public int getFallidos() {
        return fallidos;
    }

    public void setFallidos(int fallidos) {
        this.fallidos = fallidos;
    }

    public void incrementarTotal() {
        this.totalAtaques++;
    }

    public void incrementarExitosos() {
        this.exitosos++;
    }

    public void incrementarFallidos() {
        this.fallidos++;
    }

    public double getPorcentajeExito() {
        if (totalAtaques == 0) return 0.0;
        return (exitosos * 100.0) / totalAtaques;
    }
}
