/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Cantarero
 */
public abstract class Personaje {

    protected int vida;
    protected int daño;
    protected int escudo;
    protected final boolean esOscuro;
    
    public Personaje(int vida, int daño, int escudo, boolean esOscuro) { 
        this.vida = vida;
        this.daño = daño;
        this.escudo = escudo;
        this.esOscuro = esOscuro; 
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public int getVida() {
        return vida;
    }

    public int getDaño() {
        return daño;
    }

    public int getEscudo() {
        return escudo;
    }
    

    
    public boolean esOscuro() {
        return esOscuro;
    }

    public void recibirAtaque(int cantidad) {
        int dañoReal = cantidad - escudo;
        if (dañoReal < 0) {
            dañoReal = 0;
        }
        vida -= dañoReal;
        if (vida < 0) {
            vida = 0;
        }
    }
    public abstract int ataqueNormal();

}
