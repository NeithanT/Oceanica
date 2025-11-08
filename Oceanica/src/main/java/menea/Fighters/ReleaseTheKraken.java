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
    //Permite colocar en el tabelro la aparición de 3 tent q destruyan 1 casilla de radio a su alrededor
    public void tentaculos(Board tableroEnemigo) {
        for (int i = 0; i < 3; i++) {
            int[] pos = getRandomTile(tableroEnemigo);
            atacarArea(tableroEnemigo, pos[0], pos[1], 1, 100, "Kraken Tentacle");
        }
    }
    
    // lanza aliento en una dirección  destruye 1-8 casillas
    public void krakenBreath(Board tableroEnemigo, int row, int col, String direction) {
        int alcance = 1 + random.nextInt(8); // 1-8 casillas
        atacarLinea(tableroEnemigo, row, col, direction, alcance, 100, "Kraken Breath");
    }
    
    // Release the Kraken: aparece en un punto y destruye radio de 1-9 casillas
    public void releaseTheKraken(Board tableroEnemigo) {
        int[] pos = getRandomTile(tableroEnemigo);
        int radio = 1 + random.nextInt(9); // 1-9 casillas
        atacarArea(tableroEnemigo, pos[0], pos[1], radio, 100, "Release the Kraken");
    }
}
