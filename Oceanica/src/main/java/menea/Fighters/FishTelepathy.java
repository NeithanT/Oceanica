package menea.Fighters;

import menea.Tiles.Board;

public class FishTelepathy extends Attack {
    
    public FishTelepathy() {
        super(AttackType.FISH_TELEPHATY);
    }
    
    //peces atacan aleatoriamente casillas, cada pez daña 33% de la vida de la casilla
    public void cardumen(Board tableroEnemigo) {
        int numPeces = 100 + random.nextInt(201); // 100 300 peces

        for (int i = 0; i < numPeces; i++) {
            int[] pos = getRandomTile(tableroEnemigo);
            int vidaActual = tableroEnemigo.getTile(pos[0], pos[1]).getLife();

            int damage = (int) (vidaActual * 0.33); // 33% de la vida actual

            tableroEnemigo.getTile(pos[0], pos[1]).daño(damage, "Cardumen");
            }
    }
    
    public void sharkAttack(Board tableroEnemigo) {
        int[][] esquinas = {
            {0, 0}, // Superior izquierda
            {0, tableroEnemigo.getCOLUMNS() - 1}, // Superior derecha
            {tableroEnemigo.getROWS() - 1, 0}, // Inferior izquierda
            {tableroEnemigo.getROWS() - 1, tableroEnemigo.getCOLUMNS() - 1} // Inferior derecha
        };
        
        for (int[] esquina : esquinas) {
            int radio = 1 + random.nextInt(10); // 1 a 10 casillas
            atacarArea(tableroEnemigo, esquina[0], esquina[1], radio, 100, "Shark Attack");
        }
    }
    
    //Pulpos con 8 tentáculo c/u en casiillas aleatorias, con 25% de daño cada tectacu
    public void pulp(Board tableroEnemigo) {
        int numPulpos = 20 + random.nextInt(31); // 20 a 50 pulpos

        // HashMap para contar cuántos tentáculos tocan cada casilla
        java.util.HashMap<String, Integer> tentaculosPorCasilla = new java.util.HashMap<>();

        //Aca se determina en dónde caen todos los tentáculos (simultáneamente)
        for (int i = 0; i < numPulpos; i++) {
            for (int j = 0; j < 8; j++) { // 8 tentáculos por pulpo
                int[] pos = getRandomTile(tableroEnemigo);
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

            int vidaActual = tableroEnemigo.getTile(row, col).getLife();

            if (numTentaculos >= 4) {
                // Si hay 4 o más tentáculos en la misma casilla, se destruye completamente
                tableroEnemigo.getTile(row, col).daño(vidaActual, "Pulp Tentacle (4+ golpes)");
            } else {
                // Si menos de 4, cada tentáculo hace 25% de la vida actual
                int damageTotal = (int) (vidaActual * 0.25 * numTentaculos);
                tableroEnemigo.getTile(row, col).daño(damageTotal, "Pulp Tentacle (" + numTentaculos + " golpes)");
            }
        }
    }
    
    
    
    
}