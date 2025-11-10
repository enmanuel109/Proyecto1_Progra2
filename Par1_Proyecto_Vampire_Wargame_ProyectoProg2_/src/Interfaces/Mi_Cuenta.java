/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Mi_Cuenta extends JFrame {

    public Mi_Cuenta() {
        setTitle("Mi Cuenta");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);

        PLAYER Funiciones_Player = new PLAYER();
        Crear_Player Funciones_Verificar = new Crear_Player();

        ImageIcon Fondo_MiCuenta = new ImageIcon(getClass().getResource("/imagenes/Fondo_MiCuenta1.png"));
        Image imgModificada = Fondo_MiCuenta.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        Fondo_MiCuenta = new ImageIcon(imgModificada);
        JLabel label_Fondo = new JLabel(Fondo_MiCuenta);
        label_Fondo.setBounds(00, 00, 800, 800);

        ImageIcon Fondo_MiCuentaCont = new ImageIcon(getClass().getResource("/imagenes/fondo_2.png"));
        Image imgModificadaCont = Fondo_MiCuentaCont.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        Fondo_MiCuentaCont = new ImageIcon(imgModificadaCont);
        JLabel label_FondoCont = new JLabel(Fondo_MiCuentaCont);
        label_FondoCont.setBounds(00, 00, 800, 800);
        label_FondoCont.setVisible(false);

        JButton botonRegreso = new JButton();
        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/Boton_regreso.png"));
        Image imgBoton = iconoBoton.getImage().getScaledInstance(69, 35, Image.SCALE_SMOOTH);
        botonRegreso = new JButton(new ImageIcon(imgBoton));
        botonRegreso.setBounds(20, 20, 69, 35);
        botonRegreso.setBorderPainted(false);
        botonRegreso.setContentAreaFilled(false);
        botonRegreso.setFocusPainted(false);
        label_Fondo.add(botonRegreso);

        //Información del jugador 
        JLabel LblUserName = new JLabel("Nombre:");
        LblUserName.setBounds(200, 200, 200, 50);

        JLabel UserName = new JLabel(LOG_IN.JugadorActual.getName());
        UserName.setBounds(340, 200, 200, 50);

        JLabel LblEntry_Date1 = new JLabel("Fecha de creación");
        LblEntry_Date1.setBounds(200, 230, 300, 100);

        JLabel LblEntry_Date2 = new JLabel("de la cuenta:");
        LblEntry_Date2.setBounds(200, 265, 300, 100);

        Calendar fecha = LOG_IN.JugadorActual.getEntry_Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        JLabel EntryDate = new JLabel(formato.format(fecha.getTime()));
        EntryDate.setBounds(435, 247, 300, 100);

        JLabel LblPoint = new JLabel("Puntos:");
        LblPoint.setBounds(200, 315, 300, 100);

        JLabel Point = new JLabel(String.valueOf(LOG_IN.JugadorActual.getPoint()));
        Point.setBounds(340, 315, 300, 100);

        JLabel LblEstado = new JLabel("Estado de la cuenta:");
        LblEstado.setBounds(200, 370, 300, 100);

        JLabel Estado = new JLabel("");
        Estado.setBounds(495, 370, 300, 100);
        if (LOG_IN.JugadorActual.isActivo()) {
            Estado.setText("Activo");
        } else {
            Estado.setText("Desactivo");

        }

        // Botones principales
        JButton BtnEliminar_cuenta = new JButton("");
        BtnEliminar_cuenta.setBounds(450, 600, 250, 70);
        BtnEliminar_cuenta.setOpaque(false);
        BtnEliminar_cuenta.setContentAreaFilled(false);
        BtnEliminar_cuenta.setBorderPainted(false);
        BtnEliminar_cuenta.setFocusPainted(false);
        add(BtnEliminar_cuenta);

        JButton BtnCambiar_contraseña = new JButton("");
        BtnCambiar_contraseña.setBounds(110, 600, 250, 70);
        BtnCambiar_contraseña.setOpaque(false);
        BtnCambiar_contraseña.setContentAreaFilled(false);
        BtnCambiar_contraseña.setBorderPainted(false);
        BtnCambiar_contraseña.setFocusPainted(false);
        add(BtnCambiar_contraseña);

        ImageIcon Fondo_CambiarPassw = new ImageIcon(getClass().getResource("/imagenes/contenedor_mi_cuenta.png"));
        Image imgModificadaCamPass = Fondo_CambiarPassw.getImage().getScaledInstance(440, 316, Image.SCALE_SMOOTH);
        Fondo_CambiarPassw = new ImageIcon(imgModificadaCamPass);
        JLabel label_Fondo_CambiarPassw = new JLabel(Fondo_CambiarPassw);
        label_Fondo_CambiarPassw.setBounds(177, 185, 440, 316);
        label_Fondo_CambiarPassw.setVisible(false);

        JButton BtnCanecelarCambio = new JButton("");
        BtnCanecelarCambio.setBounds(41, 240, 157, 37);
        BtnCanecelarCambio.setOpaque(false);
        BtnCanecelarCambio.setContentAreaFilled(false);
        BtnCanecelarCambio.setBorderPainted(false);
        BtnCanecelarCambio.setFocusPainted(false);
        label_Fondo_CambiarPassw.add(BtnCanecelarCambio);

        JButton BtnCorfirmarCambio = new JButton("");
        BtnCorfirmarCambio.setBounds(243, 240, 150, 37);
        BtnCorfirmarCambio.setOpaque(false);
        BtnCorfirmarCambio.setContentAreaFilled(false);
        BtnCorfirmarCambio.setBorderPainted(false);
        BtnCorfirmarCambio.setFocusPainted(false);
        label_Fondo_CambiarPassw.add(BtnCorfirmarCambio);

        JLabel Label_usuario = new JLabel("Contraseña Actual:");
        Label_usuario.setBounds(70, 20, 320, 40);
        label_Fondo_CambiarPassw.add(Label_usuario);

        JTextField ContraseñaAct = new JTextField("");
        ContraseñaAct.setBounds(114, 50, 180, 80);
        label_Fondo_CambiarPassw.add(ContraseñaAct);

        ImageIcon fondoContraseñaAct = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoContActEscalado = fondoContraseñaAct.getImage().getScaledInstance(230, 80, Image.SCALE_SMOOTH);
        JLabel FondoContraseñaAct = new JLabel(new ImageIcon(fondoContActEscalado));
        FondoContraseñaAct.setBounds(90, 50, 230, 80);
        label_Fondo_CambiarPassw.add(FondoContraseñaAct);

        JLabel Label_password = new JLabel("Contraseña Nueva:");
        Label_password.setBounds(80, 125, 320, 40);
        label_Fondo_CambiarPassw.add(Label_password);

        JTextField ContraseñaNew = new JTextField("");
        ContraseñaNew.setBounds(114, 150, 180, 80);
        label_Fondo_CambiarPassw.add(ContraseñaNew);

        ImageIcon fondoContraseñaNew = new ImageIcon(getClass().getResource("/imagenes/Fondo_label.png"));
        Image fondoContNewEscalado = fondoContraseñaNew.getImage().getScaledInstance(230, 80, Image.SCALE_SMOOTH);
        JLabel FondoContraseñaNew = new JLabel(new ImageIcon(fondoContNewEscalado));
        FondoContraseñaNew.setBounds(90, 150, 230, 80);
        label_Fondo_CambiarPassw.add(FondoContraseñaNew);

        //Fondo Eliminar Cuenta 
        ImageIcon Fondo_EleminarCuenta = new ImageIcon(getClass().getResource("/imagenes/contenedor_mi_cuentaeli.png"));
        Image imgModificadaEli = Fondo_EleminarCuenta.getImage().getScaledInstance(440, 316, Image.SCALE_SMOOTH);
        Fondo_EleminarCuenta = new ImageIcon(imgModificadaEli);
        JLabel label_Fondo_EleminarCuenta = new JLabel(Fondo_EleminarCuenta);
        label_Fondo_EleminarCuenta.setBounds(177, 185, 440, 316);
        label_Fondo_EleminarCuenta.setVisible(false);

        JLabel lbl_ContraseñaActELi = new JLabel("Contraseña Actual:");
        lbl_ContraseñaActELi.setBounds(80, 50, 320, 40);
        label_Fondo_EleminarCuenta.add(lbl_ContraseñaActELi);

        JTextField ContraseñaActEli = new JTextField("");
        ContraseñaActEli.setBounds(114, 90, 180, 80);
        label_Fondo_EleminarCuenta.add(ContraseñaActEli);

        JLabel FondoContraseñaActEli = new JLabel(new ImageIcon(fondoContActEscalado));
        FondoContraseñaActEli.setBounds(90, 90, 230, 80);
        label_Fondo_EleminarCuenta.add(FondoContraseñaActEli);

        JButton BtnCorfirmarEliminacion = new JButton("");
        BtnCorfirmarEliminacion.setBounds(243, 240, 160, 37);
        BtnCorfirmarEliminacion.setOpaque(false);
        BtnCorfirmarEliminacion.setContentAreaFilled(false);
        BtnCorfirmarEliminacion.setBorderPainted(false);
        BtnCorfirmarEliminacion.setFocusPainted(false);
        label_Fondo_EleminarCuenta.add(BtnCorfirmarEliminacion);

        JButton CancelarEliminacion = new JButton("");
        CancelarEliminacion.setBounds(41, 240, 157, 37);
        CancelarEliminacion.setOpaque(false);
        CancelarEliminacion.setContentAreaFilled(false);
        CancelarEliminacion.setBorderPainted(false);
        CancelarEliminacion.setFocusPainted(false);
        label_Fondo_EleminarCuenta.add(CancelarEliminacion);

        try {
            Font Fuente_ext = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/BLACC___.TTF"));
            Fuente_ext = Fuente_ext.deriveFont(35f);

            JLabel[] labelsInfo = {LblUserName, UserName, LblEntry_Date1, LblEntry_Date2, EntryDate, LblPoint, Point, LblEstado, Estado};
            for (JLabel lbl : labelsInfo) {
                lbl.setFont(Fuente_ext);
                lbl.setForeground(Color.LIGHT_GRAY);
            }

            JLabel[] labelsCambio = {Label_usuario, Label_password};
            for (JLabel lbl : labelsCambio) {
                lbl.setFont(Fuente_ext);
                lbl.setForeground(Color.LIGHT_GRAY);
            }

            JLabel[] labelsEliminar = {lbl_ContraseñaActELi};
            for (JLabel lbl : labelsEliminar) {
                lbl.setFont(Fuente_ext);
                lbl.setForeground(Color.LIGHT_GRAY);
            }

            JTextField[] fieldsCambiar = {ContraseñaAct, ContraseñaNew};
            for (JTextField txt : fieldsCambiar) {
                txt.setFont(new Font("Serif", Font.BOLD, 22));
                txt.setForeground(Color.LIGHT_GRAY);
                txt.setOpaque(false);
                txt.setBorder(null);
                txt.setHorizontalAlignment(JTextField.CENTER);
            }

            JTextField[] fieldsEliminar = {ContraseñaActEli};
            for (JTextField txt : fieldsEliminar) {
                txt.setFont(new Font("Serif", Font.BOLD, 22));
                txt.setForeground(Color.LIGHT_GRAY);
                txt.setOpaque(false);
                txt.setBorder(null);
                txt.setHorizontalAlignment(JTextField.CENTER);
            }

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        label_Fondo.add(LblUserName);
        label_Fondo.add(UserName);

        label_Fondo.add(LblEntry_Date1);
        label_Fondo.add(LblEntry_Date2);
        label_Fondo.add(EntryDate);

        label_Fondo.add(LblPoint);
        label_Fondo.add(Point);

        label_Fondo.add(LblEstado);
        label_Fondo.add(Estado);

        botonRegreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu_principal ventana = new Menu_principal();
                ventana.setVisible(true);
            }
        });
        BtnEliminar_cuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnEliminar_cuenta.setVisible(false);
                BtnCambiar_contraseña.setVisible(false);
                label_Fondo_EleminarCuenta.setVisible(true);
                label_FondoCont.setVisible(true);

            }
        });

        BtnCambiar_contraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_Fondo_CambiarPassw.setVisible(true);
                BtnEliminar_cuenta.setVisible(false);
                BtnCambiar_contraseña.setVisible(false);
                label_FondoCont.setVisible(true);

            }
        });

        BtnCanecelarCambio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_Fondo_CambiarPassw.setVisible(false);
                ContraseñaAct.setText("");
                ContraseñaNew.setText("");
                BtnEliminar_cuenta.setVisible(true);
                BtnCambiar_contraseña.setVisible(true);
                label_FondoCont.setVisible(false);

            }
        });
        CancelarEliminacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_Fondo_EleminarCuenta.setVisible(false);
                ContraseñaActEli.setText("");
                BtnEliminar_cuenta.setVisible(true);
                BtnCambiar_contraseña.setVisible(true);
                label_FondoCont.setVisible(false);

            }
        });

        BtnCorfirmarCambio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ContraseñaAct.getText().equals(LOG_IN.JugadorActual.getPassword())) {
                    if (ContraseñaNew.getText().equals(LOG_IN.JugadorActual.getPassword())) {
                        JOptionPane.showMessageDialog(null, "La nueva contraseña no puede ser igual a la actual");
                    } else if (Funciones_Verificar.ValidarPassword(ContraseñaNew.getText())) {
                        LOG_IN.JugadorActual.setPassword(ContraseñaNew.getText());
                        label_Fondo_CambiarPassw.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
                        ContraseñaAct.setText("");
                        ContraseñaNew.setText("");
                        BtnEliminar_cuenta.setVisible(true);
                        BtnCambiar_contraseña.setVisible(true);
                        label_FondoCont.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña invalida. Debe tener 5 caracteres, al menos un simbolo especial y una letra mayuscula");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta");
                }

            }
        });

        BtnCorfirmarEliminacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ContraseñaActEli.getText().equals(LOG_IN.JugadorActual.getPassword())) {
                    LOG_IN.JugadorActual.setActivo(false);
                    JOptionPane.showMessageDialog(null, "Jugador eliminado");
                    LOG_IN.JugadorActual = null;
                    dispose();
                    new Menu_inicio().setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña actual incorrecta, no se pudo eliminar al jugador");
                }

            }
        });
        add(label_Fondo_CambiarPassw);
        add(label_Fondo_EleminarCuenta);
        add(label_FondoCont);
        add(label_Fondo);

    }

}
