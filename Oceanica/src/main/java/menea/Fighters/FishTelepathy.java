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
public class FishTelepathy extends Attack {
    public FishTelepathy() {
        super(AttackType.FISH_TELEPHATY);
    }
    //peces atacan aleatoriamente casillas, cada pez daña 33% de la vida de la casilla
    public void cardumen(Board enemyBoard) {
    int numPeces = 100 + random.nextInt(201); // 100-300 peces
    
    for (int i = 0; i < numPeces; i++) {
        int[] pos = getRandomTile(enemyBoard);
        int vidaActual = enemyBoard.getTile(pos[0], pos[1]).getLife();
        
        int damage = (int) (vidaActual * 0.33); // 33% de la vida actual
        
        enemyBoard.getTile(pos[0], pos[1]).daño(damage, "Cardumen");
        }
    }
    
    public void sharkAttack(Board enemyBoard) {
        int[][] esquinas = {
            {0, 0}, // Superior izquierda
            {0, enemyBoard.getCOLUMNS() - 1}, // Superior derecha
            {enemyBoard.getROWS() - 1, 0}, // Inferior izquierda
            {enemyBoard.getROWS() - 1, enemyBoard.getCOLUMNS() - 1} // Inferior derecha
        };
        
        for (int[] esquina : esquinas) {
            int radio = 1 + random.nextInt(10); // 1-10 casillas
            atacarArea(enemyBoard, esquina[0], esquina[1], radio, 100, "Shark Attack");
        }
    }
    
    //Pulpos con 8 tentáculo c/u en casiillas aleatorias, con 25% de daño cada tectacu
    public void pulp(Board enemyBoard) {
    int numPulpos = 20 + random.nextInt(31); // 20-50 pulpos
    
    // HashMap para contar cuántos tentáculos tocan cada casilla
    java.util.HashMap<String, Integer> tentaculosPorCasilla = new java.util.HashMap<>();
    
    //Aca se determina en dónde caen todos los tentáculos (simultáneamente)
        for (int i = 0; i < numPulpos; i++) {
            for (int j = 0; j < 8; j++) { // 8 tentáculos por pulpo
                int[] pos = getRandomTile(enemyBoard);
                String coordenadas = pos[0] + "," + pos[1];

                tentaculosPorCasilla.put(coordenadas, tentaculosPorCasilla.getOrDefault(coordenadas, 0) + 1);
            }
        }

        // Acá se aplica el daño basado en cuántos tentáculos tocaron cada casilla
        for (java.util.Map.Entry<String, Integer> entry : tentaculosPorCasilla.entrySet()) {
            String[] coords = entry.getKey().split(",");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            int numTentaculos = entry.getValue();

            int vidaActual = enemyBoard.getTile(row, col).getLife();

            if (numTentaculos >= 4) {
                // Si hay 4 o más tentáculos en la misma casilla, se destruye completamente
                enemyBoard.getTile(row, col).daño(vidaActual, "Pulp Tentacle (4+ hits)");
            } else {
                // Si menos de 4, cada tentáculo hace 25% de la vida actual
                int damageTotal = (int) (vidaActual * 0.25 * numTentaculos);
                enemyBoard.getTile(row, col).daño(damageTotal, "Pulp Tentacle (" + numTentaculos + " hits)");
            }
        }
    }
}