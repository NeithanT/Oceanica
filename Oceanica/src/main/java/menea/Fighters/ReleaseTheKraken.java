package menea.Fighters;

import menea.Tiles.Board;

/**
 *
 * @author melissa
 */
public class ReleaseTheKraken extends Attack {
    
    public ReleaseTheKraken() {
        super(AttackType.RELEASE_THE_KRAKEN);
    }
    
    // aparición a elección del jugador de 3 tent q destruyan 1 casilla de radio a su alrededor
    public boolean tentaculos(Board tableroEnemigo, int fila1, int col1, int fila2, int col2, int fila3, int col3) {
        // Tentaculo 1
        int ataquesExitosos = atacarArea(tableroEnemigo, fila1, col1, 1, 100, "Tentáculos del Kraken");

        // Tentáculo 2
        ataquesExitosos += atacarArea(tableroEnemigo, fila2, col2, 1, 100, "Tentáculos del Kraken");

        // Tentáculo 3
        ataquesExitosos += atacarArea(tableroEnemigo, fila3, col3, 1, 100, "Tentáculos del Kraken");

        return ataquesExitosos > 0;
    }
    
    // lanza aliento en una dirección  destruye 1-8 casillas
    public boolean krakenBreath(Board tableroEnemigo, int fila, int col, String direction) {
        int alcance = 1 + random.nextInt(8); // 1-8 casillas
        int ataquesExitosos = atacarLinea(tableroEnemigo, fila, col, direction, alcance, 100, "Kraken Breath");
        return ataquesExitosos > 0;
    }
    
    // Aparece en un punto y destruye radio de 1-9 casillas
    public boolean releaseTheKraken(Board tableroEnemigo, int fila, int col) {
        int radio = 1 + random.nextInt(9); // 1-9 casillas
        int ataquesExitosos = atacarArea(tableroEnemigo, fila, col, radio, 100, "Release the Kraken");
        return ataquesExitosos > 0;
    }
    
}
