/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menea.Fighters;

import java.util.ArrayList;
import java.util.Random;
import menea.Tiles.Board;
import menea.Tiles.SpecialObject;
import menea.Tiles.SpecialObjectType;
import menea.Tiles.Tile;

public class WavesControl extends Attack {

    public WavesControl() {
        super(AttackType.WAVES_CONTROL);
        this.random = new Random();
    }

    public boolean swirlRaising(Board boardAtacado) {
        //casilla aleatoria para crear remolino
        int[] casilla = getRandomTile(boardAtacado);
        int centroX = casilla[0];
        int centroY = casilla[1];

        int radio = 2 + random.nextInt(9); //radio del remolino de 2-10 casillas

        SpecialObject swirl = new SpecialObject(SpecialObjectType.SWIRL, centroX, centroY, radio);

        //destruir casillas
        int ataquesExitosos = atacarArea(boardAtacado, centroX, centroY, radio, 100, "Swirl Raising");

        //colocar el remolino en la casilla central
        Tile tileCentral = boardAtacado.getTile(centroX, centroY);
        if (tileCentral != null) {
            tileCentral.addSpecialObject(swirl);
        }

        return ataquesExitosos > 0;
    }

    public boolean sendHumanGarbage(Board boardAtacado) {
        //obtener todos los remolinos activos en el tablero
        ArrayList<SpecialObject> swirls = getSwirls(boardAtacado);

        //si no hay remolinos, no se puede hacer nada
        if (swirls.isEmpty()) {
            System.out.println("No hay remolinos disponibles para enviar basura");
            return false;
        }

        //seleccionar un remolino aleatorio
        SpecialObject swirlSeleccionado = swirls.get(random.nextInt(swirls.size()));

        //envia 10 veces el radio del remolino de toneladas de basura
        int cantidadBasura = 10 * swirlSeleccionado.getRadio();
        int ataquesExitosos = 0;

        for (int i = 0; i < cantidadBasura; i++) {
            //seleccionar casilla aleatoria
            int[] casilla = getRandomTile(boardAtacado);
            int x = casilla[0];
            int y = casilla[1];

            //cada tonelada daña 25% de la casilla
            Tile tile = boardAtacado.getTile(x, y);
            if (tile != null) {
                if (tile.daño(25, "Garbage")) {
                    ataquesExitosos++;
                }

                //50% de probabilidad de que sea radioactiva
                boolean esRadioactiva = random.nextBoolean();

                //si es radioactiva, la basura queda en la casilla
                if (esRadioactiva) {
                    SpecialObject basura = new SpecialObject(SpecialObjectType.GARBAGE, x, y, true);
                    tile.addSpecialObject(basura);
                }
            }
        }

        return ataquesExitosos > 0;
    }

    /**
     * Radioactive rush: activa toda la basura radioactiva por entre 1-10
     * segundos. Cada tonelada de basura radioactiva afecta entre 10% por
     * segundo la vida de la casilla.
     */
    public boolean radioactiveRush(Board boardAtacado) {
        //obtener toda la basura radioactiva activa en el tablero
        ArrayList<SpecialObject> radioactiveGarbage = getRadioactiveGarbage(boardAtacado);

        //si no hay basura radioactiva, no se puede hacer nada
        if (radioactiveGarbage.isEmpty()) {
            System.out.println("No hay basura radioactiva disponible");
            return false;
        }

        int duracion = 1 + random.nextInt(10); // 1-10 segundos
        int ataquesExitosos = 0;

        //basura radioactiva afecta
        for (SpecialObject basura : radioactiveGarbage) {
            Tile tile = boardAtacado.getTile(basura.getX(), basura.getY());
            if (tile != null) {
                int danoTotal = duracion * 10; //10% de daño por segundo
                if (tile.daño(danoTotal, "Radioactive rush")) {
                    ataquesExitosos++;
                }
            }
        }

        return ataquesExitosos > 0;
    }

    //busca todos los remolinos en el tablero
    private ArrayList<SpecialObject> getSwirls(Board boardAtacado) {
        ArrayList<SpecialObject> swirls = new ArrayList<>();

        for (int i = 0; i < boardAtacado.getROWS(); i++) {
            for (int j = 0; j < boardAtacado.getCOLUMNS(); j++) {
                Tile tile = boardAtacado.getTile(i, j);
                if (tile != null) {
                    for (SpecialObject obj : tile.getSpecialObjects()) {
                        if (obj.getType() == SpecialObjectType.SWIRL && obj.isActive()) {
                            swirls.add(obj);
                        }
                    }
                }
            }
        }

        return swirls;
    }

    //busca toda la basura radioactiva en el tablero
    private ArrayList<SpecialObject> getRadioactiveGarbage(Board boardAtacado) {
        ArrayList<SpecialObject> radioactiveGarbage = new ArrayList<>();

        for (int i = 0; i < boardAtacado.getROWS(); i++) {
            for (int j = 0; j < boardAtacado.getCOLUMNS(); j++) {
                Tile tile = boardAtacado.getTile(i, j);
                if (tile != null) {
                    for (SpecialObject obj : tile.getSpecialObjects()) {
                        if (obj.getType() == SpecialObjectType.GARBAGE
                                && obj.isRadioactive()
                                && obj.isActive()) {
                            radioactiveGarbage.add(obj);
                        }
                    }
                }
            }
        }

        return radioactiveGarbage;
    }

}
