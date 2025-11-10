
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Interfaces.Menu_inicio;
import Interfaces.Mi_Cuenta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Cantarero
 */
public class Reportes extends JFrame {

    public ArrayList<PLAYER> listPlayersPoint;
    private JPanel panelTabla;
    private Font FuenteTabla;

    public Reportes() {
        listPlayersPoint = OrdenRankin(PLAYER.Players);

        setTitle("Menu principal");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo principal
        ImageIcon FondoLogIn = new ImageIcon(getClass().getResource("/imagenes/menu_3.png"));
        Image imgModificada = FondoLogIn.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label_Fondo = new JLabel(new ImageIcon(imgModificada));
        label_Fondo.setBounds(0, 0, 800, 800);
        label_Fondo.setLayout(null);

        // Botón regreso principal
        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/Boton_regreso.png"));
        Image imgBoton = iconoBoton.getImage().getScaledInstance(69, 35, Image.SCALE_SMOOTH);
        JButton botonRegreso = new JButton(new ImageIcon(imgBoton));
        botonRegreso.setBounds(20, 20, 69, 35);
        botonRegreso.setBorderPainted(false);
        botonRegreso.setContentAreaFilled(false);
        botonRegreso.setFocusPainted(false);
        label_Fondo.add(botonRegreso);

        // Botones Ranking y Logs
        JButton BtnRankin = new JButton();
        BtnRankin.setBounds(240, 260, 323, 90);
        BtnRankin.setOpaque(false);
        BtnRankin.setContentAreaFilled(false);
        BtnRankin.setBorderPainted(false);
        BtnRankin.setFocusPainted(false);
        label_Fondo.add(BtnRankin);

        JButton BtnLogs = new JButton("");
        BtnLogs.setBounds(240, 383, 325, 90);
        BtnLogs.setOpaque(false);
        BtnLogs.setContentAreaFilled(false);
        BtnLogs.setBorderPainted(false);
        BtnLogs.setFocusPainted(false);
        label_Fondo.add(BtnLogs);

        // Ranking
        ImageIcon FondoLogInExt = new ImageIcon(getClass().getResource("/imagenes/RANKINS.png"));
        Image imgModificadaExt = FondoLogInExt.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label_Fondo_ext = new JLabel(new ImageIcon(imgModificadaExt));
        label_Fondo_ext.setBounds(0, 0, 800, 800);
        label_Fondo_ext.setLayout(null);
        label_Fondo_ext.setVisible(false);

        JButton botonRegresoExt = new JButton(new ImageIcon(imgBoton));
        botonRegresoExt.setBounds(20, 20, 69, 35);
        botonRegresoExt.setBorderPainted(false);
        botonRegresoExt.setContentAreaFilled(false);
        botonRegresoExt.setFocusPainted(false);
        label_Fondo_ext.add(botonRegresoExt);

        // Columnas de la tabla
        String[] columnas = {"Posicion", "Nombre", "Puntos"};

        panelTabla = new JPanel(new GridLayout(0, columnas.length, 10, 5));
        panelTabla.setOpaque(false);

        // Logs
        ImageIcon FondoLogs = new ImageIcon(getClass().getResource("/imagenes/RANKINS.png"));
        Image imgLogs = FondoLogs.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel label_Fondo_Logs = new JLabel(new ImageIcon(imgLogs));
        label_Fondo_Logs.setBounds(0, 0, 800, 800);
        label_Fondo_Logs.setLayout(null);
        label_Fondo_Logs.setVisible(false);

        JButton botonRegresoLogs = new JButton(new ImageIcon(imgBoton));
        botonRegresoLogs.setBounds(20, 20, 69, 35);
        botonRegresoLogs.setBorderPainted(false);
        botonRegresoLogs.setContentAreaFilled(false);
        botonRegresoLogs.setFocusPainted(false);
        label_Fondo_Logs.add(botonRegresoLogs);

        JPanel panelTablaLogs = new JPanel(new GridLayout(0, 1, 10, 10));
        panelTablaLogs.setOpaque(false);

        try {
            FuenteTabla = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/BLACC___.TTF"));

            JLabel LblTitulo = new JLabel("RANKING DE JUGADORES");
            LblTitulo.setBounds(180, 150, 500, 100);
            LblTitulo.setForeground(Color.lightGray);
            LblTitulo.setFont(FuenteTabla.deriveFont(35f));
            label_Fondo_ext.add(LblTitulo);

            JLabel lblTituloLogs = new JLabel("LOGS DE MIS ULTIMOS JUEGOS");
            lblTituloLogs.setBounds(170, 150, 600, 100);
            lblTituloLogs.setForeground(Color.lightGray);
            lblTituloLogs.setFont(FuenteTabla.deriveFont(30f));
            label_Fondo_Logs.add(lblTituloLogs);

            ArrayList<String> logsOrdenados = new ArrayList<>(LOG_IN.JugadorActual.getLOGSPLAYER());
            logsOrdenados = ordenarLogs(logsOrdenados, 0, logsOrdenados.size() - 1);

            for (String log : logsOrdenados) {
                JLabel lblLog = new JLabel(log, SwingConstants.CENTER);
                lblLog.setFont(FuenteTabla.deriveFont(15f));
                lblLog.setForeground(Color.lightGray);
                lblLog.setOpaque(false);
                panelTablaLogs.add(lblLog);
            }

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Scroll Ranking
        JScrollPane scroll = new JScrollPane(panelTabla);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setBounds(161, 262, 473, 405);

        // Scroll Logs
        JScrollPane scrollLogs = new JScrollPane(panelTablaLogs);
        scrollLogs.setOpaque(false);
        scrollLogs.getViewport().setOpaque(false);
        scrollLogs.setBorder(null);
        scrollLogs.setBounds(161, 262, 473, 405);

        label_Fondo_ext.add(scroll, BorderLayout.CENTER);
        label_Fondo_Logs.add(scrollLogs);

        BtnRankin.addActionListener(e -> {
            actualizarTablaRanking(columnas);
            label_Fondo_ext.setVisible(true);
        });

        BtnLogs.addActionListener(e -> label_Fondo_Logs.setVisible(true));

        botonRegreso.addActionListener(e -> {
            dispose();
            new Menu_principal().setVisible(true);
        });

        botonRegresoExt.addActionListener(e -> label_Fondo_ext.setVisible(false));

        botonRegresoLogs.addActionListener(e -> label_Fondo_Logs.setVisible(false));

        add(label_Fondo_ext);
        add(label_Fondo_Logs);
        add(label_Fondo);
    }

    private void actualizarTablaRanking(String[] columnas) {
        panelTabla.removeAll();

        // Encabezados
        for (String col : columnas) {
            JLabel lblHeader = new JLabel(col, SwingConstants.CENTER);
            lblHeader.setFont(FuenteTabla.deriveFont(30f));
            lblHeader.setForeground(Color.lightGray);
            lblHeader.setOpaque(false);
            panelTabla.add(lblHeader);
        }

        int contador = 1;
        for (PLAYER fila : OrdenRankin(PLAYER.Players)) {
            JLabel lblPos = new JLabel(String.valueOf(contador), SwingConstants.CENTER);
            JLabel lblName = new JLabel(fila.getName(), SwingConstants.CENTER);
            JLabel lblPoints = new JLabel(String.valueOf(fila.getPoint()), SwingConstants.CENTER);

            for (JLabel lbl : new JLabel[]{lblPos, lblName, lblPoints}) {
                lbl.setVerticalAlignment(SwingConstants.CENTER);
                lbl.setFont(FuenteTabla.deriveFont(23f));
                lbl.setForeground(Color.lightGray);
                lbl.setOpaque(false);
                panelTabla.add(lbl);
            }
            contador++;
        }

        panelTabla.revalidate();
        panelTabla.repaint();
    }

    public static ArrayList<PLAYER> OrdenRankin(ArrayList<PLAYER> lista) {
        ArrayList<PLAYER> activos = new ArrayList<>();
        for (PLAYER p : lista) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }

        ArrayList<PLAYER> copia = new ArrayList<>(activos);
        ordenarRanking(copia, 0, copia.size() - 1);
        return copia;
    }
    //Fundiones Recursivas
    private static void ordenarRanking(ArrayList<PLAYER> lista, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        for (int i = inicio; i < fin; i++) {
            PLAYER actual = lista.get(i);
            PLAYER siguiente = lista.get(i + 1);
            if (actual.getPoint() < siguiente.getPoint()) {
                lista.set(i, siguiente);
                lista.set(i + 1, actual);
            }
        }

        ordenarRanking(lista, inicio, fin - 1);
    }

    private static ArrayList<String> ordenarLogs(ArrayList<String> logs, int inicio, int fin) {
        if (inicio >= fin) {
            return logs;
        }

        String temp = logs.get(inicio);
        logs.set(inicio, logs.get(fin));
        logs.set(fin, temp);

        return ordenarLogs(logs, inicio + 1, fin - 1);
    }

}
