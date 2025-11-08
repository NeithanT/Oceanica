package menea.Fighters;

import menea.Tiles.Board;
import java.util.ArrayList;

public class Civilizacion {
    
    private String nombre;
    private ArrayList<Fighter> luchadores; //arreglo de 3 luchadores
    
    private static final int MAX_LUCHADORES = 3;
    
    private Board board;
    private double ocupacionCasillas; //porcentaje total de ocupación de la civilización

    private double vidaTotal;
    private boolean viva;
    private boolean ready; //saber si esta listo para jugar
    
    private ArrayList<String> bitacoraGeneral; //TODO
    private int ataquesRealizados;
    private int ataquesRecibidos;
    private int turnosJugados;

    //CONSTRUCTOR
    public Civilizacion(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()){
            System.out.println("No se ha ingresado un nombre valido para la civilizacion");
        }
        
        this.nombre = nombre;
        this.luchadores = new ArrayList<>();
        this.board = new Board(); 
        this.vidaTotal = 100.0;
        this.viva = true;
        this.ready = false;
        this.bitacoraGeneral = new ArrayList<>();
        this.ocupacionCasillas = 0.0;
        this.ataquesRealizados = 0;
        this.ataquesRecibidos = 0;
        this.turnosJugados = 0;
    }
    
    public void addFighter(Fighter luchador) {

        //validacion
        if (luchador == null) {
            System.out.println("El luchador no existe");
            return;
        }

        if (luchadores.size() >= MAX_LUCHADORES) {
            System.out.println("No puedes agregar mas luchadores");
            return;
        }

        luchadores.add(luchador); //agregarlo a la lista de luchadores

        //agregar las casillas del nuevo luchador al total que lleva la civilizacion
        int totalCasillasVivas = 0;
        for (Fighter fighter : luchadores) {
            totalCasillasVivas += fighter.getAliveTiles();
        }

        int totalCasillas = board.getTotalCasillas();

        //calcular porcentaje 
        this.ocupacionCasillas = (totalCasillasVivas * 100.0) / totalCasillas;

        System.out.println("Se agregó a un nuevo luchador");
    }

    public boolean eliminarLuchador(Fighter luchador) {

        //validaciones
        if (ready) {
            System.out.println("No se puede eliminar un luchador despues de estar listo para comenzar.");
            return false;
        }

        if (luchador != null) {
            luchadores.remove(luchador);
            System.out.println("Luchador eliminado.");
            return true;
        }

        return false;
    }
    
    public void state(){
        if (luchadores.size() != MAX_LUCHADORES) {
            System.out.println("Debes tener 3 luchadores en esta civilizacion.");
            return;
        }
        if (this.ocupacionCasillas != 100.0) {
                System.out.println("Las casillas deben estar ocupadas al 100% por los 3 luchadores.");
                return;
        }
        this.ready = true;
    }

    public void initBoard() {
        if(ready){
            for(Fighter fighter: luchadores){
                board.assignZone(fighter, fighter.getRepresentationPercentage());
            } 
        }
    }
    
    public double calcularVida(){
        //validaciones
        if (board == null){
            return 0.0;
        }
        
        int totalCasillas = board.getTotalCasillas();
        int casillasVivas = board.getCasillasVivas();
        return (casillasVivas * 100.0) / totalCasillas;
    }
    
    
    
}
