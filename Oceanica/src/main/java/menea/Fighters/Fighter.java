package menea.Fighters;

import java.awt.Color;
import java.util.ArrayList;
import menea.Tiles.Tile;


public class Fighter {
    
    private AttackType type;
    private Attack attack;
    private String name;
    protected String imageId;
    private int strength;
    private int endurance;
    private int sanity;
    private int representationPercentage; 
    protected ArrayList<Tile> assignedTiles;
    private Color color;

    public Fighter(String name, String imageId, AttackType type, int strength, int endurance, int sanity, int representationPercentage) {
        this.name = name;
        this.imageId = imageId;
        this.type = type;
        this.strength = strength;
        this.endurance = endurance;
        this.sanity = sanity;
        this.representationPercentage = representationPercentage;
        this.assignedTiles = new ArrayList<>();
        this.color = generateRandomColor();
    }
    
    public void setAttack(Attack attack) {
        this.attack = attack;
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
    
    public String getName() {
        return name;
    }
    
    public String getImageId() {
        return imageId;
    }
    
    public AttackType getType() {
        return type;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public int getEndurance() {
        return endurance;
    }
    
    public int getSanity() {
        return sanity;
    }
    
    private Color generateRandomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new Color(r, g, b);
    }
    
    public Color getColor() {
        return color;
    }
    
}
    