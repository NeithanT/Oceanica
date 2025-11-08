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
public class ThundersUnderTheSea extends Attack {
    public ThundersUnderTheSea() {
        super(AttackType.THUNDERS_UNDER_THE_SEA);
    }
    
    //rayos atacan 100 casillas , dañandolas porcentualmente de man aletoria entre 10-20%
    public void thunderRain(Board tableroEnemigo) {
        for (int i = 0; i < 100; i++) { //100 casillas
        int[] pos = getRandomTile(tableroEnemigo); 
        int vidaActual = tableroEnemigo.getTile(pos[0], pos[1]).getLife();
        
        double porcentaje = 0.10 + (random.nextDouble() * 0.10); // 10-20% como decimal
        int damage = (int) (vidaActual * porcentaje); // Daño proporcional a vida actual
        
        tableroEnemigo.getTile(pos[0], pos[1]).daño(damage, "Thunder Rain");
        }
    }
    
    //5-10 rayos, cuando tocan una casilla, onda destrucción entre 2-10 casilla 10% de la vida
    public void poseidonThunders(Board tableroEnemigo) {
        int numRayos = 5 + random.nextInt(6); // 5-10 rayos
        
        for (int i = 0; i < numRayos; i++) {
            int[] pos = getRandomTile(tableroEnemigo);
            int radio = 2 + random.nextInt(9); // cantida de casillas de la onda de destrucción 
            
            // Destrucción total en área cuadrada
            for (int r = pos[0] - radio; r <= pos[0] + radio; r++) {
                for (int c = pos[1] - radio; c <= pos[1] + radio; c++) {
                    if (tableroEnemigo.inBounds(r, c)) {
                        int vidaActual = tableroEnemigo.getTile(r, c).getLife();
                        tableroEnemigo.getTile(r, c).daño(vidaActual, "Poseidon Thunder"); // Destrucción total = 100%
                    }
                }
            }
        }
    }
    // Entre 25 y 100 angilas, cada una da 1-10 descargas de 10% cada una
    public void elAttack(Board tableroEnemigo) {
        int numAnguilas = 25 + random.nextInt(76); // 25-100 anguilas
        
        for (int i = 0; i < numAnguilas; i++) {
            int[] pos = getRandomTile(tableroEnemigo);
            int descargas = 1 + random.nextInt(10); // 1-10 descargas
            
            for (int j = 0; j < descargas; j++) {
                int vidaActual = tableroEnemigo.getTile(pos[0], pos[1]).getLife();
                int damage = (int) (vidaActual * 0.10); // 10% de la vida actual
                
                tableroEnemigo.getTile(pos[0], pos[1]).daño(damage, "El Attack");
            }
        }
    }
        
}
