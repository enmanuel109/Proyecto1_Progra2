package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Cantarero
 */
public final class Game extends JFrame {

    private static final int FILAS = 6;
    private static final int COLUMNAS = 6;

    //Variables de la Ruleta 
    private JLabel labelRuleta1;
    private JLabel labelRuleta2;
    private Image rueda1;
    private Image rueda2;
    private JLabel labelRuletaTemp;
    private Image ruedaTemp;
    private JPanel PanelRuleta;

    private double angulo = 0;
    private Timer timer;
    private Timer timerGiroContinuo;
    private int pasoActual;
    private final int[] Angulos_Objevios = {0, 60, 120, 180, 240, 300};

    private boolean Turno = true;
    private boolean primer1 = true;

    private JButton botonGirarDesdeFondo;
    private boolean isGirandoContinuo = false;

    // Variables del Tablero y Personajes
    private JLabel[][] ListaTablero = new JLabel[FILAS][COLUMNAS];
    private Personaje[][] PersonajesTablero = new Personaje[FILAS][COLUMNAS];

    private JLabel[][] EliminadosUser1 = new JLabel[6][1];
    private JLabel[][] EliminadosUser2 = new JLabel[6][1];

    private static final Color COLOR_BORDE_RULETA = Color.CYAN;

    private JLabel celdaSeleccionada = null;
    private int filaSeleccionada, colSeleccionada;

//NUEVAS VARIABLES PARA TableroINFO
    private JLabel LabelPersonActual;
    private JLabel LblVidaValor;
    private JLabel LblDañoValor;
    private JLabel LblEscudoValor;

    private boolean[] Personajes = {false, false, false, false, false, false, false, false, false, false, false, false};

    private int PerosonajeActual;

    private JPanel TableroINFO;

    private JPanel PanelGanador;
    JLabel label1;
    JLabel label2;
    JLabel label3;

    private JLabel labelFondoinfo;
    private JLabel label_Fondo;
    private JPanel Tablero;

    private final Color COLOR_ATAQUE = Color.RED;
    private boolean ataqueActivo = false;
    private Personaje personajeSeleccionadoParaAtaque;
    private Personaje personajeAtaque;
    private Personaje personajeAtacante;
    private boolean modoAtaque = false;
    private boolean modoAtaqueP1 = false;
    private boolean MuerteInvocada = false;
    final boolean[] seleccionado = {false};
    final boolean[] seleccionadoP1 = {false};
    final boolean[] seleccionadoP2 = {false};
    final boolean[] seleccionadoP3 = {false};
    boolean AtaquesZombies = false;
    private boolean AtacarConZombie;

    JButton BotonPN;
    JButton BotonP1;
    JButton BotonP2;
    JButton BotonP2Atac;
    JButton botonGirar;
    JLabel LabelInfoAtaques;

    private boolean HombreLoboMovi = true;
    private Personaje HombreLoboAc;

    private int PersonajesOscuros = 6;
    private int PersonajesClaros = 6;
    private int turnosExtra = 0;
    private boolean turnoModificado = false;
    private Personaje personajeSeleccionadoRuleta;
    private int CantVampiro = 2;
    private int CantMuerte = 2;
    private int CantHombreLobo = 2;
    private int CantVampiroOsc = 2;
    private int CantMuerteOsc = 2;
    private int CantHombreLoboOsc = 2;

    public Game() {
        setTitle("Juego");
        setSize(800, 825);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        inicializarComponentesInterfaz();
        inicializarTablero();
        add(PanelGanador);
        add(PanelRuleta);
        add(TableroINFO);
        add(labelFondoinfo);
        add(label_Fondo);
    }

