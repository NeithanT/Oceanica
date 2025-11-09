package menea.Tiles;

import java.io.Serializable;

public class SpecialObject implements Serializable {

    private SpecialObjectType type;
    private int x;
    private int y;  
    private int radio;
    private boolean isRadioactive; //para basura radioactiva
    private boolean isActive; //verificar si está activo

    //constructor para volcan y remolino
    public SpecialObject(SpecialObjectType type, int x, int y, int radio) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.isRadioactive = false; //no pueden ser radioactivos
        this.isActive = true;
    }

    //constructor para basura
    public SpecialObject(SpecialObjectType type, int x, int y, boolean isRadioactive) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.radio = 0;
        this.isRadioactive = isRadioactive;
        this.isActive = true;
    }

    //GETTERS Y SETTERS
    public SpecialObjectType getType() {
        return type;
    }

    public void setType(SpecialObjectType type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public boolean isRadioactive() {
        return isRadioactive;
    }

    public void setRadioactive(boolean isRadioactive) {
        this.isRadioactive = isRadioactive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        if (type == SpecialObjectType.GARBAGE) {
            return "SpecialObject{" +
                    "type=" + type +
                    ", position=(" + x + "," + y + ")" +
                    ", radioactive=" + isRadioactive +
                    ", active=" + isActive +
                    '}';
        } else {
            return "SpecialObject{" +
                    "type=" + type +
                    ", position=(" + x + "," + y + ")" +
                    ", radio=" + radio +
                    ", active=" + isActive +
                    '}';
        }
    }
}
