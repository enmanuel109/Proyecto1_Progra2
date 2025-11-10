/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Crear_Player extends Funciones_Players{

    public Calendar ActualFecha = Calendar.getInstance();
    public PLAYER jugador;
    private JFrame frame;

    public Crear_Player() {
        frame = new JFrame("Crear player");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Fondo principal
        ImageIcon FondoVentana = new ImageIcon(getClass().getResource("/imagenes/fondo_principal.png"));
        Image fondoEscalado = FondoVentana.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel Label_Fondo = new JLabel(new ImageIcon(fondoEscalado));
        Label_Fondo.setBounds(0, 0, 800, 800);
        Label_Fondo.setLayout(null);

        // Botón regreso
        JButton botonRegreso = new JButton();
        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/Boton_regreso.png"));
        Image imgBoton = iconoBoton.getImage().getScaledInstance(69, 35, Image.SCALE_SMOOTH);
        botonRegreso = new JButton(new ImageIcon(imgBoton));
        botonRegreso.setBounds(20, 20, 69, 35);
        botonRegreso.setBorderPainted(false);
        botonRegreso.setContentAreaFilled(false);
        botonRegreso.setFocusPainted(false);
        Label_Fondo.add(botonRegreso);

        // Botón registrar
        JButton boton_Registrar = new JButton();
        boton_Registrar.setBounds(345, 600, 140, 50);
        boton_Registrar.setOpaque(false);
        boton_Registrar.setContentAreaFilled(false);
        boton_Registrar.setBorderPainted(false);
        boton_Registrar.setFocusPainted(false);
        Label_Fondo.add(boton_Registrar);

        // Etiqueta Usuario
        JLabel Label_usuario = new JLabel("Usuario");
        Label_usuario.setBounds(305, 270, 200, 80);
        Label_usuario.setHorizontalAlignment(JTextField.CENTER);
        Label_usuario.setFont(new Font("Serif", Font.BOLD, 35));
        Label_usuario.setForeground(Color.WHITE);
        Label_Fondo.add(Label_usuario);

        // Campo Usuario
        JTextField usuario = new JTextField("");
        usuario.setBounds(305, 320, 200, 80);
        usuario.setHorizontalAlignment(JTextField.CENTER);
        usuario.setFont(new Font("Serif", Font.BOLD, 22));
        usuario.setForeground(Color.WHITE);
        usuario.setOpaque(false);
        usuario.setBorder(null);
        Label_Fondo.add(usuario);

        // Fondo campo usuario
        ImageIcon fondoUsuario = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoUsuarioEscalado = fondoUsuario.getImage().getScaledInstance(250, 80, Image.SCALE_SMOOTH);
        JLabel FondoUsuario = new JLabel(new ImageIcon(fondoUsuarioEscalado));
        FondoUsuario.setBounds(280, 320, 250, 80);
        Label_Fondo.add(FondoUsuario);

        // Etiqueta Password
        JLabel Label_password = new JLabel("Contraseña");
        Label_password.setBounds(305, 405, 200, 80);
        Label_password.setHorizontalAlignment(JTextField.CENTER);
        Label_password.setFont(new Font("Serif", Font.BOLD, 35));
        Label_password.setForeground(Color.WHITE);
        Label_Fondo.add(Label_password);

        // Campo Password
        JTextField password = new JTextField("");
        password.setBounds(305, 460, 200, 80);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Serif", Font.BOLD, 22));
        password.setForeground(Color.WHITE);
        password.setOpaque(false);
        password.setBorder(null);
        Label_Fondo.add(password);

        // Fondo campo password
        ImageIcon fondoPassword = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoPasswordEscalado = fondoPassword.getImage().getScaledInstance(250, 80, Image.SCALE_SMOOTH);
        JLabel FondoPassword = new JLabel(new ImageIcon(fondoPasswordEscalado));
        FondoPassword.setBounds(280, 460, 250, 80);
        Label_Fondo.add(FondoPassword);

        // Fondo logotipo
        ImageIcon fondoLog = new ImageIcon(getClass().getResource("/imagenes/Crear_player.png"));
        Image fondoLogIn = fondoLog.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);
        JLabel FondoLogIn = new JLabel(new ImageIcon(fondoLogIn));
        FondoLogIn.setBounds(140, 130, 550, 550);
        FondoLogIn.setLayout(null);
        Label_Fondo.add(FondoLogIn);

        frame.add(Label_Fondo);

        // Eventos
        botonRegreso.addActionListener(e -> {
            frame.dispose();
            new Menu_inicio().setVisible(true);
        });

        boton_Registrar.addActionListener(e -> {
            String pass = password.getText();
            String user = usuario.getText();

            if (user.contains(" ")) {
                JOptionPane.showMessageDialog(frame, "El nombre no puede contener espacios en blanco");
                return;
            }

            if (!ValidarName(user)) {
                JOptionPane.showMessageDialog(frame, "Nombre Invalido");
                return;
            }

            if (!ValidarPassword(pass)) {
                JOptionPane.showMessageDialog(frame, "Contraseña Invalida. Debe tener 5 caracteres, al menos un símbolo especial y una Mayuscula");
                return;
            }

            PLAYER NuevoJugador = new PLAYER(user, pass);
            PLAYER.Players.add(NuevoJugador);

            LOG_IN.JugadorActual = NuevoJugador;

            frame.dispose();
            new Menu_principal().setVisible(true);
        });

    }
     public void mostrar() {
        frame.setVisible(true);
    }

}