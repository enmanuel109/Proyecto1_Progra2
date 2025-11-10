/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author Cantarero
 */

public class LOG_IN extends JFrame {

    public Crear_Player Funciones_player;
    public static PLAYER JugadorActual = null;

    public LOG_IN() {
        setTitle("Log In");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo principal
        ImageIcon FondoLogIn = new ImageIcon(getClass().getResource("/imagenes/fondo_principal.png"));
        Image imgModificada = FondoLogIn.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label_Fondo = new JLabel(new ImageIcon(imgModificada));
        label_Fondo.setBounds(0, 0, 800, 800);
        label_Fondo.setLayout(null);

        // Botón regreso
        JButton botonRegreso = new JButton();
        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/Boton_regreso.png"));
        Image imgBoton = iconoBoton.getImage().getScaledInstance(69, 35, Image.SCALE_SMOOTH);
        botonRegreso = new JButton(new ImageIcon(imgBoton));
        botonRegreso.setBounds(20, 20, 69, 35);
        botonRegreso.setBorderPainted(false);
        botonRegreso.setContentAreaFilled(false);
        botonRegreso.setFocusPainted(false);
        label_Fondo.add(botonRegreso);

        // Etiqueta Usuario
        JLabel Label_usuario = new JLabel("Usuario");
        Label_usuario.setBounds(305, 270, 200, 80);
        Label_usuario.setHorizontalAlignment(JTextField.CENTER);
        Label_usuario.setFont(new Font("Serif", Font.BOLD, 35));
        Label_usuario.setForeground(Color.WHITE);
        label_Fondo.add(Label_usuario);

        // Campo Usuario
        JTextField usuario = new JTextField("");
        usuario.setBounds(305, 320, 200, 80);
        usuario.setHorizontalAlignment(JTextField.CENTER);
        usuario.setFont(new Font("Serif", Font.BOLD, 22));
        usuario.setForeground(Color.WHITE);
        usuario.setOpaque(false);
        usuario.setBorder(null);
        label_Fondo.add(usuario);

        // Fondo campo usuario
        ImageIcon fondoUsuario = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoUsuarioEscalado = fondoUsuario.getImage().getScaledInstance(250, 80, Image.SCALE_SMOOTH);
        JLabel FondoUsuario = new JLabel(new ImageIcon(fondoUsuarioEscalado));
        FondoUsuario.setBounds(280, 320, 250, 80);
        label_Fondo.add(FondoUsuario);

        // Etiqueta Password
        JLabel Label_password = new JLabel("Contraseña");
        Label_password.setBounds(305, 405, 200, 80);
        Label_password.setHorizontalAlignment(JTextField.CENTER);
        Label_password.setFont(new Font("Serif", Font.BOLD, 35));
        Label_password.setForeground(Color.WHITE);
        label_Fondo.add(Label_password);

        // Campo Password
        JTextField password = new JTextField("");
        password.setBounds(305, 460, 200, 80);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(new Font("Serif", Font.BOLD, 22));
        password.setForeground(Color.WHITE);
        password.setOpaque(false);
        password.setBorder(null);
        label_Fondo.add(password);

        // Fondo campo password
        ImageIcon fondoPassword = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoPasswordEscalado = fondoPassword.getImage().getScaledInstance(250, 80, Image.SCALE_SMOOTH);
        JLabel FondoPassword = new JLabel(new ImageIcon(fondoPasswordEscalado));
        FondoPassword.setBounds(280, 460, 250, 80);
        label_Fondo.add(FondoPassword);

        // Botón Enter
        JButton boton_Enter = new JButton();
        boton_Enter.setBounds(345, 600, 140, 50);
        boton_Enter.setOpaque(false);
        boton_Enter.setContentAreaFilled(false);
        boton_Enter.setBorderPainted(false);
        boton_Enter.setFocusPainted(false);
        label_Fondo.add(boton_Enter);

        // Fondo Log In
        ImageIcon fondoLog = new ImageIcon(getClass().getResource("/imagenes/Log_In.png"));
        Image fondoLogIn = fondoLog.getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);
        JLabel labelFondoLogIn = new JLabel(new ImageIcon(fondoLogIn));
        labelFondoLogIn.setBounds(140, 130, 550, 550);
        label_Fondo.add(labelFondoLogIn);
        labelFondoLogIn.setLayout(null);

        add(label_Fondo);

        // Acción botón Enter
        boton_Enter.addActionListener(e -> {
            String pass = password.getText();
            String user = usuario.getText();

            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre de usuario.");
                return;
            }

            // Buscar jugador activo
            JugadorActual = null;
            for (PLAYER temp : PLAYER.Players) {
                if (temp.isActivo() && user.equalsIgnoreCase(temp.getName())) {
                    JugadorActual = temp;
                    break;
                }
            }

            if (JugadorActual == null) {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado o inactivo.");
                return;
            }

            if (!JugadorActual.getPassword().equals(pass)) {
                JOptionPane.showMessageDialog(null, "Contraseña inválida. Debe tener 5 caracteres y al menos un símbolo especial.");
                return;
            }

            dispose(); // cerrar log in
            new Menu_principal().setVisible(true);
        });

        // Acción botón Regreso
        botonRegreso.addActionListener(e -> {
            dispose();
            new Menu_inicio().setVisible(true);
        });
    }

    
}