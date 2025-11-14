package menea.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttackStatistics implements Serializable {

    private static AttackStatistics instance;

    private ArrayList<AttackRecord> attacks;

    private AttackStatistics() {
        this.attacks = new ArrayList<>();
    }

    public static AttackStatistics getInstance() {
        if (instance == null) {
            instance = new AttackStatistics();
        }
        return instance;
    }

    //registra un ataque
    public void registrarAtaque(String nombreJugador, String tipoAtaque, boolean exitoso) {
        attacks.add(new AttackRecord(nombreJugador, tipoAtaque, exitoso));
    }

    public int getTotalAtaques() {
        return attacks.size();
    }

    //ataques exitosos (causan dano
    public int getAtaquesExitosos() {
        int count = 0;
        for (AttackRecord attack : attacks) {
            if (attack.isExitoso()) count++;
        }
        return count;
    }

    //ataques fallidos (atacan a casillas muertas o no atinan en THREE NUMBERS
    public int getAtaquesFallidos() {
        return getTotalAtaques() - getAtaquesExitosos();
    }

    public double getPorcentajeExito() {
        if (getTotalAtaques() == 0) return 0.0;
        return (getAtaquesExitosos() * 100.0) / getTotalAtaques();
    }

    //estadísticas por jugador
    public Map<String, PlayerStats> getEstadisticasPorJugador() {
        Map<String, PlayerStats> stats = new HashMap<>();

        for (AttackRecord attack : attacks) {
            stats.putIfAbsent(attack.getNombreJugador(), new PlayerStats());
            PlayerStats playerStats = stats.get(attack.getNombreJugador());
            playerStats.incrementarTotal();
            if (attack.isExitoso()) {
                playerStats.incrementarExitosos();
            } else {
                playerStats.incrementarFallidos();
            }
        }

        return stats;
    }

    //limpia las estadísticas (para reiniciar partida)
    public void limpiar() {
        attacks.clear();
    }
}
