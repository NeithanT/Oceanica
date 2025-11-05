package menea.Tiles;

import menea.Fighters.Fighter;


public class Board {

    private final int ROWS = 20;
    private final int COLUMNS = 30;
    private final int AMT_FIGHTERS = 3;
    private Tile[][] tiles;
    private Fighter[] fighters;
    public Board() {
        tiles = new Tile[ROWS][COLUMNS];
        fighters = new Fighter[AMT_FIGHTERS];
        initializeTiles();
        
    }
    
    private void initializeTiles() {
    
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
            
                tiles[i][j] = new Tile();
            }
        }
    }
    public Tile getTile(int row, int col) {
    if (!inBounds(row, col)) return null;
    return tiles[row][col];
    }
    public boolean inBounds(int r, int c) { //para que no aaceda a una celda inexistente 
    return r >= 0 && r < ROWS && c >= 0 && c < COLUMNS;
    }
    

    public void assignZone(Fighter fighter, int porcentaje) {
        // Asigna celdas proporcionalmente según el porcentaje del peleador
        int totalTiles = ROWS * COLUMNS;
        int tilesParaAsignar = (int) ((porcentaje / 100.0) * totalTiles);

        int asignados = 0;
        for (int i = 0; i < ROWS && asignados < tilesParaAsignar; i++) {
            for (int j = 0; j < COLUMNS && asignados < tilesParaAsignar; j++) {
                Tile tile = tiles[i][j];
                if (tile.getState() == TileState.EMPTY) {
                    tile.setOwner(fighter);
                    asignados++;
                }
            }
        }
    }

    public String resumen() {
        int occupied = 0, damaged = 0, destroyed = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                switch (tiles[i][j].getState()) {
                    case OCCUPIED: 
                        occupied++;
                         break;
                    case DAMAGED: 
                        damaged++;
                        break;
                    case DESTROYED: 
                        destroyed++;
                        break;
                    default:
                        break;
                }
            }
        }
        return String.format("Casillas ocupadas: %d | Dañadas: %d | Destruidas: %d", occupied, damaged, destroyed);
    }
    
    public HitResult attackAt(int row, int col) {
        // Para poder realizar un daño a una casilla específica
        if (!inBounds(row, col)) return HitResult.MISS;
        Tile t = tiles[row][col];
        switch (t.getState()) {
            case EMPTY: // si estaba vacío, se va a marca como dañado
                t.setState(TileState.DAMAGED);
                return HitResult.HIT;
            case OCCUPIED, DAMAGED:
                t.setState(TileState.DESTROYED);
                return HitResult.DESTROYED;
            case DESTROYED:
                return HitResult.MISS; // ya estaba destruida
            default:
                
                break;
        }
        
        return HitResult.MISS;
    } 
    
    public int getRows() { 
        return ROWS; 
    }

    public int getColumns() { 
        return COLUMNS; 
    }
    
    
}
