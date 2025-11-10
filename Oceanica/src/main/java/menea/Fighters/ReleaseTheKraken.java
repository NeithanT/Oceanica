/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public void tentaculos(Board tableroEnemigo, int fila1, int col1, int fila2, int col2, int fila3, int col3) {
        // Tentaculo 1
        atacarArea(tableroEnemigo, fila1, col1, 1, 100, "Tentáculos del Kraken");

        // Tentáculo 2
        atacarArea(tableroEnemigo, fila2, col2, 1, 100, "Tentáculos del Kraken");

        // Tentáculo 3
        atacarArea(tableroEnemigo, fila3, col3, 1, 100, "Tentáculos del Kraken");
    }
    
    // lanza aliento en una dirección  destruye 1-8 casillas
    public void krakenBreath(Board tableroEnemigo, int fila, int col, String direction) {
        int alcance = 1 + random.nextInt(8); // 1-8 casillas
        atacarLinea(tableroEnemigo, fila, col, direction, alcance, 100, "Kraken Breath");
    }
    
    // Aparece en un punto y destruye radio de 1-9 casillas
    public void releaseTheKraken(Board tableroEnemigo, int fila, int col) {
        int radio = 1 + random.nextInt(9); // 1-9 casillas
        atacarArea(tableroEnemigo, fila, col, radio, 100, "Release the Kraken");
    }
}
