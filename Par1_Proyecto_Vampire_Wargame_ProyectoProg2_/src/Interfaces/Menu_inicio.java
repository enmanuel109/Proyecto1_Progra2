/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

/**
 *
 * @author Cantarero
 */
public class Menu_inicio extends JFrame implements ActionListener {

    public Menu_inicio() {
        setTitle("Menu Inicio");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton botonLogIn = new JButton();
        botonLogIn.setBounds(290, 437, 237, 70);
        add(botonLogIn);
        botonLogIn.setOpaque(false);
        botonLogIn.setContentAreaFilled(false);
        botonLogIn.setBorderPainted(false);
        botonLogIn.setFocusPainted(false);

        JButton botonCrearPlayer = new JButton("");
        botonCrearPlayer.setBounds(290, 525, 237, 70);
        add(botonCrearPlayer);
        botonCrearPlayer.setOpaque(false);
        botonCrearPlayer.setContentAreaFilled(false);
        botonCrearPlayer.setBorderPainted(false);
        botonCrearPlayer.setFocusPainted(false);

        JButton botonSalir = new JButton("");
        botonSalir.setBounds(290, 610, 237, 70);
        add(botonSalir);
        botonSalir.setOpaque(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);
        botonSalir.setFocusPainted(false);

        ImageIcon FondoMenu = new ImageIcon(getClass().getResource("/imagenes/menu_inicio.png"));
        Image imgModificada = FondoMenu.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        FondoMenu = new ImageIcon(imgModificada);
        JLabel label_Fondo = new JLabel(FondoMenu);
        label_Fondo.setBounds(00, 00, 800, 800);
        add(label_Fondo);

        botonLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LOG_IN ventana = new LOG_IN();
                ventana.setVisible(true);
            }
        });

        botonCrearPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                Crear_Player ventana = new Crear_Player();
                ventana.mostrar();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
