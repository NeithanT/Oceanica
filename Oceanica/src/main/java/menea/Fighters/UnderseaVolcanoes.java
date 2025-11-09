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

public class UnderseaVolcanoes extends Attack {

    public UnderseaVolcanoes() {
        super(AttackType.UNDERSEA_VOLCANOES);
        this.random = new Random();
    }

    public void volcanoRaising(Board boardAtacado) {
        //obtener una casilla aleatoria para crear un volcan
        int[] casilla = getRandomTile(boardAtacado);
        int centroX = casilla[0];
        int centroY = casilla[1];

        int radio = 1 + random.nextInt(10); // 1-10 casillas

        SpecialObject volcano = new SpecialObject(SpecialObjectType.VOLCANO, centroX, centroY, radio);

        //destruir casillas en el radio del volcan
        atacarArea(boardAtacado, centroX, centroY, radio, 100, "Volcán");

        //colocar el volcan en la casilla central para futuros ataques
        Tile tileCentral = boardAtacado.getTile(centroX, centroY);
        if (tileCentral != null) {
            tileCentral.addSpecialObject(volcano);
        }
    }

    public void volcanoExplosion(Board boardAtacado) {
        //obtener todos los volcanes activos en el tablero
        ArrayList<SpecialObject> volcanes = getVolcanes(boardAtacado);

        //si no hay volcanes, no se hace nada
        if (volcanes.isEmpty()) {
            System.out.println("No hay volcanes disponibles para hacer erupción");
            return;
        }

        //seleccionar un volcan aleatorio para que haga erupcion
        SpecialObject volcanoSeleccionado = volcanes.get(random.nextInt(volcanes.size()));

        //envia 10 veces el tamaño del volcan en piedras
        int cantidadPiedras = 10 * volcanoSeleccionado.getRadio();

        for (int i = 0; i < cantidadPiedras; i++) {
            //casilla aleatoria en donde caeran las piedras
            int[] casilla = getRandomTile(boardAtacado);
            int x = casilla[0];
            int y = casilla[1];

            //daña 20% de la casilla por piedra
            Tile tile = boardAtacado.getTile(x, y);
            if (tile != null) {
                tile.daño(20, "Piedra volcánica");
            }
        }
    }

    public void termalRush(Board boardAtacado) {
        //obtener todos los volcanes activos en el tablero
        ArrayList<SpecialObject> volcanes = getVolcanes(boardAtacado);

        //si no hay volcanes, no se hace nada
        if (volcanes.isEmpty()) {
            System.out.println("No hay volcanes disponibles para sobrecalentamiento");
            return;
        }

        //seleccionar volcan aleatorio para sobrecalentar su radio
        SpecialObject volcanSeleccionado = volcanes.get(random.nextInt(volcanes.size()));

        int duracion = 5 + random.nextInt(2); // 5-6 segundos de sobrecalentamiento

        int danoTotal = duracion * volcanSeleccionado.getRadio(); //cada segundo daña porcentaje igual al radio

        int RADIOCALOR = 5; //siempre afecta las 5 casillas alrededor
        int centroX = volcanSeleccionado.getX();
        int centroY = volcanSeleccionado.getY();
        int radioAAtacar = volcanSeleccionado.getRadio() + RADIOCALOR;

        atacarArea(boardAtacado, centroX, centroY, radioAAtacar, danoTotal, "Termal rush");
    }

    //busca todos los volcanes del tablero
    private ArrayList<SpecialObject> getVolcanes(Board boardAtacado) {
        ArrayList<SpecialObject> volcanes = new ArrayList<>();

        for (int i = 0; i < boardAtacado.getROWS(); i++) {
            for (int j = 0; j < boardAtacado.getCOLUMNS(); j++) {
                Tile tile = boardAtacado.getTile(i, j);
                if (tile != null) {
                    for (SpecialObject obj : tile.getSpecialObjects()) {
                        if (obj.getType() == SpecialObjectType.VOLCANO && obj.isActive()) {
                            volcanes.add(obj);
                        }
                    }
                }
            }
        }

        return volcanes;
    }

}
