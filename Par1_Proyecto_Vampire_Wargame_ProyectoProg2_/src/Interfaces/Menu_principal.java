/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cantarero
 */
public class Menu_principal extends JFrame {

    public static PLAYER Rival;

    public Menu_principal() {
        setTitle("Menu principal");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon FondoImg = new ImageIcon(getClass().getResource("/imagenes/menu_principal.png"));
        Image imagenEscalada = FondoImg.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        FondoImg = new ImageIcon(imagenEscalada);
        JLabel label_Fondo = new JLabel(FondoImg);
        label_Fondo.setBounds(00, 00, 800, 800);

        JButton botonJugar = new JButton();
        botonJugar.setBounds(230, 212, 328, 76);
        botonJugar.setOpaque(false);
        botonJugar.setContentAreaFilled(false);
        botonJugar.setBorderPainted(false);
        botonJugar.setFocusPainted(false);
        label_Fondo.add(botonJugar);

        JButton botonMiCuenta = new JButton("");
        botonMiCuenta.setBounds(273, 310, 255, 72);
        botonMiCuenta.setOpaque(false);
        botonMiCuenta.setContentAreaFilled(false);
        botonMiCuenta.setBorderPainted(false);
        botonMiCuenta.setFocusPainted(false);
        label_Fondo.add(botonMiCuenta);

        JButton botonReportes = new JButton("");
        botonReportes.setBounds(273, 410, 255, 72);
        botonReportes.setOpaque(false);
        botonReportes.setContentAreaFilled(false);
        botonReportes.setBorderPainted(false);
        botonReportes.setFocusPainted(false);
        label_Fondo.add(botonReportes);

        JButton botonLogOut = new JButton("");
        botonLogOut.setBounds(273, 510, 255, 72);
        botonLogOut.setOpaque(false);
        botonLogOut.setContentAreaFilled(false);
        botonLogOut.setBorderPainted(false);
        botonLogOut.setFocusPainted(false);
        label_Fondo.add(botonLogOut);

        //Jugadores Posibles
        ImageIcon FondoExt = new ImageIcon(getClass().getResource("/imagenes/FondosJugadoresDisponibles1.png"));
        Image imgModificadaExt = FondoExt.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label_Fondo_ext = new JLabel(new ImageIcon(imgModificadaExt));
        label_Fondo_ext.setBounds(0, 0, 800, 800);
        label_Fondo_ext.setLayout(null);
        label_Fondo_ext.setVisible(false);
        add(label_Fondo_ext);

        JButton botonRegr = new JButton("REGRESAR");
        botonRegr.setBounds(320, 590, 135, 40);
        botonRegr.setOpaque(false);
        botonRegr.setContentAreaFilled(false);
        botonRegr.setBorderPainted(false);
        botonRegr.setFocusPainted(false);
        botonRegr.setForeground(Color.LIGHT_GRAY);
        label_Fondo_ext.add(botonRegr);

        //Jugadores no Posibles
        ImageIcon FondoimgError = new ImageIcon(getClass().getResource("/imagenes/JugadoresNoDisponibles.png"));
        Image imgModificadaError = FondoimgError.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel FondoError = new JLabel(new ImageIcon(imgModificadaError));
        FondoError.setBounds(0, 0, 800, 800);
        FondoError.setLayout(null);
        FondoError.setVisible(false);
        add(FondoError);

        JLabel Errortext = new JLabel("No hay rivales registrados  ", SwingConstants.CENTER);
        JLabel Errortext1 = new JLabel("para nueva partida", SwingConstants.CENTER);

        Errortext.setBounds(165, 320, 500, 50);
        Errortext.setForeground(Color.LIGHT_GRAY);
        Errortext1.setBounds(165, 370, 500, 50);
        Errortext1.setForeground(Color.LIGHT_GRAY);

        FondoError.add(Errortext);
        FondoError.add(Errortext1);

        JButton botonOk = new JButton("OK");
        botonOk.setBounds(330, 470, 135, 40);
        botonOk.setOpaque(false);
        botonOk.setContentAreaFilled(false);
        botonOk.setBorderPainted(false);
        botonOk.setFocusPainted(false);
        botonOk.setForeground(Color.LIGHT_GRAY);

        FondoError.add(botonOk);

        JPanel panelTabla = new JPanel(new GridLayout(0, 1, 10, 5));
        panelTabla.setOpaque(false);

        try {
            Font FuenteTabla = Font.createFont(
                    Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/BLACC___.TTF")
            );

            JLabel LblUserName = new JLabel("SELECCIONA TU RIVAL", SwingConstants.CENTER);
            LblUserName.setFont(FuenteTabla.deriveFont(35f));
            LblUserName.setForeground(Color.LIGHT_GRAY);
            LblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
            label_Fondo_ext.add(LblUserName);
            LblUserName.setBounds(150, 150, 500, 80);

            Errortext.setFont(FuenteTabla.deriveFont(35f));
            Errortext1.setFont(FuenteTabla.deriveFont(35f));

            botonOk.setFont(FuenteTabla.deriveFont(18f));
            botonRegr.setFont(FuenteTabla.deriveFont(18f));

            if (BusacarJugador()) {

                panelTabla.removeAll();

                for (PLAYER fila : Reportes.OrdenRankin(PLAYER.Players)) {

                    if (fila.isActivo() && !LOG_IN.JugadorActual.getName().equalsIgnoreCase(fila.getName())) {

                        JPanel filaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
                        filaPanel.setOpaque(false);

                        JLabel lblName = new JLabel(fila.getName(), SwingConstants.CENTER);
                        lblName.setFont(FuenteTabla.deriveFont(25f));
                        lblName.setForeground(Color.LIGHT_GRAY);
                        lblName.setPreferredSize(new Dimension(200, 50));
                        lblName.setHorizontalAlignment(SwingConstants.CENTER);
                        lblName.setVerticalAlignment(SwingConstants.CENTER);
                        lblName.setOpaque(false);

                        // Botón elegir
                        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/buton.png"));
                        Image imgBoton = iconoBoton.getImage().getScaledInstance(140, 50, Image.SCALE_SMOOTH);
                        JButton btnElegir = new JButton(new ImageIcon(imgBoton));
                        btnElegir.setPreferredSize(new Dimension(140, 50));
                        btnElegir.setText("Elegir Rival");
                        btnElegir.setOpaque(false);
                        btnElegir.setContentAreaFilled(false);
                        btnElegir.setBorderPainted(false);
                        btnElegir.setFocusPainted(false);
                        btnElegir.setForeground(Color.LIGHT_GRAY);
                        btnElegir.setFont(FuenteTabla.deriveFont(20f));

                        btnElegir.setVerticalTextPosition(SwingConstants.CENTER);
                        btnElegir.setHorizontalTextPosition(SwingConstants.CENTER);

                        btnElegir.addActionListener(e -> {
                            Rival = fila;
                            dispose();
                            Game ventana = new Game();
                            ventana.setVisible(true);
                        });

                        filaPanel.add(lblName);
                        filaPanel.add(btnElegir);

                        panelTabla.add(filaPanel);
                    }
                }
                panelTabla.revalidate();
                panelTabla.repaint();
            }

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        JScrollPane scroll = new JScrollPane(panelTabla);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setBounds(161, 275, 453, 310);
        label_Fondo_ext.add(scroll);

        add(label_Fondo);

        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BusacarJugador()) {
                    label_Fondo.setVisible(false);
                    label_Fondo_ext.setVisible(true);
                } else {
                    label_Fondo.setVisible(false);
                    FondoError.setVisible(true);

                }
            }
        });
        botonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_Fondo.setVisible(true);
                FondoError.setVisible(false);
            }
        });
        botonRegr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_Fondo.setVisible(true);
                label_Fondo_ext.setVisible(false);
            }
        });

        botonMiCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Mi_Cuenta ventana = new Mi_Cuenta();
                ventana.setVisible(true);
            }
        });

        botonReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Reportes ventana = new Reportes();
                ventana.setVisible(true);
            }
        });

        botonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu_inicio ventana = new Menu_inicio();
                ventana.setVisible(true);
            }
        });

    }

    public boolean BusacarJugador() {
        for (PLAYER Temp : PLAYER.Players) {
            if (Temp.isActivo() && !LOG_IN.JugadorActual.getName().equalsIgnoreCase(Temp.getName())) {
                return true;
            }
        }
        return false;
    }

  
}
