package menea.Player;

import menea.Fighters.Fighter;
import java.net.Socket;
import java.util.ArrayList;
import menea.Tiles.Board;


public class Player {

    private String name;
    private Socket socket; // socket to connect with the server
    
    private ArrayList<Fighter> fighters; //arreglo de 3 jugadores
    private Board board;
    private boolean ready; //saber si está listo para jugar
    private boolean alive; //indica si la civilizacion esta viva 
    
    //TODO: bitacora
    private ArrayList<String> bitacora;
    
    
    //CONSTUCTOR
    public Player(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
        this.fighters = new ArrayList<>();
        this.board = new Board();
        this.ready = false;
        this.alive = true;
        this.bitacora = new ArrayList<>();
    }
    
    public void addFighter(Fighter fighter){
        if (fighters.size() < 3){ //verifica que solo pueda tener 3 luchadores por jugador
            fighters.add(fighter);
        } else{
            System.out.println("Ya tienes 3 luchadores.");
        }
    }
    
    public boolean isReady(){
        if (fighters.size() == 3){
            this.ready = true;
        }
        return ready;
    }
    
    public double porcentajeVida(){
        int totalTiles = board.getRows() * board.getColumns(); //600 tiles
        //TODO: determinar aliveTiles en Board
        int aliveTiles = 10; //TODO: cambiar el 10
        return (aliveTiles * 100) / totalTiles;
    }
    
    public void aliveStatus(){
        this.alive = porcentajeVida() > 0;
    }
    
    //TODO: metodo para mostrar info en bitacora
    
    //muestra la info del jugador
    public String statusSummary() {
        return "Jugador: " + name + "\n Peleadores: " + fighters + "\n  Vida Total: " + porcentajeVida() + "\n Estado: " + (alive ? "Vivo" : "Muerto") + "\n";
    }
    
    
    //GETTERS Y SETTERS

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isAlive() {
        return alive;
    }

    public ArrayList<String> getBitacora() {
        return bitacora;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setBitacora(ArrayList<String> bitacora) {
        this.bitacora = bitacora;
    }

}
