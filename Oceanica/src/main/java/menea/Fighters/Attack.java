package menea.Fighters;

import java.util.Random;
import menea.Tiles.Board;


public abstract class Attack {
    protected Random random; //protected para que sus clases hijas sin importar el paquete, puedan utilizarlos incluso sin gets sets 
    protected AttackType tipo;
    
    //CONSTRUCTOR
    public Attack(AttackType tipo) {
        this.tipo = tipo;
        this.random = new Random();
    }
    
    public int atacarArea(Board board, int centroX, int CentroY, int radio, int damage, String atacante){
        int ataquesExitosos = 0;
        int inicioX = centroX - radio;
        int finX = centroX + radio;
        int inicioY = CentroY - radio;
        int finY = CentroY + radio;

        //ataca una zona cuadrada
        for (int fila = inicioX; fila <= finX; fila++){
            for (int columna = inicioY; columna <= finY; columna++){
                if (board.inBounds(fila, columna)) {
                    if (board.getTile(fila, columna).daño(damage, atacante)) {
                        ataquesExitosos++;
                    }
                }
            }
        }
        return ataquesExitosos;
    }
    
    public int atacarLinea (Board board, int inicioX, int inicioY, String direccion, int alcance, int damage, String ataque){
        int ataquesExitosos = 0;
        //ataca en linea recta hacia abajo, arriba, der o izq
        for (int i = 1; i <= alcance; i++){

            switch (direccion.toLowerCase()) {
                case "arriba":
                    inicioX -= i;
                    break;
                case "abajo":
                    inicioX += i;
                    break;
                case "izquierda":
                    inicioY -= i;
                    break;
                case "derecha":
                    inicioY += i;
                    break;
            }

            if (board.inBounds(inicioX, inicioY)) {
                if (board.getTile(inicioX, inicioY).daño(damage, ataque)) {
                    ataquesExitosos++;
                }
            }
        }
        return ataquesExitosos;
    }
    
    public boolean atacarRandomTile(Board board, int damage, String atacante){
        int fila = random.nextInt(board.getROWS());
        int columna = random.nextInt(board.getCOLUMNS());
        return board.getTile(fila, columna).daño(damage, atacante);
    }
    
    public int[] getRandomTile(Board board) {
        return new int[]{
            random.nextInt(board.getROWS()),
            random.nextInt(board.getCOLUMNS())
        };
    }
    
    //GETTERS Y SETTERS

    public AttackType getTipo() {
        return tipo;
    }

    public void setTipo(AttackType tipo) {
        this.tipo = tipo;
    }
    
    
    
    
    
    
 
}
