package menea.Fighters;

import java.util.Random;



public abstract class Attack {
     protected Random random; //protected para que sus clases hijas sin importar el paquete, puedan utilizarlos incluso sin gets sets 
    protected AttackType type;
    
    public Attack() {
        this.random = new Random();
    }
}
