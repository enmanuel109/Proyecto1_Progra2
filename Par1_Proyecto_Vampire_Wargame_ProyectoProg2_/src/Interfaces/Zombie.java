/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Cantarero
 */
public class Zombie extends Personaje {

    public Zombie(int vida, int daño, int escudo, boolean esOscuro) {
        super(vida, daño, escudo, esOscuro);
    }

    
    public int ataqueNormal() {
        return 1;
    }
}