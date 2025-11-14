/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menea.Fighters;

import menea.Tiles.Board;

public class TheTrident extends Attack{

    public TheTrident() {
        super(AttackType.THE_TRIDENT);
    }
    
    public boolean threeLines(Board boardAtacado, int[][] puntos){
        if (puntos.length != 3) return false; //tienen que ser 3 puntos

        String[] direcciones = {"arriba", "abajo", "izquierda", "derecha"}; //direcciones a las que puede atacar
        int ataquesExitosos = 0;

        for (int[] punto: puntos){
            if(punto.length == 2){ //verifica que sea un punto con x y y
                int fila = punto[0];
                int columna = punto[1];
                int alcance = 1 + random.nextInt(4); //debe atacar entre 1 y 4 casillas (+1 para que no salga 0)

                String direccion = direcciones[random.nextInt(direcciones.length)]; //escoge la direccion a atacar aleatoriamente

                ataquesExitosos += atacarLinea(boardAtacado, fila, columna, direccion, alcance, 100, "Three Lines");

            }
        }

        return ataquesExitosos > 0;
    }
    
    public boolean threeNumbers(Board boardAtacado, int[] numerosTrident, int[] numerosAtacado){

        //Envía 3 números (0-9) al contrincante.
        //El contrincante debe hacer lo mismo.

        //validar que hayan enviado 3 los dos
        if (numerosTrident.length != 3 || numerosAtacado.length != 3) return false;

        boolean acerto = false;

        for (int numTrident : numerosTrident) {

            for (int numAtacado : numerosAtacado) {
                if (numTrident == numAtacado) {
                    acerto = true;
                    break; //con solo que acerte 1 ya ataca
                }
            }

            if (acerto) break; //solo ocupa acertar 1
        }

        if (acerto){

            //xplota la cantidad de casillas que de la multiplicación de los números del Tridente

            int multiplicacion = numerosTrident[0] * numerosTrident[1] * numerosTrident[2];
            int ataquesExitosos = 0;
            for (int i = 0; i < multiplicacion; i++) {
                if (atacarRandomTile(boardAtacado, 100, "Three Numbers")) {
                    ataquesExitosos++;
                }
            }
            return ataquesExitosos > 0;
        }

        // Si no acertó, el ataque falla (condición especial no cumplida)
        return false;
    }

    public void controlTheKraken(Board boardAtacado, ReleaseTheKraken ataqueKraken){
        //si es atacado con Kraken, retorna ese ataque al enemigo
         int[] pos = ataqueKraken.getRandomTile(boardAtacado); //aleatorio pq q pereza
         ataqueKraken.releaseTheKraken(boardAtacado, pos[0], pos[1]);
        //TODO: registrar en bitacora
    }
    
    
    
}
