package menea.Tiles;


import java.util.ArrayList;
import menea.Fighters.Fighter;


public class Tile {

    private Fighter owner;  // quién la posee
    private TileState state;
    private int life;
    private final ArrayList<String> history; // historial para guardar td lo que pasa durante el juego, final para que si alguien hace un newAr....
    private final ArrayList<SpecialObject> specialObjects; // objetos especiales en la casilla (volcanes, remolinos, basura)

    public Tile() {
        this.state = TileState.EMPTY;
        this.life = 100;
        this.history = new ArrayList<>();
        this.specialObjects = new ArrayList<>();
    }

    public void daño(int cantidad, String atacante) {
        if (life <= 0) return;
        int antes = life;
        life = Math.max(0, life - cantidad);
        if (life == 0) {
            state = TileState.DESTROYED;
        } else {
            state = TileState.DAMAGED;
        }
        this.history.add("Ataque recibido de " + atacante + ": -" + cantidad + ", vida antes: " + antes + ", después:" + life + ":("); //Agregarle talvez que salga la fila y columna de la casilla
    }

    public void heal(int cantidad, String fuente) {
        int antes = life;
        life = Math.min(100, life + cantidad);
        this.history.add("Curación de " + fuente + ": +" + cantidad + ", vida: " + antes + ", después " + life + ":)");
        if (life > 0) {
            this.state = TileState.OCCUPIED;
        }
    }
    
    public Fighter getOwner() {
        return owner;
    }

    public void setOwner(Fighter owner) {
        this.owner = owner;
        this.state = TileState.OCCUPIED;
        this.history.add("Ocupada por: " + owner); //agregarle su ubicación?
    }

    
    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public ArrayList<SpecialObject> getSpecialObjects() {
        return specialObjects;
    }

    public void addSpecialObject(SpecialObject obj) {
        this.specialObjects.add(obj);
        this.history.add("Objeto especial añadido: " + obj.getType());
    }

    public void removeSpecialObject(SpecialObject obj) {
        this.specialObjects.remove(obj);
        this.history.add("Objeto especial removido: " + obj.getType());
    }

}
