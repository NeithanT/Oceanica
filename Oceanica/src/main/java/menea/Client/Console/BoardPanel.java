
package menea.Client.Console;

import menea.Tiles.Board;
import menea.Tiles.Tile;
import menea.Tiles.TileState;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private Board board;
    private int cellSize = 18; // ajusta si quieres más grande/pequeño
    private int padding  = 2;

    public BoardPanel(Board board) {
        this.board = board;
        setBackground(Color.DARK_GRAY);
    }

    public void setBoard(Board b) {
        this.board = b;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (board == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int r = 0; r < board.getROWS(); r++) {
            for (int c = 0; c < board.getCOLUMNS(); c++) {
                Tile t = board.getTile(r, c);
                Color fill = switch (t.getState()) {
                    case EMPTY     -> new Color(200, 200, 200); // gris
                    case OCCUPIED  -> new Color(70, 130, 180);  // azul
                    case DAMAGED   -> new Color(230, 126, 34);  // naranja
                    case DESTROYED -> new Color(35, 35, 35);    // casi negro
                };

                int x = padding + c * (cellSize + 1);
                int y = padding + r * (cellSize + 1);

                g2.setColor(fill);
                g2.fillRect(x, y, cellSize, cellSize);
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, cellSize, cellSize);
            }
        }

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        if (board == null) return new Dimension(300, 200);
        int w = padding*2 + board.getCOLUMNS()*(cellSize+1);
        int h = padding*2 + board.getROWS()*(cellSize+1);
        return new Dimension(w, h);
    }
}
