/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menea.Models;

/**
 *
 * @author melissa
 */
public class AttackResult {
   private String attackType;
    private int dañoTotal;
    private int casillasAfactadas;
    
    public AttackResult(String attackType, int dañoTotal, int casillasAfactadas) {
        this.attackType = attackType;
        this.dañoTotal = dañoTotal;
        this.casillasAfactadas = casillasAfactadas;
    }
    
    public String getAttackType() { return attackType; }
    public int getTotalDamage() { return dañoTotal; }
    public int getTilesAffected() { return casillasAfactadas; }
}
