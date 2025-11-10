/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Cantarero
 */
public class PLAYER {

    public String name;
    public String password;
    public int point;
    public Calendar Entry_Date;
    public boolean activo;

    public static ArrayList<PLAYER> Players = new ArrayList<>();
    public ArrayList<String> LOGSPLAYER = new ArrayList<>();

    public PLAYER() {
    }

    public PLAYER(String name, String password) {
        this.name = name.trim();
        this.password = password.trim();
        point = 0;
        Entry_Date = Calendar.getInstance();
        activo = true;
       LOGSPLAYER = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Calendar getEntry_Date() {
        return Entry_Date;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<String> getLOGSPLAYER() {
        return LOGSPLAYER;
    }


    @Override
    public String toString() {
        return "PLAYERS{" + "password=" + password + ", name=" + name + ", point=" + point + ", Entry_Date=" + Entry_Date + ", activo=" + activo + '}';
    }

    public static void main(String[] args) {

    }

}
