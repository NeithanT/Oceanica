package menea.Fighters;

import menea.Tiles.Board;


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
    
    // Overloaded method with specific coordinates for 3 tentacles
    public void tentaculos(Board tableroEnemigo, int f1, int c1, int f2, int c2, int f3, int c3) {
        atacarArea(tableroEnemigo, f1, c1, 1, 100, "Kraken Tentacle");
        atacarArea(tableroEnemigo, f2, c2, 1, 100, "Kraken Tentacle");
        atacarArea(tableroEnemigo, f3, c3, 1, 100, "Kraken Tentacle");
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
    
    
    public void releaseTheKraken(Board tableroEnemigo, int row, int col) {
        int radio = 1 + random.nextInt(9); // 1-10 casillas
        atacarArea(tableroEnemigo, row, col, radio, 100, "Release the Kraken");
    }
}
