/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import componentes.EstiloTablaHeader;
import componentes.EstiloTablaRenderer;
import componentes.FadeEffect;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import org.imgscalr.Scalr;
import reproductor.Disco;
import reproductor.Emergente;
import reproductor.Metodos;

/**
 *
 * @author RojeruSan
 */
public class Principal extends javax.swing.JFrame implements BasicPlayerListener {

    private int x, y, index = 0;
    private boolean minimiza = false;

    private ArrayList<String> Canciones = new ArrayList<>();
    private ArrayList<File> canciones = new ArrayList<>();
    private ArrayList<Integer> NumerosAleatorios = new ArrayList<>();
    private Disco Player = new Disco();
    private Metodos metodos = new Metodos();
    private int ItemActual, TamanoEnBytes, PrimeroDeAleatorio, PosicionMouse, Repetir = 0, AuxitemActual = 0;
    private boolean PararBarra = false, Aleatorio = false, EnSilencio = false, Mostrar = false;
    public static boolean CambioEnEcualizador = false;
    private float Ecualizador[];
    Timer timer = null;
    TimerTask task;

    Mp3File mp3file;

    /**
     * Creates new form Principal
     */
    public Principal() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        this.setTitle("Music Player RS v.1");
        FadeEffect.fadeInFrame(this, 50, 0.1f);
        this.setLocationRelativeTo(this);
        this.setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());

        this.miLista.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        this.miLista.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        this.miLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.fondoTabla.getViewport().setBackground(new java.awt.Color(0, 153, 204));

        progreso.setStringPainted(true);
        progreso.setForeground(new Color(0, 153, 204));
        progreso.setString("");

        Player.player.addBasicPlayerListener(this);
        AbrirInformacion();
    }

    private boolean PasarCancion(boolean Adelante) {
        boolean Abre = false;
        if (Canciones.size() > 0) {
            if (Aleatorio) {
                ItemActual = AuxitemActual;
            }
            if (Adelante) {
                try {
                    ItemActual++;
                    if (ItemActual > Canciones.size() - 1) {
                        ItemActual = 0;
                    }
                    this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    ItemActual--;
                    if (ItemActual < 0) {
                        ItemActual = Canciones.size() - 1;
                    }
                    this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            AuxitemActual = ItemActual;
            if (Aleatorio) {
                try {
                    ItemActual = NumerosAleatorios.get(ItemActual);
                    this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Player.Estado() != 0) {
                Abre = Player.CambiarPor(Canciones.get(ItemActual), false);
            } else {
                Abre = Player.CambiarPor(Canciones.get(ItemActual), true);
            }
            SeleccionarCancionActual();
        }
        return Abre;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new componentes.rsbuttom.RSButtonMetro();
        btnPause = new componentes.rsbuttom.RSButtonMetro();
        btnPlay = new componentes.rsbuttom.RSButtonMetro();
        btnStop = new componentes.rsbuttom.RSButtonMetro();
        btnAtras = new componentes.rsbuttom.RSButtonMetro();
        btnSig = new componentes.rsbuttom.RSButtonMetro();
        btnAbrir1 = new componentes.rsbuttom.RSButtonMetro();
        tiempo1 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();
        tiempo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new componentes.rsbuttom.RSButtonMetro();
        btnMinimizar = new componentes.rsbuttom.RSButtonMetro();
        lblImagen = new javax.swing.JLabel();
        fondoTabla = new javax.swing.JScrollPane();
        miLista = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        nombreCancion = new javax.swing.JLabel();
        nombreCancion1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vol = new javax.swing.JSlider();
        btnBorrar = new componentes.rsbuttom.RSButtonMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(9, 129, 169), 5));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(9, 129, 169));

        btnAdd.setBackground(new java.awt.Color(0, 153, 204));
        btnAdd.setForeground(new java.awt.Color(9, 129, 169));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono-abrir.png"))); // NOI18N
        btnAdd.setText("Añadir Canciones");
        btnAdd.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnAdd.setColorPressed(new java.awt.Color(30, 168, 215));
        btnAdd.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnAdd.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnPause.setBackground(new java.awt.Color(0, 153, 204));
        btnPause.setForeground(new java.awt.Color(9, 129, 169));
        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pause.png"))); // NOI18N
        btnPause.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnPause.setColorPressed(new java.awt.Color(30, 168, 215));
        btnPause.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnPause.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnPlay.setBackground(new java.awt.Color(0, 153, 204));
        btnPlay.setForeground(new java.awt.Color(9, 129, 169));
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        btnPlay.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnPlay.setColorPressed(new java.awt.Color(30, 168, 215));
        btnPlay.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnPlay.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnStop.setBackground(new java.awt.Color(0, 153, 204));
        btnStop.setForeground(new java.awt.Color(9, 129, 169));
        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stop.png"))); // NOI18N
        btnStop.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnStop.setColorPressed(new java.awt.Color(30, 168, 215));
        btnStop.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnStop.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnAtras.setBackground(new java.awt.Color(0, 153, 204));
        btnAtras.setForeground(new java.awt.Color(9, 129, 169));
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono-an.png"))); // NOI18N
        btnAtras.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnAtras.setColorPressed(new java.awt.Color(30, 168, 215));
        btnAtras.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnAtras.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAtras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnSig.setBackground(new java.awt.Color(0, 153, 204));
        btnSig.setForeground(new java.awt.Color(9, 129, 169));
        btnSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono-si.png"))); // NOI18N
        btnSig.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnSig.setColorPressed(new java.awt.Color(30, 168, 215));
        btnSig.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnSig.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSigActionPerformed(evt);
            }
        });

        btnAbrir1.setBackground(new java.awt.Color(0, 153, 204));
        btnAbrir1.setForeground(new java.awt.Color(9, 129, 169));
        btnAbrir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono-abrir.png"))); // NOI18N
        btnAbrir1.setText("Buscar Canciones");
        btnAbrir1.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnAbrir1.setColorPressed(new java.awt.Color(30, 168, 215));
        btnAbrir1.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnAbrir1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAbrir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrir1ActionPerformed(evt);
            }
        });

        tiempo1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        tiempo1.setForeground(new java.awt.Color(0, 153, 204));
        tiempo1.setText("00:00");

        progreso.setBackground(new java.awt.Color(255, 255, 255));
        progreso.setForeground(new java.awt.Color(0, 153, 204));
        progreso.setToolTipText("Adelantar");
        progreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progresoStateChanged(evt);
            }
        });
        progreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                progresoMouseClicked(evt);
            }
        });

        tiempo.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        tiempo.setForeground(new java.awt.Color(0, 153, 204));
        tiempo.setText("00:00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrir1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSig, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(tiempo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tiempo))
                    .addComponent(progreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAbrir1, btnAdd});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAbrir1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAbrir1, btnAdd});

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 129, 169));
        jLabel1.setText("Music Player RS");

        btnCerrar.setBackground(new java.awt.Color(9, 129, 169));
        btnCerrar.setText("X");
        btnCerrar.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnCerrar.setColorPressed(new java.awt.Color(9, 129, 169));
        btnCerrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnMinimizar.setBackground(new java.awt.Color(9, 129, 169));
        btnMinimizar.setText("__");
        btnMinimizar.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnMinimizar.setColorPressed(new java.awt.Color(9, 129, 169));
        btnMinimizar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });

        lblImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo1.png"))); // NOI18N
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(9, 129, 169), 4));

        fondoTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(9, 129, 169), 4));

        miLista.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        miLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MI MUSICA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        miLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miLista.getTableHeader().setReorderingAllowed(false);
        miLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miListaMouseClicked(evt);
            }
        });
        fondoTabla.setViewportView(miLista);
        if (miLista.getColumnModel().getColumnCount() > 0) {
            miLista.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.setBackground(new java.awt.Color(9, 129, 169));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombreCancion.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nombreCancion.setForeground(new java.awt.Color(0, 153, 204));
        nombreCancion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreCancion.setText("...................................");
        jPanel3.add(nombreCancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 750, 30));

        nombreCancion1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nombreCancion1.setForeground(new java.awt.Color(0, 153, 204));
        nombreCancion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreCancion1.setText("...................................");
        jPanel3.add(nombreCancion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 750, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        vol.setBackground(new java.awt.Color(0, 153, 204));
        vol.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        vol.setOrientation(javax.swing.JSlider.VERTICAL);
        vol.setToolTipText("Volumen");
        vol.setValue(100);
        vol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volStateChanged(evt);
            }
        });
        vol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volMouseClicked(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(0, 153, 204));
        btnBorrar.setForeground(new java.awt.Color(9, 129, 169));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono-delete.png"))); // NOI18N
        btnBorrar.setColorBorde(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new javax.swing.ImageIcon(getClass().getResource("/componentes/rsbuttom/border.png")))); // NOI18N
        btnBorrar.setColorPressed(new java.awt.Color(30, 168, 215));
        btnBorrar.setColorTextPressed(new java.awt.Color(9, 129, 169));
        btnBorrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fondoTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(vol, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fondoTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        Point mueve = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(mueve.x - x, mueve.y - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        FadeEffect.fadeOutFrame(this, 50, 0.1f);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setExtendedState(ICONIFIED);
        if (!minimiza) {
            minimiza = false;
        } else {
            minimiza = true;
        }
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String Ubicaciones[] = AñaArchivo();
        if (Ubicaciones != null) {
            Canciones.addAll(Arrays.asList(Ubicaciones));
            if (Canciones.size() > 0) {
                ListarTabla();
            }
            if (Aleatorio) {
                LlenarNumerosAleatorios();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed
    public String[] AñaArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(filtroImagen);
        fc.setDialogTitle("Seleccionar canciones");
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            File Archivo[] = fc.getSelectedFiles();
            String Ubicaciones[] = new String[Archivo.length];
            int i = 0;
            for (File aux : Archivo) {
                Ubicaciones[i] = aux.getPath();
                canciones.add(Archivo[i]);
                i++;
            }
            return Ubicaciones;
        }
        return null;
    }

    private boolean estadoPause = false;
    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        if (Canciones.size() > 0) {
            if (!estadoPause) {
                try {
                    ItemActual = miLista.getSelectedRow();
                    this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
                    for (int i = 0; i < NumerosAleatorios.size(); i++) {
                        if (NumerosAleatorios.get(i) == ItemActual) {
                            AuxitemActual = i;
                        }
                    }
                    if (!Player.CambiarPor(Canciones.get(ItemActual), true)) {
//                    JOptionPane.showMessageDialog(this, "¡ Imposible reproducir !", "Aviso", 0);
                    }
                    estadoPause = false;
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Player.Reproducir();
                estadoPause = false;
            }
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        if (Canciones.size() > 0) {
            Player.Pausar();
            estadoPause = true;
        }
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if (Canciones.size() > 0) {
            Player.Parar();
            tiempo.setText(metodos.FormatoReloj(Player.Duracion));
            tiempo1.setText("00:00");
            progreso.setValue(1);
            estadoPause = false;
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        if (!PasarCancion(false)) {
            if (Canciones.size() > 0) {
                MensajeError();
                ArreglaRuidos();//sirve para mostrar cosas
            }
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigActionPerformed
        if (!PasarCancion(true)) {
            if (Canciones.size() > 0) {
                MensajeError();
                ArreglaRuidos();
            }
        }
    }//GEN-LAST:event_btnSigActionPerformed
    private boolean abrir = false;
    private void btnAbrir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrir1ActionPerformed
        String Ubicaciones[] = AbrirArchivo();
        if (Ubicaciones != null) {
            ItemActual = 0;
            AuxitemActual = ItemActual;
            try {
                this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Canciones.size() > 0) {
                Canciones.clear();
            }
            Canciones.addAll(Arrays.asList(Ubicaciones));
            if (!Player.CambiarPor(Ubicaciones[0], false)) {
                MensajeError();
            }
            if (Canciones.size() > 0) {
                ListarTabla();

                Player.Reproducir();
            }
            if (Canciones.size() > 0) {
                LlenarNumerosAleatorios();
            }
        }
    }//GEN-LAST:event_btnAbrir1ActionPerformed
    FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Archivos de audio (*.MP3)", "mp3");

    public String[] AbrirArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(filtroImagen);
        fc.setDialogTitle("Seleccionar canciones");
        fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            this.canciones.clear();
            this.canciones.addAll(Arrays.asList(fc.getSelectedFiles()));
            this.index = 0;
            File Archivo[] = fc.getSelectedFiles();
            String Ubicaciones[] = new String[Archivo.length];
            int i = 0;
            for (File aux : Archivo) {
                Ubicaciones[i] = aux.getPath();
                i++;
            }

            return Ubicaciones;
        }
        return null;
    }
    private void miListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miListaMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            try {
                ItemActual = miLista.getSelectedRow();
                this.mp3file = new Mp3File(((File) this.canciones.get(ItemActual)).getAbsolutePath());
                for (int i = 0; i < NumerosAleatorios.size(); i++) {
                    if (NumerosAleatorios.get(i) == ItemActual) {
                        AuxitemActual = i;
                    }
                }
                if (!Player.CambiarPor(Canciones.get(ItemActual), true)) {
//                    JOptionPane.showMessageDialog(this, "¡ Imposible reproducir !", "Aviso", 0);
                }
            } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_miListaMouseClicked

    private void progresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progresoStateChanged
        if (progreso.getMaximum() == progreso.getValue()) {
            PararBarra = true;
            progreso.setValue(1); // esto evita que pase dos canciones a la vez
            if (Repetir != 2) {
                while (!PasarCancion(true)) {
                    MensajeError();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Player.Estado() != 0) {
                    Player.Reproducir(); //en caso de q no reprodusca automaticamente
                    SeleccionarCancionActual();
                }
            } else {
                Player.Parar();
                Player.Reproducir();
                SeleccionarCancionActual();
            }
            PararBarra = false;
            if (Repetir == 0 && ((ItemActual == 0 && !Aleatorio) || (ItemActual == PrimeroDeAleatorio && Aleatorio))) {
                Player.Parar();
                Player.Reproducir();
            }
            ArreglaRuidos(); //siempre debe llamarse de ultimo
        }
    }//GEN-LAST:event_progresoStateChanged

    private void progresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_progresoMouseClicked
        if (Canciones.size() > 0) {
            int Clic = progreso.getMousePosition().x;
            int LargoDeBarra = progreso.getSize().width;
            int finalx = (progreso.getMaximum() * Clic) / LargoDeBarra;
            Player.Ubicar(finalx * 1024);
            Mostrar = false;
            if (Player.Estado() != 1) {
                progreso.setValue(finalx);
                long LlevaEnSegundos = (Player.Duracion * progreso.getValue()) / TamanoEnBytes;
                tiempo.setText(metodos.FormatoReloj(LlevaEnSegundos) + "/" + metodos.FormatoReloj(Player.Duracion));
            }
        }
    }//GEN-LAST:event_progresoMouseClicked

    private void volStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volStateChanged
        VolumenCambiar1();
        Metodos.CrearCarpeta();
        ObjectOutputStream Escribir = null;
        try {
            Escribir = new ObjectOutputStream(new FileOutputStream(Metodos.Ruta() + "/Volumen.dat"));
            Escribir.writeDouble(Player.Volumen);
            Escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Escribir = null;
        }
    }//GEN-LAST:event_volStateChanged

    private void volMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volMouseClicked
        VolumenCambiar();
    }//GEN-LAST:event_volMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (this.miLista.getSelectedRow() > -1) {
            if (Canciones.size() > 1) {
                if (ItemActual == PosicionMouse) {
                    if (!PasarCancion(true)) {
                        MensajeError();
                    }
                }
            } else {
                Player.Parar();
                PararGiro();
                tiempo.setText("00:00");
                tiempo1.setText("00:00");
                nombreCancion.setText(". . . . . . . . . . . . . . . . . . . . . . . . . .");
                nombreCancion1.setText(". . . . . . . . . . . . . . . . . . . . . . . . . .");
                progreso.setValue(1);
                new File(Metodos.Ruta() + "/Canciones.dat").delete();
            }
            DefaultTableModel modelo = (DefaultTableModel) miLista.getModel();
            modelo.removeRow(PosicionMouse);
            Canciones.remove(PosicionMouse);
            if (PosicionMouse < ItemActual) {
                ItemActual--;
            }
            if (Canciones.size() > 0) {
                ListarTabla();
            }
            if (Aleatorio) {
                LlenarNumerosAleatorios();
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    boolean Abriendo = true;

    private void ListarTabla() {
        DefaultTableModel Modelo = (DefaultTableModel) miLista.getModel();
        while (Modelo.getRowCount() != 0) {
            Modelo.removeRow(0);
        }
        if (Canciones.size() > 0) {
            File Archivo;
            for (int i = 0; i < Canciones.size(); i++) {
                Archivo = null;
                Archivo = new File(Canciones.get(i));
                Modelo.addRow(new Object[]{"      " + Archivo.getName().substring(0, Archivo.getName().length() - 4)});
            }
        }
        SeleccionarCancionActual();
        if (!Abriendo) {
            GuardarPistas(); //Que no se llame la primera vez
        } else {
            Abriendo = false;
        }
    }

    private void SeleccionarCancionActual() {
        miLista.setRowSelectionInterval(ItemActual, ItemActual);
    }

    private void LlenarNumerosAleatorios() {
        ArrayList<Integer> Aux = new ArrayList<>();
        for (int i = 0; i < Canciones.size(); i++) {
            Aux.add(i);
        }
        Aux.remove(ItemActual);
        if (NumerosAleatorios.size() > 0) {
            NumerosAleatorios.clear();
        }
        int numeroAleatorio;
        for (int i = 0; i < Canciones.size() - 1; i++) {
            numeroAleatorio = (int) (Math.random() * (Canciones.size() - 1 - i));
            NumerosAleatorios.add(Aux.get(numeroAleatorio));
            Aux.remove(numeroAleatorio);
        }
        AuxitemActual = ItemActual;
        PrimeroDeAleatorio = ItemActual;
        NumerosAleatorios.add(ItemActual, ItemActual); //agrega el item actual en su posicion
    }

    private void VolumenCambiar1() {
        EnSilencio = false;
        VolumenActual();
    }

    private void VolumenCambiar() {
        if (!EnSilencio) {
            EnSilencio = true;
            VolumenSilenciar();
        } else {
            EnSilencio = false;
            VolumenActual();
        }
    }

    private void VolumenActual() {
        if (Player.Estado() != -1 && Player.Estado() != 3) {
            if (!Player.CambiarVolumen((double) vol.getValue() / (double) vol.getMaximum())) {
                JOptionPane.showMessageDialog(this, "Ups, algo salio mal", "Aviso", 0);
            }
        } else {
            Player.Volumen = (double) vol.getValue() / (double) vol.getMaximum();
        }
    }

    private void VolumenSilenciar() {
        if (Player.Estado() != -1 && Player.Estado() != 3) {
            if (!Player.CambiarVolumen(0)) {
                JOptionPane.showMessageDialog(this, "Ups, algo salio mal", "Aviso", 0);
            }
        } else {
            Player.Volumen = 0;
        }
    }

    int ubicacion = 250;

    private void GirarTexto_info() {
        nombreCancion.setBounds(ubicacion, 8, 750, 30);
        nombreCancion1.setBounds(ubicacion + 750, 8, 750, 30);
        ubicacion--;
        if (ubicacion == -750) {
            ubicacion = 0;
        }
    }

    private void HacerGirar(int TiempoInicial, int Frecuencia) {
        task = new TimerTask() {
            @Override
            public void run() {
                GirarTexto_info();
            }
        };
        timer = new Timer();
        timer.schedule(task, TiempoInicial, Frecuencia);
    }

    private void PararGiro() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            task = null;
            nombreCancion.setBounds(0, 8, 750, 30);
            nombreCancion1.setBounds(750, 8, 750, 30);
            ubicacion = 0;
        }
        pasarGarbageCollector();//limpiar memoria
    }

    private void MensajeError() {
        PararGiro();
        timer = null;
        tiempo.setText("00:00");
        tiempo1.setText("00:00");
        nombreCancion.setText("¡ Imposible reproducir !");
        nombreCancion1.setText("¡ Imposible reproducir !");

    }

    public void pasarGarbageCollector() {
        Runtime garbage = Runtime.getRuntime();
        garbage.gc();
    }

    private void Salir() {
        System.exit(0);
    }

    private void ArreglaRuidos() {
        //Este metodo siempre debe llamarse de ultimo, sino causara efectos no deseados
        try {
            Player.player.wait(10);
        } catch (InterruptedException ex) {
            //si hay error;
        }
    }

    private void AbrirInformacion() {
        ObjectInputStream Leer = null;
        try {
            if (new File(Metodos.Ruta() + "/Ecualizador.dat").exists()) {
                Leer = new ObjectInputStream(new FileInputStream(Metodos.Ruta() + "/Ecualizador.dat"));
                Disco.Eq[0] = Leer.readFloat();
                Disco.Eq[1] = Leer.readFloat();
                Disco.Eq[2] = Leer.readFloat();
                Disco.Eq[3] = Leer.readFloat();
                Disco.Eq[4] = Leer.readFloat();
                Disco.Eq[5] = Leer.readFloat();
                Disco.Eq[6] = Leer.readFloat();
                Disco.Eq[7] = Leer.readFloat();
                Disco.Eq[8] = Leer.readFloat();
                Disco.Eq[9] = Leer.readFloat();
                Disco.EqP[0] = Leer.readInt();
                Disco.EqP[1] = Leer.readInt();
                Disco.EqP[2] = Leer.readInt();
                Disco.EqP[3] = Leer.readInt();
                Disco.EqP[4] = Leer.readInt();
                Disco.EqP[5] = Leer.readInt();
                Disco.EqP[6] = Leer.readInt();
                Disco.EqP[7] = Leer.readInt();
                Disco.EqP[8] = Leer.readInt();
                Disco.EqP[9] = Leer.readInt();
                Disco.Balance = Leer.readFloat();
                Disco.itemEc = Leer.readInt();
                Leer.close();
            }
            if (new File(Metodos.Ruta() + "/Volumen.dat").exists()) {
                Leer = new ObjectInputStream(new FileInputStream(Metodos.Ruta() + "/Volumen.dat"));
                Player.Volumen = Leer.readDouble();
                vol.setValue((int) (Player.Volumen * vol.getMaximum()));
                Leer.close();
            }
            if (new File(Metodos.Ruta() + "/Canciones.dat").exists()) {
                Leer = new ObjectInputStream(new FileInputStream(Metodos.Ruta() + "/Canciones.dat"));
                int Tamano = Leer.readInt();
                String Aux;
                for (int i = 0; i < Tamano; i++) {
                    Aux = Leer.readUTF();
                    if (new File(Aux).exists()) {
                        Canciones.add(Aux);
                        canciones.addAll(Arrays.asList(new File(Aux)));
                    }
                }
                if (Canciones.size() > 0) {
                    ListarTabla();
                }
                Leer.close();
            } else {
                Abriendo = false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Leer = null;
        }
    }

    private void GuardarPistas() {
        Metodos.CrearCarpeta();
        ObjectOutputStream Escribir = null;
        try {
            Escribir = new ObjectOutputStream(new FileOutputStream(Metodos.Ruta() + "/Canciones.dat"));
            Escribir.writeInt(Canciones.size());
            for (String Aux : Canciones) {
                Escribir.writeUTF(Aux);
            }
            Escribir.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Escribir = null;
            pasarGarbageCollector();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.rsbuttom.RSButtonMetro btnAbrir1;
    private componentes.rsbuttom.RSButtonMetro btnAdd;
    private componentes.rsbuttom.RSButtonMetro btnAtras;
    private componentes.rsbuttom.RSButtonMetro btnBorrar;
    private componentes.rsbuttom.RSButtonMetro btnCerrar;
    private componentes.rsbuttom.RSButtonMetro btnMinimizar;
    private componentes.rsbuttom.RSButtonMetro btnPause;
    private componentes.rsbuttom.RSButtonMetro btnPlay;
    private componentes.rsbuttom.RSButtonMetro btnSig;
    private componentes.rsbuttom.RSButtonMetro btnStop;
    private javax.swing.JScrollPane fondoTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblImagen;
    public static javax.swing.JTable miLista;
    public static javax.swing.JLabel nombreCancion;
    public static javax.swing.JLabel nombreCancion1;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tiempo1;
    private javax.swing.JSlider vol;
    // End of variables declaration//GEN-END:variables

    @Override
    public void opened(Object o, Map map) {
        if (map.containsKey("audio.length.bytes")) {
            TamanoEnBytes = (int) (Double.parseDouble(map.get("audio.length.bytes").toString()) / 1024);
            progreso.setMaximum(TamanoEnBytes);
            progreso.setValue(1);
            CambioEnEcualizador = true;
        }
        tiempo.setText(metodos.FormatoReloj(Player.Duracion));
        tiempo1.setText("00:00");
        if (Player.Titulo == null || Player.Titulo.trim().isEmpty()) {
            nombreCancion.setText(Player.NombreArchivo());
            nombreCancion1.setText(Player.NombreArchivo());
        } else {
            nombreCancion.setText(Player.Titulo + " - " + Player.Artista);
            nombreCancion1.setText(Player.Titulo + " - " + Player.Artista);
        }
        PararGiro();
        HacerGirar(1500, 15);
        Mostrar = true;
        if (this.mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = this.mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();
            try {
                if (imageData != null) {
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
                    if (bufferedImage != null) {
                        BufferedImage imagenEscalada = Scalr.resize(bufferedImage, Scalr.Method.QUALITY, 300, new BufferedImageOp[]{Scalr.OP_BRIGHTER});
                        this.lblImagen.setIcon(new ImageIcon(imagenEscalada));
                    } else {
                        this.lblImagen.setIcon(new ImageIcon(getClass().getResource("/img/logo1.png")));
                    }
                } else {
                    this.lblImagen.setIcon(new ImageIcon(getClass().getResource("/img/logo1.png")));
                }
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.lblImagen.setIcon(new ImageIcon(getClass().getResource("/img/logo1.png")));
        }

    }

    Emergente MostrarLaMusica = null;

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
        if (!PararBarra) {
            float progressUpdate = (float) (i * 1.0f / TamanoEnBytes * 1.0f);
            int progressNow = (int) (TamanoEnBytes * progressUpdate) / 1024;
            progreso.setValue(progressNow);
            if (CambioEnEcualizador) {
                Ecualizador = (float[]) map.get("mp3.equalizer");
                System.arraycopy(Disco.Eq, 0, Ecualizador, 0, Ecualizador.length);
                Player.Balance(Disco.Balance);
                CambioEnEcualizador = false;
            }
            long LlevaEnSegundos = (Player.Duracion * progressNow) / TamanoEnBytes;
            tiempo.setText(metodos.FormatoReloj(Player.Duracion));
            tiempo1.setText(metodos.FormatoReloj(LlevaEnSegundos));
        }
        if (Mostrar) {
            if (MostrarLaMusica != null) {
                MostrarLaMusica.dispose();
            }
            if (Player.Titulo == null || Player.Titulo.trim().isEmpty()) {
                MostrarLaMusica = new Emergente(new java.awt.Frame(), false, Player.NombreArchivo(), "");
                MostrarLaMusica.setVisible(true);
            } else {
                MostrarLaMusica = new Emergente(new java.awt.Frame(), false, Player.Titulo, Player.Artista);
                MostrarLaMusica.setVisible(true);
            }
            Mostrar = false;
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setController(BasicController bc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
