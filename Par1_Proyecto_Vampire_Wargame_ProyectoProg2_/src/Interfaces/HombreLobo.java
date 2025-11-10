/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Cantarero
 */
public class HombreLobo extends Personaje {

    public HombreLobo(int vida, int daño, int escudo, boolean esOscuro) {
        super(vida, daño, escudo, esOscuro); 
    }

    

    @Override
    public int ataqueNormal() {
        return 5;
    }
    
}