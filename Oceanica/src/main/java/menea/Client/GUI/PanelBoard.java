package menea.Client.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import menea.Tiles.Board;
import menea.Tiles.Tile;
import static menea.Tiles.TileState.DAMAGED;
import static menea.Tiles.TileState.DESTROYED;
import static menea.Tiles.TileState.EMPTY;
import static menea.Tiles.TileState.OCCUPIED;


public class PanelBoard extends JPanel {

    private final int ROWS = 20;
    private final int COLUMNS = 30;
    private int cellSizeX;
    private int cellSizeY;
    private int width;
    private int height;
    
    
    private Board board;
    
    public PanelBoard(JPanel pnl, Board board) {
        width = (int)pnl.getWidth();
        height = (int)pnl.getHeight();
        
        cellSizeX = width/COLUMNS;
        cellSizeY = height/ROWS;
        
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics g2 = (Graphics2D) g;
        
        if (board == null) {
            return;
        }
        
        for (int row = 0; row < board.getROWS(); row++) {
            for (int column = 0; column < board.getCOLUMNS(); column++) {
                Tile tile = board.getTile(row, column);
                Color fill;
                
                if (tile.getState() == OCCUPIED && tile.getOwner() != null) {
                    fill = tile.getOwner().getColor();
                } else if (tile.getState() == DAMAGED) {
                    fill = new Color(255, 165, 0);
                } else if (tile.getState() == DESTROYED) {
                    fill = new Color(64, 64, 64);
                } else { // EMPTY
                    fill = new Color(40, 82, 220);
                }
                
                int x = column * (cellSizeX);
                int y = row * (cellSizeY);

                g2.setColor(fill);
                g2.fillRect(x, y, cellSizeX, cellSizeY);
            }
        }
        
        g2.setColor(Color.BLACK);
        
        for (int i = 0; i <= ROWS; i++) {
            g2.drawLine(0, i * cellSizeY, width, i * cellSizeY);
        }
        
        for (int j = 0; j <= COLUMNS; j++) {
            g2.drawLine(j * cellSizeX, 0, j * cellSizeX, height);
        }

        g2.dispose();
    }
    
    public void setBoard(Board b) {
        this.board = b;
        repaint();
    }

    
}
