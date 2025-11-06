package menea.Fighters;

import java.util.ArrayList;
import menea.Tiles.Tile;


public abstract class Fighter {
    
    private AttackType type;
    private Attack attack;
    
    private String name;
    protected String imagePath;
    private boolean alive;
    
    private int strength;
    private int endurance;
    private int sanity;
      
    private int representationPercentage; 
    protected ArrayList<Tile> assignedTiles;

    //CONSTRUCTOR
    public Fighter(AttackType type, Attack attack, String name, String imagePath, int strength, int endurance, int sanity, int representationPercentage, ArrayList<Tile> assignedTiles) {
        this.type = type;
        this.attack = attack;
        this.name = name;
        this.imagePath = imagePath;
        this.alive = true;
        this.strength = strength;
        this.endurance = endurance;
        this.sanity = sanity;
        this.representationPercentage = representationPercentage;
        this.assignedTiles = assignedTiles;
    }
    
    
    
    public void addTile(Tile tile) {
        assignedTiles.add(tile);
    }
    
    public boolean aliveStatus(){
        if (this.assignedTiles.isEmpty()){
            this.alive = false;
        }
        return alive;
    }
    

    public int getAliveTiles() {
        return assignedTiles.size(); //TODO: hacerlo bien
    }

    public int getDeadTiles() {
        return assignedTiles.size() - getAliveTiles();
    }

    public double getLifePercentage() {
        if (assignedTiles.isEmpty()) {
            return 0;
        }
        return (getAliveTiles() * 100.0) / assignedTiles.size();
    }

    public int getRepresentationPercentage() {
        return representationPercentage;
    }
    
    
    
}
