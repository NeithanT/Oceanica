package menea.Fighters;

import menea.Tiles.Board;
import java.util.ArrayList;

public class Civilizacion {
    
    private String nombre;
    private ArrayList<Fighter> luchadores; //arreglo de 3 luchadores
    private static final int MAX_LUCHADORES = 3;
    private Board board;
    private double vidaTotal;
    private boolean viva;
    private ArrayList<String> bitacoraGeneral; //TODO

    //CONSTRUCTOR
    public Civilizacion(String nombre, Board board, ArrayList<String> bitacoraGeneral) {
        if (nombre == null || nombre.trim().isEmpty()){
            System.out.println("Error"); //TODO
        }
        
        this.nombre = nombre;
        this.luchadores = new ArrayList<>();
        this.board = board; 
        this.vidaTotal = 100.0;
        this.viva = true;
        this.bitacoraGeneral = bitacoraGeneral;
    }
    
    
    
}