    private void inicializarComponentesInterfaz() {

        //Fondo Principal
        ImageIcon Fondo = new ImageIcon(getClass().getResource("/imagenes/Tablero11.png"));
        Image imgModificada = Fondo.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        Fondo = new ImageIcon(imgModificada);
        label_Fondo = new JLabel(Fondo);
        label_Fondo.setBounds(00, 00, 800, 800);

        //Panel de Informacion
        ImageIcon Fondoinfo = new ImageIcon(getClass().getResource("/imagenes/INFORMACION.png"));
        Image imgModificadainfo = Fondoinfo.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        Fondoinfo = new ImageIcon(imgModificadainfo);
        labelFondoinfo = new JLabel(Fondoinfo);
        labelFondoinfo.setBounds(50, 50, 700, 700);
        labelFondoinfo.setVisible(true);

        ImageIcon iconoEnter = new ImageIcon(getClass().getResource("/imagenes/buton.png"));
        Image imgBotonenter = iconoEnter.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        JButton Botonsig = new JButton(new ImageIcon(imgBotonenter));
        Botonsig.setBounds(275, 595, 150, 50);
        Botonsig.setText("Empezar Juego");
        Botonsig.setFont(new Font("Serif", Font.BOLD, 17));
        Botonsig.setForeground(Color.WHITE);
        Botonsig.setHorizontalTextPosition(JButton.CENTER);
        Botonsig.setVerticalTextPosition(JButton.CENTER);
        Botonsig.setOpaque(false);
        labelFondoinfo.add(Botonsig);

        //Labels de Elimindados y Rendirse 
        JLabel LabelEliUs1 = new JLabel("Enemigos Eliminados Claros");
        LabelEliUs1.setBounds(30, 170, 150, 80);
        LabelEliUs1.setFont(new Font("Serif", Font.BOLD, 9));
        LabelEliUs1.setForeground(Color.WHITE);
        label_Fondo.add(LabelEliUs1);

        ImageIcon iconoInf = new ImageIcon(getClass().getResource("/imagenes/into.png"));
        Image imgBotonenInf = iconoInf.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JButton BotonInf = new JButton(new ImageIcon(imgBotonenInf));
        BotonInf.setBounds(530, 130, 60, 60);
        BotonInf.setOpaque(false);
        BotonInf.setContentAreaFilled(false);
        BotonInf.setBorderPainted(false);
        label_Fondo.add(BotonInf);

        JLabel Us1 = new JLabel(LOG_IN.JugadorActual.getName());
        Us1.setBounds(30, 182, 165, 80);
        Us1.setFont(new Font("Serif", Font.BOLD, 13));
        Us1.setForeground(Color.WHITE);
        label_Fondo.add(Us1);

        JButton botonRendirse = new JButton();
        botonRendirse.setBounds(610, 135, 160, 40);
        botonRendirse.setText("Rendirse");
        botonRendirse.setForeground(Color.WHITE);
        botonRendirse.setFont(new Font("Arial", Font.BOLD, 16));
        botonRendirse.setOpaque(false);
        botonRendirse.setContentAreaFilled(false);
        botonRendirse.setBorderPainted(false);
        label_Fondo.add(botonRendirse);

        LabelInfoAtaques = new JLabel("");
        LabelInfoAtaques.setBounds(20, 130, 485, 50);
        LabelInfoAtaques.setFont(new Font("Serif", Font.BOLD, 23));
        LabelInfoAtaques.setForeground(Color.WHITE);
        LabelInfoAtaques.setOpaque(false);
        LabelInfoAtaques.setHorizontalAlignment(SwingConstants.CENTER);

        label_Fondo.add(LabelInfoAtaques);

        JLabel LabelRendirse = new JLabel("Enemigos Eliminados Oscuros");
        LabelRendirse.setBounds(645, 165, 200, 80);
        LabelRendirse.setFont(new Font("Serif", Font.BOLD, 9));
        LabelRendirse.setForeground(Color.WHITE);
        label_Fondo.add(LabelRendirse);

        JLabel Us2 = new JLabel(Menu_principal.Rival.getName());
        Us2.setBounds(645, 182, 205, 80);
        Us2.setFont(new Font("Serif", Font.BOLD, 13));
        Us2.setForeground(Color.WHITE);
        label_Fondo.add(Us2);

        //Panel Ruleta 
        PanelRuleta = new JPanel(null);
        PanelRuleta.setOpaque(false);
        PanelRuleta.setBounds(0, 0, 800, 800);
        PanelRuleta.setVisible(false);

        ImageIcon iconoBoton = new ImageIcon(getClass().getResource("/imagenes/buton.png"));
        Image imgBoton = iconoBoton.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        botonGirar = new JButton(new ImageIcon(imgBoton));
        botonGirar.setBounds(335, 540, 150, 50);
        botonGirar.setText("Detener Giro");
        botonGirar.setFont(new Font("Serif", Font.BOLD, 19));
        botonGirar.setForeground(Color.WHITE);
        botonGirar.setHorizontalTextPosition(JButton.CENTER);
        botonGirar.setVerticalTextPosition(JButton.CENTER);
        botonGirar.setOpaque(false);
        PanelRuleta.add(botonGirar);

        ImageIcon fondoRuletsa = new ImageIcon(getClass().getResource("/imagenes/eleccion_rueda1.png"));
        Image imgEscaladaa = fondoRuletsa.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
        JLabel labelRuletaa = new JLabel(new ImageIcon(imgEscaladaa));
        labelRuletaa.setBounds(185, 70, 450, 450);
        PanelRuleta.add(labelRuletaa);

        ImageIcon fondoRuleta1 = new ImageIcon(getClass().getResource("/imagenes/rueda1.png"));
        rueda1 = fondoRuleta1.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        labelRuleta1 = new JLabel(new ImageIcon(rueda1));
        labelRuleta1.setBounds(205, 85, 400, 400);
        PanelRuleta.add(labelRuleta1);
        labelRuleta1.setVisible(true);

        ImageIcon fondoRuleta2 = new ImageIcon(getClass().getResource("/imagenes/rueda2.png"));
        rueda2 = fondoRuleta2.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        labelRuleta2 = new JLabel(new ImageIcon(rueda2));
        labelRuleta2.setBounds(205, 85, 400, 400);
        PanelRuleta.add(labelRuleta2);
        labelRuleta2.setVisible(false);

        // Tablero de Informacion del Personaje Actual 
        TableroINFO = new JPanel();
        TableroINFO.setBounds(170, 27, 455, 86);
        TableroINFO.setOpaque(false);
        TableroINFO.setLayout(null);
        TableroINFO.setVisible(false);

        this.LabelPersonActual = new JLabel();
        this.LabelPersonActual.setBounds(5, 5, 90, 120);
        TableroINFO.add(this.LabelPersonActual);

        JLabel LblVida = new JLabel("Vida:");
        LblVida.setBounds(105, 0, 80, 40);
        LblVida.setFont(new Font("Serif", Font.BOLD, 18));
        LblVida.setForeground(Color.WHITE);
        TableroINFO.add(LblVida);

        JLabel LblDaño = new JLabel("Daño:");
        LblDaño.setBounds(105, 25, 80, 40);
        LblDaño.setFont(new Font("Serif", Font.BOLD, 18));
        LblDaño.setForeground(Color.WHITE);
        TableroINFO.add(LblDaño);

        JLabel LblEsc = new JLabel("Escudo:");
        LblEsc.setBounds(105, 50, 80, 40);
        LblEsc.setFont(new Font("Serif", Font.BOLD, 18));
        LblEsc.setForeground(Color.WHITE);
        TableroINFO.add(LblEsc);

        LblVidaValor = new JLabel("0");
        LblVidaValor.setBounds(180, 0, 80, 40);
        LblVidaValor.setFont(new Font("Serif", Font.BOLD, 18));
        LblVidaValor.setForeground(Color.YELLOW);
        TableroINFO.add(LblVidaValor);

        LblDañoValor = new JLabel("0");
        LblDañoValor.setBounds(180, 25, 80, 40);
        LblDañoValor.setFont(new Font("Serif", Font.BOLD, 18));
        LblDañoValor.setForeground(Color.RED);
        TableroINFO.add(LblDañoValor);

        LblEscudoValor = new JLabel("0");
        LblEscudoValor.setBounds(180, 50, 80, 40);
        LblEscudoValor.setFont(new Font("Serif", Font.BOLD, 18));
        LblEscudoValor.setForeground(Color.CYAN);
        TableroINFO.add(LblEscudoValor);

        ImageIcon iconoBtnPdr = new ImageIcon(getClass().getResource("/imagenes/butoncambiado.png"));
        Image imgBotonPdrs = iconoBtnPdr.getImage().getScaledInstance(180, 20, Image.SCALE_SMOOTH);
        BotonPN = new JButton(new ImageIcon(imgBotonPdrs));
        BotonPN.setBounds(250, 3, 180, 20);
        BotonPN.setText("Ataque Normal");
        BotonPN.setForeground(Color.WHITE);
        BotonPN.setFont(new Font("Arial", Font.BOLD, 18));

        BotonPN.setHorizontalTextPosition(JButton.CENTER);
        BotonPN.setVerticalTextPosition(JButton.CENTER);
        BotonPN.setOpaque(true);
        TableroINFO.add(BotonPN);

        ImageIcon iconoBtnP1 = new ImageIcon(getClass().getResource("/imagenes/butoncambiado.png"));
        Image imgBotonP1 = iconoBtnP1.getImage().getScaledInstance(210, 20, Image.SCALE_SMOOTH);
        BotonP1 = new JButton(new ImageIcon(imgBotonP1));
        BotonP1.setBounds(223, 31, 210, 20);
        BotonP1.setText("Ataque Especial");
        BotonP1.setForeground(Color.WHITE);
        BotonP1.setFont(new Font("Arial", Font.BOLD, 15));

        BotonP1.setHorizontalTextPosition(JButton.CENTER);
        BotonP1.setVerticalTextPosition(JButton.CENTER);
        BotonP1.setOpaque(true);
        TableroINFO.add(BotonP1);

        ImageIcon iconoBtnP2 = new ImageIcon(getClass().getResource("/imagenes/butoncambiado.png"));
        Image imgBotonP2 = iconoBtnP2.getImage().getScaledInstance(120, 20, Image.SCALE_SMOOTH);
        BotonP2 = new JButton(new ImageIcon(imgBotonP2));
        BotonP2.setBounds(212, 63, 120, 20);
        BotonP2.setText("INVOCAR ZOMBIE");
        BotonP2.setForeground(Color.WHITE);
        BotonP2.setFont(new Font("Arial", Font.BOLD, 9));

        BotonP2.setHorizontalTextPosition(JButton.CENTER);
        BotonP2.setVerticalTextPosition(JButton.CENTER);
        BotonP2.setVisible(false);
        TableroINFO.add(BotonP2);

        ImageIcon iconoBtnP2Atak = new ImageIcon(getClass().getResource("/imagenes/butoncambiado.png"));
        Image imgBotonP2Atak = iconoBtnP2Atak.getImage().getScaledInstance(120, 20, Image.SCALE_SMOOTH);
        BotonP2Atac = new JButton(new ImageIcon(imgBotonP2Atak));
        BotonP2Atac.setBounds(339, 63, 120, 20);
        BotonP2Atac.setText("Atacar con Zombie");
        BotonP2Atac.setForeground(Color.WHITE);
        BotonP2Atac.setFont(new Font("Arial", Font.BOLD, 9));

        BotonP2Atac.setHorizontalTextPosition(JButton.CENTER);
        BotonP2Atac.setVerticalTextPosition(JButton.CENTER);
        BotonP2Atac.setVisible(false);
        TableroINFO.add(BotonP2Atac);

        //PanelGanador
        PanelGanador = new JPanel();
        PanelGanador.setLayout(null);
        PanelGanador.setSize(800, 800);
        PanelGanador.setOpaque(false);
        PanelGanador.setVisible(false);
        PanelGanador.setEnabled(false);
        PanelGanador.setFocusable(false);
        PanelGanador.requestFocus();

        ImageIcon FondoWin = new ImageIcon(getClass().getResource("/imagenes/fondorojo.png"));
        Image imgWIN = FondoWin.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH);
        JLabel label_Ganador = new JLabel(new ImageIcon(imgWIN));
        label_Ganador.setBounds(50, 20, 700, 700);
        label_Ganador.setLayout(null);

        label1 = new JLabel("Ganador:");
        label2 = new JLabel("Perdedor");
        label3 = new JLabel("Fin de La Partida");
        Font fuente = new Font("Arial", Font.BOLD, 24);
        Color colorTexto = Color.WHITE;

        label1.setFont(fuente);
        label1.setForeground(colorTexto);
        label1.setBounds(100, 100, 500, 50);

        label2.setFont(fuente);
        label2.setForeground(colorTexto);
        label2.setBounds(100, 160, 500, 50);

        label3.setFont(fuente);
        label3.setForeground(colorTexto);
        label3.setBounds(100, 220, 500, 50);

        label_Ganador.add(label1);
        label_Ganador.add(label2);
        label_Ganador.add(label3);

