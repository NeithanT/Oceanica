package menea.Fighters;

import java.util.ArrayList;
import menea.Tiles.Tile;


public abstract class Fighter {
    
    private AttackType type;
    private String name;
    protected String imagePath;
    private int strength;
    private int endurance;
    private int sanity;
    private int representationPercentage; 
    protected ArrayList<Tile> assignedTiles;

    public Fighter(String name, String imagePath, AttackType type, int strength, int endurance, int sanity, int representationPercentage) {
        this.name = name;
        this.imagePath = imagePath;
        this.type = type;
        this.strength = strength;
        this.endurance = endurance;
        this.sanity = sanity;
        this.representationPercentage = representationPercentage;
        this.assignedTiles = new ArrayList<>();
    }
    
    public void addTile(Tile tile) {
        assignedTiles.add(tile);
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
