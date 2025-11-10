/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Cantarero
 */
public class Funciones_Players {
     public boolean ValidarPassword(String password) {
        if (password.length() != 5) {
            return false;
        }

        String caracteres_especiales = "!@#$%|^&*()-+={}[]|\\:;\"'<>,.?/~`_¬§€©®™";
        boolean tieneEspecial = false;
        for (int i = 0; i < password.length(); i++) {
            if (caracteres_especiales.indexOf(password.charAt(i)) >= 0) {
                tieneEspecial = true;
                break;
            }
        }

        boolean tieneMayuscula = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                tieneMayuscula = true;
                break;
            }
        }

        return tieneEspecial && tieneMayuscula;
    }

    public boolean ValidarName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (PLAYER JuTem : PLAYER.Players) {
            if (name.equalsIgnoreCase(JuTem.getName())) {
                return false;
            }
        }

        return true;
    }
}