        ImageIcon ButonWin = new ImageIcon(getClass().getResource("/imagenes/butoncambiado.png"));
        Image Buton1WIN = ButonWin.getImage().getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        JButton botonRegreso = new JButton(new ImageIcon(Buton1WIN));
        botonRegreso.setBounds(210, 550, 300, 50);
        botonRegreso.setBorderPainted(false);
        botonRegreso.setContentAreaFilled(false);
        botonRegreso.setFocusPainted(false);
        botonRegreso.setText("Regresar al menu principal");
        botonRegreso.setForeground(Color.WHITE);
        botonRegreso.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegreso.setHorizontalTextPosition(SwingConstants.CENTER);
        label_Ganador.add(botonRegreso);
        PanelGanador.add(label_Ganador);

        Botonsig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (primer1) {
                    if (timer != null && timer.isRunning()) {
                        return;
                    }

                    if (!isGirandoContinuo) {
                        PanelRuleta.setVisible(true);
                        PanelRuleta.setEnabled(true);

                        if (Turno) {
                            labelRuletaTemp = labelRuleta1;
                            ruedaTemp = rueda1;
                            labelRuleta1.setVisible(true);
                            labelRuleta2.setVisible(false);
                        } else {
                            labelRuletaTemp = labelRuleta2;
                            ruedaTemp = rueda2;
                            labelRuleta2.setVisible(true);
                            labelRuleta1.setVisible(false);
                        }
                        iniciarGiroContinuo();
                        isGirandoContinuo = true;
                    } else {
                        PanelRuleta.setEnabled(true);
                        Botonsig.setEnabled(false);
                        detenerGiro();
                        isGirandoContinuo = false;

                    }
                    primer1 = false;
                }
                labelFondoinfo.setVisible(false);
                TableroINFO.setVisible(true);

            }
        });

        BotonInf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Botonsig.setText("Seguir Jugando");
                labelFondoinfo.setVisible(true);
                TableroINFO.setVisible(false);

            }
        });

        botonGirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    return;
                }

                if (!isGirandoContinuo) {
                    PanelRuleta.setVisible(true);
                    PanelRuleta.setEnabled(true);

                    if (Turno) {
                        labelRuletaTemp = labelRuleta1;
                        ruedaTemp = rueda1;
                        labelRuleta1.setVisible(true);
                        labelRuleta2.setVisible(false);
                    } else {
                        labelRuletaTemp = labelRuleta2;
                        ruedaTemp = rueda2;
                        labelRuleta2.setVisible(true);
                        labelRuleta1.setVisible(false);
                    }
                    iniciarGiroContinuo();
                    isGirandoContinuo = true;
                } else {
                    botonGirar.setEnabled(false);
                    PanelRuleta.setOpaque(false);
                    PanelRuleta.setVisible(true);
                    PanelRuleta.setEnabled(true);
                    detenerGiro();

                    isGirandoContinuo = false;

                }
            }
        });

        botonRendirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres rendirte?");
                if (respuesta == JOptionPane.YES_OPTION) {
                    if (Turno) {
                        PanelGanador.setVisible(true);
                        PanelGanador.setEnabled(true);
                        PanelGanador.setFocusable(true);
                        label1.setText("Ganador:" + Menu_principal.Rival.getName() + " Gano 3 puntos");
                        label2.setText("Ya que se ridio el Jugador :" + LOG_IN.JugadorActual.getName());
                        label3.setText("Fin de La Partida");
                        Menu_principal.Rival.setPoint(Menu_principal.Rival.getPoint() + 3);
                        Menu_principal.Rival.LOGSPLAYER.add("El Jugador: " + Menu_principal.Rival.getName() + " Gano 3 puntos; Por que se rindio :" + LOG_IN.JugadorActual.getName());
                        LOG_IN.JugadorActual.LOGSPLAYER.add("El Jugador: " + Menu_principal.Rival.getName() + " Gano 3 puntos; Por que se rindio :" + LOG_IN.JugadorActual.getName());
                    } else {

                        PanelGanador.setVisible(true);
                        PanelGanador.setEnabled(true);
                        PanelGanador.setFocusable(true);
                        label1.setText("Ganador:" + LOG_IN.JugadorActual.getName() + " Gano 3 puntos");
                        label2.setText("Ya que se ridio el Jugador :" + Menu_principal.Rival.getName());
                        label3.setText("Fin de La Partida");
                        LOG_IN.JugadorActual.setPoint(LOG_IN.JugadorActual.getPoint() + 3);
                        LOG_IN.JugadorActual.LOGSPLAYER.add("El Jugador: " + LOG_IN.JugadorActual.getName() + " Gano 3 puntos; Por que se rindio :" + Menu_principal.Rival.getName());
                        Menu_principal.Rival.LOGSPLAYER.add("El Jugador: " + LOG_IN.JugadorActual.getName() + " Gano 3 puntos; Por que se rindio :" + Menu_principal.Rival.getName());
                    }
                }

            }
        });

        botonRegreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu_principal ventana = new Menu_principal();
                ventana.setVisible(true);
            }
        });

        BotonPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionado[0] = !seleccionado[0];
                if (seleccionado[0]) {
                    modoAtaque = true;
                    BotonPN.setText("Mover Pieza");
                } else {
                    modoAtaque = false;
                    BotonPN.setText("Ataque Normal");

                }
            }
        });

        BotonP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionadoP1[0] = !seleccionadoP1[0];

                if (seleccionadoP1[0]) {
                    if (personajeSeleccionadoParaAtaque instanceof Vampiro) {
                        modoAtaqueP1 = true;
                        limpiarResaltados();
                        LabelInfoAtaques.setText("");
                        BotonP1.setText("Mover Pieza");
                    } else if (personajeSeleccionadoParaAtaque instanceof Muerte) {
                        modoAtaqueP1 = true;
                        limpiarResaltados();
                        LabelInfoAtaques.setText("");
                        BotonP1.setText("Mover Pieza");
                    } else if (personajeSeleccionadoParaAtaque instanceof HombreLobo) {
                        modoAtaqueP1 = true;
                        limpiarResaltados();
                        LabelInfoAtaques.setText("");
                        BotonP1.setText("Mover Pieza");
                    }
                } else {

                    modoAtaqueP1 = false;
                    BotonP1.setText("Ataque Especial");
                }

            }
        });
        BotonP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionadoP2[0] = !seleccionadoP2[0];

                if (seleccionadoP2[0]) {
                    MuerteInvocada = true;
                    BotonP1.setText("Seleccione Casilla");
                } else {

                    MuerteInvocada = false;
                    BotonP1.setText("INVOCAR MUERTE");
                }

            }
        });
        BotonP2Atac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionadoP3[0] = !seleccionadoP3[0];

                if (seleccionadoP3[0]) {
                    AtacarConZombie = true;
                    BotonP2Atac.setText("Selecciona el Zombie");
                } else {
                    AtacarConZombie = false;
                    BotonP2Atac.setText("Atacar con zombie");
                }

            }
        });
    }

    private final void inicializarTablero() {

        Tablero = new JPanel();
        Tablero.setBounds(170, 225, 455, 510);
        Tablero.setLayout(new GridLayout(FILAS, COLUMNAS, 2, 2));
        Tablero.setOpaque(false);
        label_Fondo.add(Tablero);

        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JLabel celda = new JLabel("");
                celda.setOpaque(false);
                celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                celda.setHorizontalAlignment(JLabel.CENTER);
                celda.setVerticalAlignment(JLabel.CENTER);

                ImageIcon ImgPer = null;
                Personaje nuevoPersonaje = null;

                if (fila == 0) {
                    boolean esOscuro = true;
                    switch (col) {
                        case 0:
                        case 5:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/Lobooscuro.png"));
                            nuevoPersonaje = new HombreLobo(5, 5, 2, esOscuro);
                            break;
                        case 1:
                        case 4:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/Vampiro_oscuro.png"));
                            nuevoPersonaje = new Vampiro(4, 3, 5, esOscuro);
                            break;
                        case 2:
                        case 3:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/Muerte_oscuro.png"));
                            nuevoPersonaje = new Muerte(3, 4, 1, esOscuro);
                            break;
                    }
                } else if (fila == 5) {
                    boolean esOscuro = false;
                    switch (col) {
                        case 0:
                        case 5:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/lobo.png"));
                            nuevoPersonaje = new HombreLobo(5, 5, 2, esOscuro);
                            break;
                        case 1:
                        case 4:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/vampiro.png"));
                            nuevoPersonaje = new Vampiro(4, 3, 5, esOscuro);
                            break;
                        case 2:
                        case 3:
                            ImgPer = new ImageIcon(getClass().getResource("/imagenes/muerte.png"));
                            nuevoPersonaje = new Muerte(3, 4, 1, esOscuro);
                            break;
                    }
                }

                if (ImgPer != null) {
                    Image img = ImgPer.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
                    celda.setIcon(new ImageIcon(img));
                }

                PersonajesTablero[fila][col] = nuevoPersonaje;

                final int f = fila;
                final int c = col;

                celda.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        Personaje perClick = PersonajesTablero[f][c];
                        personajeSeleccionadoParaAtaque = perClick;
                        boolean esTurnoValido = false;
                        boolean esPiezaHabilitada = false;

                        if (AtaquesZombies && personajeSeleccionadoParaAtaque instanceof Muerte) {
                            BotonP2Atac.setVisible(true);
                        }
                        // Caso 1: Clic en una celda OCUPADA
                        if (modoAtaque) {
                            // Si clic en tu propia pieza -> seleccionar atacante
                            if (perClick != null && perClick.esOscuro() == Turno) {
                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;

                                if (BuscarEnemigos(f, c)) {
                                    limpiarResaltados();
                                    // Si es Vampiro en modo especial, borde amarillo
                                    celda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

                                    resaltarPersonajesAtacar(f, c);

                                    String imagenRuta = obtenerRutaImagen(perClick);
                                    actualizarInfoPersonaje(perClick, imagenRuta);
                                } else {
                                    LabelInfoAtaques.setText("NO HAY ENEMIGOS ALREDEDOR");
                                }

                            } // Si clic en enemigo -> atacar
                            else if (celdaSeleccionada != null && perClick != null && perClick.esOscuro() != Turno) {
                                intentarAtacar(f, c);  // Ataque normal
                                iniciarGiroContinuo();
                                isGirandoContinuo = true;
                            }
                        } else if (modoAtaqueP1) {
                            if (perClick != null && perClick.esOscuro() == Turno) {
                                esTurnoValido = (Turno && perClick.esOscuro()) || (!Turno && !perClick.esOscuro());
                                esPiezaHabilitada = esMovimientoHabilitado(perClick);

                                if (esTurnoValido && esPiezaHabilitada) {
                                    if (celda == celdaSeleccionada) {
                                        celdaSeleccionada = null;
                                        limpiarResaltados();
                                        actualizarInfoPersonaje(null, null);
                                        return;
                                    }
                                    celdaSeleccionada = celda;
                                    filaSeleccionada = f;
                                    colSeleccionada = c;
                                    if (personajeSeleccionadoParaAtaque instanceof Vampiro) {
                                        if (BuscarEnemigos(f, c)) {
                                            limpiarResaltados();
                                            celda.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
                                            resaltarPersonajesAtacar(f, c);
                                            String imagenRuta = obtenerRutaImagen(perClick);
                                            actualizarInfoPersonaje(perClick, imagenRuta);
                                            personajeAtacante = personajeSeleccionadoParaAtaque;
                                        } else {
                                            LabelInfoAtaques.setText("No hay enemigos Alrededor");

                                        }
                                    }
                                    if (personajeSeleccionadoParaAtaque instanceof Muerte) {
                                        if (BuscarEnemigosMuerte(f, c)) {
                                            limpiarResaltados();
                                            celda.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
                                            resaltarPersonajesAtacarMuerte(f, c);
                                            String imagenRuta = obtenerRutaImagen(perClick);
                                            actualizarInfoPersonaje(perClick, imagenRuta);
                                            personajeAtacante = personajeSeleccionadoParaAtaque;
                                        } else {
                                            LabelInfoAtaques.setText("No hay enemigos Alrededor");

                                        }
                                    }
                                    if (personajeSeleccionadoParaAtaque instanceof HombreLobo) {
                                        if (HombreLoboMovi) {
                                            HombreLoboAc = perClick;
                                            HombreLoboMovi = false;
                                        }
                                        if (celdaSeleccionada != null) {

                                        }
                                        celdaSeleccionada = celda;
                                        filaSeleccionada = f;
                                        colSeleccionada = c;
                                        limpiarResaltados();
                                        celda.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                                        resaltarMovimientosPosiblesHombreLobo(f, c);
                                        String imagenRuta = obtenerRutaImagen(perClick);
                                        actualizarInfoPersonaje(perClick, imagenRuta);
                                        personajeAtacante = personajeSeleccionadoParaAtaque;

                                    }
                                }
                            } else if (celdaSeleccionada != null && perClick != null && perClick.esOscuro() != Turno) {
                                if (personajeAtacante instanceof Vampiro) {
                                    AtaqueVampiro(f, c);
                                }
                                if (personajeAtacante instanceof Muerte) {
                                    AtaqueMuerte(f, c);
                                }

                            }
                        } else if (MuerteInvocada) {
                            if (perClick != null && perClick.esOscuro() == Turno) {
                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;

                                if (hayEspaciosVacios()) {
                                    resaltarCeldasVacias();
                                    celda.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                                    String imagenRuta = obtenerRutaImagen(perClick);
                                    actualizarInfoPersonaje(perClick, imagenRuta);
                                } else {
                                    LabelInfoAtaques.setText("NO HAY Espacios Vacios");
                                    MuerteInvocada = false;
                                }

                            } else if (celdaSeleccionada != null && perClick == null) {
                                invocarZombie(f, c);
                                limpiarResaltados();
                                MuerteInvocada = false;

                                celdaSeleccionada = null;

                            }
                        } else if (AtacarConZombie) {
                            if (perClick != null && perClick.esOscuro() == Turno) {
                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;

                                if (hayZombiesEnTablero(Turno)) {
                                    LabelInfoAtaques.setText("Elije el zombie");
                                    resaltarZombiesEnTablero(Turno);
                                    celda.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                                    String imagenRuta = obtenerRutaImagen(perClick);
                                    actualizarInfoPersonaje(perClick, imagenRuta);
                                } else {
                                    LabelInfoAtaques.setText("No hay enemigos alrededor de los zombies");
                                    AtacarConZombie = false;
                                }

                            }
                            if (perClick instanceof Zombie
                                    && perClick.esOscuro() == Turno
                                    && perClick.esOscuro() == PersonajesTablero[filaSeleccionada][colSeleccionada].esOscuro()) {
                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;

                                limpiarResaltados();
                                resaltarZombiesEnTablero(Turno);
                                celda.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                                resaltarZombiesEnTablero(Turno);

                                LabelInfoAtaques.setText("Selecciona un enemigo para atacar con el Zombie");
                                String imagenRuta = obtenerRutaImagen(perClick);
                                actualizarInfoPersonaje(perClick, imagenRuta);
                            } else if (celdaSeleccionada != null && perClick != null && perClick.esOscuro() != Turno) {
                                intentarAtacar(f, c);
                                limpiarResaltados();
                                AtacarConZombie = false;
                                celdaSeleccionada = null;

                            }
                        } else if (modoAtaqueP1 && personajeSeleccionadoParaAtaque instanceof HombreLobo) {
                            esTurnoValido = (Turno && perClick.esOscuro()) || (!Turno && !perClick.esOscuro());
                            esPiezaHabilitada = esMovimientoHabilitado(perClick);

                            if (esTurnoValido && esPiezaHabilitada) {
                                if (celda == celdaSeleccionada) {
                                    celdaSeleccionada = null;
                                    limpiarResaltados();
                                    actualizarInfoPersonaje(null, null);
                                    return;
                                }
                                personajeAtacante = personajeSeleccionadoParaAtaque;

                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;
                                limpiarResaltados();
                                celda.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                                resaltarMovimientosPosiblesHombreLobo(f, c);
                                String imagenRuta = obtenerRutaImagen(perClick);
                                actualizarInfoPersonaje(perClick, imagenRuta);
                            }
                        } else if (celdaSeleccionada != null && personajeAtacante instanceof HombreLobo) {
                            HabilidadHombreLobo(celda, f, c);
                        } else if (perClick != null) {
                            esTurnoValido = (Turno && perClick.esOscuro()) || (!Turno && !perClick.esOscuro());
                            esPiezaHabilitada = esMovimientoHabilitado(perClick);

                            if (esTurnoValido && esPiezaHabilitada) {
                                if (celda == celdaSeleccionada) {
                                    celdaSeleccionada = null;
                                    limpiarResaltados();
                                    actualizarInfoPersonaje(null, null);
                                    return;
                                }

                                celdaSeleccionada = celda;
                                filaSeleccionada = f;
                                colSeleccionada = c;
                                limpiarResaltados();
                                celda.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                                resaltarMovimientosPosibles(f, c);
                                String imagenRuta = obtenerRutaImagen(perClick);
                                actualizarInfoPersonaje(perClick, imagenRuta);
                            }
                        } else if (celdaSeleccionada != null) {
                            MoverPieza(celda, f, c);
                        }

                    }
                }
                );

                ListaTablero[fila][col] = celda;

                Tablero.add(celda);
            }
        }

        JPanel TableroUser2 = new JPanel();
        TableroUser2.setBounds(662, 235, 70, 500);
        TableroUser2.setOpaque(false);
        TableroUser2.setLayout(new GridLayout(6, 1, 2, 2));
        label_Fondo.add(TableroUser2);

        JPanel TableroUser1 = new JPanel();
        TableroUser1.setBounds(65, 235, 70, 500);
        TableroUser1.setOpaque(false);
        TableroUser1.setLayout(new GridLayout(6, 1, 2, 2));
        label_Fondo.add(TableroUser1);

        for (int i = 0; i < EliminadosUser1.length; i++) {
            EliminadosUser1[i][0] = new JLabel();
            EliminadosUser1[i][0].setHorizontalAlignment(JLabel.CENTER);
            EliminadosUser1[i][0].setVerticalAlignment(JLabel.CENTER);
            EliminadosUser1[i][0].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            TableroUser1.add(EliminadosUser1[i][0]);
        }

        for (int i = 0; i < EliminadosUser2.length; i++) {
            EliminadosUser2[i][0] = new JLabel();
            EliminadosUser2[i][0].setHorizontalAlignment(JLabel.CENTER);
            EliminadosUser2[i][0].setVerticalAlignment(JLabel.CENTER);
            EliminadosUser2[i][0].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            TableroUser2.add(EliminadosUser2[i][0]);
        }
    }

    public final void mostrarGanadorPorRendicion() {
        PanelGanador.setVisible(true);
        PanelGanador.setEnabled(true);
        PanelGanador.setFocusable(true);

        if (Turno) {
            label1.setText("Ganador: " + Menu_principal.Rival.getName() + " Gano 3 puntos");
            label2.setText("Ya que le gano a Jugador : " + LOG_IN.JugadorActual.getName());
            label3.setText("Fin de la partida");
            Menu_principal.Rival.setPoint(Menu_principal.Rival.getPoint() + 3);

            Menu_principal.Rival.LOGSPLAYER.add("El Jugador: " + Menu_principal.Rival.getName()
                    + " Gano 3 puntos , Ya que le gano al Jugador : " + LOG_IN.JugadorActual.getName());
            LOG_IN.JugadorActual.LOGSPLAYER.add("El Jugador: " + Menu_principal.Rival.getName()
                    + " Gano 3 puntos , Ya que le gano al Jugador : " + LOG_IN.JugadorActual.getName());

        } else {
            label1.setText("Ganador: " + LOG_IN.JugadorActual.getName() + " Gano 3 puntos");
            label2.setText("Ya que le gano al Jugador: " + Menu_principal.Rival.getName());
            label3.setText("Fin de la partida");

            LOG_IN.JugadorActual.setPoint(LOG_IN.JugadorActual.getPoint() + 3);
            LOG_IN.JugadorActual.LOGSPLAYER.add("El Jugador: " + LOG_IN.JugadorActual.getName()
                    + " Gano 3 puntos , Ya que le gano al Jugador: " + Menu_principal.Rival.getName());
            Menu_principal.Rival.LOGSPLAYER.add("El Jugador: " + LOG_IN.JugadorActual.getName()
                    + " Gano 3 puntos , Ya que le gano al Jugador : " + Menu_principal.Rival.getName());
        }
    }

    private final void resaltarPersonajesAtacar(int fila, int col) {
        Personaje atacante = PersonajesTablero[fila][col];
        if (atacante == null) {
            return;
        }
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {

                    if (i == fila && j == col) {
                        continue;
                    }

                    Personaje objetivo = PersonajesTablero[i][j];

                    if (objetivo != null) {
                        if (objetivo.esOscuro() != atacante.esOscuro()) {
                            ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        }
                    }
                }
            }
        }
    }

    private boolean BuscarEnemigos(int fila, int col) {
        Personaje atacante = PersonajesTablero[fila][col];

        if (atacante == null) {
            return false;
        }

        boolean enemigoEncontrado = false;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {

                    if (i == fila && j == col) {
                        continue;
                    }

                    Personaje objetivo = PersonajesTablero[i][j];

                    if (objetivo != null && objetivo.esOscuro() != atacante.esOscuro()) {
                        ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        enemigoEncontrado = true;
                    }
                }
            }
        }

        return enemigoEncontrado;
    }

    private void intentarAtacar(int filaObjetivo, int colObjetivo) {
        if (celdaSeleccionada == null) {
            return;
        }

        int filaAtacante = filaSeleccionada;
        int colAtacante = colSeleccionada;

        Personaje atacante = PersonajesTablero[filaAtacante][colAtacante];
        Personaje objetivo = PersonajesTablero[filaObjetivo][colObjetivo];

        if (objetivo.esOscuro() == atacante.esOscuro()) {
            return;
        }

        if (Math.abs(filaObjetivo - filaAtacante) <= 1 && Math.abs(colObjetivo - colAtacante) <= 1) {
            int daño = atacante.ataqueNormal();

            int escudoActual = objetivo.getEscudo();
            if (escudoActual > 0) {
                if (daño >= escudoActual) {
                    daño -= escudoActual;
                    objetivo.setEscudo(0);
                } else {
                    objetivo.setEscudo(escudoActual - daño);
                    daño = 0;
                }
            }

            if (daño > 0) {
                objetivo.setVida(objetivo.getVida() - daño);
            }

            LabelInfoAtaques.setText("El atacante inflige daño: " + atacante.getDaño()
                    + " Escudo restante: " + objetivo.getEscudo()
                    + ", Vida restante: " + objetivo.getVida());

            if (objetivo.getVida() <= 0) {

                LabelInfoAtaques.setText("El objetivo ha sido derrotado.");

                Icon iconObjetivo = ListaTablero[filaObjetivo][colObjetivo].getIcon();
                ListaTablero[filaObjetivo][colObjetivo].setIcon(null);

                if (objetivo instanceof Zombie) {
                } else {
                    if (objetivo.esOscuro()) {
                        for (int i = 0; i < EliminadosUser2.length; i++) {
                            if (EliminadosUser2[i][0].getIcon() == null) {
                                EliminadosUser2[i][0].setIcon(iconObjetivo);

                                PersonajesClaros--;
                                if (PersonajesClaros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLobo--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerte--;
                                    if (CantMuerte == 0) {
                                        eliminarZombiesPorBando(true);
                                    }
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiro--;
                                }
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < EliminadosUser1.length; i++) {
                            if (EliminadosUser1[i][0].getIcon() == null) {
                                PersonajesOscuros--;
                                EliminadosUser1[i][0].setIcon(iconObjetivo);
                                if (PersonajesOscuros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLoboOsc--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerteOsc--;
                                     if (CantMuerteOsc == 0) {
                                        eliminarZombiesPorBando(false);
                                    }
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiroOsc--;
                                   
                                }
                                break;
                            }
                        }
                    }
                }
                PersonajesTablero[filaObjetivo][colObjetivo] = null;
            }
            modoAtaque = false;
            celdaSeleccionada = null;

            limpiarResaltados();
            BotonPN.setText("Ataque Normal");
            PanelRuleta.setVisible(true);
            PanelRuleta.setEnabled(true);
            if (Turno) {
                labelRuletaTemp = labelRuleta1;
                ruedaTemp = rueda1;
                labelRuleta1.setVisible(true);
                labelRuleta2.setVisible(false);
            } else {
                labelRuletaTemp = labelRuleta2;
                ruedaTemp = rueda2;
                labelRuleta2.setVisible(true);
                labelRuleta1.setVisible(false);
            }

            iniciarGiroContinuo();
            isGirandoContinuo = true;

        }

    }

    private void AtaqueVampiro(int filaObjetivo, int colObjetivo) {
        if (celdaSeleccionada == null) {
            return;
        }

        int filaAtacante = filaSeleccionada;
        int colAtacante = colSeleccionada;

        Personaje atacante = PersonajesTablero[filaAtacante][colAtacante];
        Personaje objetivo = PersonajesTablero[filaObjetivo][colObjetivo];

        if (objetivo == null || objetivo.esOscuro() == atacante.esOscuro()) {
            return;
        }

        if (Math.abs(filaObjetivo - filaAtacante) <= 1 && Math.abs(colObjetivo - colAtacante) <= 1) {
            int daño = 1;
            objetivo.setVida(objetivo.getVida() - daño);
            atacante.setVida(atacante.getVida() + daño);

            LabelInfoAtaques.setText("El Vampiro Robo 1 inflige daño: " + daño
                    + ", Vida: " + objetivo.getVida() + "");
            if (objetivo.getVida() <= 0) {
                LabelInfoAtaques.setText("El Vampiro Derroto al enemigo");
                Icon iconObjetivo = ListaTablero[filaObjetivo][colObjetivo].getIcon();
                ListaTablero[filaObjetivo][colObjetivo].setIcon(null);

                if (objetivo instanceof Zombie) {
                } else {
                    if (objetivo.esOscuro()) {
                        for (int i = 0; i < EliminadosUser2.length; i++) {
                            if (EliminadosUser2[i][0].getIcon() == null) {
                                EliminadosUser2[i][0].setIcon(iconObjetivo);
                                PersonajesClaros--;
                                if (PersonajesClaros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLobo--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerte--;
                                    if (CantMuerte == 0) {
                                        eliminarZombiesPorBando(true);
                                    }
                                    
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiro--;
                                }

                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < EliminadosUser1.length; i++) {
                            if (EliminadosUser1[i][0].getIcon() == null) {
                                EliminadosUser1[i][0].setIcon(iconObjetivo);

                                PersonajesOscuros--;
                                if (PersonajesOscuros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLoboOsc--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerteOsc--;
                                    if (CantMuerteOsc == 0) {
                                        eliminarZombiesPorBando(false);
                                    }
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiroOsc--;
                                }

                                break;
                            }
                        }
                    }
                }

                PersonajesTablero[filaObjetivo][colObjetivo] = null;
            }

            modoAtaqueP1 = false;
            celdaSeleccionada = null;
            limpiarResaltados();
            BotonPN.setText("Ataque Normal");

            PanelRuleta.setVisible(true);
            PanelRuleta.setEnabled(true);
            if (Turno) {
                labelRuletaTemp = labelRuleta1;
                ruedaTemp = rueda1;
                labelRuleta1.setVisible(true);
                labelRuleta2.setVisible(false);
            } else {
                labelRuletaTemp = labelRuleta2;
                ruedaTemp = rueda2;
                labelRuleta2.setVisible(true);
                labelRuleta1.setVisible(false);
            }

            iniciarGiroContinuo();
            isGirandoContinuo = true;
        }
    }

    private void AtaqueMuerte(int filaObjetivo, int colObjetivo) {
        if (celdaSeleccionada == null) {
            return;
        }

        int filaAtacante = filaSeleccionada;
        int colAtacante = colSeleccionada;

        Personaje atacante = PersonajesTablero[filaAtacante][colAtacante];
        Personaje objetivo = PersonajesTablero[filaObjetivo][colObjetivo];

        if (atacante == null || objetivo == null) {
            return;
        }

        if (objetivo.esOscuro() == atacante.esOscuro()) {
            return;
        }

        int difFila = Math.abs(filaObjetivo - filaAtacante);
        int difCol = Math.abs(colObjetivo - colAtacante);

        boolean esDiagonal = difFila == difCol && difFila <= 2;
        boolean esVertical = difCol == 0 && difFila <= 2;
        boolean esHorizontal = difFila == 0 && difCol <= 2;

        if (esDiagonal || esVertical || esHorizontal) {

            int daño = atacante.getDaño() / 2;
            objetivo.setVida(objetivo.getVida() - daño);

            LabelInfoAtaques.setText("La Muerte Robo 1 inflige daño: " + daño
                    + ", Vida: " + objetivo.getVida() + ")");

            // Si el enemigo muere
            if (objetivo.getVida() <= 0) {
                LabelInfoAtaques.setText("La muerte derroto al enemigo");

                Icon iconObjetivo = ListaTablero[filaObjetivo][colObjetivo].getIcon();

                ListaTablero[filaObjetivo][colObjetivo].setIcon(null);

                if (objetivo instanceof Zombie) {
                } else {
                    if (objetivo.esOscuro()) {
                        for (int i = 0; i < EliminadosUser2.length; i++) {
                            if (EliminadosUser2[i][0].getIcon() == null) {
                                EliminadosUser2[i][0].setIcon(iconObjetivo);
                                PersonajesClaros--;
                                if (PersonajesClaros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLobo--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerte--;
                                    if (CantMuerte == 0) {
                                        eliminarZombiesPorBando(true);
                                    }
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiro--;
                                }
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < EliminadosUser1.length; i++) {
                            if (EliminadosUser1[i][0].getIcon() == null) {
                                EliminadosUser1[i][0].setIcon(iconObjetivo);
                                PersonajesOscuros--;
                                if (PersonajesOscuros == 0) {
                                    mostrarGanadorPorRendicion();
                                }
                                if (objetivo instanceof HombreLobo) {
                                    CantHombreLoboOsc--;
                                }
                                if (objetivo instanceof Muerte) {
                                    CantMuerteOsc--;
                                    if (CantMuerteOsc == 0) {
                                        eliminarZombiesPorBando(false);
                                    }
                                }
                                if (objetivo instanceof Vampiro) {
                                    CantVampiroOsc--;
                                }
                                break;
                            }
                        }
                    }
                }
                PersonajesTablero[filaObjetivo][colObjetivo] = null;
            }

            modoAtaqueP1 = false;
            celdaSeleccionada = null;
            limpiarResaltados();
            BotonPN.setText("Ataque Normal");

            PanelRuleta.setVisible(true);
            PanelRuleta.setEnabled(true);

            if (Turno) {
                labelRuletaTemp = labelRuleta1;
                ruedaTemp = rueda1;
                labelRuleta1.setVisible(true);
                labelRuleta2.setVisible(false);
            } else {
                labelRuletaTemp = labelRuleta2;
                ruedaTemp = rueda2;
                labelRuleta2.setVisible(true);
                labelRuleta1.setVisible(false);
            }

            iniciarGiroContinuo();
            isGirandoContinuo = true;
        }
    }

    private boolean BuscarEnemigosMuerte(int fila, int col) {
        Personaje atacante = PersonajesTablero[fila][col];
        if (atacante == null) {
            return false;
        }

        boolean enemigoEncontrado = false;

        int[] Vert = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] Hori = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int dir = 0; dir < Vert.length; dir++) {
            for (int paso = 1; paso <= 2; paso++) {
                int i = fila + Vert[dir] * paso;
                int j = col + Hori[dir] * paso;

                if (i < 0 || i >= FILAS || j < 0 || j >= COLUMNAS) {
                    continue;
                }

                Personaje objetivo = PersonajesTablero[i][j];

                if (objetivo != null && objetivo.esOscuro() != atacante.esOscuro()) {
                    ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    enemigoEncontrado = true;
                }
            }
        }

        return enemigoEncontrado;

    }

    private void resaltarPersonajesAtacarMuerte(int fila, int col) {
        Personaje atacante = PersonajesTablero[fila][col];

        if (atacante == null) {
            return;
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int dir = 0; dir < dx.length; dir++) {
            for (int paso = 1; paso <= 2; paso++) {
                int i = fila + dx[dir] * paso;
                int j = col + dy[dir] * paso;

                if (i < 0 || i >= FILAS || j < 0 || j >= COLUMNAS) {
                    break;
                }

                Personaje objetivo = PersonajesTablero[i][j];

                if (objetivo != null) {
                    if (objetivo.esOscuro() != atacante.esOscuro()) {
                        ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }
                    break;
                }
            }
        }
    }

    private void HabilidadHombreLobo(JLabel destino, int filaDestino, int colDestino) {
        if (celdaSeleccionada == null) {
            return;
        }

        Personaje personajeAMover = PersonajesTablero[filaSeleccionada][colSeleccionada];
        if (personajeAMover == null) {
            return;
        }

        if (ComprobarSiHayPersonaje(destino)) {
            return;
        }
        boolean esTurnoValido = (Turno && personajeAMover.esOscuro()) || (!Turno && !personajeAMover.esOscuro());
        if (!esTurnoValido) {
            return;
        }

        int dx = filaDestino - filaSeleccionada;
        int dy = colDestino - colSeleccionada;

        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);

        if ((absDx <= 2 && absDy <= 2) && (absDx + absDy > 0)) {

            if (absDx == 2 || absDy == 2) {
                int pasoFila = Integer.compare(filaDestino, filaSeleccionada);
                int pasoCol = Integer.compare(colDestino, colSeleccionada);

                int filaIntermedia = filaSeleccionada + pasoFila;
                int colIntermedia = colSeleccionada + pasoCol;

                if (PersonajesTablero[filaIntermedia][colIntermedia] != null) {
                    return;
                }
            }

            destino.setIcon(celdaSeleccionada.getIcon());
            celdaSeleccionada.setIcon(null);

            PersonajesTablero[filaDestino][colDestino] = personajeAMover;
            PersonajesTablero[filaSeleccionada][colSeleccionada] = null;
            LabelInfoAtaques.setText("Hombre Lobo Uso su Hablilidad Especial");
            celdaSeleccionada = null;
            limpiarResaltados();

            modoAtaqueP1 = false;
            celdaSeleccionada = null;
            limpiarResaltados();
            BotonPN.setText("Ataque Normal");

            PanelRuleta.setVisible(true);
            PanelRuleta.setEnabled(true);
            PanelRuleta.setBackground(Color.CYAN);

            if (Turno) {
                labelRuletaTemp = labelRuleta1;
                ruedaTemp = rueda1;
                labelRuleta1.setVisible(true);
                labelRuleta2.setVisible(false);
            } else {
                labelRuletaTemp = labelRuleta2;
                ruedaTemp = rueda2;
                labelRuleta2.setVisible(true);
                labelRuleta1.setVisible(false);
            }

            iniciarGiroContinuo();
            isGirandoContinuo = true;
        }
    }

    private void resaltarMovimientosPosiblesHombreLobo(int fila, int col) {
        int[][] direcciones = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
        };

        for (int[] dir : direcciones) {
            int dx = dir[0];
            int dy = dir[1];

            for (int paso = 1; paso <= 2; paso++) {
                int i = fila + dx * paso;
                int j = col + dy * paso;

                // Verifica que no se salga del tablero
                if (i < 0 || i >= FILAS || j < 0 || j >= COLUMNAS) {
                    break;
                }

                if (ComprobarSiHayPersonaje(ListaTablero[i][j])) {
                    break;
                }

                ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            }
        }
    }

    private void resaltarCeldasVacias() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                // Si la celda no tiene personaje
                if (PersonajesTablero[fila][col] == null) {
                    ListaTablero[fila][col].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
                }
            }
        }
    }

    private void invocarZombie(int fila, int col) {
        if (fila < 0 || fila >= FILAS || col < 0 || col >= COLUMNAS) {
            return;
        }

        if (PersonajesTablero[fila][col] == null) {
            boolean esOscuro = Turno;
            Personaje nuevoZombie = new Zombie(1, 1, 0, esOscuro);

            PersonajesTablero[fila][col] = nuevoZombie;
            String rutaImagen;
            if (esOscuro) {
                rutaImagen = "/imagenes/Zombie_Oscuro.png";
            } else {
                rutaImagen = "/imagenes/zombie.png";
            }
            ImageIcon imgZombie = new ImageIcon(getClass().getResource(rutaImagen));
            Image imgEscalada = imgZombie.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            ListaTablero[fila][col].setIcon(new ImageIcon(imgEscalada));

            ListaTablero[fila][col].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

            LabelInfoAtaques.setText("Zombie invocado en (" + fila + ", " + col + ")");
        } else {
            LabelInfoAtaques.setText("No se puede invocar: celda ocupada");
        }

        modoAtaque = false;
        celdaSeleccionada = null;
        limpiarResaltados();
        BotonPN.setText("Ataque Normal");

        PanelRuleta.setVisible(true);
        PanelRuleta.setEnabled(true);

        if (Turno) {
            labelRuletaTemp = labelRuleta1;
            ruedaTemp = rueda1;
            labelRuleta1.setVisible(true);
            labelRuleta2.setVisible(false);
        } else {
            labelRuletaTemp = labelRuleta2;
            ruedaTemp = rueda2;
            labelRuleta2.setVisible(true);
            labelRuleta1.setVisible(false);
        }

        iniciarGiroContinuo();
        isGirandoContinuo = true;
        AtaquesZombies = true;
    }

    private void eliminarZombiesPorBando(boolean eliminarOscuro) {
    for (int i = 0; i < FILAS; i++) {
        for (int j = 0; j < COLUMNAS; j++) {
            Personaje p = PersonajesTablero[i][j];
            if (p instanceof Zombie) {
                if ((eliminarOscuro && p.esOscuro()) || (!eliminarOscuro && !p.esOscuro())) {
                    PersonajesTablero[i][j] = null;
                    ListaTablero[i][j].setIcon(null);
                    ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            }
        }
    }
}

    private boolean hayEspaciosVacios() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                if (PersonajesTablero[fila][col] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hayZombiesEnTablero(boolean esOscuro) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                Personaje p = PersonajesTablero[i][j];
                if (p instanceof Zombie && p.esOscuro() == esOscuro) {
                    return true;
                }
            }
        }
        return false;
    }

    private void resaltarZombiesEnTablero(boolean turnoActual) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                Personaje p = PersonajesTablero[i][j];
                if (p instanceof Zombie && p.esOscuro() == turnoActual) {
                    ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));

                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x == i && y == j) {
                                continue;
                            }
                            if (x >= 0 && x < FILAS && y >= 0 && y < COLUMNAS) {
                                Personaje enemigo = PersonajesTablero[x][y];
                                if (enemigo != null && enemigo.esOscuro() != p.esOscuro()) {
                                    ListaTablero[x][y].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String obtenerRutaImagen(Personaje p) {
        if (p == null) {
            return "";
        }

        String base = "";
        String faccion = p.esOscuro() ? "_oscuro" : "";

        if (p instanceof HombreLobo) {
            base = "/imagenes/Lobo";
        } else if (p instanceof Vampiro) {
            base = "/imagenes/Vampiro";
        } else if (p instanceof Muerte) {
            base = "/imagenes/Muerte";
        } else if (p instanceof Zombie) {
            base = "/imagenes/zombie";
        }

        if (p instanceof Vampiro && p.esOscuro()) {
            return "/imagenes/Vampiro_oscuro.png";
        } else if (p instanceof Muerte && p.esOscuro()) {
            return "/imagenes/Muerte_oscuro.png";
        } else if (p instanceof HombreLobo && p.esOscuro()) {
            return "/imagenes/Lobooscuro.png";
        } else if (p instanceof Vampiro && !p.esOscuro()) {
            return "/imagenes/vampiro.png";
        } else if (p instanceof Muerte && !p.esOscuro()) {
            return "/imagenes/muerte.png";
        } else if (p instanceof HombreLobo && !p.esOscuro()) {
            return "/imagenes/lobo.png";
        }

        return base + faccion + ".png";
    }

    private void actualizarInfoPersonaje(Personaje p, String imagenRuta) {
        if (p == null) {
            LblVidaValor.setText("---");
            LblDañoValor.setText("---");
            LblEscudoValor.setText("---");
            LabelPersonActual.setIcon(null);
            return;
        }

        LblVidaValor.setText(String.valueOf(p.getVida()));
        LblDañoValor.setText(String.valueOf(p.getDaño()));
        LblEscudoValor.setText(String.valueOf(p.getEscudo()));

        try {
            ImageIcon fondoPersonActu = new ImageIcon(getClass().getResource(imagenRuta));
            Image PersActua = fondoPersonActu.getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH);
            LabelPersonActual.setIcon(new ImageIcon(PersActua));
        } catch (Exception e) {
            LabelInfoAtaques.setText("Error al cargar la imagen del personaje: " + imagenRuta);
            LabelPersonActual.setIcon(null);
        }
        personajeAtaque = p;
        if (personajeSeleccionadoParaAtaque instanceof Vampiro) {
            BotonP1.setText("Chupar Sangre");
            BotonP2.setVisible(false);
        } else if (personajeSeleccionadoParaAtaque instanceof Muerte) {
            BotonP1.setText("Atacar Con Lanza");
            BotonP2.setVisible(true);
        } else if (personajeSeleccionadoParaAtaque instanceof HombreLobo) {
            BotonP1.setText("Moverse Dos Casillas");
            BotonP2.setVisible(false);
        }

        TableroINFO.setVisible(true);
    }

    private boolean esMovimientoHabilitado(Personaje p) {
        if (p == null) {
            return false;
        }

        switch (PerosonajeActual) {
            case 0:
            case 3:
                return p instanceof HombreLobo;

            case 1:
            case 4:
                return p instanceof Vampiro;

            case 2:
            case 5:
                return p instanceof Muerte;

            default:
                return false;
        }
    }

    private void resaltarMovimientosPosibles(int fila, int col) {
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS) {
                    if ((i != fila || j != col) && !ComprobarSiHayPersonaje(ListaTablero[i][j])) {
                        ListaTablero[i][j].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                }
            }
        }
    }

    private void resaltarPersonajesHabilitadosPorRuleta() {
        limpiarResaltadosRuleta();

        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                Personaje p = PersonajesTablero[fila][col];

                if (p != null) {
                    boolean esTurnoValido = (Turno && p.esOscuro()) || (!Turno && !p.esOscuro());

                    boolean esPiezaHabilitada = esMovimientoHabilitado(p);

                    if (esTurnoValido && esPiezaHabilitada) {
                        ListaTablero[fila][col].setBorder(BorderFactory.createLineBorder(COLOR_BORDE_RULETA, 3));
                    }
                }
            }
        }
    }

    private void limpiarResaltadosRuleta() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JLabel currentCelda = ListaTablero[fila][col];

                if (currentCelda != celdaSeleccionada) {
                    currentCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }
        }
    }

    private void limpiarResaltados() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JLabel currentCelda = ListaTablero[fila][col];
                Personaje p = PersonajesTablero[fila][col];

                if (currentCelda == celdaSeleccionada) {
                    continue;
                }

                if (p != null) {
                    boolean esTurnoValido = (Turno && p.esOscuro()) || (!Turno && !p.esOscuro());
                    boolean esPiezaHabilitada = esMovimientoHabilitado(p);

                    if (esTurnoValido && esPiezaHabilitada) {
                        currentCelda.setBorder(BorderFactory.createLineBorder(COLOR_BORDE_RULETA, 3));
                        continue;
                    }
                }

                currentCelda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }
    }

    private boolean ComprobarSiHayPersonaje(JLabel celda) {
        return celda.getIcon() != null;
    }

    private void MoverPieza(JLabel destino, int filaDestino, int colDestino) {
        if (celdaSeleccionada == null) {
            return;
        }

        Personaje personajeAMover = PersonajesTablero[filaSeleccionada][colSeleccionada];

        if (personajeAMover == null) {
            return;
        }
        if (ComprobarSiHayPersonaje(destino)) {
            return;
        }

        boolean esTurnoValido = (Turno && personajeAMover.esOscuro()) || (!Turno && !personajeAMover.esOscuro());

        if (!esTurnoValido) {
            return;
        }

        int dx = Math.abs(filaDestino - filaSeleccionada);
        int dy = Math.abs(colDestino - colSeleccionada);

        if (dx <= 1 && dy <= 1 && (dx + dy > 0)) {

            destino.setIcon(celdaSeleccionada.getIcon());
            celdaSeleccionada.setIcon(null);

            PersonajesTablero[filaDestino][colDestino] = personajeAMover;
            PersonajesTablero[filaSeleccionada][colSeleccionada] = null;

            celdaSeleccionada = null;
            limpiarResaltados();
            PanelRuleta.setVisible(true);
            PanelRuleta.setEnabled(true);
            PanelRuleta.setBackground(Color.CYAN);
            if (Turno) {
                labelRuletaTemp = labelRuleta1;
                ruedaTemp = rueda1;
                labelRuleta1.setVisible(true);
                labelRuleta2.setVisible(false);
            } else {
                labelRuletaTemp = labelRuleta2;
                ruedaTemp = rueda2;
                labelRuleta2.setVisible(true);
                labelRuleta1.setVisible(false);
            }

            iniciarGiroContinuo();
            isGirandoContinuo = true;
        }
    }

    private void iniciarGiroContinuo() {
        if (timerGiroContinuo != null && timerGiroContinuo.isRunning()) {
            return;
        }

        if (Turno) {
            labelRuletaTemp = labelRuleta1;
            ruedaTemp = rueda1;
        } else {
            labelRuletaTemp = labelRuleta2;
            ruedaTemp = rueda2;
        }
        botonGirar.setEnabled(true);
        timerGiroContinuo = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angulo = (angulo + 10) % 360;
                labelRuletaTemp.setIcon(new ImageIcon(rotarImagen(ruedaTemp, angulo)));
            }
        });
        timerGiroContinuo.start();
    }

    private final void detenerGiro() {
        if (timerGiroContinuo != null) {
            timerGiroContinuo.stop();
        }
        boolean PersonajeEntablero = false;
        int resultado = 0;
        Random aleatorio = new Random();

        while (!PersonajeEntablero) {
            resultado = aleatorio.nextInt(6);

            if (Turno) {
                if ((resultado == 0 || resultado == 3) && CantHombreLoboOsc > 0) {
                    PersonajeEntablero = true;
                }
                if ((resultado == 1 || resultado == 4) && CantVampiroOsc > 0) {
                    PersonajeEntablero = true;
                }
                if ((resultado == 2 || resultado == 5) && CantMuerteOsc > 0) {
                    PersonajeEntablero = true;
                }
            } else {
                if ((resultado == 0 || resultado == 3) && CantHombreLobo > 0) {
                    PersonajeEntablero = true;
                }
                if ((resultado == 1 || resultado == 4) && CantVampiro > 0) {
                    PersonajeEntablero = true;
                }
                if ((resultado == 2 || resultado == 5) && CantMuerte > 0) {
                    PersonajeEntablero = true;
                }
            }
        }
        PerosonajeActual = resultado;
        Personajes[resultado] = true;
        String PersonajePorRulta = "";
        if (resultado == 0 || resultado == 3) {
            PersonajePorRulta = "Hombre Lobo";
            BotonP2Atac.setVisible(false);

        } else if (resultado == 1 || resultado == 4) {
            PersonajePorRulta = "Vampiro";
            BotonP2Atac.setVisible(false);

        } else if (resultado == 2 || resultado == 5) {
            BotonP2Atac.setVisible(false);
            PersonajePorRulta = "Muerte";
            if (hayZombiesEnTablero(Turno)) {
                BotonP2Atac.setVisible(true);
            } else {
                BotonP2Atac.setVisible(false);
            }
            
        }

        LabelInfoAtaques.setText("Resultado de Ruleta: " + PersonajePorRulta);
        double anguloObjetivo = Angulos_Objevios[resultado];
        girarHastaAnguloFinal(anguloObjetivo, resultado);

        new Timer(3500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelRuleta.setVisible(false);
                ((Timer) e.getSource()).stop();

            }
        }).start();
    }

    private final void girarHastaAnguloFinal(double anguloObjetivo, final int resultadoFinal) {
        final double anguloInicio = angulo;
        pasoActual = 0;
        double desplazamientoNecesario = anguloObjetivo - anguloInicio;
        final double anguloTotalAGirar = 720 + desplazamientoNecesario;

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pasoActual > 100) {
                    ((Timer) e.getSource()).stop();
                    angulo = anguloObjetivo;
                    Turno = !Turno;
                    resaltarPersonajesHabilitadosPorRuleta();
                    return;
                }

                double progreso = pasoActual / 100.0;
                double anguloCubierto = anguloTotalAGirar * progreso;
                angulo = anguloInicio + anguloCubierto;

                labelRuletaTemp.setIcon(new ImageIcon(rotarImagen(ruedaTemp, angulo)));

                pasoActual++;
            }
        });

        timer.start();
    }

    private final Image rotarImagen(Image img, double angulo) {
        BufferedImage buffered = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics ImagenNew = buffered.getGraphics();
        ImagenNew.drawImage(img, 0, 0, null);

        double AnguloSumado = angulo * (Math.PI / 180.0);

        AffineTransform RotacionEEje = AffineTransform.getRotateInstance(AnguloSumado, 200, 200);
        AffineTransformOp rotacion = new AffineTransformOp(RotacionEEje, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage imagenRotada = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);

        rotacion.filter(buffered, imagenRotada);
        return imagenRotada;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
}
