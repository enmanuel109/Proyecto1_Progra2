/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Cantarero
 */
public class Muerte extends Personaje {

    public Muerte(int vida, int daño, int escudo, boolean esOscuro) { 
        super(vida, daño, escudo, esOscuro); 
    }
    
    public int ataqueNormal() {
        return 4;
    }
}