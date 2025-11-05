package menea.Fighters;

import java.util.ArrayList;
import menea.Tiles.Tile;


public abstract class Fighter {
    
    private AttackType type;
    private Attack attack;
    
    private String name;
    protected String imagePath;
    
    private int strength;
    private int endurance;
    private int sanity;
      
    protected int representationPercentage; 
    protected ArrayList<Tile> assignedTiles;
    
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
    
}
